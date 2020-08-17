package org.kingtec.utils.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.kingtec.utils.ViewAnimation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import static android.view.View.NO_ID;


public class BaseDialogFragment extends DialogFragment {
    String unvId = null;

    /**
     * @hide
     */
    @IntDef({TYPE_ADD, TYPE_UPDATE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FragmentType {
    }


    public static final int TYPE_ADD = 0;
    private static final int CODE_REQUEST_PERMISSION = 1;
    private static final String mypreference = "unvapp";
    public static boolean isEdit = false;
    SharedPreferences sharedPref;
    private static JsonElement tagObject;
    private static String tagStringValue;
    private static int tagIntValue;

    public static String tagsStringValidator(JsonObject tagHolder, String tagName) {
        if (tagHolder != null) {
            try {
                tagObject = tagHolder.get(tagName);
            } catch (Exception e) {
                return "";
            }
            if (tagObject != null && !tagObject.isJsonNull()) {
                tagStringValue = tagObject.getAsString();
                if (tagStringValue != null && !tagStringValue.equals(""))
                    return repairJsonValueQuotes(tagStringValue);
            }
        }
        return "";
    }

    public static int tagsIntValidator(JsonObject tagHolder, String tagName) {
        if (tagHolder != null) {
            try {
                tagObject = tagHolder.get(tagName);
            } catch (Exception e) {
                return 0;
            }
            if (tagObject != null && !tagObject.isJsonNull()) {
                try {
                    tagIntValue = tagObject.getAsInt();
                    if (tagIntValue != 0) return tagIntValue;
                } catch (Exception ignored) {
                    return 0;
                }
            }
        }
        return 0;
    }

    public static String repairJsonValueQuotes(String value) {
        if (value != null) {
            String firstChar = String.valueOf(value.charAt(0));
            String lastChar = value.substring(value.length() - 1);

            if (firstChar.equals("\"")) value = value.substring(1);
            if (lastChar.equals("\"")) value = value.substring(0, value.length() - 1);
        }
        return value;
    }

    /////////////// SharedPreferences ///////////////
    /////////////// //////////////// ///////////////
    protected boolean expanded = true;
    protected String report = "";

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    protected boolean toggleLayoutExpand(boolean show, View lyt_expand) {
        //Tools.toggleArrow(show, view);
        if (show) {
            ViewAnimation.expand(lyt_expand);
        } else {
            ViewAnimation.collapse(lyt_expand);
        }
        return show;
    }

    public static final int TYPE_UPDATE = 1;

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(getContext(), cls));
    }

    protected void startActivityWithUnvId(Class<?> cls, String unvId) {
        startActivity(new Intent(getContext(), cls).putExtra("unvId", unvId));
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

    protected String getUnvId() {
        return (isValid(unvId) ? unvId : null);
    }

    protected String getUId() {
        return baseClass.getId();
    }

    public boolean isValid(@NonNull String text) {
        return BaseClass.isValid(text);
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


    //    public void showSuccessToast( String toast)   {try {
//        EzzToast.showSuccessToast(getContext(),   getText(toast)); } catch (Exception ignored) {}}
//    public void showWarningToast( String toast)   {try {EzzToast.showWarningToast(getContext(),   getText(toast)); } catch (Exception ignored) {}}
//    public void showErrorToast( String toast)     {try {EzzToast.showErrorToast(getContext(),     getText(toast)); } catch (Exception ignored) {}}
//    public void showInfoToast( String toast)      {try {EzzToast.showInfoToast(getContext(),      getText(toast)); } catch (Exception ignored) {}}
//    public void showDefaultToast( String toast)   {try {EzzToast.showDefaultToast(getContext(),   getText(toast)); } catch (Exception ignored) {}}
//    public void showConfusingToast( String toast) {try {EzzToast.showConfusingToast(getContext(), getText(toast)); } catch (Exception ignored) {}}
    public void showToast(String toast) {
        try {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
        } catch (Exception ignored) {
        }
    }

    ProgressDialog mProgressDialog;

    public void startDialog(String title, String message) {
        startDialog(title, message, false);
    }

    public void startDialog(String title, String message, boolean canCancel) {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCanceledOnTouchOutside(canCancel);
        mProgressDialog.show();
    }


    public void dismissDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public void setDialogTitle(String title) {
        if (mProgressDialog != null) {
            mProgressDialog.setTitle(title);
        } else {
            startDialog(title, "");
        }
    }


    public void setDialogMessage(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(message);
        } else {
            startDialog("", message);
        }
    }

    public void setfView(View fView) {
        this.fView = fView;
    }

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        if (id == NO_ID) {
            return null;
        }
        return (T) fView.findViewById(id);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fView = view;
    }

    BaseClass baseClass;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        baseClass = new BaseClass(getApplicationContext());
        super.onCreate(savedInstanceState);

    }

    View fView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fView = super.onCreateView(inflater, container, savedInstanceState);
        return fView;
    }
}
