package org.kingtec.utils.x2c;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class J2C {

    public static void setContentView(Activity activity, View view) {

        if (view != null) {
            activity.setContentView(view);
        } else {
        }
    }


    public static View inflate(Context context, View layoutId, ViewGroup parent) {
        return inflate(context, layoutId, parent, parent != null);
    }

    public static View inflate(Context context, View layoutId, ViewGroup parent, boolean attach) {
        return inflate(LayoutInflater.from(context), layoutId, parent, attach);
    }

    public static View inflate(LayoutInflater inflater, View layoutId, ViewGroup parent) {
        return inflate(inflater, layoutId, parent, parent != null);
    }

    public static View inflate(LayoutInflater inflater, View layoutId, ViewGroup parent, boolean attach) {
        View view = getView(inflater.getContext(), layoutId);
        if (view != null) {
            if (parent != null && attach) {
                parent.addView(view);
            }
            return view;
        } return view;
    }

    public static View getView(Context context, View view) {
        return view;
    }
    private static class DefaultCreator implements IViewCreator {

        @Override
        public View createView(Context context) {
            return null;
        }
    }

    private static int generateGroupId(int layoutId) {
        return layoutId >> 24;
    }
}
