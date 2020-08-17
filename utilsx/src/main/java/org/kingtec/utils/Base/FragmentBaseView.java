package org.kingtec.utils.Base;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.EzzalddeenAli.utilsx.R;
import org.kingtec.utils.ViewAnimation;
import org.kingtec.utils.tools.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class FragmentBaseView<T> extends BaseFragment {
//    private class ActionModeCallback implements ActionMode.Callback {
//        @Override
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            Tools.setSystemBarColor(getActivity(), R.color.blue_grey_700);
//            mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            return false;
//        }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            int id = item.getItemId();
//            if (id == R.id.action_delete) {
//               //mAdapter.deleteInboxes();
//                mode.finish();
//                return true;
//            }
//            return false;
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//            mAdapter.clearSelections();
//            actionMode = null;
//            Tools.setSystemBarColor(getActivity(), R.color.red_600);
//        }
//    }

    //    private ActionModeCallback actionModeCallback;
//    private ActionMode actionMode;
    protected interface GetDataListener<T> {
        void onGetData(List<T> list);
        List<T> onGetData();
    }

    GetDataListener<T> getDataListener;

    public FragmentBaseView() {

    }

    RecyclerView recyclerView;
    View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout._fragment_music_song, container, false);
        setfView(root);

//        mAdapter.setOnClickListener(new AdapterListInbox.OnClickListener() {
//            @Override
//            public void onItemClick(View view, Inbox obj, int pos) {
//                if (mAdapter.getSelectedItemCount() > 0) {
//                    enableActionMode(pos);
//                } else {
//                    // read the inbox which removes bold from the row
//                    Inbox inbox = mAdapter.getItem(pos);
//                    Toast.makeText(getApplicationContext(), "Read: " + inbox.from, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onItemLongClick(View view, Inbox obj, int pos) {
//                enableActionMode(pos);
//            }
//        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void enableActionMode(int position) {
//        if (actionMode == null) {
//            actionMode = getActivity().startActionMode(actionModeCallback);
//        }
        //toggleSelection(position);
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
        lyt_progress = root.findViewById(R.id.lyt_progress);
        llNoItems = root.findViewById(R.id.llNoItems);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new ItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL,
                getResources().getDrawable(R.drawable.decorator_activity_contact_list)));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        cd = new ConnectionDetector(getApplicationContext());
        loading();

        setNoData("لاتوجد بيانات !!", "يرجى التاكد من الاتصال بالانترنت");
