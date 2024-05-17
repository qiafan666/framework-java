package com.ning.web.jotato.base.utils;

import lombok.NonNull;

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseDateUtil {
    protected static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();
    public static final long DAY;
    private static final int[] SEASON;
    private static final int[] HALF_YEAR;

    protected BaseDateUtil() {
    }

    public static DateTimeFormatter getFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    public static Date addNanos(Date date, int amount) {
        return addTime(date, Duration.ofNanos((long)amount));
    }

    public static Date addMillis(Date date, int amount) {
        return addTime(date, Duration.ofMillis((long)amount));
    }

    public static Date addSeconds(Date date, int amount) {
        return addTime(date, Duration.ofSeconds((long)amount));
    }

    public static Date addMinutes(Date date, int amount) {
        return addTime(date, Duration.ofMinutes((long)amount));
    }

    public static Date addHours(Date date, int amount) {
        return addTime(date, Duration.ofHours((long)amount));
    }

    public static Date addDays(Date date, int amount) {
        return addTime(date, Period.ofDays(amount));
    }

    public static Date addWeeks(Date date, int amount) {
        return addTime(date, Period.ofWeeks(amount));
    }

    public static Date addMonths(Date date, int amount) {
        return addTime((Date)date, 2, amount).getTime();
    }

    public static Date addYears(Date date, int amount) {
        return addTime((Date)date, 1, amount).getTime();
    }

    public static Date getBeginDay(Date date) {
        return null == date ? null : setClock00(toCalendar(date)).getTime();
    }

    public static Date getEndDay(Date date) {
        return null == date ? null : setClock235959999(toCalendar(date)).getTime();
    }

    public static Date getNextDate() {
        Calendar cal = addTime((Calendar)Calendar.getInstance(), 5, 1);
        return setClock00(cal).getTime();
    }

    public static Date getNextMonth() {
        return addTime((Calendar)Calendar.getInstance(), 2, 1).getTime();
    }

    public static Long getCurUnixTimeMis() {
        return System.currentTimeMillis();
    }

    public static Integer getCurUnixTime() {
        return (int)(System.currentTimeMillis() / 1000L);
    }

    public static Integer getCurDay() {
        return Calendar.getInstance().get(5);
    }

    public static Integer getCurMonth() {
        return Calendar.getInstance().get(2) + 1;
    }

    public static Integer getCurYear() {
        return Calendar.getInstance().get(1);
    }

    public static Day getThisDay(Date date) {
        Calendar cal = toCalendar(date);
        return new Day(setClock00(cal).getTime(), setClock235959999(cal).getTime());
    }

    public static Week getThisWeek(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar cal = setClock00(toCalendar(date));
            int dayOfWeek = cal.get(7);
            if (dayOfWeek == 1) {
                dayOfWeek += 7;
            }

            Date start = addTime((Calendar)cal, 5, 2 - dayOfWeek).getTime();
            Calendar end = addTime((Calendar)cal, 5, 6);
            return new Week(start, setClock235959999(end).getTime());
        }
    }

    public static Month getThisMonth(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar cal = setClock00(toCalendar(date));
            cal.set(5, 1);
            Date start = cal.getTime();
            int day = cal.getActualMaximum(5);
            cal.set(5, day);
            Date end = setClock235959999(cal).getTime();
            return new Month(start, end);
        }
    }

    public static Season getThisSeason(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar cal = setClock00(toCalendar(date));
            int sean = SEASON[cal.get(2)];
            cal.set(2, sean * 3 - 3);
            cal.set(5, 1);
            Date start = cal.getTime();
            cal.set(2, sean * 3 - 1);
            int day = cal.getActualMaximum(5);
            cal.set(5, day);
            Date end = setClock235959999(cal).getTime();
            return new Season(start, end);
        }
    }

    public static HalfYear getThisHalfYear(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar cal = setClock00(toCalendar(date));
            int halfYear = HALF_YEAR[cal.get(2)];
            int beginMonth = 0;
            int endMonth = 5;
            int beginDay = 1;
            int endDay = 30;
            if (2 == halfYear) {
                beginMonth = 6;
                endMonth = 11;
                endDay = 31;
            }

            cal.set(2, beginMonth);
            cal.set(5, beginDay);
            Date start = cal.getTime();
            cal.set(2, endMonth);
            cal.set(5, endDay);
            setClock235959999(cal);
            Date end = cal.getTime();
            return new HalfYear(start, end);
        }
    }

    public static Year getThisYear(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar cal = setClock00(toCalendar(date));
            addTime((Calendar)cal, 6, 1);
            Date start = cal.getTime();
            addTime((Calendar)cal, 1, 1);
            addTime((Calendar)cal, 6, -1);
            Date end = setClock235959999(cal).getTime();
            return new Year(start, end);
        }
    }

    public static int compare(@NonNull Date firstDate, @NonNull Date secondDate) {
        if (firstDate == null) {
            throw new NullPointerException("firstDate is marked non-null but is null");
        } else if (secondDate == null) {
            throw new NullPointerException("secondDate is marked non-null but is null");
        } else {
            return firstDate.toInstant().compareTo(secondDate.toInstant());
        }
    }

    public static boolean isAfterDay(@NonNull Date firstDate, @NonNull Date secondDate) {
        if (firstDate == null) {
            throw new NullPointerException("firstDate is marked non-null but is null");
        } else if (secondDate == null) {
            throw new NullPointerException("secondDate is marked non-null but is null");
        } else {
            return !isBeforeOrSameDay(firstDate, secondDate);
        }
    }

    public static boolean isAfterOrSameDay(@NonNull Date firstDate, @NonNull Date secondDate) {
        if (firstDate == null) {
            throw new NullPointerException("firstDate is marked non-null but is null");
        } else if (secondDate == null) {
            throw new NullPointerException("secondDate is marked non-null but is null");
        } else {
            return !isBeforeDay(firstDate, secondDate);
        }
    }

    public static boolean isBeforeOrSameDay(@NonNull Date firstDate, @NonNull Date secondDate) {
        if (firstDate == null) {
            throw new NullPointerException("firstDate is marked non-null but is null");
        } else if (secondDate == null) {
            throw new NullPointerException("secondDate is marked non-null but is null");
        } else {
            Date date1 = getBeginDay(firstDate);
            Date date2 = getBeginDay(secondDate);
            return compare(date1, date2) <= 0;
        }
    }

    public static boolean isBeforeDay(@NonNull Date firstDate, @NonNull Date secondDate) {
        if (firstDate == null) {
            throw new NullPointerException("firstDate is marked non-null but is null");
        } else if (secondDate == null) {
            throw new NullPointerException("secondDate is marked non-null but is null");
        } else {
            Date date1 = getBeginDay(firstDate);
            Date date2 = getBeginDay(secondDate);
            return compare(date1, date2) < 0;
        }
    }

    public static boolean isSameWeek(@NonNull Date firstDate, @NonNull Date secondDate) {
        if (firstDate == null) {
            throw new NullPointerException("firstDate is marked non-null but is null");
        } else if (secondDate == null) {
            throw new NullPointerException("secondDate is marked non-null but is null");
        } else {
            Week w = getThisWeek(firstDate);
            return secondDate.getTime() >= w.getStartDate().getTime() && secondDate.getTime() <= w.getLastDate().getTime();
        }
    }

    public static boolean isSameWeek(long firstDate, long secondDate) {
        return isSameWeek(new Date(firstDate), new Date(secondDate));
    }

    public static boolean isSameWeekForWest(@NonNull Date firstDate, @NonNull Date secondDate) {
        if (firstDate == null) {
            throw new NullPointerException("firstDate is marked non-null but is null");
        } else if (secondDate == null) {
            throw new NullPointerException("secondDate is marked non-null but is null");
        } else {
            Calendar cal1 = toCalendar(firstDate);
            Calendar cal2 = toCalendar(secondDate);
            int subYear = cal1.get(1) - cal2.get(1);
            if (subYear == 0) {
                return cal1.get(3) == cal2.get(3);
            } else if (subYear == 1 && cal2.get(2) == 11) {
                return cal1.get(3) == cal2.get(3);
            } else {
                return subYear == -1 && cal1.get(2) == 11 && cal1.get(3) == cal2.get(3);
            }
        }
    }

    public static int getDiffDays(@NonNull Date beginDate, @NonNull Date endDate) {
        if (beginDate == null) {
            throw new NullPointerException("beginDate is marked non-null but is null");
        } else if (endDate == null) {
            throw new NullPointerException("endDate is marked non-null but is null");
        } else {
            long diff = (endDate.getTime() - beginDate.getTime()) / DAY;
            return (int)diff;
        }
    }

    public static long dateDiff(@NonNull Date beginDate, @NonNull Date endDate) {
        if (beginDate == null) {
            throw new NullPointerException("beginDate is marked non-null but is null");
        } else if (endDate == null) {
            throw new NullPointerException("endDate is marked non-null but is null");
        } else {
            long date1ms = beginDate.getTime();
            long date2ms = endDate.getTime();
            return date2ms - date1ms;
        }
    }

    public static Date max(Date firstDate, Date secondDate) {
        if (Objects.isNull(firstDate) && Objects.isNull(secondDate)) {
            return null;
        } else if (firstDate == null) {
            return secondDate;
        } else if (secondDate == null) {
            return firstDate;
        } else {
            return firstDate.getTime() < secondDate.getTime() ? secondDate : firstDate;
        }
    }

    public static Date min(Date firstDate, Date secondDate) {
        if (Objects.isNull(firstDate) && Objects.isNull(secondDate)) {
            return null;
        } else if (firstDate == null) {
            return secondDate;
        } else if (secondDate == null) {
            return firstDate;
        } else {
            return firstDate.getTime() > secondDate.getTime() ? secondDate : firstDate;
        }
    }

    protected static Calendar setClock00(Calendar cal) {
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal;
    }

    protected static Calendar setClock235959999(Calendar cal) {
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, 999);
        return cal;
    }

    protected static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    protected static Calendar addTime(@NonNull Date date, int timeType, int amount) {
        if (date == null) {
            throw new NullPointerException("date is marked non-null but is null");
        } else {
            return addTime(toCalendar(date), timeType, amount);
        }
    }

    protected static Date addTime(@NonNull Date date, TemporalAmount amount) {
        if (date == null) {
            throw new NullPointerException("date is marked non-null but is null");
        } else {
            Instant instant = date.toInstant();
            return Date.from(instant.plus(amount));
        }
    }

    protected static Calendar addTime(Calendar cal, int timeType, int amount) {
        cal.add(timeType, amount);
        return cal;
    }

    static {
        DAY = TimeUnit.DAYS.toMillis(1L);
        SEASON = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
        HALF_YEAR = new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2};
    }

    public static class Year extends IDate.AbstractDate {
        private static final long serialVersionUID = 8431429720225029074L;

        Year(Date firstDate, Date lastDate) {
            super(firstDate, lastDate);
        }
    }

    public static class HalfYear extends IDate.AbstractDate {
        private static final long serialVersionUID = -9219855412860408528L;

        HalfYear(Date firstDate, Date lastDate) {
            super(firstDate, lastDate);
        }
    }

    public static class Season extends IDate.AbstractDate {
        private static final long serialVersionUID = -2122400933036843013L;

        Season(Date firstDate, Date lastDate) {
            super(firstDate, lastDate);
        }
    }

    public static class Month extends IDate.AbstractDate {
        private static final long serialVersionUID = -6649490915526710487L;

        Month(Date firstDate, Date lastDate) {
            super(firstDate, lastDate);
        }
    }

    public static class Week extends IDate.AbstractDate {
        private static final long serialVersionUID = 7336546976641014131L;

        Week(Date firstDate, Date lastDate) {
            super(firstDate, lastDate);
        }
    }

    public static class Day extends IDate.AbstractDate {
        private static final long serialVersionUID = 8222859888957711969L;

        Day(Date clock0, Date clock24) {
            super(clock0, clock24);
        }
    }
}
