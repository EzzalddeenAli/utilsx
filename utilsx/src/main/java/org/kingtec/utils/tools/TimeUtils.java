package org.kingtec.utils.tools;
//import org.obsapp.utils.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.github.EzzalddeenAli.utilsx.R;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.Math.abs;

/**
 * Created by stefano on 05/09/2015.
 */
public class TimeUtils {
    private static final String TAG = TimeUtils.class.getName();


    public static String timeAgo(Date date) {
        return timeAgo(date.getTime());
    }

    public static String timeAgo(long millis) {
        String prefix = "منذ ";
        return timeAgo(millis,new Date().getTime(),prefix);
    }
    public static String timeAgo(int millis) {
        String prefix = "منذ ";
        Timestamp ts=new Timestamp(Long.valueOf(millis)*1000);
        return timeAgo(ts.getTime(),new Date().getTime(),prefix);
    }

    public static String timeAgo(String start,String end,String prefix) {
        return timeAgo(getTime(start),getTime(end),prefix);
    }
    public static String timeAgo(long millis,long end,String prefix) {
       if(millis==0||end==0)return "";
//       Long.valueOf(10);
        long diff =end - millis;


//		String prefix = "";
        String suffix = "";

        double seconds = abs(diff) / 1000;
        double minutes = seconds / 60;
        double hours = minutes / 60;
        double days = hours / 24;
        double years = days / 365;

        String words;

        if (seconds < 45) {
            words = "اقل من دقيقة";
        } else if (seconds < 90) {
            words = "دقيقة";
        } else if (minutes < 60) {
            words = Math.round(minutes) + " دقائق ";
        } else if (minutes < 90) {
            words = "ساعة";
        } else if (hours < 24) {
            words = Math.round(hours) + " ساعات ";
        } else if (hours < 42) {
            words = "يوم";
        } else if (days < 30) {
            words = Math.round(days) + " ايام ";
        } else if (days < 45) {
            words = "شهر";
        } else if (days < 365) {
            words = Math.round(days / 30) + " اشهر ";
        } else if (years < 1.5) {
            words = "سنة";
        } else {
            words = Math.round(years) + " سنوات ";
        }
        words = words.trim();
        StringBuilder sb = new StringBuilder();

        if (prefix != null && prefix.length() > 0) {
            sb.append(prefix).append(" ");
        }

        sb.append(words);

        if (suffix != null && suffix.length() > 0) {
            sb.append(" ").append(suffix);
        }

        return sb.toString().trim();
    }

    public static final long SECOND = 1000L;
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;

    /**
     * This method will take one long parameter, the time to be converted.
     * If the date is past two days, a formatted date is returned, the format is YYYY/MM/DD.
     * Will return an empty string if the time parameter is wrong.
     *
     * @param context   Context of the calling app, needed for localization.
     * @param time      The time (long) to be converted to string
     * @param separator Separator to be used between fields in the formatted date
     */
    public static String getTimeAgo(Context context, Date time, String separator) {
        return getTimeAgo(context, time.getTime(), separator);
    }

