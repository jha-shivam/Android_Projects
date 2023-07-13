package com.example.vmeet.ExtraClass.Navigations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.vmeet.Activities.MainActivity3;
import com.example.vmeet.Activities.Payments;
import com.example.vmeet.Activities.Consultations;
import com.example.vmeet.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Bottomnavigation {
    public Context mctx;
    int Activity_Num;

    public Bottomnavigation(Context mctx, int activity_Num) {
        this.mctx = mctx;
        Activity_Num = activity_Num;
    }


    @SuppressLint("NonConstantResourceId")
    public void bottomnavigatations(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:                                 //ACTIVITY_NUM=0
                    if (Activity_Num == 0) {
                        Log.e("Activity Number", "This is Activity Number 1 ");
                    } else {
                        Intent intent2 = new Intent(mctx, MainActivity3.class);
                        mctx.startActivity(intent2);
                        break;
                    }

                case R.id.Consultations:                        //ACTIVITY_NUM=1
                    if (Activity_Num == 1) {
                        Log.e("Activity Number", "This is Activity Number 2 ");
                    } else {
                        Intent intent = new Intent(mctx, Consultations.class);
                        mctx.startActivity(intent);
                        break;
                    }

                case R.id.payment_bottom_nav_menu:              //ACTIVITY_NUM=2
                    if (Activity_Num == 2) {
                        Log.e("Activity Number", "This is Activity Number 3 ");
                    } else {
                        Intent intent1 = new Intent(mctx, Payments.class);
                        mctx.startActivity(intent1);
                        break;
                    }

            }
            return false;
        });
    }
}
