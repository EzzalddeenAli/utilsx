package org.kingtec.utils.Base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.kingtec.utils.MErr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 24/03/2019.
 */

public class ResultData<T> extends RResult<T> {
    @SerializedName("errList")
    @Expose
    private List<MErr> errList = new ArrayList<>();

    public List<MErr> getErrList() {
        return errList;
    }

    public void setErrList(List<MErr> errList) {
        this.errList = errList;
    }

}
