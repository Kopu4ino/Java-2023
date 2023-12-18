package edu.hw5;

public class Task6 {
    private Task6() {
    }

    public static boolean containsSubString(String text, String subString) {
        String pattern = ".*" + subString + ".*";
        return text.matches(pattern);
    }
}
