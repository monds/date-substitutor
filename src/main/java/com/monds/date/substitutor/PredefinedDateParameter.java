package com.monds.date.substitutor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public enum PredefinedDateParameter implements DateParser {

    BASE_DATE_SHORT("baseDateShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.format(DateTimeFormatter.BASIC_ISO_DATE);
        }
    },

    BASE_DATE("baseDate") {
        @Override
        public String parse(LocalDateTime time) {
            return time.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    },

    BASE_DATE_TIME("baseDateTime") {
        @Override
        public String parse(LocalDateTime time) {
            return time.truncatedTo(ChronoUnit.DAYS).format(PredefinedDateFormatter.DATE_TIME_LONG.format());
        }
    },

    BASE_YEAR("baseYear") {
        @Override
        public String parse(LocalDateTime time) {
            return String.valueOf(time.getYear());
        }
    },

    PRE_30M_DATE_SHORT("pre30mDateShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusMinutes(time.getMinute() % 30 + 30)
                    .truncatedTo(ChronoUnit.MINUTES)
                    .format(DateTimeFormatter.BASIC_ISO_DATE);
        }
    },

    PRE_30M_START_HHMM_SHORT("pre30mStartHHmmShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusMinutes(time.getMinute() % 30 + 30)
                    .truncatedTo(ChronoUnit.MINUTES)
                    .format(PredefinedDateFormatter.HHMM_SHORT.format());
        }
    },

    PRE_30M_START_DATE_TIME_SHORT("pre30mStartDateTimeShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusMinutes((time.getMinute() % 30) + 30)
                    .truncatedTo(ChronoUnit.MINUTES)
                    .format(PredefinedDateFormatter.DATE_TIME_SHORT.format());
        }
    },

    PRE_30M_END_DATE_TIME_SHORT("pre30mEndDateTimeShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusMinutes(time.getMinute() % 30)
                    .truncatedTo(ChronoUnit.MINUTES)
                    .format(PredefinedDateFormatter.DATE_TIME_SHORT.format());
        }
    },

    PRE_30M_START_DATE_TIME("pre30mStartDateTime") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusMinutes((time.getMinute() % 30) + 30)
                    .truncatedTo(ChronoUnit.MINUTES)
                    .format(PredefinedDateFormatter.DATE_TIME_LONG.format());
        }
    },

    PRE_30M_END_DATE_TIME("pre30mEndDateTime") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusMinutes(time.getMinute() % 30)
                    .truncatedTo(ChronoUnit.MINUTES)
                    .format(PredefinedDateFormatter.DATE_TIME_LONG.format());
        }
    },

    MINUS_6_DAYS_DATE("minus6DaysDate") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusDays(6).format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    },

    MINUS_6_DAYS_DATE_SHORT("minus6DaysDateShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusDays(6).format(DateTimeFormatter.BASIC_ISO_DATE);
        }
    },

    MINUS_6_DAYS_DATE_TIME_SHORT("minus6DaysDateTimeShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusDays(6).truncatedTo(ChronoUnit.DAYS).format(PredefinedDateFormatter.DATE_TIME_SHORT.format());
        }
    },

    START_DATE_TIME_SHORT("startDateTimeShort") {
        @Override
        public String parse(LocalDateTime time) {
                return time.truncatedTo(ChronoUnit.DAYS).format(PredefinedDateFormatter.DATE_TIME_SHORT.format());
        }
    },

    END_DATE_TIME_SHORT("endDateTimeShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.toLocalDate().atTime(LocalTime.MAX).format(PredefinedDateFormatter.DATE_TIME_SHORT.format());
        }
    },

    START_DATE_TIME("startDateTime") {
        @Override
        public String parse(LocalDateTime time) {
            return time.truncatedTo(ChronoUnit.DAYS).format(PredefinedDateFormatter.DATE_TIME_LONG.format());
        }
    },

    END_DATE_TIME("endDateTime") {
        @Override
        public String parse(LocalDateTime time) {
            return time.toLocalDate().atTime(LocalTime.MAX).format(PredefinedDateFormatter.DATE_TIME_LONG.format());
        }
    },

    MONTH_START_DATE("monthStartDate") {
        @Override
        public String parse(LocalDateTime time) {
            return time.toLocalDate().withDayOfMonth(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    },

    MONTH_START_DATE_SHORT("monthStartDateShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.toLocalDate().withDayOfMonth(1).format(DateTimeFormatter.BASIC_ISO_DATE);
        }
    },

    MONTH_START_DATE_TIME_SHORT("monthStartDateTimeShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.truncatedTo(ChronoUnit.DAYS).withDayOfMonth(1).format(PredefinedDateFormatter.DATE_TIME_SHORT.format());
        }
    },

    MINUS_1_YEAR_DATE_SHORT("minus1YearDateShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusYears(1).format(DateTimeFormatter.BASIC_ISO_DATE);
        }
    },

    MINUS_3_MONTHS_DATE_SHORT("minus3MonthsDateShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusMonths(3).format(DateTimeFormatter.BASIC_ISO_DATE);
        }
    },

    MINUS_6_MONTHS_DATE_SHORT("minus6MonthsDateShort") {
        @Override
        public String parse(LocalDateTime time) {
            return time.minusMonths(6).format(DateTimeFormatter.BASIC_ISO_DATE);
        }
    }

    ;

    private String key;

    PredefinedDateParameter(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
