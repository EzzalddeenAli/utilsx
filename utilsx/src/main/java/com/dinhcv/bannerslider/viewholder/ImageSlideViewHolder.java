package com.dinhcv.bannerslider.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.dinhcv.bannerslider.Slider;

import org.kingtec.utils.Base.ItemViewHolder;

import androidx.annotation.DrawableRes;

public class ImageSlideViewHolder extends ItemViewHolder {
    public ImageView imageView;

    public ImageSlideViewHolder(View itemView) {
        super(itemView);
        this.imageView =  Slider.getImageLoadingService().getImage(this);
    }

    public void bindImageSlide(String imageUrl) {
        if (imageUrl != null) {
            Slider.getImageLoadingService().loadImage(imageUrl, imageView);
        }
    }

    public void bindImageSlide(@DrawableRes int imageResourceId) {
        Slider.getImageLoadingService().loadImage(imageResourceId, imageView);
    }

    public void bindImageSlide(String url, @DrawableRes int placeHolder, @DrawableRes int error) {
        Slider.getImageLoadingService().loadImage(url, placeHolder, error, imageView);
    }

}