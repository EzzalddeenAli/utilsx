package org.kingtec.utils.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by 伟平 on 2015/10/16.
 */

public class MyGridView extends GridView {

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec;

        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {

            // The two leftmost bits in the height measure spec have
            // a special meaning, hence we can't use them to describe height.
            heightSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        } else {
            // Any other height should be respected as is.
            heightSpec = heightMeasureSpec;
        }

        super.onMeasure(widthMeasureSpec, heightSpec);
    }

    public MyGridView columns(int numColumns) {
        super.setNumColumns(numColumns);
        return this;
    }

    public MyGridView adapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        return this;
    }

    //    public MyGridView adapter(Context context, List<Tag> tags, boolean isD) {
//     //  super.setAdapter(adapter);
//        return this;
//    }
    int rowId = 0;

    public MyGridView row(int row) {
        this.rowId = row;
        return this;
    }
}
