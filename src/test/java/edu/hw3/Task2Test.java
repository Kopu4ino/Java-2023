package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class Task2Test {
    @Test
    void clusterizeDefaultInput() {
        String input1 = "()()()";
        String input2 = "((()))";
        String input3 = "((()))(())()()(()())";
        String input4 = "((())())(()(()()))";

        assertThat(Task2.clusterize(input1)).isEqualTo(List.of("()", "()", "()"));
        assertThat(Task2.clusterize(input2)).isEqualTo(List.of("((()))"));
        assertThat(Task2.clusterize(input3)).isEqualTo(List.of("((()))", "(())", "()", "()", "(()())"));
        assertThat(Task2.clusterize(input4)).isEqualTo(List.of("((())())", "(()(()()))"));
    }

    @Test
    void clusterizeIncorectInput() {
        String input1 = "(123";
        String input2 = "._.)";

        assertThatThrownBy(() -> Task2.clusterize(input1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Task2.clusterize(input2)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void clusterizeEmptyInput() {
        String emptyInput = "";

        assertThat(Task2.clusterize(emptyInput)).isEqualTo(new ArrayList<>());
    }
}
