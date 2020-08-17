package org.kingtec.utils.Base;

import android.os.Bundle;
import android.view.View;

import org.kingtec.utils.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class FragmentListView<T> extends BaseFragmentListView<T> {
    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new ItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL,
                getResources().getDrawable(R.drawable.decorator_activity_contact_list));
    }

    @Override
    protected void loadPage() {

    }

    @Override
    protected int getPageLayout() {
        return R.layout._fragment_music_song;
    }


    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getApplicationContext());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}