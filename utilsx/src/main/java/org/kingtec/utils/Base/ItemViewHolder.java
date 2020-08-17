package org.kingtec.utils.Base;

import android.util.SparseArray;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import org.kingtec.utils.StringUtils;
import org.kingtec.utils.widget.togglebuttongroup.SingleSelectToggleGroup;
import org.kingtec.utils.widget.togglebuttongroup.ToggleButtonGroup;
import org.kingtec.utils.widget.togglebuttongroup.button.LabelToggle;

import java.util.List;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Visibility;


/**
 * Created by Ezz Ali on 12/03/2018.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mHeaderViews = new SparseArray<>();
    public View mView;
    public int type=0;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }
    public ItemViewHolder(@NonNull View itemView,int type ) {
        super(itemView);
        mView = itemView;
        this.type = type;
    }

    public <T> SingleSelectToggleGroup setChooser(int id,List<T> list, ToggleButtonGroup.Chooser<T> chooser
    ) {

        //    final Dialog dialog = new Dialog(this);


        SingleSelectToggleGroup single_selects = find(id);
        for (T o : list) {
            LabelToggle toggle = new LabelToggle(mView.getContext());
            toggle.setText(chooser.title(o));
            toggle.setTag(o);
            single_selects.addView(toggle);
        }
        single_selects.setOnCheckedChangeListener((group, checkedId) -> {
            String v = ((LabelToggle) group.findViewById(checkedId)).getText().toString();
            chooser.choosed((T) group.findViewById(checkedId).getTag(), v);
        });
        return single_selects;
    }
    public TextView setTextView(@IdRes int res, @NonNull String title) {

        try {
            TextView textView;
            textView = find(res);
            if (StringUtils.isValid(title)) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(title);
            }
            return textView;

        } catch (Exception ignored) {
            return null;
        }

    }

    public TextView setText(@IdRes int res, @NonNull String title) {

        try {
            TextView textView;
            textView = find(res);
            if (StringUtils.isValid(title)) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(title);
            }
            return textView;

        } catch (Exception ignored) {
            return null;
        }

    }

    public TextView setText(@IdRes int res, int title) {

        try {
            TextView textView;
            textView = find(res);
            if (StringUtils.isValid(String.valueOf(title))) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(title);
            }
            return textView;

        } catch (Exception ignored) {
            return null;
        }

    }

    public int getType() {
        return type;
    }
    public View hide(@IdRes int res) {

        try {
            View v = find(res);
            v.setVisibility(View.GONE);
            return v;

        } catch (Exception ignored) {
            return null;
        }

    }

    public View show(@IdRes int res) {

        try {
            View v = find(res);
            v.setVisibility(View.VISIBLE);
            return v;

        } catch (Exception ignored) {
            return null;
        }

    }
    public View setVisibility(@IdRes int res, int vis) {

        try {
            View v = find(res);
            v.setVisibility(vis);
            return v;

        } catch (Exception ignored) {
            return null;
        }

    }

    public WebView setWebView(@IdRes int res, @NonNull String title) {

        try {
            WebView textView;
            textView = find(res);
            textView.setVisibility(View.VISIBLE);
            if (StringUtils.isValid(title)) {

                textView.getSettings().setDefaultTextEncodingName("utf-8");
                textView.loadData("<html><head><meta charset='utf-8'/></head><body dir='rtl'>" + title + "</body></html>", "text/html; charset=utf-8", null);
            }
            return textView;

        } catch (Exception ignored) {
            return null;
        }

    }


//    public HtmlTextView setHtmlTextView(@IdRes int res,@NonNull  String title) {
//
//        try {
//            HtmlTextView textView;
//            textView = find(res);
//            textView.setVisibility(View.VISIBLE);
//            if(StringUtils.isValid(title)){
//
//                textView.setHtml(title,
//                        new HtmlResImageGetter(textView));            }
//            return textView;
//
//        }catch (Exception ignored){
//            return null;
//        }
//
//    }

    public ImageButton setImageButton(@IdRes int res, @NonNull String title) {

        try {
            ImageButton imageButton;
            imageButton = find(res);
            imageButton.setVisibility(View.VISIBLE);
            if (StringUtils.isValid(title)) {

            }
            return imageButton;

        } catch (Exception ignored) {
            return null;
        }

    }

    public Button setButton(@IdRes int res, @NonNull String title) {

        try {
            Button imageButton;
            imageButton = find(res);
            imageButton.setVisibility(View.VISIBLE);
            if (StringUtils.isValid(title)) {
                imageButton.setText(title);
            }
            return imageButton;

        } catch (Exception ignored) {
            return null;
        }

    }

    public void onObjectClicked(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }
    public void setOnClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }
    public void setOnLongClickListener(View.OnLongClickListener listener) {
        itemView.setOnLongClickListener(listener);
    }

    public <T extends View> T find(@IdRes int id) {
        View view = mHeaderViews.get(id);
        if (view == null) {
            view = itemView
                    .findViewById(id);
            mHeaderViews.put(id, view);
        }
        return (T) view;
    }

    public <T extends View> T onClicked(@IdRes int res, final View.OnClickListener listener) {
        T v = find(res);
        v.setOnClickListener(listener);
        return v;


    }

    public ImageView setImageView(@IdRes int res, @NonNull int title) {

        try {
            ImageView imageView;
            imageView = find(res);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(title);

            return imageView;

        } catch (Exception ignored) {
            return null;
        }

    }
//
//    public ImageView setImageView(@IdRes int res, @NonNull String url) {
//
//        try {
//            ImageView imageView;
//            imageView = find(res);
//            imageView.setVisibility(View.VISIBLE);
//
//            return imageView;
//
//        } catch (Exception ignored) {
//            return null;
//        }
//
//    }

    public ImageView getImageView(@IdRes int res) {

        try {
            ImageView imageView;
            imageView = find(res);
            imageView.setVisibility(View.VISIBLE);

            return imageView;

        } catch (Exception ignored) {
            return null;
        }

    }

    public View getView(@IdRes int res) {

        try {
            View imageView;
            imageView = find(res);
            imageView.setVisibility(View.VISIBLE);

            return imageView;

        } catch (Exception ignored) {
            return null;
        }

    }


}
