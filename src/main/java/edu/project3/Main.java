package edu.project3;

import edu.project3.Formatters.AdocFormatter;
import edu.project3.Formatters.MarkdownFormatter;
import edu.project3.LogReaders.LocalLogFileReader;
import edu.project3.LogReaders.UrlLogFileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

@SuppressWarnings("RegexpSinglelineJava")
public class Main {
    private Main() {
    }

    private static final String OPTION_PATH = "path";
    private static final String OPTION_FROM = "from";
    private static final String OPTION_TO = "to";
    private static final String OPTION_FORMAT = "format";

    public static void main(String[] args) {
        Options options = initOptions();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            String filePath = cmd.getOptionValue(OPTION_PATH);
            String fromDate = cmd.getOptionValue(OPTION_FROM);
            String toDate = cmd.getOptionValue(OPTION_TO);
            String format = cmd.getOptionValue(OPTION_FORMAT);

            analyzeLogs(filePath, fromDate, toDate, format);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("nginx-log-stats", options);
            System.exit(1);
        }
    }

    private static void analyzeLogs(String filePath, String fromDate, String toDate, String format) {
        System.out.println(filePath);
        OffsetDateTime parsedFromDate = parseDate(fromDate) != null ? parseDate(fromDate) : OffsetDateTime.MIN;
        OffsetDateTime parsedToDate = parseDate(toDate) != null ? parseDate(toDate) : OffsetDateTime.MAX;

        try {
            Stream<String> logLines = Stream.of();

            if (filePath == null) {
                throw new IllegalArgumentException("--path Can not be null");
            } else if (filePath.startsWith("http://") || filePath.startsWith("https://")) {
                logLines = new UrlLogFileReader().readLines(filePath);
            } else {
                logLines = new LocalLogFileReader().readLines(filePath);
            }

            LogStatistics statistics = new LogStatistics();

            logLines.map(LogRecord::parseLogRecord)
                .flatMap(Optional::stream)
                .filter(logRecord -> isWithinRange(logRecord.timeLocal(), parsedFromDate, parsedToDate))
                .forEach(statistics::calc);

            printStatisticInfo(statistics, format, filePath, fromDate, toDate);

        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Options initOptions() {
        Options options = new Options();

        Option pathOption = new Option("p", OPTION_PATH, true, "Path to the log file or URL");
        pathOption.setRequired(true);
        options.addOption(pathOption);

        Option fromOption = new Option("f", OPTION_FROM, true, "Start date for filtering");
        fromOption.setRequired(false);
        options.addOption(fromOption);

        Option toOption = new Option("t", OPTION_TO, true, "End date for filtering");
        toOption.setRequired(false);
        options.addOption(toOption);

        Option formatOption = new Option("fmt", OPTION_FORMAT, true, "Output format (markdown or adoc)");
        formatOption.setRequired(false);
        options.addOption(formatOption);

        return options;
    }

    private static void printStatisticInfo(
        LogStatistics statistics,
        String format,
        String filePath,
        String fromDate,
        String toDate
    ) {
        if (format == null) {
            System.out.println(statistics.getStatisticInfo());
        } else if (format.equals("adoc")) {
            AdocFormatter formatter = new AdocFormatter();
            String adocOutput = formatter.formatStatistics(statistics, filePath, fromDate, toDate);
            System.out.println(adocOutput);
        } else if (format.equals("markdown")) {
            MarkdownFormatter formatter = new MarkdownFormatter();
            String markdownOutput = formatter.formatStatistics(statistics, filePath, fromDate, toDate);
            System.out.println(markdownOutput);
        } else {
            throw new IllegalArgumentException("Invalid format: " + format);
        }
    }

    private static OffsetDateTime parseDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        LocalDate date = LocalDate.parse(dateString);
        return date.atStartOfDay().atOffset(ZoneOffset.UTC);
    }

    private static boolean isWithinRange(OffsetDateTime time, OffsetDateTime start, OffsetDateTime end) {
        return !time.isBefore(start) && !time.isAfter(end);
    }
}
