package com.liyolomoko.attendeventappfrontend.conf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Leo on 4/25/2016.
 */
public class AppUtil {

    public static Date getDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date value = null;
        try {
            value = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }
}
