package com.rse.webservice.locket.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_2 = "dd/MM/yyyy";
    public static final String DATE_FORMAT_3 = "dd-MM-yyyy";
    public static final String DATE_FORMAT_4 = "yyyy/MM/dd";
    public static final String MONTH_FORMAT_ = "yyyy-MM";
    public static final String MONTH_FORMAT_1 = "MM/yyyy";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_2 = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_TIME_FORMAT_3 = "yyyyMMddHHmmss";
    public static final String DATE_TIME_FORMAT_4 = "HH:mm:ss - dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT_5 = "yyyyMMddHHmmss.SSS";
    public static final String DATE_TIME_FORMAT_6 = "yyyy-MM-dd HH:mm:ss.SSSSSS";
    public static final String DATE_TIME_FORMAT_7 = "MM/dd/yyyy HH:mm:ss";
    public static final String DATE_TIME_FORMAT_8 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_TIME_FORMAT_9 = "EEE, dd MMM yyyy HH:mm:ss";
    public static final String DATE_TIME_FORMAT_10 = "dd-MM-yyyy HH:mm:ss";
    public static final String DATE_TIME_FORMAT_11 = "dd-MMM-yyyy HH:mm:ss";
    public static final String TIME_ZONE = "Asia/Ho_Chi_Minh";
    public static final String UTC = "UTC";

    public static final String DATE_PATTERN_DDMMYYYY = "ddMMYYYY";

    public static final String DATE_PATTERN_HHMMSS = "HH:mm:ss";

    public static final String DATE_PATTERN_YYYY = "YYYY";

    public static final String DATE_PATTERN_MM = "MM";

    public static String format(Date date) {
        String pattern = DATE_TIME_FORMAT_10 + " 'UTC'";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone(UTC));
        return sdf.format(date);
    }
}
