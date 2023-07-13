package com.example.vmeet.Activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.vmeet.ExtraClass.Navigations.AnimateNavigationDrawer;
import com.example.vmeet.ExtraClass.Navigations.Bottomnavigation;
import com.example.vmeet.MYSQL.FetchUserData.FetchImageForProfile;
import com.example.vmeet.R;
import com.example.vmeet.ExtraClass.Navigations.Rightnavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity3 extends AppCompatActivity {
    public static final int ACTIVITY_NUM = 0;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ConstraintLayout constraintLayout;
    Toolbar toolbar;
    TextView t1_headerfile;
    ImageView i1_headerfile;
    View hview;
    String UserName;
    BottomNavigationView bottomNavigationView;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view1);
        constraintLayout = findViewById(R.id.cn1_main3);
        toolbar = findViewById(R.id.tool1_main3);
        hview = navigationView.getHeaderView(0);
        t1_headerfile = hview.findViewById(R.id.t1_headerfile);
        i1_headerfile = hview.findViewById(R.id.i1_headerfile);
        setSupportActionBar(toolbar);
        setUpRight();
        setupBottomNav();
        Rightnavigation rightnavigation = new Rightnavigation(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rightnavigation.Rightnavigations(this, navigationView);
        }
        setTextOnRightNavigation();
    }


    public void setUpRight() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        navigationView.bringToFront();
        toggle.syncState();
        animateNavigationDrawer();
    }

    private void setupBottomNav() {
        bottomNavigationView = findViewById(R.id.bc1_main3);
        Bottomnavigation bottomnavigation = new Bottomnavigation(MainActivity3.this, ACTIVITY_NUM);
        bottomnavigation.bottomnavigatations(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    private void animateNavigationDrawer() {
        AnimateNavigationDrawer animateNavigationDrawer = new AnimateNavigationDrawer(MainActivity3.this, drawerLayout, constraintLayout);
        animateNavigationDrawer.AnimationForDrawers();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    public void setTextOnRightNavigation() {
        SharedPreferences sp = getSharedPreferences("Shivam", MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sp.edit();
        if (sp.contains("User_Name")) {
            UserName = sp.getString("User_Name", "");
            t1_headerfile.setText(UserName);
        }
    }
}