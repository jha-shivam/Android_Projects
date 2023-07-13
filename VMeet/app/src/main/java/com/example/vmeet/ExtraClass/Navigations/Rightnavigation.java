package com.example.vmeet.ExtraClass.Navigations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.vmeet.Activities.Profile;
import com.example.vmeet.Activities.Work_Profile;
import com.example.vmeet.Activities.Logout;
import com.example.vmeet.R;
import com.google.android.material.navigation.NavigationView;

public class Rightnavigation {
    public Context mctx;


    public Rightnavigation(Context mctx) {
        this.mctx = mctx;
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void Rightnavigations(Context context, NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.nav_work_profile:
                    context.startActivity(new Intent(context, Work_Profile.class));
                    break;
                case R.id.nav_personal_profile:
                    context.startActivity(new Intent(context, Profile.class));
                    break;
                case R.id.nav_logout: {
                    context.startActivity(new Intent(context, Logout.class));
                    break;
                }
            }
            return false;
        });
    }
}
