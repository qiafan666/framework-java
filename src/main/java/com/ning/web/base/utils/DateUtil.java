package com.ning.web.base.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String D_FORMAT_YYYYMMDD = "yyyyMMdd";

    public static String getDay(){
        SimpleDateFormat df = new SimpleDateFormat(D_FORMAT_YYYYMMDD);
        return df.format(System.currentTimeMillis());
    }

    public static String getDay(int day){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat df = new SimpleDateFormat(D_FORMAT_YYYYMMDD);
        return df.format(cal.getTimeInMillis());
    }

    public static String getDayByTime(Date time, int day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat df = new SimpleDateFormat(D_FORMAT_YYYYMMDD);
        return df.format(cal.getTimeInMillis());
    }

    public static Date parse(String time, String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            Date date = df.parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Timestamp getDiffDay(Timestamp current, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(current.getTime());
        cal.add(Calendar.DATE, days);
        cal.set(Calendar.HOUR_OF_DAY,16);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Date addDay(Date date, int day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //把日期往后增加一天,整数  往后推,负数往前移动
        cal.add(cal.DATE, day);
        return cal.getTime();
    }

//    public static void main(String[] args){
//        Date date = new Date();
//
//        //System.out.println("getDayByTime ===>>> "+ getFirstDayOfWeek());
//        //System.out.println("getMinutes ====>>> " + getMinutes(getFirstDayOfWeek(), current));
//        System.out.println("getAddDay ===>>> "+ addDay(date, 1));
//        System.out.println("getDiffDay ===>>> "+ new Timestamp(1616395386000L));
//        //System.out.println("getDiffDay ===>>> "+ getSaturdayOfLastWeek(current));
//    }
}
