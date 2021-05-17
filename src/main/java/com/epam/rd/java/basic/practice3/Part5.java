package com.epam.rd.java.basic.practice3;

public class Part5 {

    private static final String SEPARATOR = " --> ";

    enum RomanNum {
        I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50),
        XC(90), C(100), CD(400), D(500), CM(900), M(1000);
        int value;
        RomanNum(int value){
            this.value = value;
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++){
            System.out.println(i + SEPARATOR + decimal2Roman(i) + SEPARATOR + roman2Decimal(decimal2Roman(i)));
        }
        System.out.println("...");
        for (int i = 95; i <= 100; i++){
            System.out.println(i + SEPARATOR + decimal2Roman(i) + SEPARATOR + roman2Decimal(decimal2Roman(i)));
        }
    }

    public static String decimal2Roman(int dec) {
        StringBuilder sb = new StringBuilder();
        RomanNum[] res = RomanNum.values();
        for (int i = res.length - 1; i >= 0; i--) {
            while (dec >= res[i].value) {
                sb.append(res[i]);
                dec -= res[i].value;
            }
        }
        return sb.toString();
    }

    public static int roman2Decimal(String roman) {
        char [] romanByLetters = roman.toCharArray();
        return decodeEachLetter(romanByLetters);
    }

    private static int decodeEachLetter(char[] romanByLetters) {
        int res = 0;
        for (int i = 0; i < romanByLetters.length; i++){
            if (i < romanByLetters.length - 1 &&
                    getRomanVal(romanByLetters[i]) < getRomanVal(romanByLetters[i + 1])){
                res -= getRomanVal(romanByLetters[i]);
                continue;
            }
            res += getRomanVal(romanByLetters[i]);
        }
        return res;
    }

    private static int getRomanVal(char letter){
        switch (letter){
            case 'M' : return  1000;
            case 'D' : return  500;
            case 'C' : return  100;
            case 'L' : return  50;
            case 'X' : return  10;
            case 'V' : return  5;
            case 'I' : return  1;
            default: return  0;
        }
    }
}
