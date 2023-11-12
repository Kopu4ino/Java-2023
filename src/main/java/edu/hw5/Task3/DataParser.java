package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public interface DataParser {
    Optional<LocalDate> tryParse(String dateToParse);

    void setNext(DataParser next);
}
