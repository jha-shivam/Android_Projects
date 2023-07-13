package com.example.vmeet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.vmeet.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread() {
            public void run() {
                try {
                    long millis = 1000;
                    sleep(millis);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    SharedPreferences sp = getSharedPreferences("Shivam", MODE_PRIVATE);
                    String sss = sp.getString("User_Name", "SSS");
                    if (sss.equals("SSS")) {
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                    } else {
                        startActivity(new Intent(MainActivity.this, MainActivity3.class));
                    }
                    finish();
                }
            }

        };
        thread.start();
    }
}