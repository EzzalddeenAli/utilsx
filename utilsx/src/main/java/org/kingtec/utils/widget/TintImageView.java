package org.kingtec.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;

import org.kingtec.utils.R;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by ezzaldeen  Dev. Team.
 */
public class TintImageView extends AppCompatImageView {

    private int TintColor;

    public TintImageView(Context context) {
        super(context);

    }

    public TintImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public TintImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.com_kingtec_utils_TintImageView_CusTint);
        TintColor = a.getInteger(R.styleable.com_kingtec_utils_TintImageView_CusTint_tintVal, 0);

        setColorFilter(TintColor, PorterDuff.Mode.SRC_ATOP);

        if (a.getBoolean(R.styleable.com_kingtec_utils_TintImageView_CusTint_haveBorder, false)) {
            GradientDrawable gd = new GradientDrawable();
            gd.setCornerRadius(Concurrent.getTintImageStrokeRadius(context, 50));
            gd.setStroke(Concurrent.getTintImageStrokeWidth(context, 2), TintColor);
            setPadding(Concurrent.getTintImagePadding(context, 7), Concurrent.getTintImagePadding(context, 7), Concurrent.getTintImagePadding(context, 7), Concurrent.getTintImagePadding(context, 7));

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                setBackgroundDrawable(gd);
            } else {
                setBackground(gd);
            }
        }
    }

}
