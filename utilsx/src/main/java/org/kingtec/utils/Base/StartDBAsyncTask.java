//package org.kingtec.utils.Base;
//
//import android.os.AsyncTask;
//
//import com.myapp.ejasatk.utils.SmartGate.DataBase.ObjectDao;
//import java.util.List;
//
//public class StartDBAsyncTask<T extends Object> extends AsyncTask<Void, Void, Boolean> {
//
//    private SynDBListener<T> mSynListener;
//    ObjectDao<T> mObjectDao;
//    String mAcc;
//    List<T> list;
//    public StartDBAsyncTask(ObjectDao<T> objectDao,String acc, SynDBListener<T> synDBListener) {
//        mSynListener = synDBListener;
//        mObjectDao = objectDao;
//        mAcc = acc;
//    }
//
//    @Override
//    protected Boolean doInBackground(Void... voids) {
//        mObjectDao.updateAll(mSynListener.GetList(),mAcc);
//        list =mObjectDao.getAll(mAcc);
//        return false;
//    }
//
//    @Override
//    protected void onPostExecute(Boolean aBoolean) {
//        super.onPostExecute(aBoolean);
//        mSynListener.onPostExecute(list,aBoolean);
//    }
//}
