package org.kingtec.utils.Base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Set;

public class Concurrent {
    public static String APP_TOKEN;
    public static String APP_SCHOOL_NAME;
    public static Boolean APP_SECTION_ENABLED;
    public static HashMap<String, String> LanguageHolder = new HashMap<>();
    public static String APP_USERNAME;
    public static String APP_PASS;
    public static Integer APP_USER_ID = 0;
    public static Integer APP_ROLE;
    public static Integer APP_ROLE_ADMIN = 1;
    public static Integer APP_ROLE_TEACHER = 2;
    public static Integer APP_ROLE_STUDENT = 3;
    public static Integer APP_ROLE_PARENT = 4;
    public static boolean AttendanceModelIsClass;
    public static String LangDirection;
    public static Integer TintImageStrokeWidth;
    public static Float TintImageStrokeRadius;
    public static Integer TintImagePadding;
    public static String DateFormat = "dd-mm-yyyy";
    private static JsonElement tagObject;
    private static int tagIntValue;
    private static String tagStringValue;
    private static boolean externalStorageWritable;
    private static SharedPreferences Prefs;
    public static String TDate;
    public static float refreshInMessagesInterval = -1; // in milliseconds
    public static float refreshOutMessagesInterval = -1; // in milliseconds
    public static String[] WeekDaysNames = new String[7];// first item is first day name in week and second is second ...

    public static float getRefreshInMessagesInterval(Context cntx) {
        if (refreshInMessagesInterval == -1)
            refreshInMessagesInterval = PreferenceManager.getDefaultSharedPreferences(cntx).getFloat("refresh_in_messages_interval", 2000);
        return refreshInMessagesInterval;
    }

    public static float getRefreshOutMessagesInterval(Context cntx) {
        if (refreshOutMessagesInterval == -1)
            refreshOutMessagesInterval = PreferenceManager.getDefaultSharedPreferences(cntx).getFloat("refresh_out_messages_interval", 600000);
        return refreshOutMessagesInterval;
    }

    public static void setRefreshInMessagesInterval(Context cntx, Float timeInSeconds) {
        Float timeInMilliseconds = timeInSeconds * 1000;
        PreferenceManager.getDefaultSharedPreferences(cntx).edit().putFloat("refresh_in_messages_interval", timeInMilliseconds).apply();
        refreshInMessagesInterval = timeInMilliseconds;
    }

    public static void setRefreshOutMessagesInterval(Context cntx, Float timeInSeconds) {
        Float timeInMilliseconds = timeInSeconds * 1000;
        PreferenceManager.getDefaultSharedPreferences(cntx).edit().putFloat("refresh_out_messages_interval", timeInMilliseconds).apply();
        refreshOutMessagesInterval = timeInMilliseconds;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static Integer getTintImageStrokeWidth(Context context, Integer value) {
        if (TintImageStrokeWidth == null) {
            TintImageStrokeWidth = (int) convertDpToPixel(Float.valueOf(value), context);
        }
        return TintImageStrokeWidth;
    }

    public static Float getTintImageStrokeRadius(Context context, Integer value) {
        if (TintImageStrokeRadius == null) {
            TintImageStrokeRadius = convertDpToPixel(Float.valueOf(value), context);
        }
        return TintImageStrokeRadius;
    }

    public static Integer getTintImagePadding(Context context, Integer value) {
        if (TintImagePadding == null) {
            TintImagePadding = (int) convertDpToPixel(Float.valueOf(value), context);
        }
        return TintImagePadding;
    }

    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        /*if (version >= 23) {
            return ContextCompat.getColor(context,id);
        } else {*/
        return context.getResources().getColor(id);
        //}
    }

