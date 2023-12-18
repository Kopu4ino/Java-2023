package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class SlashDateParser implements DataParser {
    DataParser next = null;
    String[] formats = {"d/M/yyyy", "d/M/yy", "dd/MM/yyyy"};

    @Override
    public Optional<LocalDate> tryParse(String dateToParse) {

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
