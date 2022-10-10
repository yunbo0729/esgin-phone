package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * @mes   时间戳转标准日期格式
     * @param seconds
     * @param format
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str
     *            字符串日期
     * @param format
     *            如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param Date方式
     *
     */
    public static String getNewDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * @param
     * */
    public static String getNewDate2() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * @mes Calendar的简单使用
     * */
    public static void calendarUtil() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("年：" + calendar.get(calendar.YEAR));
        System.out.println("月：" + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("日：" + calendar.get(Calendar.DATE));
        /**
         * 获取时分秒
         */
        // 24小时制
        System.out.println("时：" + calendar.get(Calendar.HOUR_OF_DAY));
        // 12小时制
        // System.out.println(calendar.get(Calendar.HOUR));
        System.out.println("分：" + calendar.get(Calendar.MINUTE));
        System.out.println("秒：" + calendar.get(Calendar.SECOND));

        /**
         * 这一年的第几天,这个月的第几天，这周的第几天
         */
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));

        /**
         * 得到时间，Fri Aug 19 14:33:03 CST 2016 得到本周第一天 得到时间的毫秒数
         */
        System.out.println(calendar.getTime());
        System.out.println(calendar.getFirstDayOfWeek());
        System.out.println(calendar.getTimeInMillis());

        // System.out.println(calendar.compareTo(anotherCalendar));


        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(new Date());
        try {
            Date date =  sdf.parse(s);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&"+date);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

    }

    public static void main(String[] args) {
        calendarUtil();
        System.out.println(getNewDate2());
        System.exit(-1);

        System.out.println(timeStamp2Date(
                (System.currentTimeMillis() + "").substring(0, 10),
                "yyyy-MM-dd HH:mm:ss"));
        System.exit(-1);
    }
}
