package org.kingtec.utils.Base;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Created by Administrator on 24/03/2019.
 */

public class RResult<T extends Object> {

    @SerializedName("count")
    @Expose
    private int count = 0;

    @SerializedName("sysErr")
    @Expose
    private int sysErr = 0;

    @NonNull
    public int getSysErr() {
        return sysErr;
    }


    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("msg")
    @Expose
    private String msg = "";
    @SerializedName("maps")
    @Expose
    Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @SerializedName("objcet")
    @Expose
    public T objcet;

    @SerializedName("item")
    @Expose
    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getObjcet() {
        return objcet;
    }

    public void setObjcet(T objcet) {
        this.objcet = objcet;
    }


    @SerializedName("arrayList")
    @Expose
    private List<T> arrayList = new LinkedList<>();

    /**
     * @return The arrayList
     */
    public List<T> getArrayList() {
        return arrayList;
    }

    /**
     * @param arrayList The contacts
     */
    public void setArrayList(List<T> arrayList) {
        this.arrayList = arrayList;
    }

    @SerializedName("result")
    @Expose
    private List<T> result = new ArrayList<>();

    /**
     * @return The result
     */
    public List<T> getResult() {
        return result;
    }

    /**
     * @param result The contacts
     */
    public RResult<T> setResult(List<T> result) {
        this.result = result;
        return this;
    }
}
