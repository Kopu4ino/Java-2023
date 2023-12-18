package edu.project3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LogStatistics {
    public static final int TOP_IPS_LIMIT = 3;
    private long totalRequests = 0;
    private Map<String, Long> resourceRequestCounts = new HashMap<>();
    private Map<Integer, Long> statusCodeCounts = new HashMap<>();
    private long totalBodyBytes = 0;
    private Map<String, Long> requestTypeCounts = new HashMap<>();
    private Map<String, Long> ipRequestCounts = new HashMap<>();

    public void calc(LogRecord logRecord) {
        totalRequests++;
        resourceRequestCounts.merge(logRecord.requestPath(), 1L, Long::sum);
        statusCodeCounts.merge(logRecord.status(), 1L, Long::sum);
        totalBodyBytes += logRecord.bodyBytesSent();
        requestTypeCounts.merge(logRecord.requestType(), 1L, Long::sum);
        ipRequestCounts.merge(logRecord.remoteAddr(), 1L, Long::sum);
    }

    public long getTotalRequests() {
        return totalRequests;
    }

    public Map<String, Long> getResourceRequestCounts() {
        return resourceRequestCounts;
    }

    public Map<Integer, Long> getStatusCodeCounts() {
        return statusCodeCounts;
    }

    public double getAverageBodySize() {
        return totalRequests > 0 ? (double) totalBodyBytes / totalRequests : 0.0;
    }

    public Map<String, Long> getRequestTypeCounts() {
        return requestTypeCounts;
    }

    public Map<String, Long> getIpRequestCounts() {
        return ipRequestCounts;
    }

    public Map<String, Long> getTopIpAddresses(int limit) {
        return ipRequestCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(limit)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
    }

    @SuppressWarnings("MultipleStringLiterals")
    public String getStatisticInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Общая информация\n");
        sb.append("| Метрика | Значение |\n");
        sb.append("|---------|----------|\n");
        sb.append("| Количество запросов | ").append(totalRequests).append(" |\n");
        sb.append("| Средний размер ответа | ").append(getAverageBodySize()).append("b |\n\n");

        sb.append("#### Запрашиваемые ресурсы\n");
        sb.append("| Ресурс | Количество |\n");
        sb.append("|--------|------------|\n");
        resourceRequestCounts.forEach((resource, count) ->
            sb.append("| ").append(resource).append(" | ").append(count).append(" |\n"));

        sb.append("\n#### Типы запросов\n");
        sb.append("| Тип запроса | Количество |\n");
        sb.append("|-------------|------------|\n");
        requestTypeCounts.forEach((type, count) ->
            sb.append("| ").append(type).append(" | ").append(count).append(" |\n"));

        sb.append("\n#### Топ IP-адресов отправляющих запросы\n");
        sb.append("| IP-адрес | Количество запросов |\n");
        sb.append("|-----------|---------------------|\n");
        getTopIpAddresses(TOP_IPS_LIMIT).forEach((ip, count) ->
            sb.append("| ").append(ip).append(" | ").append(count).append(" |\n"));

        sb.append("\n#### Коды ответа\n");
        sb.append("| Код | Количество |\n");
        sb.append("|-----|------------|\n");
        statusCodeCounts.forEach((status, count) ->
            sb.append("| ").append(status).append(" | ").append(count).append(" |\n"));

        return sb.toString();
    }
}

