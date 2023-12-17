package edu.project3;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogStatisticsTest {

    @Test
    public void testGetStatisticInfo() {
        LogStatistics statistics = new LogStatistics();
        OffsetDateTime time = OffsetDateTime.now(ZoneOffset.UTC);

        statistics.calc(new LogRecord("192.168.1.1", "-", time, "GET", "/index.html", "HTTP/1.1", 200, 500, "-", "-"));
        statistics.calc(new LogRecord("192.168.1.2", "-", time, "POST", "/submit", "HTTP/1.1", 404, 300, "-", "-"));
        statistics.calc(new LogRecord("192.168.1.1", "-", time, "GET", "/about", "HTTP/1.1", 200, 400, "-", "-"));

        String statsInfo = statistics.getStatisticInfo();

        assertThat(statsInfo).contains(
            "#### Общая информация",
            "| Количество запросов | 3 |",
            "| Средний размер ответа | 400.0b |",
            "#### Запрашиваемые ресурсы",
            "| /index.html | 1 |",
            "| /submit | 1 |",
            "| /about | 1 |",
            "#### Типы запросов",
            "| GET | 2 |",
            "| POST | 1 |",
            "#### Топ IP-адресов отправляющих запросы",
            "| 192.168.1.1 | 2 |",
            "| 192.168.1.2 | 1 |",
            "#### Коды ответа",
            "| 200 | 2 |",
            "| 404 | 1 |"
        );
    }
}

