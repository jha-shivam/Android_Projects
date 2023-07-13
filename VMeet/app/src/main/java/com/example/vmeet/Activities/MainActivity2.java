package com.example.vmeet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vmeet.R;

public class MainActivity2 extends AppCompatActivity {
    Button b1, b2;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b1 = findViewById(R.id.b1_main2);
        b2 = findViewById(R.id.b2_main2);
        t1 = findViewById(R.id.t1_login);
        //   b3=findViewById(R.id.b3);
        b1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this, Logins.class);
            startActivity(intent);
            // startActivity(new Intent(MainActivity2.this,MainActivity3.class));
            finish();
        });
        b2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2.this, Signups_activity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        MainActivity2.this.finish();
        System.exit(0);
        moveTaskToBack(true);
    }
}