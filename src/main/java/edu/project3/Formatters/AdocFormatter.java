package edu.project3.Formatters;

import edu.project3.LogStatistics;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.project3.LogStatistics.TOP_IPS_LIMIT;

public class AdocFormatter {
    private static final Logger LOGGER = LogManager.getLogger(AdocFormatter.class);

    @SuppressWarnings({"MultipleStringLiterals", "LineLength"})
    public String formatStatistics(LogStatistics statistics, String filePath, String fromDate, String toDate) {
        StringBuilder sb = new StringBuilder();

        sb.append(".Общая информация\n")
            .append("|====\n")
            .append("|Метрика |Значение\n");

        sb.append("|Файл(-ы) |").append(filePath).append("\n")
            .append("|Начальная дата |").append(fromDate != null ? fromDate : "-").append("\n")
            .append("|Конечная дата |").append(toDate != null ? toDate : "-").append("\n")
            .append("|Количество запросов |").append(statistics.getTotalRequests()).append("\n")
            .append("|Средний размер ответа |").append(statistics.getAverageBodySize()).append("b\n")
            .append("|====\n\n");

        sb.append(".Запрашиваемые ресурсы\n")
            .append("|====\n")
            .append("|Ресурс |Количество\n");

        statistics.getResourceRequestCounts().forEach((resource, count) ->
            sb.append("|").append(resource).append(" |").append(count).append("\n"));

        sb.append("|====\n\n");

        sb.append(".Типы запросов\n")
            .append("|====\n")
            .append("|Тип запроса |Количество\n");
        statistics.getRequestTypeCounts().forEach((type, count) ->
            sb.append("|").append(type).append(" |").append(count).append("\n"));
        sb.append("|====\n\n");

        sb.append(".Топ IP-адресов отправляющих запросы\n")
            .append("|====\n")
            .append("|IP-адрес |Количество запросов\n");
        statistics.getTopIpAddresses(TOP_IPS_LIMIT).forEach((ip, count) ->
            sb.append("|").append(ip).append(" |").append(count).append("\n"));
        sb.append("|====\n");

        sb.append(".Коды ответа\n")
            .append("|====\n")
            .append("|Код |Имя |Количество\n");

        statistics.getStatusCodeCounts().forEach((status, count) ->
            sb.append("|").append(status).append(" |").append(HttpStatus.getStatusName(status))
                .append(" |")
                .append(count)
                .append("\n"));

        sb.append("|====\n");

        String result = sb.toString();
        String defaultPathToSave = "src/main/project3FormatterOutputFiles/out.adoc";
        try {
            FormatterUtils.writeToFile(result, defaultPathToSave);
        } catch (IOException e) {
            LOGGER.error("Ошибка при записи в файл: {}", defaultPathToSave, e);
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

}
