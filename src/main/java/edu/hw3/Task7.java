package edu.hw3;

import java.util.Comparator;
import java.util.TreeMap;

public class Task7 {
    private Task7() {
    }

    static Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            if (s1 == null && s2 == null) {
                return 0;
            } else if (s1 == null) {
                return -1;
            } else if (s2 == null) {
                return 1;
            } else {
                return s1.compareTo(s2);
            }
        }
    };
    public static TreeMap<String, String> tree = new TreeMap<>(Task7.comparator);
}
