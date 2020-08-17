package org.kingtec.utils.Base;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public abstract class AbsViewRenderer<M extends ListItemType, VH extends ItemViewHolder> {

    @NonNull
    private Context context;

    @NonNull
    private int type;
    @NonNull
    private int res;

    public AbsViewRenderer(@NonNull int type, @NonNull int res) {
        this.type = type;
        this.res = res;
    }

    public void setContext(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    public Context getContext() {
        return context;
    }

    @NonNull
    public int getType() {
        return type;
    }

    @NonNull
    public int getRes() {
        return res;
    }

    // create viewholder
    public abstract VH createViewHolder(ViewGroup parent);

    // bind model to viewholder
    public abstract void bindView(M object, ItemViewHolder holder, int position);
}