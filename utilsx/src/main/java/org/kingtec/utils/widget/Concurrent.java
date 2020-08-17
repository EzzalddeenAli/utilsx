package org.kingtec.utils.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.google.gson.JsonElement;

public class Concurrent {

    public static Integer TintImageStrokeWidth;
    public static Float TintImageStrokeRadius;
    public static Integer TintImagePadding;
    public static String DateFormat = "yyyy-mm-dd";
    private static JsonElement tagObject;
    private static int tagIntValue;
    private static String tagStringValue;
    private static boolean externalStorageWritable;

    public static String TDate;
    public static float refreshInMessagesInterval = -1; // in milliseconds
    public static float refreshOutMessagesInterval = -1; // in milliseconds
    public static String[] WeekDaysNames = new String[7];// first item is first day name in week and second is second ...


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

    public static Drawable getDrawable(Context context, @DrawableRes int id) {
        final int version = Build.VERSION.SDK_INT;
       /* if (version >= 23) {
            return ContextCompat.getDrawable(context,id);
        } else {*/
        return context.getResources().getDrawable(id);
        // }
    }

    public static void setLangWords(Context context, TextView... views) {
//        if (LanguageHolder != null) {
//            for (TextView view : views) {
//                /*if (LangMap.containsKey(view.hashCode())){
//                    view.setText(LangMap.get(view.hashCode()));
//                }else {*/
//                if (view != null) {
//                    String id = context.getResources().getResourceEntryName(view.getId()).replace("_", "");
//
//                    String nameTrans = null;
//                    try {
//                        nameTrans = LanguageHolder.get(id.toLowerCase());
//                    } catch (Exception ignored) {
//                    }
//
//                    if (nameTrans != null) {
//                        String nameValue = CapsFirst(nameTrans);
//                        view.setText(nameValue);
//                    }
//                }
//
//                //LangMap.put(view.hashCode(), nameValue);
//                //}
//            }
//        }
    }

}
