package com.letsolve.oyster.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xavier.qiu
 * 5/28/18 8:04 PM
 */
public class DateUtil {


    public static final DateFormat df = new SimpleDateFormat("MMM/dd/yyyy");
    public static final DateFormat df_ = new SimpleDateFormat("yyyy-MM-dd");

    public static synchronized String toYYYYMMDD(Date date) {
        return df.format(date);
    }

    public static synchronized String toYYYY_MM_DD(Date date) {
        return df_.format(date);
    }
}
