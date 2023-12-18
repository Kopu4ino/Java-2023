package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class DataParsersChain {
    private final DataParser chain;

    public DataParsersChain() {
        DataParser isoParser = new ISODateParse();
        DataParser slashParser = new SlashDateParser();
        DataParser prevCurNextParser = new PrevCurNextParser();
        DataParser nDaysAgoParser = new NDaysAgoParser();

        isoParser.setNext(slashParser);
        slashParser.setNext(prevCurNextParser);
        prevCurNextParser.setNext(nDaysAgoParser);

        this.chain = isoParser;
    }

    public Optional<LocalDate> parseDate(String dateToParse) {
        return this.chain.tryParse(dateToParse);
    }
}
