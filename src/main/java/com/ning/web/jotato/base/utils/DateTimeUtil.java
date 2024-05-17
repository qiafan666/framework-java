package com.ning.web.jotato.base.utils;

import com.ning.web.jotato.base.Assert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeUtil extends BaseDateUtil {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_MINI_FORMAT = "yyyyMMddHHmmss";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATETIME_MINI_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private DateTimeUtil() {
    }

    public static Date getCurDateTime() {
        return Calendar.getInstance().getTime();
    }

    public static boolean isAfter(Date firstDate, Date secondDate) {
        return compare(firstDate, secondDate) > 0;
    }

    public static boolean isAfterOrSame(Date firstDate, Date secondDate) {
        return compare(firstDate, secondDate) >= 0;
    }

    public static boolean isBefore(Date firstDate, Date secondDate) {
        return compare(firstDate, secondDate) < 0;
    }

    public static boolean isBeforeOrSame(Date firstDate, Date secondDate) {
        return compare(firstDate, secondDate) <= 0;
    }

    public static LocalTime parseTime(String dateStr) {
        return parseTime(dateStr, TIME_FORMATTER);
    }

    public static LocalTime parseTime(String dateStr, String pattern) {
        return parseTime(dateStr, getFormatter(pattern));
    }

    public static LocalTime parseTime(String dateStr, DateTimeFormatter formatter) {
        return LocalTime.parse(dateStr, formatter);
    }

    public static Date parse(String dateStr) {
        return parse(dateStr, DATETIME_FORMATTER);
    }

    public static Date parse(String dateStr, String pattern) {
        return parse(dateStr, getFormatter(pattern));
    }

    public static Date parse(String dateStr, DateTimeFormatter pattern) {
        Assert.notNull(pattern, "pattern is null");
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, pattern);
        return asDate(localDateTime);
    }

    public static String format(Date date) {
        return format(asLocalDateTime(date), DATETIME_FORMATTER);
    }

    public static String format(Date datetime, String pattern) {
        return format(asLocalDateTime(datetime), getFormatter(pattern));
    }

    public static String format(LocalDateTime datetime, String pattern) {
        return format(datetime, getFormatter(pattern));
    }

    public static String format(LocalDateTime datetime, DateTimeFormatter pattern) {
        return datetime.format(pattern);
    }

    public static LocalDateTime asLocalDateTime(long milliseconds) {
        return asLocalDateTime(Instant.ofEpochMilli(milliseconds));
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return asLocalDateTime(date.toInstant());
    }

    public static LocalDateTime asLocalDateTime(Calendar calendar) {
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? DEFAULT_ZONE : tz.toZoneId();
        return asLocalDateTime(calendar.toInstant(), zid);
    }

    public static LocalDateTime asLocalDateTime(Instant instant) {
        return asLocalDateTime(instant, DEFAULT_ZONE);
    }

    public static LocalDateTime asLocalDateTime(Instant instant, ZoneId zid) {
        return LocalDateTime.ofInstant(instant, zid);
    }

    public static long asMilliseconds(LocalDateTime localDateTime) {
        return asInstant(localDateTime).toEpochMilli();
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(asInstant(localDateTime));
    }

    public static Instant asInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(DEFAULT_ZONE).toInstant();
    }

    public static Calendar asCalendar(LocalDateTime localDateTime) {
        return GregorianCalendar.from(ZonedDateTime.of(localDateTime, DEFAULT_ZONE));
    }

    public static LocalDate asLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }
}
