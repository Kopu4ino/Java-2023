package edu.hw5;

public class Task4 {
    private Task4() {
    }

    public static boolean validatePassword(String password) {
        String pattern = ".*[~!@#$%^&*|]+.*";
        return password.matches(pattern);
    }
}
