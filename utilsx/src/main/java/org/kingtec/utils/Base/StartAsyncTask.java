//package org.kingtec.utils.Base;
//
//import android.os.AsyncTask;
//
////import com.bumptech.glide.load.Key;
//
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.HttpHostConnectException;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//
//
//public class StartAsyncTask extends AsyncTask<String, Void, String> {
//    private JSONObject jsonObject=null;
//
//    String massege;
//    private SynListener mSynListener;
//    public StartAsyncTask(SynListener onAddDataToDataBaseListener) {
//        mSynListener = onAddDataToDataBaseListener;
//    }
//
//    protected void onPreExecute() {
//        super.onPreExecute();
//        mSynListener.onPreExecute();
//    }
//
//    protected String doInBackground(String... params) {
//        InputStream is = null;
//        String result = null;
//        StartAsyncTask.this.massege = null;
//        try {
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(mSynListener.GetUrl());
//            httpPost.setEntity(new UrlEncodedFormEntity(mSynListener.GetValuePairs(params)));
//            is = httpClient.execute(httpPost).getEntity().getContent();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
//            StringBuilder sb = new StringBuilder();
//            while (true) {
//                String line = reader.readLine();
//                if (line == null) {
//                    break;
//                }
//                sb.append(line + "\n");
//            }
//            result = sb.toString();
//            try {
//                is.close();
//            } catch (Exception e) {
//            }
//        } catch (RuntimeException | HttpHostConnectException e2) {
//            StartAsyncTask.this.massege = e2.getMessage();
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (Exception ignored) {
//                }
//            }
//        } catch (ClientProtocolException | UnsupportedEncodingException e6) {
//            StartAsyncTask.this.massege = e6.getMessage();
//
//            e6.printStackTrace();
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (Exception ignored) {
//                }
//            }
//        } catch (Throwable th) {
//
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (Exception ignored) {
//                }
//            }
//        }
//        return result;
//    }
//
//    protected void onPostExecute(String result) {
//        super.onPostExecute(result);
//        mSynListener.onPostExecute(result,StartAsyncTask.this.massege);
//
//    }
//}
