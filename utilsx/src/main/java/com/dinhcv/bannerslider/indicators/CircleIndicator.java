package com.dinhcv.bannerslider.indicators;

import android.content.Context;
import android.os.Build;
import com.github.EzzalddeenAli.utilsx.R;


import androidx.core.content.res.ResourcesCompat;


/**
 * Created by dinhcv on 2019-08-20.
 * Copyright (c) 2019 Pacom-Solution. All rights reserved.
 */

public class CircleIndicator extends IndicatorShape {

    public CircleIndicator(Context context, int indicatorSize, boolean mustAnimateChanges) {
        super(context, indicatorSize, mustAnimateChanges);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_circle_unselected, null));
        } else {
            setBackgroundDrawable(getResources().getDrawable(R.drawable.indicator_circle_unselected));
        }
    }

    @Override
    public void onCheckedChange(boolean isChecked) {
        super.onCheckedChange(isChecked);
        if (isChecked) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_circle_selected, null));
            } else {
                setBackgroundDrawable(getResources().getDrawable(R.drawable.indicator_circle_selected));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_circle_unselected, null));
            } else {
                setBackgroundDrawable(getResources().getDrawable(R.drawable.indicator_circle_unselected));
            }
        }
    }
}
