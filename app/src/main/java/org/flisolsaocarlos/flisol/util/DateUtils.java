package org.flisolsaocarlos.flisol.util;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private final static String TAG = DateUtils.class.getName();

    public static String formatDateTime(Context context, String timeToFormat) {

        String finalDateTime = "";

        SimpleDateFormat iso8601Format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        Date date = null;
        if (timeToFormat != null) {
            try {
                date = iso8601Format.parse(timeToFormat);
            } catch (ParseException e) {
                Log.e(TAG, e.getMessage());
            }

            if (date != null) {
                long when = date.getTime();
                int flags = 0;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_TIME;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_DATE;
                flags |= android.text.format.DateUtils.FORMAT_ABBREV_MONTH;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_YEAR;

                finalDateTime = android.text.format.DateUtils.formatDateTime(context,
                        when + TimeZone.getDefault().getOffset(when), flags);
            }
        }
        return finalDateTime;
    }

    public static Date getDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}