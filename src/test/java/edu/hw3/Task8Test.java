package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task8Test {
    @Test
    void backwardIteratorTest() {
        BackwardIterator<Integer> backIter = new BackwardIterator<>(List.of(1, 2, 3));

        List<Integer> reversedData = new ArrayList<>();
        while (backIter.hasNext()) {
            reversedData.add(backIter.next());
        }

        List<Integer> expect = List.of(3, 2, 1);

        assertThat(reversedData).isEqualTo(expect);

        // Проверяем исключительную ситуацию, когда элементы уже закончились
        assertThatThrownBy(backIter::next).isInstanceOf(NoSuchElementException.class);
    }
}
