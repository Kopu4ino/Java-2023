package edu.hw5.Task3;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        DataParser isoParser = new ISODateParse();
        DataParser slashParser = new SlashDateParser();
        DataParser prevCurNextParser = new PrevCurNextParser();
        DataParser nDaysAgoParser = new NDaysAgoParser();

        isoParser.setNext(slashParser);
        slashParser.setNext(prevCurNextParser);
        prevCurNextParser.setNext(nDaysAgoParser);

        String test1 = "2020-10-20";
        String test2 = "1/3/23";
        String test3 = "yesterday";
        String test4 = "11 days ago";
        var res = isoParser.tryParse(test2);

        res.ifPresent(System.out::println);

    }
}
