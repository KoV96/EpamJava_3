package com.epam.rd.java.basic.practice3;

import java.util.regex.*;

public class Part6 {
    private static final String FILE_PATH = "part6.txt";

    public static void main(String[] args) {
        String input = Util.getInput(FILE_PATH);
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        String res = input;
        Matcher matcher;
        String[] inputByWords = input.replaceAll("(\r\n)|(\n)", " ").split(" ");
        for (String word : inputByWords) {
            matcher = Pattern.compile(getSearchRegex(word)).matcher(input);
            int count = 0;
            while (matcher.find()) {
                count++;
                if (count > 1) {
                    res = res.replaceAll(getWordRegex(matcher.group()), "_" + matcher.group());
                }
            }
        }
        return res;
    }

    private static String getWordRegex(String group) {
        return "\\b" + group + "\\b";
    }

    private static String getSearchRegex(String word) {
        return "\\b(" + word + ")\\b";
    }
}
