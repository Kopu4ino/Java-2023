package edu.hw1;

import java.util.ArrayList;

public class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(Integer num) {
        String curNumInString = num.toString();
        String descendant;

        if (Task5.isPalindrome(curNumInString)) {
            return true;
        }

        if (curNumInString.length() % 2 == 1) {
            curNumInString += "0";
        }

        while (curNumInString.length() > 1) {
            descendant = Task5.getDescendant(curNumInString);
            if (Task5.isPalindrome(descendant) && descendant.length() > 1) {
                return true;
            }
            curNumInString = descendant;
        }
        return false;
    }

    // Методу всегда приходят строки четной длины
    public static String getDescendant(String parent) {
        ArrayList<Character> descendant = new ArrayList<>();

        for (int i = 0; i < parent.length() - 1; i += 2) {
            int sum = (parent.charAt(i) - '0') + (parent.charAt(i + 1) - '0');
            String sumStr = Integer.toString(sum);

            for (int j = 0; j < sumStr.length(); j++) {
                descendant.add(sumStr.charAt(j));
            }
        }
        return Task5.getStringFromListChar(descendant);
    }

    public static boolean isPalindrome(String st) {
        String reversed = new StringBuilder(st).reverse().toString();
        return st.equals(reversed);
    }

    private static String getStringFromListChar(ArrayList<Character> lst) {
        char[] charArr = new char[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            charArr[i] = lst.get(i);
        }

        return new String(charArr);
    }
}
