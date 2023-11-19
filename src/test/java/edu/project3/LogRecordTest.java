package edu.project3;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LogRecordTest {

    @Test
    void parseLogRecordCorrectInput() {
        String logLine = "204.77.168.241 - - " +
            "[17/May/2015:21:05:43 +0000] " +
            "\"GET " +
            "/downloads/product_2 " +
            "HTTP/1.1\" " +
            "404 " +
            "338 " +
            "\"-\" " +
            "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.7)\"";
        OffsetDateTime parsedDate = LogRecord.parseTimeLocal("17/May/2015:21:05:43 +0000");

        LogRecord expect = new LogRecord(
            "204.77.168.241",
            "-",
            parsedDate,
            "GET",
            "/downloads/product_2",
            "HTTP/1.1",
            404,
            338,
            "-",
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.7)"
        );

        assertThat(LogRecord.parseLogRecord(logLine)).isEqualTo(expect);
    }

    @Test
    void parseLogRecordIncorrectInput() {
        String logLine1 = "204.77.168.241 - **********";

        assertThatThrownBy(() -> LogRecord.parseLogRecord(logLine1)).isInstanceOf(IllegalArgumentException.class);
    }
}