    public static int currentTimeMillis() {
        return (int)  System.currentTimeMillis() / 1000;
    }
    public static long currentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }
    public static String getTimeAgo(Context context, Date time) {
        return getTimeAgo(context, time.getTime(), "/");
    }

    public static String getTimeAgo(Context context, long time) {
        return getTimeAgo(context, time, "/");
    }

    public static String getTimeDif( long time) {

String separator=":";
        Calendar calendar = Calendar.getInstance();

        long currentTime = calendar.getTimeInMillis();

        if (time <= 0 || currentTime <= 0) {
            return "";
        }

        if (time > currentTime) {
            return "";
        }

        final double difference = currentTime - time;


        double mseconds = abs(difference) ;
        double seconds = mseconds / 1000;
        double minutes = seconds / 60;
        double hours = minutes / 60;
        double days = hours / 24;
            return ((hours>0)?hours:0)
                    + separator
                    + ((minutes>0)?minutes:0)
                    + separator
                    + ((seconds>0)?seconds:0)
                    + separator
                    + mseconds;


    }
    public static String getTimeAgo(Context context, long time, String separator) {

        Calendar calendar = Calendar.getInstance();

        long currentTime = calendar.getTimeInMillis();

        if (time <= 0 || currentTime <= 0) {
            return "";
        }

        if (time > currentTime) {
            return "";
        }

        final long difference = currentTime - time;

        if (difference < MINUTE) {

            return context.getString(R.string.momentsAgo);

        } else if (difference < (2 * MINUTE)) {

            return context.getString(R.string.oneMinuteAgo);

        } else if (difference < (3 * MINUTE)) {

            return context.getString(R.string.twoMinutesAgo);

        } else if (difference < (11 * MINUTE)) {

            return context.getString(R.string.since) + " "
                    + difference / MINUTE + " "
                    + context.getString(R.string.minutes);

        } else if (difference < HOUR) {

            return Locale.getDefault().getLanguage().equalsIgnoreCase("ar")
                    ? (context.getString(R.string.since) + " "
                    + difference / MINUTE + " "
                    + context.getString(R.string.minute))
                    : (context.getString(R.string.since)
                    + " " + difference / MINUTE + " "
                    + context.getString(R.string.minutes));

        } else if (difference < (2 * HOUR)) {

            return context.getString(R.string.hourAgo);

        } else if (difference < (3 * HOUR)) {

            return context.getString(R.string.twoHoursAgo);

        } else if (difference < (11 * HOUR)) {

            return context.getString(R.string.since) + " "
                    + difference / HOUR + " "
                    + context.getString(R.string.hours);

        } else if (difference < DAY) {

            return Locale.getDefault().getLanguage().equalsIgnoreCase("ar")
                    ? (context.getString(R.string.since) + " "
                    + difference / HOUR + " "
                    + context.getString(R.string.hour))
                    : context.getString(R.string.since) + " "
                    + difference / HOUR + " "
                    + context.getString(R.string.hours);

        } else if (difference < (2 * DAY)) {

            return context.getString(R.string.dayAgo);

        } else if (difference < (3 * DAY)) {

            return context.getString(R.string.twoDaysAgo);

        } else {

            calendar.setTimeInMillis(time);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return year
                    + separator
                    + (month + 1)
                    + separator
                    + day;
        }

    }
    @SuppressLint("SimpleDateFormat")
    public static String getFormattedDate(String timestamp) {
        try {
            Date date=((new SimpleDateFormat()).parse(timestamp));
            if (date != null) {
                return   getFormattedDate(date.getTime());
            }
        } catch (ParseException e) {
//            e.printStackTrace();
        }
        return  timestamp;
    }
    @SuppressLint("SimpleDateFormat")
    public static long getTime(String timestamp) {
        try {
            Date date=getDate(timestamp);
            if (date != null) {
                return   date.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  0;
    }
    @SuppressLint("SimpleDateFormat")
    public static Date getDate(String timestamp) {
        try {
            Date date=((new SimpleDateFormat()).parse(timestamp));
            if (date != null) {
                return   date;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    @SuppressLint("SimpleDateFormat")
    public static String getFormattedDate(Long timestamp) {
        Log.d(TAG, "getFormattedDate");

        Long currentTime = getCurrentTime(Locale.getDefault());
        long timeDiff = currentTime - timestamp;
        // 1 day = 86400000 millis
        long onedayInMillis = 86400000L;

        /*
         * if the difference between the current time and the timestamp is
         * 1 day or more than show the compete timestamp else show the timestamp as
         * HH:mm
         */
        DateFormat dateFormat;
        if (timeDiff <= onedayInMillis) {
            dateFormat = new SimpleDateFormat("hh:mm aaa");
        } else {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        }
        return dateFormat.format(timestamp);
    }

    // return the current time in millis
    private static Long getCurrentTime(Locale locale) {
        Log.d(TAG, "getCurrentTime");
        return Calendar.getInstance(locale).getTimeInMillis();
    }

    public static String fixPrettyTimeFutureMessage(String convertedTimestamp) {
        Log.d(TAG, "fixPrettyTimeFutureMessage");
        String fixedTimestamp = convertedTimestamp;
        if (convertedTimestamp.compareTo("fra poco") == 0) {
            fixedTimestamp = "adesso";
        }
        return fixedTimestamp;
    }

//    public static String getFormattedTimestamp(long timestampLong) {
//        Log.d(TAG, "getFormattedTimestamp");
//        PrettyTime p = new PrettyTime();
//        String timestamp =
//                p.format(new Date((timestampLong)));
//        timestamp = TimeUtils.fixPrettyTimeFutureMessage(timestamp);
//        return timestamp;
//    }

    // resolve Issue #38
    public static String timestampToHour(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public static String timestampToStrDate(long timestamp) {
        int currentYear = getCurrentYear();
        int timestampYear = getYearFromTimestamp(timestamp);

        // if it is the current year hide it, otherwise show it
        String pattern = "dd MMM yyyy";
        if (timestampYear == currentYear) {
            pattern = "dd MMM";
        }

        // timestamp date
        Date timestampDate = new Date(timestamp);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(timestampDate);
    }

    // returns the date of week for the date
    public static String getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    // check if a timestamp is the current date
    public static boolean isDateToday(long milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        Date getDate = calendar.getTime();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startDate = calendar.getTime();

        return getDate.compareTo(startDate) > 0;
    }

    // returns the year of the current date
    private static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    // returns the year from the timestamp
    private static int getYearFromTimestamp(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        return cal.get(Calendar.YEAR);
    }


    public static boolean isYesterday(long milliSeconds) {

        Calendar toCheck = Calendar.getInstance();
        toCheck.setTimeInMillis(milliSeconds); // your date

        Calendar yesterday = Calendar.getInstance(); // today
        yesterday.add(Calendar.DAY_OF_YEAR, -1); // yesterday

        return yesterday.get(Calendar.YEAR) == toCheck.get(Calendar.YEAR)
                && yesterday.get(Calendar.DAY_OF_YEAR) == toCheck.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isDateInCurrentWeek(long milliSeconds) {
        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);

        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTimeInMillis(milliSeconds);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);

        return week == targetWeek && year == targetYear;
    }

    public static String getFormattedTimestamp(Context context, long milliSeconds) {
        if (isDateToday(milliSeconds)) {
            return formatTimestamp(milliSeconds, "HH:mm");
        } else if (isYesterday(milliSeconds)) {
            return "yesterday";
        } else if (isDateInCurrentWeek(milliSeconds)) {
            return formatTimestamp(milliSeconds, "EEEE");
        } else {
            return formatTimestamp(milliSeconds, "dd/MM/yy");
        }
    }

    public static String formatTimestamp(Long timestamp, String pattern) {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(timestamp);
    }


}