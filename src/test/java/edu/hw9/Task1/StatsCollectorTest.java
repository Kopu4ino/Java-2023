package edu.hw9.Task1;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {
    @Test
    public void testPushAndStats() {
        // Arrange
        StatsCollector collector = new StatsCollector();

        // Act
        collector.push("metric1", new double[] {1, 2});
        collector.push("metric2", new double[] {3, 4, 5});
        Map<String, Map<String, Double>> stats = collector.stats();

        // Assert
        assertThat(stats).containsKeys("metric1", "metric2");
        Map<String, Double> metric1Stats = stats.get("metric1");
        assertThat(metric1Stats.get("sum")).isEqualTo(3);
        assertThat(metric1Stats.get("average")).isEqualTo(1.5);
        Map<String, Double> metric2Stats = stats.get("metric2");
        assertThat(metric2Stats.get("sum")).isEqualTo(12);
        assertThat(metric2Stats.get("average")).isEqualTo(4);
    }

    @Test
    public void testMultiThreadedPush() throws InterruptedException {
        // Arrange
        StatsCollector collector = new StatsCollector();
        int numberOfThreads = 10;
        int numberOfPushes = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // Act
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < numberOfPushes; j++) {
                    collector.push("testMetric", new double[] {1, 2, 3});
                }
            });
        }
        executorService.shutdown();
        boolean finished = executorService.awaitTermination(1, TimeUnit.MINUTES);

        // Assert
        assertThat(finished).isTrue();
        Map<String, Map<String, Double>> stats = collector.stats();
        Map<String, Double> metricStats = stats.get("testMetric");
        double expectedSum = 6 * numberOfThreads * numberOfPushes;
        double expectedCount = 3 * numberOfThreads * numberOfPushes;
        assertThat(metricStats.get("sum")).isEqualTo(expectedSum);
        assertThat(metricStats.get("count")).isEqualTo(expectedCount);
    }
}
