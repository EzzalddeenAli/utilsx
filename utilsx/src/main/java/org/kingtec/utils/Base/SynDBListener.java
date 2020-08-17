package org.kingtec.utils.Base;

import java.util.List;

public interface SynDBListener<T> {
    List<T> GetList();

    void onPostExecute(List<T> mList, boolean su);
}
