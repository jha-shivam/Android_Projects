package com.example.vmeet.ExtraClass.CheckNetworkConnectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetConnection1 {
    Context context;

    public CheckInternetConnection1(Context context) {
        this.context = context;
    }

    public boolean check1() {
        boolean value = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (null != networkInfo) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                value = true;
                return value;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                value = true;
                return value;
            }
        } else {
            value = false;
            return value;
        }
        return value;
    }

}

