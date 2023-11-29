package edu.project3.Formatters;

public enum HttpStatus {
    OK(200, "OK (Успешно)"),
    PARTIAL_CONTENT(206, "Partial Content (Частичное содержимое)"),
    NOT_MODIFIED(304, "Not Modified (Не изменено)"),
    FORBIDDEN(403, "Forbidden (Запрещено)"),
    NOT_FOUND(404, "Not Found (Не найдено)"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable (Запрошенный диапазон не достижим)");

    private final int code;
    private final String description;

    HttpStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static String getStatusName(int code) {
        for (HttpStatus status : HttpStatus.values()) {
            if (status.getCode() == code) {
                return status.getDescription();
            }
        }
        return "Unknown (Неизвестно)";
    }
}
