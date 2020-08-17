package org.kingtec.utils.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.kingtec.utils.Base.BaseClass;
import org.kingtec.utils.StringUtils;
import org.kingtec.utils.tools.LocaleHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class zBaseActivity extends AppCompatActivity {
//    AccountManager accountManager;

    private static final int CODE_REQUEST_PERMISSION = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        accountManager = AccountManager.get(this);
    }

    public interface OnDateSet {


        void onDateSet(String date, long date_ship_millis);
    }










    public Context setLocale(String languageCode) {

        return LocaleHelper.setLocale(this, languageCode);
    }



    @Override
    public void setContentView(int layoutResID) {
        checkGooglePlayServices();
        super.setContentView(layoutResID);
    }
    public <T extends View> T getView(@IdRes int res) {
        return find(res);
    }

    public <T extends View> T find(@IdRes int id) {
        return findViewById(id);
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



    public View hide(@IdRes int res) {

        try {
            View v = findViewById(res);
            v.setVisibility(View.GONE);
            return v;

        } catch (Exception ignored) {
            return null;
        }

    }
    public View hide( View v) {

        try {
            v.setVisibility(View.GONE);
            return v;

        } catch (Exception ignored) {
            return null;
        }

    }
    public View show( View v) {

        try {
            v.setVisibility(View.VISIBLE);
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

    protected void checkGooglePlayServices() {
//        int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
//
//        if (result != ConnectionResult.SUCCESS) {
//            notifyGcmFailure();
//        }
    }

    private void notifyGcmFailure() {
        finish();
    }


    public boolean isValid(@NonNull String text) {
        return BaseClass.isValid(text);
    }
    public void showErr(String sh)
    {
        showToast(sh);
    }
    public void showErr(String sh,String co) {
        showToast(sh);
//        SweetUtil.with(getActivity()).error().title(sh,co).confirm(" X ", new SweetUtil.OnClick() {
//            @Override
//            public void onClick(SweetAlertDialog d) {
//                d.dismissWithAnimation();
//            }
//        }).show();;

    }

    public void asyncThread(Runnable runnable){
        new Thread(runnable).start();
    }
    public boolean isValid(@NonNull Object object) {
        return object != null;
    }

    public boolean isValid(@NonNull EditText editText) {
        return BaseClass.isValid(editText.getText().toString());
    }

    public String getText(@NonNull EditText editText) {
        return BaseClass.getText(editText);
    }

    public float getFloat(@NonNull EditText editText) {
        return Float.parseFloat(getText(editText));
    }

    public int getInt(@NonNull EditText editText) {
        return Integer.parseInt(getText(editText));
    }

    public int getInt(@NonNull String editText) {
        return Integer.parseInt(getText(editText));
    }

    public float getFloat(@NonNull String editText) {
        return Float.parseFloat(getText(editText));
    }

    public String getText(@NonNull String editText) {
        return BaseClass.getText(editText);
    }

    public void showToast(String toast) {
        try {
            Toast.makeText(getContext(), toast, Toast.LENGTH_LONG).show();
        } catch (Exception ignored) {
        }
    }

    public void show(String toast) {
        try {
            Toast.makeText(getContext(), toast, Toast.LENGTH_LONG).show();
        } catch (Exception ignored) {
        }
    }


    protected void setContentViewWithoutInject(int layoutResId) {
        super.setContentView(layoutResId);
    }

    protected String getStringExtra(String k) {
        return getStringExtra(k,"");
    }
    protected int getIntExtra(String k,int d) {
        return  getIntent().getIntExtra(k,d);
    }
    protected String getStringExtra(String k,String d) {

        Intent i=getIntent();
        String s="";
        if(i!=null)
            s=i.getStringExtra(k);
        if(s!=null) {
            return isValid(s)
                    ?
                    s
                    :
                    d;
        }
        return d;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODE_REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int result = grantResults[i];
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            String permission = permissions[i];
                            deniedPermissions.add(permission);
                        }
                    }

                    if (deniedPermissions.isEmpty()) {
                        //  mPermissionListener.onGranted();
                    } else {
                        //  mPermissionListener.onDenied(deniedPermissions);
                    }
                }
                break;

            default:
                break;
        }
    }

    protected static final int SDK_PERMISSION_REQUEST = 127;
    protected String permissionInfo;


    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(getContext(), cls));
    }

    protected void startActivityWithFinsh(Class<?> cls) {
        startActivity(new Intent(getContext(), cls));
        finish();
    }

    protected Intent getIntent(Class<?> cls) {
        return new Intent(getContext(), cls);
    }

    protected Context getContext() {
        return getApplicationContext();
    }

    protected Context getActivity() {
        return this;
    }

    ProgressDialog mProgressDialog;


    protected void startDialog(String title, String message) {
        startDialog(title, message, true);
    }

    protected void startDialog(String title, String message, Boolean canCancel) {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCanceledOnTouchOutside(canCancel);
        mProgressDialog.show();
    }

    protected void dismissDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    protected void setDialogTitle(String title) {
        if (mProgressDialog != null) {
            mProgressDialog.setTitle(title);
        } else {
            startDialog(title, "");
        }
    }


    protected void setDialogMessage(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(message);
        } else {
            startDialog("", message);
        }
    }

}

