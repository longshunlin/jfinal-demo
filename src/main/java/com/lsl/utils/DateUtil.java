//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lsl.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
    static Calendar calendar;
    protected static final Log _log = LogFactory.getLog(DateUtil.class);
    public static final Long HOUR_TIME_STAMP = Long.valueOf(3600000L);
    public static final Long DAY_TIME_STAMP = Long.valueOf(86400000L);

    public DateUtil() {
    }

    public static String DateToStr(Date date, String format) {
        try {
            SimpleDateFormat e = new SimpleDateFormat(format);
            return e.format(date);
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("Date 转 String 类型失败: " + var3);
            }

            return null;
        }
    }

    public static String DateToStr(Date date) {
        return DateToStr(date, "yyyy-MM-dd");
    }

    public static String dateToStr(Date date) {
        return DateToStr(date, "yyyyMMdd");
    }

    public static String DateTimeToStr(Date date) {
        return DateToStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String DateTimeToStrBySSS(Date date) {
        return DateToStr(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static String dateTimeToStr(Date date) {
        return DateToStr(date, "yyyyMMddHHmmss");
    }

    public static String TimeToStr(Date date) {
        return DateToStr(date, "HH:mm:ss");
    }

    public static String timeToStr(Date date) {
        return DateToStr(date, "HHmmss");
    }

    public static String hourMinuteToStr(Date date) {
        return DateToStr(date, "HHmm");
    }

    public static String YearToStr(Date date) {
        return DateToStr(date, "yyyy");
    }

    public static int YearToInt(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(1);
    }

    public static String MonthToStr(Date date) {
        return DateToStr(date, "MM");
    }

    public static int MonthToInt(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(2) + 1;
    }

    public static String DayToStr(Date date) {
        return DateToStr(date, "dd");
    }

    public static int DayToInt(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(5);
    }

    public static String YearMonthToStr(Date date) {
        return DateToStr(date, "yyyy-MM");
    }

    public static String yearMonthToStr(Date date) {
        return DateToStr(date, "yyyyMM");
    }

    public static String MonthDayToStr(Date date) {
        return DateToStr(date, "MM-dd");
    }

    public static String monthDayToStr(Date date) {
        return DateToStr(date, "MMdd");
    }

    public static String DayMonthYearToStr(Date date) {
        return DateToStr(date, "dd/MM/yyyy");
    }

    public static String YearMonthDayToStr(Date date) {
        return DateToStr(date, "yyyy/MM/dd");
    }

    public static Date StrToDate(String sDate, String format) {
        try {
            SimpleDateFormat e = new SimpleDateFormat(format);
            return e.parse(sDate);
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("String 转 Date 类型失败: " + var3);
            }

            return null;
        }
    }

    public static Date StrToDate(String sDate) {
        return StrToDate(sDate, "yyyy-MM-dd");
    }

    public static Date strToDate(String sDate) {
        return StrToDate(sDate, "yyyyMMdd");
    }

    public static Date SDateTimeToDate(String sDateTime) {
        return StrToDate(sDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date SDateTimeToDateBySSS(String sDateTime) {
        return StrToDate(sDateTime, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static Date sDateTimeToDate(String sDateTime) {
        return StrToDate(sDateTime, "yyyyMMddHHmmss");
    }

    public static Date addDate(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(5, val);
        return gc.getTime();
    }

    public static Date subDate(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(5, -val);
        return gc.getTime();
    }

    public static Date subHour(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(10, -val);
        return gc.getTime();
    }

    public static Date subMinute(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(12, -val);
        return gc.getTime();
    }

    public static Date addMonth(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(2, val);
        return gc.getTime();
    }

    public static Date addYear(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(1, val);
        return gc.getTime();
    }

    public static int getDayOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(6);
    }

    public static int sumDayByYearMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(1, year);
        c.set(2, month - 1);
        return c.getActualMaximum(5);
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(5, c.getActualMaximum(5));
        return c.getTime();
    }

    public static Date getLastDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, c.getActualMaximum(5));
        return c.getTime();
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(5, c.getActualMinimum(5));
        return c.getTime();
    }

    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, c.getActualMinimum(5));
        return c.getTime();
    }

    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(7);
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(7) - 1;
        if(dayOfWeek == 0) {
            dayOfWeek = 7;
        }

        c.add(7, 1 - dayOfWeek);
        return c.getTime();
    }

    public static List<Date> getDateList(Date startDate, Date endDate) {
        Date tempDate = startDate;
        ArrayList dateList = new ArrayList();
        if(isSameDay(startDate, endDate)) {
            dateList.add(startDate);
        } else {
            while(true) {
                if(!tempDate.before(endDate)) {
                    dateList.add(tempDate);
                    break;
                }

                dateList.add(tempDate);
                tempDate = DateUtils.addDays(tempDate, 1);
            }
        }

        return dateList;
    }

    public static List<String> getDateToStrList(String startDate, String endDate) {
        return getDateList(startDate, endDate, "yyyy-MM-dd");
    }

    public static List<String> getDateList(String startDate, String endDate, String format) {
        ArrayList sDateList = new ArrayList();
        Date periodDate = StrToDate(startDate, format);
        if(startDate.equals(endDate)) {
            sDateList.add(DateToStr(periodDate, format));
        } else {
            while(true) {
                if(!periodDate.before(StrToDate(endDate, format))) {
                    sDateList.add(DateToStr(periodDate, format));
                    break;
                }

                sDateList.add(DateToStr(periodDate, format));
                periodDate = DateUtils.addDays(periodDate, 1);
            }
        }

        return sDateList;
    }

    public static List<Date> getYearMonthAllDate(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        ArrayList dateList = new ArrayList();
        int size = c.getActualMaximum(5);

        for(int i = 0; i < size; ++i) {
            c.set(5, i + 1);
            dateList.add(c.getTime());
        }

        return dateList;
    }

    public boolean isLeapYear(int year) {
        GregorianCalendar gc = new GregorianCalendar();
        return gc.isLeapYear(year);
    }

    public boolean isLeapYear(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        return gc.isLeapYear(YearToInt(date));
    }

    public static int getDays(Date sd, Date ed) {
        return (int)((ed.getTime() - sd.getTime()) / 86400000L);
    }

    public static Calendar mergeDateTime(Date date, Time time) {
        Calendar cal = Calendar.getInstance();
        if(date != null) {
            cal.setTime(date);
        }

        if(time != null) {
            Calendar temp = Calendar.getInstance();
            temp.setTime(time);
            cal.set(11, temp.get(11));
            cal.set(12, temp.get(12));
            cal.set(13, temp.get(13));
            cal.set(14, temp.get(14));
        }

        return cal;
    }

    public static int diff_in_date(Date d1, Date d2) {
        return (int)(d1.getTime() - d2.getTime()) / 86400000;
    }

    public static Calendar getDateBegin(int year, int month, int date) {
        Calendar begin_time = Calendar.getInstance();
        begin_time.set(1, year);
        begin_time.set(2, month - 1);
        begin_time.set(5, date);
        begin_time.set(11, 0);
        begin_time.set(12, 0);
        begin_time.set(13, 0);
        begin_time.set(14, 0);
        return begin_time;
    }

    public static Long getDayBeginTimestamp() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 1);
        return Long.valueOf(cal.getTimeInMillis());
    }

    public static Long getNow() {
        return Long.valueOf(System.currentTimeMillis());
    }

    public static String getNowStr() {
        return DateTimeToStr(new Date());
    }

    public static String getNowStrSSS() {
        return DateTimeToStrBySSS(new Date());
    }

    public static void resetTime(Calendar cal) {
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameInstant(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            return date1.getTime() == date2.getTime();
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.getTime().getTime() == cal2.getTime().getTime();
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameLocalTime(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(14) == cal2.get(14) && cal1.get(13) == cal2.get(13) && cal1.get(12) == cal2.get(12) && cal1.get(10) == cal2.get(10) && cal1.get(6) == cal2.get(6) && cal1.get(1) == cal2.get(1) && cal1.get(0) == cal2.get(0) && cal1.getClass() == cal2.getClass();
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isDateBefore(Date start, Date end) {
        GregorianCalendar sgc = new GregorianCalendar();
        GregorianCalendar egc = new GregorianCalendar();
        sgc.setTime(start);
        egc.setTime(end);
        return sgc.before(egc);
    }

    public static boolean isDateBetweenStartAndEnd(Date start, Date end, Date date) {
        return isDateBefore(start, end) && isDateBefore(start, date) && isDateBefore(date, end);
    }

    public static boolean isDateBetween(Date start, Date end, Date date) {
        int sd = TypeUtil.strToInt(dateToStr(start));
        int ed = TypeUtil.strToInt(dateToStr(end));
        int d = TypeUtil.strToInt(dateToStr(date));
        return sd <= ed && sd <= d && d <= ed;
    }

    public static boolean isDateLe(Date date1, Date date2) {
        int d1 = TypeUtil.strToInt(dateToStr(date1));
        int d2 = TypeUtil.strToInt(dateToStr(date2));
        return d1 <= d2;
    }

    public static boolean isDateGe(Date date1, Date date2) {
        int d1 = TypeUtil.strToInt(dateToStr(date1));
        int d2 = TypeUtil.strToInt(dateToStr(date2));
        return d1 >= d2;
    }

    public static boolean yearMonthBetweenStartAndEnd(Date start, Date end, Date date) {
        int sd = TypeUtil.strToInt(YearMonthToStr(start).replace("-", ""));
        int ed = TypeUtil.strToInt(YearMonthToStr(end).replace("-", ""));
        int d = TypeUtil.strToInt(YearMonthToStr(date).replace("-", ""));
        return sd <= ed && sd <= d && d <= ed;
    }

    public static boolean yearMonthBetweenStartAndEnd(Date start, Date end, String yearMonth) {
        int sd = TypeUtil.strToInt(YearMonthToStr(start).replace("-", ""));
        int ed = TypeUtil.strToInt(YearMonthToStr(end).replace("-", ""));
        int ym = TypeUtil.strToInt(yearMonth.replace("-", ""));
        return sd <= ed && sd <= ym && ym <= ed;
    }

    public static boolean afterCurDate(Date date) {
        GregorianCalendar now = new GregorianCalendar();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return c.after(now);
    }

    public static String getMonthStr(String str) {
        String[] monthOfYear = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        if("01".equals(str)) {
            str = monthOfYear[0];
        } else if("02".equals(str)) {
            str = monthOfYear[1];
        } else if("03".equals(str)) {
            str = monthOfYear[2];
        } else if("04".equals(str)) {
            str = monthOfYear[3];
        } else if("05".equals(str)) {
            str = monthOfYear[4];
        } else if("06".equals(str)) {
            str = monthOfYear[5];
        } else if("07".equals(str)) {
            str = monthOfYear[6];
        } else if("08".equals(str)) {
            str = monthOfYear[7];
        } else if("09".equals(str)) {
            str = monthOfYear[8];
        } else if("10".equals(str)) {
            str = monthOfYear[9];
        } else if("11".equals(str)) {
            str = monthOfYear[10];
        } else if("12".equals(str)) {
            str = monthOfYear[11];
        } else {
            str = "ERROR";
        }

        return str;
    }

    public static int getNumByStrMonth(String strMonth) {
        return strMonth.equals("January")?1:(strMonth.equals("February")?2:(strMonth.equals("March")?3:(strMonth.equals("April")?4:(strMonth.equals("May")?5:(strMonth.equals("June")?6:(strMonth.equals("July")?7:(strMonth.equals("August")?8:(strMonth.equals("September")?9:(strMonth.equals("October")?10:(strMonth.equals("November")?11:(strMonth.equals("December")?12:0)))))))))));
    }

    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Date datet = null;

        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException var6) {
            var6.printStackTrace();
        }

        int w = cal.get(7) - 1;
        if(w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    public static String IntegerTo(Integer i) {
        String str = "";
        if(i.intValue() < 10) {
            str = "0" + String.valueOf(i);
        } else {
            str = String.valueOf(i);
        }

        return str;
    }

    public static String getShortTime(String time) {
        calendar = Calendar.getInstance();
        calendar.setTime(SDateTimeToDate(time));
        String shortstring = null;
        long now = Calendar.getInstance().getTimeInMillis();
        Date date = SDateTimeToDate(time);
        if(date == null) {
            return shortstring;
        } else {
            int num = getIntervalDayNum(Long.valueOf(date.getTime()), Long.valueOf(now)).intValue();
            long deltime = (now - date.getTime()) / 1000L;
            if(deltime > 31536000L) {
                shortstring = time;
            } else if(deltime > 86400L) {
                if(num == 1) {
                    shortstring = "昨天 " + IntegerTo(Integer.valueOf(calendar.get(11))) + "时" + IntegerTo(Integer.valueOf(calendar.get(12)));
                } else if(num == 2) {
                    shortstring = "前天 " + IntegerTo(Integer.valueOf(calendar.get(11))) + "时" + IntegerTo(Integer.valueOf(calendar.get(12)));
                } else if((float)deltime * 1.0F / 86400.0F <= 186.0F) {
                    shortstring = IntegerTo(Integer.valueOf(calendar.get(2) + 1)) + "月" + IntegerTo(Integer.valueOf(calendar.get(5))) + "日 " + IntegerTo(Integer.valueOf(calendar.get(11))) + ":" + IntegerTo(Integer.valueOf(calendar.get(12)));
                } else {
                    shortstring = time;
                }
            } else if(deltime > 3600L) {
                if((float)deltime * 1.0F / 3600.0F < 12.0F) {
                    shortstring = (int)(deltime / 3600L) + "小时前";
                } else if(num == 1) {
                    shortstring = "昨天 " + IntegerTo(Integer.valueOf(calendar.get(11))) + "时" + IntegerTo(Integer.valueOf(calendar.get(12)));
                } else {
                    shortstring = "今天 " + IntegerTo(Integer.valueOf(calendar.get(11))) + "时" + IntegerTo(Integer.valueOf(calendar.get(12)));
                }
            } else if(deltime > 60L) {
                shortstring = Integer.valueOf((int)(deltime / 60L)) + "分钟前";
            } else if(deltime > 1L) {
                shortstring = "刚刚";
            } else {
                shortstring = "刚刚";
            }

            return shortstring;
        }
    }

    public static Integer getIntervalDayNum(Long date1, Long date2) {
        boolean dayNum = false;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date(date1.longValue()));
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date(date2.longValue()));
        int day1 = cal1.get(6);
        int day2 = cal2.get(6);
        int year1 = cal1.get(1);
        int year2 = cal2.get(1);
        int var11;
        if(year1 != year2) {
            int timeDistance = 0;

            for(int i = year1; i < year2; ++i) {
                if((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                    timeDistance += 365;
                } else {
                    timeDistance += 366;
                }
            }

            var11 = timeDistance + (day2 - day1);
        } else {
            var11 = day2 - day1;
        }

        return Integer.valueOf(var11);
    }

    public static String getTime(Date date) {
        String todySDF = "今天 HH:mm";
        String yesterDaySDF = "昨天 HH:mm";
        String otherSDF = "yyyy年M月d日 HH:mm";
        SimpleDateFormat sfd = null;
        String time = "";
        Date now = new Date();
        Long intervalTime_seconds = Long.valueOf((now.getTime() - date.getTime()) / 1000L);
        Long intervalTime_minutes = Long.valueOf((now.getTime() - date.getTime()) / 60000L);
        Long intervalTime_hours = Long.valueOf((now.getTime() - date.getTime()) / 3600000L);
        if(intervalTime_seconds.longValue() < 60L) {
            time = intervalTime_seconds + "秒之前";
            return time;
        } else if(intervalTime_minutes.longValue() < 60L) {
            time = intervalTime_minutes + "分钟之前";
            return time;
        } else if(intervalTime_hours.longValue() < 24L) {
            time = intervalTime_hours + "小时之前";
            return time;
        } else {
            sfd = new SimpleDateFormat(otherSDF);
            time = sfd.format(date);
            return time;
        }
    }

    public static String getTimeForStr(Date date) {
        String todySDF = "今天 HH:mm";
        String yesterDaySDF = "昨天 HH:mm";
        String otherSDF = "yyyy年M月d日 HH:mm";
        SimpleDateFormat sfd = null;
        String time = "";
        Date now = new Date();
        Long intervalTime_seconds = Long.valueOf((now.getTime() - date.getTime()) / 1000L);
        Long intervalTime_minutes = Long.valueOf((now.getTime() - date.getTime()) / 60000L);
        Long intervalTime_hours = Long.valueOf((now.getTime() - date.getTime()) / 3600000L);
        if(intervalTime_seconds.longValue() <= 0L) {
            time = "刚刚";
            return time;
        } else if(intervalTime_seconds.longValue() < 60L) {
            time = intervalTime_seconds + "秒之前";
            return time;
        } else if(intervalTime_minutes.longValue() < 60L) {
            time = intervalTime_minutes + "分钟之前";
            return time;
        } else if(intervalTime_hours.longValue() < 24L) {
            time = intervalTime_hours + "小时之前";
            return time;
        } else {
            sfd = new SimpleDateFormat(otherSDF);
            time = sfd.format(date);
            return time;
        }
    }

    public static Integer DateToTimestamp(Date time) {
        Timestamp ts = new Timestamp(time.getTime());
        return Integer.valueOf((int)(ts.getTime() / 1000L));
    }

    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(5);
    }

    public static void main(String[] args) {
        System.out.println(DateToStr(subDate(new Date(), 7)));
    }
}
