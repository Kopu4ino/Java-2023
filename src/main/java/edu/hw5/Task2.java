package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    final static int TARGET_DAY = 13;
    final static int MONTH_IN_YEAR = 12;
    final static int DAY_IN_WEEK = 7;

    private Task2() {
    }

    public static List<LocalDate> getAllBlackFridayByYear(int year) {

        List<LocalDate> blackFridays = new ArrayList<>();
        for (int month = 1; month <= MONTH_IN_YEAR; month++) {
            LocalDate curDay = LocalDate.of(year, month, TARGET_DAY);
            if (curDay.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                blackFridays.add(curDay);
            }
        }
        return blackFridays;
    }

    public static LocalDate getNextBlackFriday(LocalDate curDate) {
        TemporalAdjuster nextBlackFridayAdjuster = new TemporalAdjuster() {
            @Override
            public Temporal adjustInto(Temporal temporal) {
                LocalDate date = LocalDate.from(temporal);
                while (date.getDayOfWeek() != DayOfWeek.FRIDAY) {
                    date = date.plusDays(1);
                }

                while (date.getDayOfMonth() != TARGET_DAY) {
                    date = date.plusDays(DAY_IN_WEEK);
                }

                return date;
            }
        };

        return curDate.with(nextBlackFridayAdjuster);
    }
}
