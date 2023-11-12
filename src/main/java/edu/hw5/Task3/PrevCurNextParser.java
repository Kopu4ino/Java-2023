package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class PrevCurNextParser implements DataParser {
    DataParser next;

    @Override
    public Optional<LocalDate> tryParse(String dateToParse) {
        return switch (dateToParse) {
            case ("today") -> Optional.of(LocalDate.now());
            case ("tomorrow") -> Optional.of(LocalDate.now().plusDays(1));
            case ("yesterday") -> Optional.of(LocalDate.now().minusDays(1));
            default -> next != null ? next.tryParse(dateToParse) : Optional.empty();
        };
    }

    @Override
    public void setNext(DataParser next) {
        this.next = next;
    }
}
