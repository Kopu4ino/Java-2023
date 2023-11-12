package edu.hw5;

import edu.hw5.Task3.DataParsersChain;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    private final DataParsersChain chain = new DataParsersChain();
    @Test
    void testChainOfParsersCorrectInput() {
        String test1 = "2020-10-20";
        LocalDate expect1 = LocalDate.of(2020, 10, 20);

        String test2 = "1/3/23";
        LocalDate expect2 = LocalDate.of(2023, 3, 1);

        String test3 = "yesterday";
        LocalDate expect3 = LocalDate.now().minusDays(1);

        String test4 = "11 days ago";
        LocalDate expect4 = LocalDate.now().minusDays(11);

        assertThat(chain.parseDate(test1).get()).isEqualTo(expect1);
        assertThat(chain.parseDate(test2).get()).isEqualTo(expect2);
        assertThat(chain.parseDate(test3).get()).isEqualTo(expect3);
        assertThat(chain.parseDate(test4).get()).isEqualTo(expect4);
    }

    @Test
    void testChainOfParsersInCorrectInput() {
        String test1 = "2020-10-20000";
        String test2 = "/10/20";
        String test3 = "1 days ago! sdfsf";

        var expect = Optional.empty();

        assertThat(chain.parseDate(test1)).isEqualTo(Optional.empty());
        assertThat(chain.parseDate(test2)).isEqualTo(Optional.empty());
        assertThat(chain.parseDate(test3)).isEqualTo(Optional.empty());
    }
}
