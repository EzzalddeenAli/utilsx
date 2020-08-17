package org.kingtec.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Spannable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Mohammad Reza Eram  on 20/01/2018.
 */
import com.github.EzzalddeenAli.utilsx.R;
public class GuideButtonsView extends LinearLayout {

    private Paint mPaint;
    private RectF mRect;


    public GuideButtonsView(Context context) {
        super(context);

        float density = context.getResources().getDisplayMetrics().density;
        setWillNotDraw(false);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        mRect = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        final int padding = (int) (10 * density);
        final int paddingBetween = (int) (3 * density);

        mNext = new Button(context);
        mNext.setPadding(padding, padding, padding, paddingBetween);
        mNext.setGravity(Gravity.CENTER);
        mNext.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        mNext.setTextColor(Color.BLACK);
        addView(mNext, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        mClose = new Button(context);
        mClose.setTextColor(Color.BLACK);
        mClose.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        mClose.setPadding(padding, paddingBetween, padding, padding);
        mClose.setGravity(Gravity.CENTER);
        addView(mClose, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    public void setContentSpan(Spannable content) {
        mClose.setText(content);
    }

    public void setContentTypeFace(Typeface typeFace) {
        mClose.setTypeface(typeFace);
    }

    public void setTitleTypeFace(Typeface typeFace) {
        mNext.setTypeface(typeFace);
    }

    public void setTitleTextSize(int size) {
        mNext.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setContentTextSize(int size) {
        mClose.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    private Button mNext;
    private Button mClose;

    public void setCloseClick(OnClickListener c) {
        mClose.setOnClickListener(c);
    }

    public void setNextClick(OnClickListener c) {
        mNext.setOnClickListener(c);
    }

    public void setNextTitle(String title) {
        if (title == null) {
            removeView(mNext);
            return;
        }
        mNext.setText(title);
    }


    public void setCloseText(String content) {
        mClose.setText(content);
    }

    public void setColor(int color) {

        mPaint.setAlpha(255);
        mPaint.setColor(color);

        invalidate();
    }

    int[] location = new int[2];

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        this.getLocationOnScreen(location);


        mRect.set(getPaddingLeft(),
                getPaddingTop(),
                getWidth() - getPaddingRight(),
                getHeight() - getPaddingBottom());


        canvas.drawRoundRect(mRect, 15, 15, mPaint);
    }
}
