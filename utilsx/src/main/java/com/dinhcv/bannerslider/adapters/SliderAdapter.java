package com.dinhcv.bannerslider.adapters;

import android.view.ViewGroup;

import com.dinhcv.bannerslider.SlideType;
import com.dinhcv.bannerslider.viewholder.ImageSlideViewHolder;

import androidx.annotation.NonNull;

/**
 * Created by dinhcv on 2019-08-20.
 * Copyright (c) 2019 Pacom-Solution. All rights reserved.
 */

public abstract class SliderAdapter {
    public abstract int getItemCount();
    public abstract   ImageSlideViewHolder getViewHolder(@NonNull ViewGroup parent, int viewType, ViewGroup.LayoutParams imageViewLayoutParams);

    public final SlideType getSlideType(int position) {
        return SlideType.IMAGE;
    }

    public abstract void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder);
}
