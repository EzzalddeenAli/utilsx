package org.kingtec.utils.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;


import com.github.EzzalddeenAli.utilsx.R;
import org.kingtec.utils.widget.togglebuttongroup.button.ToggleButton;

import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatToggleButton;

public class CustomButton extends AppCompatToggleButton implements ToggleButton {
    private static final int[] CHECKED_STATE_SET = { android.R.attr.state_checked };

    private boolean mChecked;
    private org.kingtec.utils.widget.togglebuttongroup.button.OnCheckedChangeListener mOnCheckedChangeListener;
    private boolean mBroadcasting;

    public CustomButton(Context context, AttributeSet attributeSet, int i) {
//        <com.org.kingtec.utils.ui.CustomButton android:textSize="@dimen/text_size_8"
//        android:background="@drawable/beverages_size_selector"
//        android:visibility="visible"
//        android:layout_width="@dimen/item_beverages_items_width"
//        android:layout_height="20.0dip"
//        android:drawablePadding="10.0dip"
//        android:textAllCaps="true"
//        android:fontFamily="@font/muli_bold"
//        android:paddingStart="10.0dip"
//        android:paddingEnd="10.0dip"
//        xmlns:android="http://schemas.android.com/apk/res/android" />
        super(context, attributeSet, R.style.borderlessBtnBevCart);
        setBackgroundResource(R.drawable.beverages_size_selector);


    }
    public CharSequence getText() {
        return getTextOn();
    }
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        mChecked = checked;
        if (checked) {

            refreshDrawableState();

            if (mBroadcasting) {
                return;
            }
            mBroadcasting = true;

            mBroadcasting = false;
            String str = "ar";
            boolean equalsIgnoreCase = Locale.getDefault().getLanguage().equalsIgnoreCase(str);
            int i = R.drawable.ic_tick;
            int i2 = equalsIgnoreCase ? R.drawable.ic_tick : 0;
            if (Locale.getDefault().getLanguage().equalsIgnoreCase(str)) {
                i = 0;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(i2, 0, i, 0);
            }
            setTextColor(Color.parseColor("#EB2C2C"));

        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            }
            setTextColor(Color.parseColor("#6B6B6B"));
        }
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, mChecked);
        }
        invalidate();
    }

    public void setText(String str) {
        super.setTextOff(str);
        super.setTextOn(str);
    }
    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs,R.style.borderlessBtnBevCart);
    }


    public CustomButton(Context context) {
        super(context);
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }


    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }


    @Override
    public void setOnCheckedChangeListener(org.kingtec.utils.widget.togglebuttongroup.button.OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }
}
