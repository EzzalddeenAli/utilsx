package org.kingtec.utils.Base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;


public abstract class BaseFragment extends BaseStaticFragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getPageLayout(), container, false);
        cd = new ConnectionDetector(getApplicationContext());
        setfView(v);
        loadPage();
        return v;
    }

    protected abstract @LayoutRes
    int getPageLayout();

}
