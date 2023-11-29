package edu.project3.Formatters;

import edu.project3.LogStatistics;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.project3.LogStatistics.TOP_IPS_LIMIT;

public class MarkdownFormatter {
    private static final Logger LOGGE = LogManager.getLogger(AdocFormatter.class);

    @SuppressWarnings({"MultipleStringLiterals", "LineLength"})
    public String formatStatistics(LogStatistics statistics, String filePath, String fromDate, String toDate) {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Общая информация\n\n");
        sb.append("| Метрика | Значение |\n");
        sb.append("|---------|----------|\n");
        sb.append("| Файл(-ы) | `").append(filePath).append("` |\n");
        sb.append("| Начальная дата | ").append(fromDate != null ? fromDate : "-").append(" |\n");
        sb.append("| Конечная дата | ").append(toDate != null ? toDate : "-").append(" |\n");
        sb.append("| Количество запросов | ").append(statistics.getTotalRequests()).append(" |\n");
        sb.append("| Средний размер ответа | ").append(statistics.getAverageBodySize()).append("b |\n\n");

        sb.append("#### Запрашиваемые ресурсы\n\n");
        sb.append("| Ресурс | Количество |\n");
        sb.append("|--------|------------|\n");
        statistics.getResourceRequestCounts().forEach((resource, count) ->
            sb.append("| ").append(resource).append(" | ").append(count).append(" |\n"));

        sb.append("#### Типы запросов\n\n");
        sb.append("| Тип запроса | Количество |\n");
        sb.append("|-------------|------------|\n");
        statistics.getRequestTypeCounts().forEach((type, count) ->
            sb.append("| ").append(type).append(" | ").append(count).append(" |\n"));

        sb.append("\n#### Топ IP-адресов отправляющих запросы\n\n");
        sb.append("| IP-адрес | Количество запросов |\n");
        sb.append("|----------|---------------------|\n");
        statistics.getTopIpAddresses(TOP_IPS_LIMIT).forEach((ip, count) ->
            sb.append("| ").append(ip).append(" | ").append(count).append(" |\n"));

        sb.append("\n#### Коды ответа\n\n");
        sb.append("| Код | Имя | Количество |\n");
        sb.append("|-----|-----|------------|\n");
        statistics.getStatusCodeCounts().forEach((status, count) ->
            sb.append("| ").append(status).append(" | ").append(HttpStatus.getStatusName(status))
                .append(" | ")
                .append(count)
                .append(" |\n"));

        String result = sb.toString();
        String defaultPathToSave = "src/main/project3FormatterOutputFiles/out.md";
        try {
            FormatterUtils.writeToFile(result, defaultPathToSave);
        } catch (IOException e) {
            LOGGE.error("Ошибка при записи в файл: {}", defaultPathToSave, e);
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

}
