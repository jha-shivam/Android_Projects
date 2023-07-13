package com.example.vmeet.Activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vmeet.ExtraClass.Navigations.AnimateNavigationDrawer;
import com.example.vmeet.ExtraClass.Navigations.Bottomnavigation;
import com.example.vmeet.ExtraClass.Navigations.Rightnavigation;
import com.example.vmeet.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Consultations extends AppCompatActivity {
    public static final int ACTIVITY_NUM = 1;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ConstraintLayout constraintLayout;
    Toolbar toolbar;
    ImageView i1_headerfile;
    TextView t1_headerfile;
    View view;
    String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultations);

        drawerLayout = findViewById(R.id.d1_consultions);
        navigationView = findViewById(R.id.nav_consultions);
        constraintLayout = findViewById(R.id.cn1_consltions);
        toolbar = findViewById(R.id.tool1_conslutions);
        navigationView = findViewById(R.id.nav_consultions);
        setSupportActionBar(toolbar);
        view = navigationView.getHeaderView(0);
        i1_headerfile = view.findViewById(R.id.i1_headerfile);
        t1_headerfile = view.findViewById(R.id.t1_headerfile);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        navigationView.bringToFront();
        toggle.syncState();
        animateNavigationDrawer();
        setupBottomNav();
        Rightnavigation rightnavigation = new Rightnavigation(Consultations.this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rightnavigation.Rightnavigations(Consultations.this, navigationView);
        }
        fetchssImages();
        setTextOnRightNavigation();
    }

    private void setupBottomNav() {
        bottomNavigationView = findViewById(R.id.bc1_conslutions);
        Bottomnavigation bottomnavigation = new Bottomnavigation(this, ACTIVITY_NUM);
        bottomnavigation.bottomnavigatations(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    private void animateNavigationDrawer() {
        AnimateNavigationDrawer animateNavigationDrawer = new AnimateNavigationDrawer(Consultations.this, drawerLayout, constraintLayout);
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

    }

    public void fetchssImages() {
        final String mm = "https://myvidmeet.000webhostapp.com/fetchImage.php";
        StringRequest request11 = new StringRequest(Request.Method.POST, mm, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String success = jsonObject.getString("success");
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if (success.equals("1")) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String Ss = object.getString("User_Image");
                        Picasso.get().load(Ss).centerCrop().fit().into(i1_headerfile);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Toast.makeText(this, "Sorry yrr nai la paya", Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        requestQueue1.add(request11);
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