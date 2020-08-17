package org.kingtec.utils.Base;

import android.app.Dialog;

import java.util.List;

/**
 * Created by Verizon on 16/03/2018.
 */

public interface OnSetUpDialog<T extends Object> {

    void setViews(Dialog dialog, List<T> itemsFiltered, List<T> items);

}
