package edu.hw9.Task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatsCollector {
    private final Map<String, StatisticsData> statisticsMap = new ConcurrentHashMap<>();

    public void push(String metricName, double[] values) {
        StatisticsData statsData = statisticsMap.computeIfAbsent(metricName, k -> new StatisticsData());
        statsData.addData(values);
    }

    public Map<String, Map<String, Double>> stats() {
        Map<String, Map<String, Double>> allStats = new ConcurrentHashMap<>();

        for (Map.Entry<String, StatisticsData> entry : statisticsMap.entrySet()) {
            Map<String, Double> stats = new ConcurrentHashMap<>();
            StatisticsData data = entry.getValue();

            stats.put("sum", data.getSum());
            stats.put("average", data.getAverage());
            stats.put("min", data.getMin());
            stats.put("max", data.getMax());
            stats.put("count", (double) data.getCount());

            allStats.put(entry.getKey(), stats);
        }

        return allStats;
    }
}
