package org.kingtec.utils.Base;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.EzzalddeenAli.utilsx.R;
import org.kingtec.utils.ViewAnimation;
import org.kingtec.utils.tools.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseFragmentListView<T> extends BaseFragment {

    public BaseFragmentListView() {
    }

    RecyclerView recyclerView;
    View root;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    protected boolean toggleLayoutExpand(boolean show, View view, View lyt_expand) {
        Tools.toggleArrow(show, view);
        if (show) {
            ViewAnimation.expand(lyt_expand);
        } else {
            ViewAnimation.collapse(lyt_expand);
        }
        return show;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setfView(view);
        this.memberList = new ArrayList<>();
        lyt_progress = findViewById(R.id.lyt_progress);
        llNoItems = findViewById(R.id.llNoItems);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(getLayoutManager());

        if(getItemDecoration()!=null)
        recyclerView.addItemDecoration(getItemDecoration());
        recyclerView.setHasFixedSize(true);
        cd = new ConnectionDetector(getApplicationContext());
        loading();

        setNoData("لاتوجد بيانات !!", "");


        getData();

        findViewById(R.id.fabRefresh).setOnClickListener(v -> {
            loading();
            getData();
        });

    }
    @Nullable
    protected abstract RecyclerView.ItemDecoration getItemDecoration();

    protected abstract @LayoutRes
    int getPageLayout();

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    public void toggleCheckedIcon(View checked, View viw, int position) {
        mAdapter.toggleCheckedIcon(checked, viw, position);
    }

    public void toggleCheckedIcon(View checked, int position) {
        mAdapter.toggleCheckedIcon(checked, position);
    }

    public void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
    }

    LinearLayout lyt_progress;
    LinearLayout llNoItems;

    public void loading() {
        try {
            recyclerView.setVisibility(View.GONE);
            llNoItems.setVisibility(View.GONE);
            lyt_progress.setVisibility(View.VISIBLE);
            lyt_progress.setAlpha(1.0f);
        } catch (Exception ignored) {
        }
    }

    protected void setFabAdd(View.OnClickListener onClickListener) {
        try {
            findViewById(R.id.fab_add).setVisibility(View.VISIBLE);
            findViewById(R.id.fab_add).setOnClickListener(onClickListener);
        } catch (Exception e) {
        }

    }

    protected RecyclerView getRecyclerView() {
        return recyclerView;
    }

    protected void scrollToPosition(int p) {
        if (mAdapter.getItemCount() > p)
            getRecyclerView().scrollToPosition(p);
    }

    public void noData() {
        try {

            if (recyclerView.getAdapter().getItemCount() > 0) {
                llNoItems.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                lyt_progress.setVisibility(View.GONE);

            } else {
                llNoItems.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                lyt_progress.setVisibility(View.GONE);
            }

        } catch (Exception ignored) {
        }
    }


    boolean isAdd = false;
    boolean isLoad = false;
    boolean isLocal = false;

    protected void getData() {

        try {

            if (!(memberList.size() > 0))
                loading();

            loadData();

        } catch (Exception ignored) {
            noData();
        }
    }

    protected void setNoData(String title, String subTitle) {
        try {
            setNoData(title, subTitle, getNoItemImg());
        } catch (Exception ignored) {
        }
    }

    protected void setNoData(String title, String subTitle, int img) {
        try {
            ((ImageView) findViewById(R.id.ivNoItemsIcon)).setImageResource(img);
        } catch (Exception ignored) {
            ((ImageView) findViewById(R.id.ivNoItemsIcon)).setImageResource(getNoItemImg());
        }
        try {
            ((TextView) findViewById(R.id.tvNoItemsTitle)).setText(title);
        } catch (Exception ignored) {
            ((TextView) findViewById(R.id.tvNoItemsTitle)).setText("لاتوجد بيانات !!");
        }
        try {
            ((TextView) findViewById(R.id.tvNoItemsSubTitle)).setText(subTitle);
        } catch (Exception ignored) {
            ((TextView) findViewById(R.id.tvNoItemsSubTitle)).setText("");
        }
    }

    private void DisplayContent() {
        try {
            ViewAnimation.fadeOut(lyt_progress);
            new Handler().postDelayed(() -> {
                testShowFabs();
                if (isValid(memberList))
                    if (Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() > 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                noData();

            }, 400);

        } catch (Exception ignored) {

        }
    }

    protected ItemsListAdapter<T> mAdapter;
    protected List<T> memberList;

    public void showData() {
        try {
            showData(memberList);
        } catch (Exception ignored) {
        }
    }

    protected int findLastVisibleItemPosition() {
        try {
            return ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        } catch (Exception ignored) {
            return 0;
        }
    }

    protected int getChildCount() {
        try {
            return recyclerView.getLayoutManager().getChildCount();
        } catch (Exception ignored) {
            return 0;
        }
    }

    protected int getItemCount() {
        try {
            return recyclerView.getLayoutManager().getItemCount();
        } catch (Exception ignored) {
            return 0;
        }
    }

    protected int findLastCompletelyVisibleItemPosition() {
        try {
            return ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
        } catch (Exception ignored) {
            return 0;
        }
    }

    protected int findFirstVisibleItemPosition() {
        try {
            return ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        } catch (Exception ignored) {
            return 0;
        }
    }

    protected void testShowFabs() {
//        try {
//            if(mAdapter.getItemCount()<=10)
//                findViewById(R.id.fabShow).setVisibility(View.GONE);
//            else findViewById(R.id.fabShow).setVisibility(View.VISIBLE);
//            if(showFabs()){
//
//                if (findLastVisibleItemPosition() > 12) {
//                   findViewById(R.id.llUp).setVisibility(View.VISIBLE);
//                } else {
//                    findViewById(R.id.llUp).setVisibility(View.GONE);
//                }
//
//                if (findLastVisibleItemPosition() < mAdapter.getItemCount() - 10) {
//                   findViewById(R.id.llDown).setVisibility(View.VISIBLE);
//                } else {
//                   findViewById(R.id.llDown).setVisibility(View.GONE);
//                }
//            }else {
//                findViewById(R.id.llUp).setVisibility(View.GONE);
//                findViewById(R.id.llDown).setVisibility(View.GONE);
//            }
//        }catch (Exception ignored){
//        }
    }

    //    boolean fabsShow=true;
    protected boolean loading = true;
    int pastVisibleItems, visibleItemCount, totalItemCount;

    protected void showData(List<T> memberList) {
        try {
            this.memberList = memberList;
//            Collections.shuffle(memberList);

            mAdapter = new ItemsListAdapter<T>(memberList,
                    getRowLayout(),
                    new ItemsListAdapter.ListAdapterListener<T>() {
                        @Override
                        public void onItemHolder(ItemViewHolder holder, T object, int position) {
                            try {
                                BaseFragmentListView.this.onItemHolder(holder, object, position);
                            } catch (Exception ignored) {
                            }
                        }

                        @Override
                        public String onFilter(T object) {
                            try {
                                return BaseFragmentListView.this.onFilter(object);
                            } catch (Exception ignored) {
                                return null;
                            }
                        }
                    }) {
                @Override
                public String onFilter(T object) {
                    try {
                        return BaseFragmentListView.this.onFilter(object);
                    } catch (Exception ignored) {
                        return null;
                    }
                }
            };
//            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                    super.onScrollStateChanged(recyclerView, newState);
//                }
//
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                }
//            })

//.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//                @Override
//                public void onScrollStateChanged(AbsListView view, int scrollState) {
//                }
//
//                @Override
//                public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
//                    boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount;
//                    if (loadMore && !NoFooter) mListInterface.loadMore();
//                }
//            });

//            findViewById(R.id.fabShow).setOnClickListener(v -> {
//                showFabs(!showFabs());
//                testShowFabs();
//            });
//            findViewById(R.id.fabUp).setOnClickListener(v -> {
//                if (findLastVisibleItemPosition()  > 12)
//                    recyclerView.scrollToPosition(1);
//                testShowFabs();
//            });
//            findViewById(R.id.fabUpShort).setOnClickListener(v -> {
//                if (findLastVisibleItemPosition() > 12)
//                    recyclerView.scrollToPosition( findLastVisibleItemPosition() - 12);
//                testShowFabs();
//            });
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    int f = findLastVisibleItemPosition();
//                    boolean loadMore = f  + mAdapter.getItemCount()- >= totalItemCount;
//                    if (findLastVisibleItemPosition() < mAdapter.getItemCount() - 5) {
                    if (dy > 0) {
                        if (!loading) {
                            visibleItemCount = getChildCount();
                            totalItemCount = getItemCount();
                            pastVisibleItems = findFirstVisibleItemPosition();

                            if ((visibleItemCount + pastVisibleItems) >= totalItemCount - 5) {
                                loading = true;
                                //page = page+1;
                                loadMore();
                            }
                        }
                        //if (findLastVisibleItemPosition() == mAdapter.getItemCount() - 1) {
                        //    loadMore();
                        //    showToast("fffff");
                        // }
                    }
                    testShowFabs();

                }
            });

