package edu.hw3;

public class Task1 {

    private Task1() {
    }

    public static String atbash(String str) {
        StringBuilder ans = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                ans.append((char) ('Z' - (c - 'A')));
            } else if (Character.isLowerCase(c)) {
                ans.append((char) ('z' - (c - 'a')));
            } else {
                ans.append(c);
            }
        }

        return ans.toString();
    }
}
