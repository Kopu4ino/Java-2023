package edu.hw3;

public class Task4 {
    private Task4() {
    }

    private static final int[] VALUES = {
        100, 90, 50, 40, 10, 9, 5, 4, 1
    };

    private static final String[] ROMAN = {
        "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    private static final Integer LOW_BORDER = 0;
    private static final Integer UP_BORDER = 400;

    public static String convertToRoman(Integer num) {
        if (!validateNum(num)) {
            throw new IllegalArgumentException();
        }
        Integer curNum = num;

        StringBuilder newRomanNum = new StringBuilder();

        for (int i = 0; i < VALUES.length && curNum > 0; i++) {
            while (curNum >= VALUES[i]) {
                newRomanNum.append(ROMAN[i]);
                curNum -= VALUES[i];
            }
        }

        return newRomanNum.toString();
    }

    public static boolean validateNum(Integer num) {
        if (num <= LOW_BORDER) {
            return false;
        }

        return num < UP_BORDER;
    }
}
