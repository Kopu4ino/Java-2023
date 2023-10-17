package edu.hw1;

import java.util.Arrays;

public class Task6 {
    public static final Integer K = 6174;

    private Task6() {
    }

    public static int countK(Integer num) {
        final int DEFAULT_STEP_CNT = 0;

        if (validateNumForKaprekar(num)) {
            return kaprekarStepCnt(num, DEFAULT_STEP_CNT);
        } else {
            return -1;
        }

    }

    private static int kaprekarStepCnt(Integer num, Integer stepCnt) {
        if (num.equals(K)) {
            return stepCnt;
        }

        Integer numAscOrder = getNumInAscOrder(num);
        Integer numDescOrder = getNumInDescOrder(num);

        Integer difference = numAscOrder - numDescOrder;

        return kaprekarStepCnt(difference, stepCnt + 1);
    }

    public static int getNumInDescOrder(Integer num) {
        String strNum = num.toString();
        char[] charsDigits = strNum.toCharArray();

        Arrays.sort(charsDigits);
        String strSortedNum = new String(charsDigits);

        return Integer.parseInt(strSortedNum);
    }

    public static int getNumInAscOrder(Integer num) {
        String strNum = num.toString();
        char[] charsDigits = strNum.toCharArray();

        Arrays.sort(charsDigits);
        String strSortedNum = new String(charsDigits);
        String strReversSortedNum = new StringBuilder(strSortedNum).reverse().toString();

        return Integer.parseInt(strReversSortedNum);
    }

    private static boolean validateNumForKaprekar(Integer num) {
        final Integer LOWER_BOUND = 1000;
        final Integer UPPER_BOUND = 10000;

        if (num <= LOWER_BOUND || num >= UPPER_BOUND) {
            return false;
        }

        Integer numAscOrder = getNumInAscOrder(num);
        Integer numDescOrder = getNumInDescOrder(num);

        if (numAscOrder.equals(numDescOrder)) {
            return false;
        }

        return true;
    }
}
