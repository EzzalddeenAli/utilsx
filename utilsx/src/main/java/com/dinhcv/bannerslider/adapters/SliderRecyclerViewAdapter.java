package com.dinhcv.bannerslider.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.dinhcv.bannerslider.event.OnSlideClickListener;
import com.dinhcv.bannerslider.viewholder.ImageSlideViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by dinhcv on 2019-08-20.
 * Copyright (c) 2019 Pacom-Solution. All rights reserved.
 */
public class SliderRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ViewGroup.LayoutParams imageViewLayoutParams;
    private OnSlideClickListener onSlideClickListener;
    private SliderAdapter sliderAdapter;
    private boolean loop;
    private View.OnTouchListener itemOnTouchListener;
    private PositionController positionController;

    public SliderRecyclerViewAdapter(SliderAdapter iSliderAdapter, boolean loop, ViewGroup.LayoutParams imageViewLayoutParams, View.OnTouchListener itemOnTouchListener, PositionController positionController) {
        this.sliderAdapter = iSliderAdapter;
        this.imageViewLayoutParams = imageViewLayoutParams;
        this.loop = loop;
        this.itemOnTouchListener = itemOnTouchListener;
        this.positionController = positionController;
    }

    public void setOnSlideClickListener(OnSlideClickListener onSlideClickListener) {
        this.onSlideClickListener = onSlideClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return sliderAdapter.getViewHolder(  parent,  viewType,imageViewLayoutParams);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (!loop) {
            sliderAdapter.onBindImageSlide(position, (ImageSlideViewHolder) holder);
        } else {
            if (position == 0) {
                sliderAdapter.onBindImageSlide(positionController.getLastUserSlidePosition(), (ImageSlideViewHolder) holder);
            } else if (position == getItemCount() - 1) {
                sliderAdapter.onBindImageSlide(positionController.getFirstUserSlidePosition(), (ImageSlideViewHolder) holder);
            } else {
                sliderAdapter.onBindImageSlide(position - 1, (ImageSlideViewHolder) holder);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSlideClickListener != null)
                    onSlideClickListener.onSlideClick(positionController.getUserSlidePosition(holder.getAdapterPosition()));
            }
        });

        holder.itemView.setOnTouchListener(itemOnTouchListener);
    }

    @Override
    public int getItemCount() {
        return sliderAdapter.getItemCount() + (loop ? 2 : 0);
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }


}
