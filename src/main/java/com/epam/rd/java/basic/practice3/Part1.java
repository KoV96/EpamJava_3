package com.epam.rd.java.basic.practice3;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.*;

public class Part1 {
    private static final String FILE_PATH = "part1.txt";
    private static final String SLASH = "--------------------------------------";
    private static final String LOGIN = "[a-z\\p{IsCyrillic}]+";
    private static final String DOMAIN = "\\w+\\.\\w{2,4}";

    public static void main(String[] args) {
        String input = Util.getInput(FILE_PATH);
        System.out.println(convert1(input));
        System.out.println(SLASH);
        System.out.println(convert2(input));
        System.out.println(SLASH);
        System.out.println(convert3(input));
        System.out.println(SLASH);
        System.out.println(convert4(input));
    }

    public static String convert1(String input) {
        if (input == null) {
            return null;
        }
        String[] inputByLines = input.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < inputByLines.length; i++) {
            String[] line = inputByLines[i].split(";");
            sb.append(line[0]).append(": ").append(line[2]).append("\n");
        }
        return sb.toString();
    }

    private static String transform(String str) {
        String[] arr = str.split(" ");
        return arr[1] + " " + arr[0];
    }

    public static String convert2(String input) {
        if (input == null) {
            return null;
        }
        String[] inputArr = input.split(System.lineSeparator());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < inputArr.length; i++) {
            String[] line = inputArr[i].split(";");
            sb.append(transform(line[1])).append(" (email: ").append(line[2]).append(")");
            if (i < inputArr.length - 1) {
                sb.append("\n");
            }
        }
        return sb.append("\n").toString();
    }

    public static String convert3(String input) {
        if (input == null){
            return null;
        }
        String[] data = input.split("\n");

        return getDomainsInfo(data);
    }

    private static String getDomainsInfo(String[] data) {
        Matcher matcherLog;
        Matcher matcherDom;
        String currentDom;
        StringBuilder domainsInfo = new StringBuilder();
        for (int i = 1; i < data.length; i++) {
            if (data[i] == null) {
                continue;
            }
            matcherDom = Pattern.compile(DOMAIN).matcher(data[i]);
            matcherLog = Pattern.compile(LOGIN).matcher(data[i]);
            if (matcherDom.find()) {
                currentDom = matcherDom.group();
                if (matcherLog.find()) {
                    domainsInfo.append(currentDom).append(" ==> ").append(matcherLog.group()).append(", ");
                }
                data[i] = null;
                domainsInfo.append(searchNextLogins(i, data, currentDom));
                domainsInfo.append("\n");
            }
        }
        return domainsInfo.toString();
    }

    private static String searchNextLogins(int i, String[] data, String currentDom) {
        StringBuilder logins = new StringBuilder();
        Matcher matcherLog;
        for (int j = i + 1; j < data.length; j++) {
            if (data[j] != null && data[j].contains(currentDom)) {
                matcherLog = Pattern.compile(LOGIN).matcher(data[j]);
                if (matcherLog.find())
                    logins.append(matcherLog.group());
                data[j] = null;
            }
        }
        return logins.toString();
    }

    public static String convert4(String input) {
        if (input == null){
            return null;
        }
        String userData = "\\.com$";
        Pattern patternUser = Pattern.compile(userData, Pattern.MULTILINE);
        Matcher matcherUser = patternUser.matcher(input);
        StringBuffer sb = new StringBuffer();
        Random r = new SecureRandom();
        while (matcherUser.find()) {
            matcherUser.appendReplacement(sb, ".com;" + (r.nextInt(9000) + 999));
        }
        return sb.toString();
    }
}