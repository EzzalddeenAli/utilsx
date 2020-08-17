package org.kingtec.utils.Base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionDetector extends AsyncTask<String, Void, Integer> {
    Context context;

    public ConnectionDetector(Context context) {
        this.context = context;
    }

    @SuppressLint("WrongConstant")
    public boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) this.context.getSystemService("connectivity");
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            return info != null && info.getState() == State.CONNECTED;
        }
        return false;
    }

    public Integer doInBackground(String... params) {
        Integer result = 0;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("8.8.8.8", 53), 300);
            socket.close();
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }

    public void onPostExecute(Integer result) {
        if (!isConnected()) {
            Toast.makeText(this.context, "لايوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();
        } else if (result == 0) {
        } else {
        }
        super.onPostExecute(result);
    }
}
