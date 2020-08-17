package org.kingtec.utils.Base;

import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static android.view.View.NO_ID;

//import com.myapp.ejasatk.utils.SmartGate.DataBase.AppDatabase;


public class zBaseFragment extends Fragment {


    protected ConnectionDetector cd;
    AccountManager accountManager;

    BaseClass baseClass;

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(getContext(), cls));
    }

//    private static AppDatabase database;
//    @NonNull
//    protected AppDatabase getDatabase() {
//        try {
//            if(database == null)
//                database = AppDatabase.getAppDatabase(getApplicationContext());
//            return database;
//        }catch (Exception ignored){
//            return null;
//        }
//    }


    protected boolean showFabs(boolean val) {
        return baseClass.showFabs(val);
    }

    protected boolean showFabs() {
        return baseClass.showFabs();
    }


    protected String getUId() {
        return baseClass.getId();
    }

    protected String getToken() {
        return baseClass.getToken();
    }

    protected String getFullName() {
        return baseClass.getFullName();
    }

    protected boolean setRole(String val) {
        return baseClass.setRole(val);
    }


    protected boolean setId(String val) {
        return baseClass.setId(val);
    }

    protected boolean setApi(String val) {
        return baseClass.setApi(val);
    }

    protected String getApi() {
        return baseClass.getApi();
    }

    protected String getWeb() {
        return baseClass.getWeb();
    }

    protected boolean setWeb(String val) {
        return baseClass.setWeb(val);
    }


    protected String getUserType() {
        return baseClass.getUserType();
    }


    protected boolean isUser() {
        return isValid(getUserType()) && getUserType().equals(User.PARENT);
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        baseClass = new BaseClass(getApplicationContext());
        super.onCreate(savedInstanceState);

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

    public boolean setText(@NonNull EditText editText, @NonNull String text) {
        if (isValid(text))
            editText.setText(text);
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

    ProgressDialog mProgressDialog;

    public void startDialog(String title, String message) {
        startDialog(title, message, false);
    }

    public void startDialog(String title, String message, Boolean canCancel) {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCanceledOnTouchOutside(canCancel);
        mProgressDialog.show();
    }

    public void setfView(View fView) {
        this.fView = fView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fView = view;
    }

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        if (id == NO_ID) {
            return null;
        }
        try {

            return (T) getView().findViewById(id);

        } catch (Exception e) {

            return (T) fView.findViewById(id);

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        accountManager = AccountManager.get(getApplicationContext());
        fView = super.onCreateView(inflater, container, savedInstanceState);
        cd = new ConnectionDetector(getApplicationContext());
        baseClass = new BaseClass(getApplicationContext());

        return fView;
    }
}
