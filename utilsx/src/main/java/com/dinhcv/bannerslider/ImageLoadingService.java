package com.dinhcv.bannerslider;

import android.widget.ImageView;

import com.dinhcv.bannerslider.viewholder.ImageSlideViewHolder;

import androidx.annotation.DrawableRes;

/**
 * Created by dinhcv on 2019-08-20.
 * Copyright (c) 2019 Pacom-Solution. All rights reserved.
 */

public interface ImageLoadingService {
    ImageView getImage(ImageSlideViewHolder holder);
//    ImageSlideViewHolder getViewHolder(@NonNull ViewGroup parent, int viewType, ViewGroup.LayoutParams imageViewLayoutParams);
    void loadImage(String url, ImageView imageView);

    void loadImage(@DrawableRes int resource, ImageView imageView);

    void loadImage(String url, @DrawableRes int placeHolder, @DrawableRes int errorDrawable, ImageView imageView);
}
