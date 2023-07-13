package com.example.vmeet.ExtraClass.Navigations;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.vmeet.R;

public class AnimateNavigationDrawer {
    Context context;
    DrawerLayout drawerLayout;
    RelativeLayout relativeLayout;
    ConstraintLayout constraintLayout;
    static final float END_SCALE = 0.6f;

    public AnimateNavigationDrawer() {
        Toast.makeText(context, "Hello Mate", Toast.LENGTH_SHORT).show();
    }

    public AnimateNavigationDrawer(Context context, DrawerLayout drawerLayout, ConstraintLayout constraintLayout) {
        this.context = context;
        this.drawerLayout = drawerLayout;
        this.constraintLayout = constraintLayout;
    }

    public AnimateNavigationDrawer(Context context, DrawerLayout drawerLayout, RelativeLayout relativeLayout, ConstraintLayout constraintLayout) {
        this.context = context;
        this.drawerLayout = drawerLayout;
        this.relativeLayout = relativeLayout;
        this.constraintLayout = constraintLayout;
    }

    public void AnimationForDrawers() {
        drawerLayout.setScrimColor(context.getResources().getColor(R.color.trans));
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                final float diffiScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffiScaledOffset;
                constraintLayout.setScaleX(offsetScale);
                constraintLayout.setScaleY(offsetScale);

                //Translet The View , Accounting For The Scaled Width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = constraintLayout.getWidth() * diffiScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                constraintLayout.setTranslationX(xTranslation);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
}
