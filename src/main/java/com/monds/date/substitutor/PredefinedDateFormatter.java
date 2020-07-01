package com.monds.date.substitutor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public enum PredefinedDateFormatter {

    ISO_OFFSET_DATE_TIME_FRACTION("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"),

    DATE_TIME_LONG("yyyy-MM-dd HH:mm:ss"),

    DATE_TIME_SHORT("yyyyMMddHHmmss"),

    HHMM_SHORT("HHmm"),

    ;

    private DateTimeFormatter formatter;

    PredefinedDateFormatter(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public DateTimeFormatter format() {
        return formatter;
    }

    public static LocalDateTime parse(String dateStr) {
        LocalDateTime ldt;
        for (PredefinedDateFormatter dateFormat : PredefinedDateFormatter.values()) {
            if (dateFormat != HHMM_SHORT) {
                try {
                    ldt = LocalDateTime.parse(dateStr, dateFormat.formatter);
                } catch (DateTimeParseException e) {
                    continue;
                }

                return ldt;
            }
        }

        throw new DateTimeParseException("There is no parsable PredefinedDateFormat: " + dateStr, dateStr, 0);
    }

    public Long toEpochMilli(String text) {
        return LocalDateTime.from(formatter.parse(text)).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static void main(String[] args) {

        LocalDateTime ldt = LocalDateTime.parse("20200501000000", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")).minusMonths(1);

        System.out.println(ldt.format(DateTimeFormatter.BASIC_ISO_DATE));
    }
}
