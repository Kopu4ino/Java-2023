package edu.hw5;

public class Task5 {
    private Task5() {
    }

    public static boolean validateCarNumber(String number) {
        String pattern = "^[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$";
        return number.matches(pattern);
    }
}
