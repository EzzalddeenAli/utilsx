package org.kingtec.utils;

import java.util.regex.Pattern;

/**
 * Created by stefano on 18/05/2016.
 */
public class StringUtils {
    private static final String TAG = StringUtils.class.getName();


    public static String[] sort(String x, String y) {
//        Log.d(TAG, "sort");
        String[] results = new String[2];

        if (x.compareToIgnoreCase(y) <= 0) {
            results[0] = x;
            results[1] = y;
        } else {
            results[0] = y;
            results[1] = x;
        }

        return results;
    }

    public static boolean isValid(String string) {
//        Log.d(TAG, "isValid");
        return string != null && !string.trim().isEmpty() && !string.trim().equals("");
    }

    public static String getText(String string) {
//        Log.d(TAG, "isValid");
        return (isValid(string)) ? string : "";
    }

    public static String getDBText(String string) {
//        Log.d(TAG, "isValid");
        return (isValid(string)) ? string : "-";
    }

    public static String getTextOrNull(String string) {
//        Log.d(TAG, "isValid");
        return (isValid(string)) ? string : null;
    }

    public static String getText(String s1, String s2) {
//        Log.d(TAG, "isValid");
        return (isValid(s1)) ? s1 : (isValid(s2)) ? s2 : "";
    }

    /**
     * Replaces all the Firebase forbidden character with a custom string
     *
     * @param text      the text to normalize
     * @param toReplace the custom string
     * @return the normalized string
     */
    public static String removeFirebaseForbiddenChars(String text, String toReplace) {
//        Log.d(TAG, "removeFirebaseForbiddenChars");
        text = text.replace("https://firebasestorage.googleapis.com/", toReplace);
        text = text.replace(".", toReplace);
        text = text.replace("$", toReplace);
        text = text.replace("#", toReplace);
        text = text.replace("[", toReplace);
        text = text.replace("]", toReplace);
        text = text.replace("|", toReplace);
        text = text.replace("\"", toReplace);
        text = text.replace("/", toReplace);
        text = text.replace("&", toReplace);
        text = text.replace("=", toReplace);
        text = text.replace("?", toReplace);
        text = text.replace("@", toReplace);
        text = text.replace("'", toReplace);
        return text;
    }

    /**
     * Replaces all the Firebase forbidden character with a custom string
     *
     * @param text the text to normalize
     * @return the normalized string
     */
    public static String removeChars(String text) {
        return removeFirebaseForbiddenChars(text, "");
    }

    public static String removeChars(String text, String toReplace) {
        return removeFirebaseForbiddenChars(text, toReplace);
    }

    public static String[] splitByChar(String toSplit, String criteria) {
        return toSplit.split(criteria);
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        return pattern.matcher(email).matches();

    }

}