package com.example.vmeet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.vmeet.MYSQL.FetchUserData.GetProfileData;
import com.example.vmeet.R;

public class LoadingData extends AppCompatActivity {
    ProgressBar progressBar;
    String sss = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_data);
        progressBar = findViewById(R.id.progressBar);
        SharedPreferences sp = getSharedPreferences("Shivam", MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sp.edit();
        if (sp.contains("User_Name")) {
            sss = sp.getString("User_Name", "");

        }
        GetProfileData getProfileData = new GetProfileData(LoadingData.this, progressBar);
        getProfileData.execute(sss);
    }
}