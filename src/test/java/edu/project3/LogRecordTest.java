package edu.project3;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogRecordTest {

    @Test
    public void testParseValidLogRecord() {
        String logLine =
            "192.168.1.1 - user1 [01/Jan/2023:10:00:00 +0000] \"GET /index.html HTTP/1.1\" 200 1234 \"-\" \"Mozilla/5.0\"";
        Optional<LogRecord> result = LogRecord.parseLogRecord(logLine);

        assertThat(result).isPresent();
        result.ifPresent(logRecord -> {
            assertThat(logRecord.remoteAddr()).isEqualTo("192.168.1.1");
            assertThat(logRecord.remoteUser()).isEqualTo("user1");
            assertThat(logRecord.timeLocal()).isEqualTo(OffsetDateTime.of(2023, 1, 1, 10, 0, 0, 0, ZoneOffset.UTC));
            assertThat(logRecord.requestType()).isEqualTo("GET");
            assertThat(logRecord.requestPath()).isEqualTo("/index.html");
            assertThat(logRecord.protocol()).isEqualTo("HTTP/1.1");
            assertThat(logRecord.status()).isEqualTo(200);
            assertThat(logRecord.bodyBytesSent()).isEqualTo(1234);
            assertThat(logRecord.httpReferer()).isEqualTo("-");
            assertThat(logRecord.userAgent()).isEqualTo("Mozilla/5.0");
        });
    }

    @Test
    public void testParseInvalidLogRecord() {
        String invalidLogLine = "invalid log line";
        Optional<LogRecord> result = LogRecord.parseLogRecord(invalidLogLine);

        assertThat(result).isEmpty();
    }
}
