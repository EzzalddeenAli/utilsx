package org.kingtec.utils.Base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


abstract public class ItemRender<SingleItem extends ListItemType> extends AbsViewRenderer<SingleItem, ItemViewHolder> {

    public ItemRender(int type, int res) {
        super(type, res);
    }

    @Override
    public ItemViewHolder createViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(getRes(), parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    abstract public void bindView(SingleItem model, ItemViewHolder viewHolder, int pos);


}