package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String st) {
        char[] letters = st.toCharArray();
        for (int i = 0; i < st.length() - 1; i += 2) {
            char temp = letters[i];
            letters[i] = letters[i + 1];
            letters[i + 1] = temp;
        }
        return new String(letters);
    }
}
