package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task1 {
    private Task1() {
    }

    public static String gameClubAnalytics(List<String> userVisits) {
        final int MINUTE_IN_HOUR = 60;
        Duration sumDurations = Duration.ZERO;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        for (var visit : userVisits) {
            String[] event = visit.split(" - ");

            LocalDateTime eventStart = LocalDateTime.parse(event[0], formatter);
            LocalDateTime eventEnd = LocalDateTime.parse(event[1], formatter);

            sumDurations = sumDurations.plus(Duration.between(eventStart, eventEnd));
        }
        Duration result = sumDurations.dividedBy(userVisits.size());
        long hours = result.toHours();
        long minutes = result.toMinutes() % MINUTE_IN_HOUR;

        return String.format("%dч %dм", hours, minutes);

    }
}