//            findViewById(R.id.fabDown).setOnClickListener(v -> {
//                if (findLastVisibleItemPosition() < mAdapter.getItemCount() - 10)
//                    recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
//                testShowFabs();
//            });
//            findViewById(R.id.fabDownShort).setOnClickListener(v -> {
//                if (findLastVisibleItemPosition() < mAdapter.getItemCount() - 10)
//                    recyclerView.scrollToPosition( findLastVisibleItemPosition() + 9);
//                testShowFabs();
//            });

            BaseFragmentListView.this.recyclerView.setAdapter(mAdapter);
            DisplayContent();
        } catch (Exception ignored) {
        }
        loading = false;
    }

    SearchView searchView;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        try {
//            MenuItem item = menu.findItem(R.id.action_search);
//            searchView = (SearchView) MenuItemCompat.getActionView(item);
//            MenuItemCompat.setShowAsAction(item,
//                    MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW |
//                            MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
//            MenuItemCompat.setActionView(item, searchView);
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    if (mAdapter != null) mAdapter.getFilter().filter(query);
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String query) {
//                    if (mAdapter != null) mAdapter.getFilter().filter(query);
//                    return true;
//                }
//            });
//
//            super.onCreateOptionsMenu(menu, inflater);
//        }catch (Exception ignored){
//
//        }
    }

    public void onBackPressed() {

        if (searchView != null && !searchView.isIconified()) {

            searchView.onActionViewCollapsed();
            return;
        }

    }

    public String getArg(String string) {

        return getArg(string, "");
    }

    protected boolean showFabs(boolean val) {
        return baseClass.showFabs(val);
    }

    protected boolean showFabs() {
        return baseClass.showFabs();
    }


    protected abstract void onItemHolder(@NonNull ItemViewHolder holder, @NonNull T o, int position);

    protected abstract void setViews();
    @Nullable
    protected abstract String onFilter(@NonNull T o);

    protected abstract int getRowLayout();

    protected int getNoItemImg() {
        return R.drawable.img_no_feed;
    }

    protected abstract void loadData();

    protected abstract void loadMore();

}