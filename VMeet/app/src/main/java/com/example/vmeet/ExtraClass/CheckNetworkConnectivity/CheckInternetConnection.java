package com.example.vmeet.ExtraClass.CheckNetworkConnectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckInternetConnection {
    Context context;

    public CheckInternetConnection(Context context) {
        this.context = context;
    }

    public void checkNetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (null != networkInfo) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(context, "Wifi Enabled", Toast.LENGTH_SHORT).show();
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(context, "DataNetwork Enabled", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "No Internet Connection Please Connect The Internet", Toast.LENGTH_LONG).show();
        }
    }
}
