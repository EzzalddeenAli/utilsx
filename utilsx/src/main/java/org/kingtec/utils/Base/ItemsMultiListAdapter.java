package org.kingtec.utils.Base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public abstract class ItemsMultiListAdapter<SingleItem extends Object> extends ItemsListAdapter<SingleItem>
 {
     public ItemsMultiListAdapter(List mList, int res, ListAdapterListener listener) {
         super(mList, res, listener);
     }

     public ItemsMultiListAdapter(List mList, int res) {
         super(mList, res);
     }
//     @SuppressLint("SetTextI18n")
//     @Override
//     public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
//super.onBindViewHolder(holder,position);
//     }
     @SuppressLint("ResourceType")
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(getTypeRes(viewType), parent, false);
        return new ItemViewHolder(view,viewType);
    }
     @Override
    public int getItemViewType(int i2) {
        return getItemViewType(this.mList.get(i2));
    }
    public abstract int getItemViewType(SingleItem i2) ;
    public abstract int getTypeRes(int type) ;
}