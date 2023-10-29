package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    public static Map<Object, Integer> freqDict(List<Object> elements) {
        Map<Object, Integer> frequency = new HashMap<>();

        for (Object element : elements) {
            frequency.merge(element, 1, Integer::sum);
        }
        return frequency;
    }
}
