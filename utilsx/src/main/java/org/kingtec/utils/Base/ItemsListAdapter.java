package org.kingtec.utils.Base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public abstract class ItemsListAdapter<SingleItem extends Object> extends RecyclerView.Adapter<ItemViewHolder>
        implements Filterable {

    protected List<SingleItem> mList;
    ListAdapterListener<SingleItem> listener;

    public interface ListAdapterListener<T extends Object> {
        void onItemHolder(ItemViewHolder holder, T object, int position);

        String onFilter(T object);

    }


    @LayoutRes
    int res;
    public List<SingleItem> mListFiltered;


    public ItemsListAdapter(List<SingleItem> mList, @LayoutRes int res, ListAdapterListener<SingleItem> listener) {
        this.mList = mList;
        this.mListFiltered = mList;
        this.listener = listener;
        this.res = res;
        selected_items = new SparseBooleanArray();
    }

    public ItemsListAdapter(List<SingleItem> mList, @LayoutRes int res) {
        this.mList = mList;
        this.mListFiltered = mList;
        this.res = res;
        selected_items = new SparseBooleanArray();
    }
//    @Override
//    public int getItemViewType(int i2) {
//        return this.a.get(i2).isHeader() ? 1 : 2;
//    }

    public void toggleCheckedIcon(View checked, View viw, int position) {
        toggleSelection(position);
        if (selected_items.get(position, false)) {
            viw.setVisibility(View.GONE);
            checked.setVisibility(View.VISIBLE);
            if (current_selected_idx == position) resetCurrentIndex();
        } else {
            checked.setVisibility(View.GONE);
            viw.setVisibility(View.VISIBLE);
            if (current_selected_idx == position) resetCurrentIndex();
        }
    }

    public void toggleCheckedIcon(View checked, int position) {
        toggleSelection(position);
        if (selected_items.get(position, false)) {
            checked.setVisibility(View.VISIBLE);
            if (current_selected_idx == position) resetCurrentIndex();
        } else {
            checked.setVisibility(View.GONE);
            if (current_selected_idx == position) resetCurrentIndex();
        }
    }

    private SparseBooleanArray selected_items;
    private int current_selected_idx = -1;

    public void toggleSelection(int pos) {
        current_selected_idx = pos;
        if (selected_items.get(pos, false)) {
            selected_items.delete(pos);
        } else {
            selected_items.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void setListener(ListAdapterListener<SingleItem> listAdapterListener) {
        this.listener = listAdapterListener;
    }
    public int getRes() {
      return res;
    }

    public void clearSelections() {
        selected_items.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selected_items.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(getSelectedItemCount());
        for (int i = 0; i < getSelectedItemCount(); i++) {
            items.add(selected_items.keyAt(i));
        }
        return items;
    }

    public void removeData(int position) {
        mList.remove(position);
        resetCurrentIndex();
    }

    private void resetCurrentIndex() {
        current_selected_idx = -1;
    }

    public void setList(List<SingleItem> list) {
        this.mList = list;
    }

    public void add(SingleItem SingleItem) {
        update(SingleItem);
    }

    public List<SingleItem> getItems() {
        List<SingleItem> mList = new ArrayList<>();

        if (this.mList != null && this.mList.size() != 0) {
            mList = this.mList;
        }

        return mList;
    }

    public SingleItem getItem(int position) {
        return mList.get(position);
    }

    /**
     * Remove the item into a specific position
     *
     * @param position the position
     */
    public void remove(int position) {
        if (mList != null && mList.size() > 0) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * add an item into at the top of the list
     *
     * @param item the item to add
     */
    public void insertTop(SingleItem item) {
        int position = 0;
        if (item != null) {
            mList.add(position, item);
            notifyItemInserted(position);
        }
    }


    public void insertBottom(SingleItem item) {
        if (mList != null) {
            int position = mList.size();
            if (item != null) {
                mList.add(position, item);
                notifyItemInserted(position);

            }
        }
    }

    public void insertBottom(List<SingleItem> item) {
        if (mList != null) {
            int position = mList.size();
            if (item != null) {
                mList.addAll(position, item);
                notifyDataSetChanged();
                // notifyItemInserted(position);
            }
        }
    }


    public void insert(SingleItem item, int position) {
        if (mList != null) {
            if (item != null) {
                mList.add(position, item);
                notifyItemInserted(position);
            }
        }
    }


    public void clear() {
        if (mList != null) {
            if (mList.size() > 0) {
                mList.clear();
            }
            notifyDataSetChanged();
        }
    }


    public void update(SingleItem item) {
        if (item != null) {
            List<SingleItem> list = mList;

            int itemPosition = list.indexOf(item);

            if (itemPosition >= 0 && itemPosition < list.size()) {
                mList.set(itemPosition, item);
            } else {
                mList.add(item);
            }
            unfilter();
            notifyDataSetChanged();
        }
    }

    public void unfilter() {
        mListFiltered = mList;
    }

    @SuppressLint("ResourceType")
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(getRes(), parent, false);
        return new ItemViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        final SingleItem employee = mListFiltered.get(position);
        holder.mView.setActivated(selected_items.get(position, false));
        listener.onItemHolder(holder, employee, position);
    }


    @Override
    public int getItemCount() {
        return mListFiltered.size();
    }

    public List<SingleItem> getList() {
        return mList;
    }

    public abstract String onFilter(SingleItem object);

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mListFiltered = mList;
                } else {
                    List<SingleItem> filteredList = new CopyOnWriteArrayList<>();
                    for (SingleItem row : mList) {
                        if (onFilter(row).toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    mListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
            }
        };
    }

    public List<SingleItem> getAllData() {
        return mList;
    }

    public void setAllData(List<SingleItem> mList) {
        this.mList = mList;
        this.mListFiltered = mList;
        notifyDataSetChanged();
    }

    public void setFilteredData(List<SingleItem> mListFiltered) {
        this.mListFiltered = mListFiltered;
        notifyDataSetChanged();
    }

    public List<SingleItem> getStringEqualsFiltered(List<String> genre,
                                                    List<SingleItem> mList,
                                                    OnGetString<SingleItem> onGetString) {
        List<SingleItem> tempList = new ArrayList<>();
        for (SingleItem movie : mList) {
            for (String g : genre) {
                if (onGetString.getString(movie).equalsIgnoreCase(g)) {
                    tempList.add(movie);
                }
            }

        }
        return tempList;
    }

    public List<String> getUniqueStringKeys(
            OnGetString<SingleItem> onGetString) {
        List<String> genres = new ArrayList<>();
        for (SingleItem movie : mList) {
            if (!genres.contains(onGetString.getString(movie))) {
                genres.add(onGetString.getString(movie));
            }
        }
        Collections.sort(genres);
        return genres;
    }

    //////////////////////////////////////////////////


    //////////////////////////////////////////////////
    public List<SingleItem> getNumbersEqualsFiltered(List<String> yearstr, List<SingleItem> mList,
                                                     OnGetNumber<SingleItem> onGetNumber) {
        List<SingleItem> tempList = new ArrayList<>();
        for (SingleItem movie : mList) {
            for (String y : yearstr) {
                if (onGetNumber.getNumber(movie) == Integer.parseInt(y)) {
                    tempList.add(movie);
                }
            }
        }
        return tempList;
    }


    public List<String> getUniqueNumbersKeys(OnGetNumber<SingleItem> onGetNumber) {
        List<String> years = new ArrayList<>();
        for (SingleItem movie : mList) {
            if (!years.contains(onGetNumber.getNumber(movie) + "")) {
                years.add(onGetNumber.getNumber(movie) + "");
            }
        }
        Collections.sort(years);
        return years;
    }

    public List<SingleItem> getGrateThanNumbers(List<String> rating, List<SingleItem> mList,
                                                OnGetNumber<SingleItem> onGetNumber) {
        List<SingleItem> tempList = new ArrayList<>();
        for (SingleItem movie : mList) {
            for (String r : rating) {
                if (onGetNumber.getNumber(movie) >= Float.parseFloat(r
                        .replace(">", "")
                        .replace("<", "")
                        .replace("=", "")
                        .replace("!", "")
                )) {
                    tempList.add(movie);
                }
            }
        }
        return tempList;
    }

    public List<SingleItem> getLessThanNumbers(List<String> rating, List<SingleItem> mList,
                                               OnGetNumber<SingleItem> onGetNumber) {
        List<SingleItem> tempList = new ArrayList<>();
        for (SingleItem movie : mList) {
            for (String r : rating) {
                if (onGetNumber.getNumber(movie) <= Float.parseFloat(r
                        .replace(">", "")
                        .replace("<", "")
                        .replace("=", "")
                        .replace("!", "")
                )) {
                    tempList.add(movie);
                }
            }
        }
        return tempList;
    }


    public List<SingleItem> getTextFilter(String txt, List<SingleItem> aList,
                                          OnGetString<SingleItem> onGetString) {
        List<SingleItem> filteredList = new CopyOnWriteArrayList<>();

        String charString = txt;
        if (charString.isEmpty()) {
            return aList;
        } else {
            for (SingleItem row : aList) {
                if (onGetString.getString(row).toLowerCase().contains(charString.toLowerCase())) {
                    filteredList.add(row);
                }
            }

            return filteredList;
        }

    }


    public void showSearchDialog(Activity activity, @LayoutRes int res, OnSetUpDialog<SingleItem> onSetUpDialog) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(res);
        dialog.setCancelable(true);
        onSetUpDialog.setViews(dialog, mListFiltered, mList);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}