package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class NDaysAgoParser implements DataParser {
    DataParser next = null;

    @Override
    public Optional<LocalDate> tryParse(String dateToParse) {
        String pattern = "^\\d+ da(y|ys) ago$";

        if (dateToParse.matches(pattern)) {
            Integer days = Integer.parseInt(dateToParse.split(" ")[0]);
            return Optional.of(LocalDate.now().minusDays(days));
        }

        return next != null ? next.tryParse(dateToParse) : Optional.empty();
    }

    @Override
    public void setNext(DataParser next) {
        this.next = next;
    }
}
