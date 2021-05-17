package com.epam.rd.java.basic.practice3;

import java.security.NoSuchAlgorithmException;

public class Demo {
    private static final String SLASH = "***************************************";
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Part1.main(new String[]{});
        System.out.println(SLASH);
        Part2.main(new String[]{});
        System.out.println(SLASH);
        Part3.main(new String[]{});
        System.out.println(SLASH);
        Part4.main(new String[]{});
        System.out.println(SLASH);
        Part5.main(new String[]{});
        System.out.println(SLASH);
        Part6.main(new String[]{});
    }

}
