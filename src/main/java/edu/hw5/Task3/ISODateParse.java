package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ISODateParse implements DataParser {
    DataParser next = null;

    @Override
    public Optional<LocalDate> tryParse(String dateToParse) {
        String[] formats = {"yyyy-MM-dd", "yyyy-MM-d"};

        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                return Optional.of(LocalDate.parse(dateToParse, formatter));
            } catch (DateTimeParseException ex) {

            }
        }
        return next != null ? next.tryParse(dateToParse) : Optional.empty();
    }

    @Override
    public void setNext(DataParser next) {
        this.next = next;
    }
}
