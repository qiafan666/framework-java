package com.ning.web.jotato.base.utils;

import com.ning.web.jotato.base.Assert;
import lombok.NonNull;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtil extends BaseDateUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_MINI_FORMAT = "yyyyMMdd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_MINI_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private DateUtil() {
    }

    public static Date getCurDate() {
        return setClock00(Calendar.getInstance()).getTime();
    }

    public static List<LocalDate> getMiddleDate(@NonNull Date begin, @NonNull Date end) {
        if (begin == null) {
            throw new NullPointerException("begin is marked non-null but is null");
        } else if (end == null) {
            throw new NullPointerException("end is marked non-null but is null");
        } else {
            return getMiddleDate(asLocalDate(begin), asLocalDate(end));
        }
    }

    public static List<LocalDate> getMiddleDate(LocalDate begin, LocalDate end) {
        List<LocalDate> localDateList = new ArrayList();
        long length = end.toEpochDay() - begin.toEpochDay();

        for(long i = length; i >= 0L; --i) {
            localDateList.add(end.minusDays(i));
        }

        return localDateList;
    }

    public static Date parse(String dateStr) {
        return parse(dateStr, DATE_FORMATTER);
    }

    public static Date parse(String dateStr, String pattern) {
        return parse(dateStr, getFormatter(pattern));
    }

    public static Date parse(String dateStr, DateTimeFormatter pattern) {
        Assert.notNull(pattern, "pattern is null");
        LocalDate localDate = LocalDate.parse(dateStr, pattern);
        return asDate(localDate);
    }

    public static String format(Date date) {
        return format(asLocalDate(date), DATE_FORMATTER);
    }

    public static String format(Date date, String pattern) {
        return format(asLocalDate(date), getFormatter(pattern));
    }

    public static String format(LocalDate date, String pattern) {
        return format(date, getFormatter(pattern));
    }

    public static String format(LocalDate date, DateTimeFormatter pattern) {
        return date.format(pattern);
    }

    public static LocalDate asLocalDate(long milliseconds) {
        return asLocalDate(Instant.ofEpochMilli(milliseconds));
    }

    public static LocalDate asLocalDate(Date date) {
        return asLocalDate(date.getTime());
    }

    public static LocalDate asLocalDate(Calendar calendar) {
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? DEFAULT_ZONE : tz.toZoneId();
        return asLocalDate(calendar.toInstant(), zid);
    }

    public static LocalDate asLocalDate(Instant instant) {
        return asLocalDate(instant, DEFAULT_ZONE);
    }

    public static LocalDate asLocalDate(Instant instant, ZoneId zid) {
        return instant.atZone(zid).toLocalDate();
    }

    public static long asMilliseconds(LocalDate localDate) {
        return DateTimeUtil.asMilliseconds(asLocalDateTime(localDate));
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(DEFAULT_ZONE).toInstant());
    }

    public static Instant asInstant(LocalDate localDate) {
        return DateTimeUtil.asInstant(asLocalDateTime(localDate));
    }

    public static LocalDateTime asLocalDateTime(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    public static LocalDateTime asLocalDateTime(LocalDate localDate, boolean isMaxTime) {
        return LocalDateTime.of(localDate, isMaxTime ? LocalTime.MAX : LocalTime.MIN);
    }

    public static LocalDateTime asLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return LocalDateTime.of(localDate, localTime);
    }
}
