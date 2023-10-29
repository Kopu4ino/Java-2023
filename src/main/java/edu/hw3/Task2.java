package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Task2 {
    private Task2() {
    }

    public static List<String> clusterize(String input) {
        Stack<Character> stack = new Stack<>();
        List<String> clusters = new ArrayList<>();

        StringBuilder curCluster = new StringBuilder();

        for (char c : input.toCharArray()) {
            curCluster.append(c);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                stack.pop();
                if (stack.empty()) {
                    clusters.add(curCluster.toString());
                    curCluster.setLength(0);
                }
            } else {
                throw new IllegalArgumentException("Строка должна содержать только скобки");
            }
        }

        return clusters;
    }
}
