package com.epam.rd.java.basic.practice3;

import java.util.regex.*;

public class Part2 {

    private static final Pattern WORD = Pattern.compile("[a-zA-Z\\p{IsCyrillic}]+-?", Pattern.MULTILINE);
    private static final String FILE_PATH = "part2.txt";

    public static void main(String[] args) {
        String input = Util.getInput(FILE_PATH);
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        if (input == null) {
            return null;
        }
        return "Min: " + findMin(input) + "\n" + "Max: " + findMax(input);
    }

    private static String findMax(String input) {
        Matcher matcher = WORD.matcher(input);
        StringBuilder max = new StringBuilder();
        int counter = 0;
        int length = 0;
        while (matcher.find()) {
            if (counter == 0) {
                max.append(matcher.group()).append(", ");
                length = matcher.group().length();
            }
            if (matcher.group().length() > length) {
                max.replace(0, max.length() - 1, matcher.group() + ",");
                length = matcher.group().length();
            } else if (matcher.group().length() == length && !max.toString().contains(matcher.group())) {
                max.append(matcher.group()).append(", ");
            }
            counter++;
        }
        max.replace(max.length() - 2, max.length() - 1, "");
        return max.toString().trim();
    }

    private static String findMin(String input) {
        Matcher matcher = WORD.matcher(input);
        StringBuilder min = new StringBuilder();
        int counter = 0;
        int length = 0;
        while (matcher.find()) {
            if (counter == 0) {
                min.append(matcher.group()).append(", ");
                length = matcher.group().length();
            }
            if (matcher.group().length() < length) {
                min.replace(0, min.length() - 1, matcher.group() + ",");
                length = matcher.group().length();
            } else if (matcher.group().length() == length && !min.toString().contains(matcher.group())) {
                min.append(matcher.group()).append(", ");
            }
            counter++;
        }
        min.replace(min.length() - 2, min.length() - 1, "");
        return min.toString().trim();
    }
}