    public static Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
       /* if (version >= 23) {
            return ContextCompat.getDrawable(context,id);
        } else {*/
        return context.getResources().getDrawable(id);
        // }
    }

    public static String tagsStringValidator(JsonObject tagHolder, String tagName) {
        if (tagHolder != null) {
            try {
                tagObject = tagHolder.get(tagName);
            } catch (Exception e) {
                return "";
            }
            if (tagObject != null && !tagObject.isJsonNull()) {
                tagStringValue = tagObject.getAsString();
                if (tagStringValue != null && !tagStringValue.equals(""))
                    return repairJsonValueQuotes(tagStringValue);
            }
        }
        return "";
    }

    public static String getLangDirection(Context mContext) {
        if (LangDirection == null) {
            SharedPreferences Prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
            String langDir = Prefs.getString("lang_direction", null);
            LangDirection = langDir;
        }
        if (LangDirection == null) {
            return "en";
        } else {
            return "en";
        }
    }

    public static int tagsIntValidator(JsonObject tagHolder, String tagName) {
        if (tagHolder != null) {
            try {
                tagObject = tagHolder.get(tagName);
            } catch (Exception e) {
                return 0;
            }
            if (tagObject != null && !tagObject.isJsonNull()) {
                try {
                    tagIntValue = tagObject.getAsInt();
                    if (tagIntValue != 0) return tagIntValue;
                } catch (Exception ignored) {
                    return 0;
                }
            }
        }
        return 0;
    }

    public static void setLangWords(Context context, TextView... views) {
        if (LanguageHolder != null) {
            for (TextView view : views) {
                /*if (LangMap.containsKey(view.hashCode())){
                    view.setText(LangMap.get(view.hashCode()));
                }else {*/
                if (view != null) {
                    String id = context.getResources().getResourceEntryName(view.getId()).replace("_", "");

                    String nameTrans = null;
                    try {
                        nameTrans = LanguageHolder.get(id.toLowerCase());
                    } catch (Exception ignored) {
                    }

                    if (nameTrans != null) {
                        String nameValue = CapsFirst(nameTrans);
                        view.setText(nameValue);
                    }
                }

                //LangMap.put(view.hashCode(), nameValue);
                //}
            }
        }
    }

    public static String getLangSubWords(String findName, String word) {
        if (LanguageHolder != null) {
            findName = findName.toLowerCase().replace(" ", "");
            String nameValue = null;
            try {
                nameValue = LanguageHolder.get(findName);
            } catch (Exception ignored) {
            }

            if (nameValue != null)
                return CapsFirst(nameValue);
            else
                return word;
        }
        return word;
    }

    private static String CapsFirst(String str) {
        String[] words = str.split(" ");
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            ret.append(Character.toUpperCase(words[i].charAt(0)));
            ret.append(words[i].substring(1));
            if (i < words.length - 1) {
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    public static Integer getScreenWidth(Activity activity) {
        Prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        int width = Prefs.getInt("screenWidth", 0);
        if (width == 0) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            Prefs.edit().putInt("screenWidth", width).apply();
        }
        return width;
    }

    public static final String accessFunc(final String s) {

        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static JsonObject getJsonObject(JsonObject tagHolder, String tagName) {
        JsonElement JsonElem = tagHolder.get(tagName);
        if (JsonElem != null) {
            try {
                return JsonElem.getAsJsonObject();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static JsonArray getJsonArray(JsonObject tagHolder, String tagName) {
        if (tagHolder != null) {
            JsonElement JsonElem = tagHolder.get(tagName);
            if (JsonElem != null) {
                try {
                    return JsonElem.getAsJsonArray();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    public static String repairJsonValueQuotes(String value) {
        if (value != null) {
            String firstChar = String.valueOf(value.charAt(0));
            String lastChar = value.substring(value.length() - 1);

            if (firstChar.equals("\"")) value = value.substring(1);
            if (lastChar.equals("\"")) value = value.substring(0, value.length() - 1);
        }
        return value;
    }

    public static void setAppRole(String APP_ROLE_NAME) {
        switch (APP_ROLE_NAME) {
            case "admin":
                APP_ROLE = APP_ROLE_ADMIN;
                break;
            case "teacher":
                APP_ROLE = APP_ROLE_TEACHER;
                break;
            case "student":
                APP_ROLE = APP_ROLE_STUDENT;
                break;
            case "parent":
                APP_ROLE = APP_ROLE_PARENT;
                break;
            default:
                APP_ROLE = APP_ROLE_STUDENT;
                break;
        }
    }

    public static Integer getAppRole(Context cntx) {
        if (APP_ROLE == null || APP_ROLE == 0) {
            String APP_ROLE_NAME = PreferenceManager.getDefaultSharedPreferences(cntx).getString("app_user_role", null);
            switch (APP_ROLE_NAME) {
                case "admin":
                    APP_ROLE = APP_ROLE_ADMIN;
                    break;
                case "teacher":
                    APP_ROLE = APP_ROLE_TEACHER;
                    break;
                case "student":
                    APP_ROLE = APP_ROLE_STUDENT;
                    break;
                case "parent":
                    APP_ROLE = APP_ROLE_PARENT;
                    break;
                default:
                    APP_ROLE = APP_ROLE_STUDENT;
                    break;
            }
        }
        return APP_ROLE;
    }

    public static String getAppToken(Context cntx) {
        if (APP_TOKEN == null || APP_TOKEN.equals(""))
            APP_TOKEN = PreferenceManager.getDefaultSharedPreferences(cntx).getString("app_token", null);
        APP_TOKEN = PreferenceManager.getDefaultSharedPreferences(cntx).getString("app_token2", null);

        return PreferenceManager.getDefaultSharedPreferences(cntx).getString("app_token2", null);
    }

    public static String getSchoolName(Context cntx) {
        if (APP_SCHOOL_NAME == null || APP_SCHOOL_NAME.equals(""))
            APP_SCHOOL_NAME = PreferenceManager.getDefaultSharedPreferences(cntx).getString("school_name", null);

        return APP_SCHOOL_NAME;
    }

    public static Boolean isSectionEnabled(Context cntx) {
        if (APP_SECTION_ENABLED == null)
            APP_SECTION_ENABLED = PreferenceManager.getDefaultSharedPreferences(cntx).getBoolean("school_section_state", true);
        return APP_SECTION_ENABLED;
    }

    public static void setSectionState(Context cntx, Boolean sectionState) {
        PreferenceManager.getDefaultSharedPreferences(cntx).edit().putBoolean("school_section_state", sectionState).apply();
    }

    public static void setSchoolName(Context cntx, String SchoolName) {
        PreferenceManager.getDefaultSharedPreferences(cntx).edit().putString("school_name", SchoolName).apply();
    }

    public static String getAppUsername(Context cntx) {
        if (APP_USERNAME == null || APP_USERNAME.equals(""))
            APP_USERNAME = PreferenceManager.getDefaultSharedPreferences(cntx).getString("app_username", null);

        return APP_USERNAME;
    }

    public static Integer getAppUserID(Context cntx) {
        if (APP_USER_ID == null || APP_USER_ID == 0)
            APP_USER_ID = PreferenceManager.getDefaultSharedPreferences(cntx).getInt("app_user_id", 0);

        return APP_USER_ID;
    }

    public static String getAppPassword(Context cntx) {
        if (APP_PASS == null || APP_PASS.equals(""))
            APP_PASS = PreferenceManager.getDefaultSharedPreferences(cntx).getString("app_password", null);

        return APP_PASS;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            externalStorageWritable = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            externalStorageWritable = false;
        }
        return externalStorageWritable;
    }

 /*public static String getDateString(Instant instant) {
        JavaDateInstant valCarry = ((JavaDateInstant) instant);
        int month = valCarry.getMonthOfYear() + 2;
        int day = valCarry.getDayOfMonth();
        String stringMonth;
        String stringDay;
        String stringYear;

        if (month <= 9) stringMonth = "0" + month;
        else stringMonth = "" + month;

        if (day <= 9) stringDay = "0" + day;
        else stringDay = "" + day;

        stringYear = "" + valCarry.getYear();

        if (Concurrent.DateFormat.equals("d/m/Y")) {
            return (stringDay + "/" + stringMonth + "/" + stringYear);
        } else {
            return (stringMonth + "/" + stringDay + "/" + stringYear);

        }
    }*/


    public static Object convertStringToJson(String Response) {
        JsonParser parser = new JsonParser();
        JsonObject objectResponse;
        JsonArray arrayResponse;
        try {
            arrayResponse = parser.parse(Response).getAsJsonArray();
            return arrayResponse;
        } catch (Exception e) {
            try {
                objectResponse = parser.parse(Response).getAsJsonObject();
                return objectResponse;
            } catch (Exception e2) {
                return null;
            }
        }
    }


    public static boolean isModuleActivated(Context mContext, String moduleName) {
        SharedPreferences Prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        Set<String> modules = Prefs.getStringSet("active_modules", null);
        if (modules != null && modules.size() > 0) {
            return modules.contains(moduleName);
        } else {
            return true;
        }
    }


    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}
