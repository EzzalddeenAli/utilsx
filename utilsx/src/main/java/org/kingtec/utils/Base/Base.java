package org.kingtec.utils.Base;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import org.kingtec.utils.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Base {

    public static boolean isValid(String text) {
        return StringUtils.isValid(text);
    }

    public static boolean isValid(Object object) {
        return object != null;
    }

    public static String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static String getText(String text) {
        return StringUtils.getText(text);
    }

    public static String getDBText(String text) {
        return StringUtils.getDBText(text);
    }

    public static Date getDate(String stringData) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
        return format.parse(stringData);
    }

    public Base(Context context) {
        mContext = context;
    }

    Context mContext;
    private static final int CODE_REQUEST_PERMISSION = 1;
    private static final String mypreference = "unvapp";
    SharedPreferences sharedPref;

    protected SharedPreferences getSharedPref() {
        sharedPref = mContext.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        return mContext.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
    }

    /////////////// SharedPreferences ///////////////
    /////////////// //////////////// ///////////////
    protected boolean initAuth() {
        return true;
    }

}