//        if(!cd.isConnected()){
//            DisplayContent();
//        }
        setViews();


        getData();

        root.findViewById(R.id.fabRefresh).setOnClickListener(v -> {
            loading();
            getData();
        });

    }

    LinearLayout lyt_progress;
    LinearLayout llNoItems;

    protected void loading() {
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
            root.findViewById(R.id.fab_add).setVisibility(View.VISIBLE);
            root.findViewById(R.id.fab_add).setOnClickListener(onClickListener);
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

    private void noData() {
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

    protected void synsData(GetDataListener<T> listener) {
        try {
            getDataListener = listener;
            isLocal = true;
        } catch (Exception ignored) {
        }
    }

    boolean isAdd = false;
    boolean isLoad = false;
    boolean isLocal = false;

    protected void getData() {

        try {
            if (isLocal) {
                loading();
                if (isValid(getDataListener))
                    showData(getDataListener.onGetData());
            }

            if (!(memberList.size() > 0))
                loading();

//                getServiceData().enqueue(new Callback<ResultData<T>>() {
//                    @Override
//                    public void onResponse(Call<ResultData<T>> call, Response<ResultData<T>> response) {
//                        try {
//                            if(response.isSuccessful()){
//                                memberList = response.body().getResult();
//                                showData(memberList);
//                                if(isLocal){
//                                    if(getDataListener!=null){
//                                        isLoad=true;
//                                        getDataListener.onGetData(memberList);
//                                        isLoad=false;
//
//                                    }
//
//                                }else  showData();
//
//
//                            }
//                            else{
//                                if(isLocal){
//                                    if(isValid(getDataListener))
//                                        showData(getDataListener.onGetData());
//                                }else {
//                                    noData();
//                                }
//                                isLoad=false;
//
//                            }
//                        }
//                        catch (Exception ignored){
//                            if(isLocal){
//                                if(isValid(getDataListener))
//                                    showData(getDataListener.onGetData());
//                            }else {
//                                noData();
//
//                            }
//                            isLoad=false;
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResultData<T>> call, Throwable t) {
//                        noData();
//                        if(isLocal){
//                            if(isValid(getDataListener))
//                                showData(getDataListener.onGetData());
//                        }
//                    }
//                });

        } catch (Exception ignored) {
        }
    }

    protected void setNoData(String title, String subTitle) {
        try {
            setNoData(title, subTitle, R.drawable.img_no_feed);
        } catch (Exception ignored) {
        }
    }

    protected void setNoData(String title, String subTitle, int img) {
        try {
            ((ImageView) root.findViewById(R.id.ivNoItemsIcon)).setImageResource(img);
        } catch (Exception ignored) {
            ((ImageView) root.findViewById(R.id.ivNoItemsIcon)).setImageResource(R.drawable.img_no_feed);
        }
        try {
            ((TextView) root.findViewById(R.id.tvNoItemsTitle)).setText(title);
        } catch (Exception ignored) {
            ((TextView) root.findViewById(R.id.tvNoItemsTitle)).setText("لاتوجد بيانات !!");
        }
        try {
            ((TextView) root.findViewById(R.id.tvNoItemsSubTitle)).setText(subTitle);
        } catch (Exception ignored) {
            ((TextView) root.findViewById(R.id.tvNoItemsSubTitle)).setText("يرجى مراجعة المدرسة لاضافة البيانات");
        }
    }

    private void DisplayContent() {
        try {
            ViewAnimation.fadeOut(lyt_progress);
            new Handler().postDelayed(() -> {
                testShowFabs();
                if (isValid(memberList))
                    if (recyclerView.getAdapter().getItemCount() > 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                noData();

            }, 400);

        } catch (Exception ignored) {

        }
    }

    public void toggleCheckedIcon(View checked, View viw, int position) {
        mAdapter.toggleCheckedIcon(checked, viw, position);
    }

    public void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
    }

    ItemsListAdapter<T> mAdapter;
    protected List<T> memberList;

    protected void showData() {
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
//                root.findViewById(R.id.fabShow).setVisibility(View.GONE);
//            else root.findViewById(R.id.fabShow).setVisibility(View.VISIBLE);
//            if(showFabs()){
//
//                if (findLastVisibleItemPosition() > 12) {
//                    root.findViewById(R.id.llUp).setVisibility(View.VISIBLE);
//                } else {
//                    root.findViewById(R.id.llUp).setVisibility(View.GONE);
//                }
//
//                if (findLastVisibleItemPosition() < mAdapter.getItemCount() - 10) {
//                    root.findViewById(R.id.llDown).setVisibility(View.VISIBLE);
//                } else {
//                    root.findViewById(R.id.llDown).setVisibility(View.GONE);
//                }
//            }else {
//                root.findViewById(R.id.llUp).setVisibility(View.GONE);
//                root.findViewById(R.id.llDown).setVisibility(View.GONE);
//            }
//        }catch (Exception ignored){
//        }
    }

    //    boolean fabsShow=true;
    protected void showData(List<T> memberList) {
        try {
            this.memberList = memberList;
            Collections.shuffle(memberList);

            mAdapter = new ItemsListAdapter<T>(memberList,
                    getRowLayout(),
                    new ItemsListAdapter.ListAdapterListener<T>() {
                        @Override
                        public void onItemHolder(ItemViewHolder holder, T object, int position) {
                            try {
                                FragmentBaseView.this.onItemHolder(holder, object, position);
                            } catch (Exception ignored) {
                            }
                        }

                        @Override
                        public String onFilter(T object) {
                            try {
                                return FragmentBaseView.this.onFilter(object);
                            } catch (Exception ignored) {
                                return null;
                            }
                        }


                    }) {
                @Override
                public String onFilter(T object) {
                    try {
                        return FragmentBaseView.this.onFilter(object);
                    } catch (Exception ignored) {
                        return null;
                    }
                }
            };

//            root.findViewById(R.id.fabShow).setOnClickListener(v -> {
//                //showFabs(!showFabs());
//                testShowFabs();
//            });
//            root.findViewById(R.id.fabUp).setOnClickListener(v -> {
//                if (findLastVisibleItemPosition()  > 12)
//                    recyclerView.scrollToPosition(1);
//                testShowFabs();
//            });
//            root.findViewById(R.id.fabUpShort).setOnClickListener(v -> {
//                if (findLastVisibleItemPosition() > 12)
//                    recyclerView.scrollToPosition( findLastVisibleItemPosition() - 12);
//                testShowFabs();
//            });
//            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    testShowFabs();
//
//                }
//            });
//
//            root.findViewById(R.id.fabDown).setOnClickListener(v -> {
//                if (findLastVisibleItemPosition() < mAdapter.getItemCount() - 10)
//                    recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
//                testShowFabs();
//            });
//            root.findViewById(R.id.fabDownShort).setOnClickListener(v -> {
//                if (findLastVisibleItemPosition() < mAdapter.getItemCount() - 10)
//                    recyclerView.scrollToPosition( findLastVisibleItemPosition() + 9);
//                testShowFabs();
//            });

            FragmentBaseView.this.recyclerView.setAdapter(mAdapter);
            DisplayContent();
        } catch (Exception ignored) {
        }
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
        // close search view on back button pressed
        if (searchView != null && !searchView.isIconified()) {
//            searchView.setIconified(true);
            searchView.onActionViewCollapsed();
            return;
        }
//        onBackPressed();
    }

    protected abstract void onItemHolder(ItemViewHolder holder, T object, int position);

    protected abstract void setViews();

    protected abstract String onFilter(T object);

    protected abstract int getRowLayout();
//    protected abstract Call<ResultData<T>> getServiceData();


}