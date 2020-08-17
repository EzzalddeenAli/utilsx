package org.kingtec.utils.Base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.kingtec.utils.StringUtils;

import java.util.Objects;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


public  class BaseStatic0Fragment extends Fragment {
    protected ConnectionDetector cd;
    private SparseArray<View> mHeaderViews = new SparseArray<>();
    public <T extends View> T find(@IdRes int id) {
        View view = mHeaderViews.get(id);
        if (view == null) {
            view = getfView()
                    .findViewById(id);
            mHeaderViews.put(id, view);
        }
        return (T) view;
    }
    public View findView(@IdRes int id) {
        return find(id);
    }

    public TextView findTextView(@IdRes int id) {
        return find(id);
    }
    public ImageView findImage(@IdRes int id) {
        return find(id);
    }public ImageView findImageView(@IdRes int id) {
        return find(id);
    }

    public Button findButton(@IdRes int id) {
        return find(id);
    }
    public RecyclerView findRecyclerView(@IdRes int id) {
        return find(id);
    }
    public EditText findEditText(@IdRes int id) {
        return find(id);
    }

    public <T extends View> T onClicked(@IdRes int res, final View.OnClickListener listener) {
        T v = find(res);
        v.setOnClickListener(listener);
        return v;

    }

    public <T extends View> T onClick(@IdRes int res, final View.OnClickListener listener) {
        T v = find(res);
        v.setOnClickListener(listener);
        return v;


    }
    public View click(@IdRes int res, final View.OnClickListener listener) {
        View v = find(res);
        v.setOnClickListener(listener);
        return v;


    }
    public <T extends View> T onLongClick(@IdRes int res, final View.OnLongClickListener listener) {
        T v = find(res);
        v.setOnLongClickListener(listener);
        return v;


    }
    public  View longClick(@IdRes int res, final View.OnLongClickListener listener) {
        View v = find(res);
        v.setOnLongClickListener(listener);
        return v;


    }

    @Nullable
    public String getArg(@Nullable String string) {
        return getArg(string, "");
    }
    public void runOnUiThread( Runnable action) {
       if(   getActivity()!=null)
         getActivity().runOnUiThread(action);
    }

    @Nullable
    public String getArg(@Nullable String string, @Nullable String d) {

        if (getArguments() != null) {
            return getArguments().getString(string, d);
        }
        return d;
    }

    public ImageView setImageView(@IdRes int res, @NonNull int title) {

        try {
            ImageView imageView;
            imageView = findViewById(res);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(title);

            return imageView;

        } catch (Exception ignored) {
            return null;
        }

    }

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

    public <T extends View> T getView(@IdRes int res) {
        return find(res);
    }


    public TextView setTextView(@IdRes int res, @NonNull String title) {

        try {
            TextView textView;
            textView = findViewById(res);
            textView.setVisibility(View.VISIBLE);
            if (StringUtils.isValid(title)) {

                textView.setText(title);
            }
            return textView;

        } catch (Exception ignored) {
            return null;
        }

    }

    public View hide(@IdRes int res) {

        try {
            View v = findViewById(res);
            v.setVisibility(View.GONE);
            return v;

        } catch (Exception ignored) {
            return null;
        }

    }

    public View show(@IdRes int res) {

        try {
            View v = findViewById(res);
            v.setVisibility(View.VISIBLE);
            return v;

        } catch (Exception ignored) {
            return null;
        }

    }

    public void e(Exception ignored) {

        Log.e(ignored.getClass().getSimpleName(), ignored.getMessage(), ignored);

    }



    public static int dpToPx(Context c, int dp) {
        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    BaseClass baseClass;

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(getContext(), cls));
    }

    protected void startActivityWithUnvId(Class<?> cls, String unvId) {
        startActivity(new Intent(getContext(), cls).putExtra("id", unvId));
    }


    protected Intent getIntent(Class<?> cls) {
        return new Intent(getContext(), cls);
    }

    protected Context getApplicationContext() {
        return (getActivity() != null) ? getActivity().getApplicationContext() : null;
    }

    protected FragmentManager getSupportFragmentManager() {
        return (getActivity() != null) ? getActivity().getSupportFragmentManager() : null;

    }
    @NonNull
    protected AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) Objects.requireNonNull(getActivity());

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        baseClass = new BaseClass(getApplicationContext());
        super.onCreate(savedInstanceState);

    }


    public boolean isValid(@NonNull String text) {
        return BaseClass.isValid(text);
    }

    public boolean isValid(Object object) {
        return object != null;
    }

    public boolean isValid(@NonNull EditText editText) {
        return BaseClass.isValid(editText.getText().toString());
    }

    public String getText(@NonNull EditText editText) {
        return BaseClass.getText(editText);
    }

    public boolean setText(@NonNull TextView editText, @NonNull String text) {
        if (isValid(text))
            editText.setText(text);
        return true;
    }
    public boolean setText(int editText, @NonNull String text) {
        if (isValid(text))
            setText(find(editText),text);
        return true;
    }

    public boolean setText(@NonNull String text, @NonNull EditText editText) {
        if (isValid(text))
            editText.setText(text);
        return true;
    }

    public String getText(@NonNull String editText) {
        return BaseClass.getText(editText);
    }


    public void showToast(String toast) {
        try {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
        } catch (Exception ignored) {
        }
    }


    public void show(String toast) {
        try {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
        } catch (Exception ignored) {
        }
    }


    public void setfView(View fView) {
        this.fView = fView;
    }

    @NonNull
    public View getfView() {
        return this.fView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fView = view;
    }

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        try {

            return find(id);

        } catch (Exception e) {

            return fView.findViewById(id);

        }
    }

    public void finish() {
        if (getActivity() != null)
            getActivity().finish();
    }

    public void back() {
        if (getFragmentManager() != null)
            getFragmentManager().popBackStack();
    }

    @Override
    public void onStart() {
        super.onStart();
        baseClass = new BaseClass(getApplicationContext());

    }

    View fView;

}
