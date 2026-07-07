package utn.ba.ddsi.climalert.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime toLocalDateTime(String fechaStr) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(fechaStr, FORMATTER);
    }

    public static String toString(LocalDateTime fecha) {
        if (fecha == null) {
            return null;
        }
        return fecha.format(FORMATTER);
    }
}
