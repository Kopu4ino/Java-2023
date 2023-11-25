package edu.hw7.Task3_5;

import edu.hw7.Task3.Person;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ReadWriteLockPersonDataBaseTest {
    @Test
    void testParallelReadAndWrite() throws InterruptedException {
        ReadWriteLockPersonDataBase db = new ReadWriteLockPersonDataBase();
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable writeTask = () -> db.add(new Person(1, "Artur Kop", "Chkalova 123", "+7777777"));
        Runnable readTask = () -> db.findByName("Artur Kop");

        for (int i = 0; i < 1000000; i++) {
            executor.submit(i % 2 == 0 ? writeTask : readTask);
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(1, TimeUnit.MINUTES);

        assertThat(finished).isTrue();

        assertThat(db.findByName("Artur Kop")).isNotNull();
    }
}
