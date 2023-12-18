package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record LogRecord(
    String remoteAddr,
    String remoteUser,
    OffsetDateTime timeLocal,
    String requestType,
    String requestPath,
    String protocol,
    int status,
    int bodyBytesSent,
    String httpReferer,
    String userAgent) {
    @SuppressWarnings({"MagicNumber", "LineLength"})
    public static Optional<LogRecord> parseLogRecord(String logLine) {
        Pattern pattern = Pattern.compile(
            "^(\\S+) - (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"");
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.find()) {
            String remoteAddr = matcher.group(1);
            String remoteUser = matcher.group(2);
            OffsetDateTime timeLocal = parseTimeLocal(matcher.group(3));
            String requestType = matcher.group(4);
            String requestPath = matcher.group(5);
            String protocol = matcher.group(6);
            int status = Integer.parseInt(matcher.group(7));
            int bodyBytesSent = Integer.parseInt(matcher.group(8));
            String httpReferer = matcher.group(9);
            String userAgent = matcher.group(10);

            return Optional.of(new LogRecord(remoteAddr, remoteUser, timeLocal, requestType, requestPath, protocol,
                status, bodyBytesSent, httpReferer, userAgent
            ));
        } else {
            return Optional.empty();
        }
    }

    public static OffsetDateTime parseTimeLocal(String timeLocalStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        return OffsetDateTime.parse(timeLocalStr, formatter);
    }
}
