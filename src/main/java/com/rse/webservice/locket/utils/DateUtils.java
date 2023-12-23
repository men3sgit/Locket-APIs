package com.rse.webservice.locket.utils;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT2 = "dd/MM/yyyy";
    public static final String DATE_FORMAT3 = "dd-MM-yyyy";
    public static final String DATE_FORMAT4 = "yyyy/MM/dd";
    public static final String MONTH_FORMAT = "yyyy-MM";
    public static final String MONTH_FORMAT1 = "MM/yyyy";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT2 = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_TIME_FORMAT3 = "yyyyMMddHHmmss";
    public static final String DATE_TIME_FORMAT4 = "HH:mm:ss - dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT5 = "yyyyMMddHHmmss.SSS";
    public static final String DATE_TIME_FORMAT6 = "yyyy-MM-dd HH:mm:ss.SSSSSS";
    public static final String DATE_TIME_FORMAT7 = "MM/dd/yyyy HH:mm:ss";
    public static final String DATE_TIME_FORMAT8 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_TIME_FORMAT9 = "EEE, dd MMM yyyy HH:mm:ss";
    public final String TIME_ZONE = "Asia/Ho_Chi_Minh";
    public final String UTC = "UTC";

    public static final String DATE_PATTERN_DDMMYYYY = "ddMMYYYY";

    public static final String DATE_PATTERN_HHMMSS = "HH:mm:ss";

    public static final String DATE_PATTERN_YYYY = "YYYY";

    public static final String DATE_PATTERN_MM = "MM";

    public static String format(Date date) {
        String pattern = "dd-MM-yyyy hh:mm:ss 'UTC'";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }
}
