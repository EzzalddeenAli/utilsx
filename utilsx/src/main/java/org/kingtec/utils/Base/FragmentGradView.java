package org.kingtec.utils.Base;

import android.os.Bundle;
import android.view.View;

import org.kingtec.utils.Base.widget.SpacingItemDecoration;
import com.github.EzzalddeenAli.utilsx.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class FragmentGradView<T> extends BaseFragmentListView<T> {
    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new SpacingItemDecoration((getSpanCount() == 0) ? 2 : getSpanCount(),
                dpToPx(getApplicationContext(), 3), true);
    }

    @Override
    protected int getPageLayout() {
        return R.layout._fragment_music_song;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getApplicationContext(), (getSpanCount() == 0) ? 2 : getSpanCount());
    }

    protected abstract int getSpanCount();

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