package com.epam.rd.java.basic.practice3;

import java.util.regex.*;

public class Part3 {
    private static final Pattern WORD = Pattern.compile("[a-zA-Z\\p{IsCyrillic}]+-?", Pattern.MULTILINE);
    private static final String FILE_PATH = "part3.txt";

    public static void main(String[] args) {
        String input = Util.getInput(FILE_PATH);
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        if (input == null) {
            return null;
        }
        String[] lines = input.split(System.lineSeparator());
        Matcher matcher;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            matcher = WORD.matcher(lines[i]);
            while (matcher.find()) {
                sb.append(changeCase(matcher.group())).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            if (i < lines.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static String changeCase(String word) {
        if (word.length() < 3) return word;
        if (Character.isUpperCase(word.charAt(0))) {
            return word.replace(word.charAt(0), Character.toLowerCase(word.charAt(0)));
        }
        return word.replace(word.charAt(0), Character.toUpperCase(word.charAt(0)));
    }
}
