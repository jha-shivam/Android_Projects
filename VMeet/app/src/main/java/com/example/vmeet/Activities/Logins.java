package com.example.vmeet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vmeet.ExtraClass.CheckNetworkConnectivity.CheckInternetConnection;
import com.example.vmeet.ExtraClass.CheckNetworkConnectivity.CheckInternetConnection1;
import com.example.vmeet.MYSQL.loginsignup.LoginBackGround;
import com.example.vmeet.R;
import com.google.android.material.textfield.TextInputLayout;

public class Logins extends AppCompatActivity {
    Button b1;
    TextView t2, t1;
    TextInputLayout e1, e2;
    EditText ee1, ee2;
    String User_Name;
    String PassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_logins);
        b1 = findViewById(R.id.b1_login);
        t1 = findViewById(R.id.t1_login);
        e1 = findViewById(R.id.e1_login);
        e2 = findViewById(R.id.e2_login);
        t2 = findViewById(R.id.t2_login);
        ee1 = findViewById(R.id.ee1_login);
        ee2 = findViewById(R.id.ee2_login);
        checkConnection();

        t2.setOnClickListener(view -> {
            Intent intent = new Intent(Logins.this, Signups_activity.class);
            startActivity(intent);
            finish();
        });
    }

    public void LoginOperation(View view) {
        // overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
        String Type = "Login";
        User_Name = e1.getEditText().getText().toString().trim();
        PassWord = e2.getEditText().getText().toString().trim();
        if ((User_Name.isEmpty()) && (PassWord.isEmpty())) {
            e1.setError("Please Enter UserName");
            e2.setError("Please Enter PassWord");
        } else {
            boolean check;
            CheckInternetConnection1 checkInternetConnection1 = new CheckInternetConnection1(Logins.this);
            check = checkInternetConnection1.check1();
            if (check) {
                LoginBackGround loginBackGround = new LoginBackGround(Logins.this, ee1, ee2);
                loginBackGround.execute(Type, User_Name, PassWord);
            } else {
                Toast.makeText(this, "Please make sure you have internet connection ", Toast.LENGTH_SHORT).show();
            }
        }
    }


    // Check Internet Connection Is Available for Login Process Or not
    public void checkConnection() {
        CheckInternetConnection checkInternetConnection = new CheckInternetConnection(Logins.this);
        checkInternetConnection.checkNetConnection();
    }

    public void gotoMainActivity2FromLogin(View view) {
        startActivity(new Intent(Logins.this, MainActivity2.class));
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Logins.this, MainActivity2.class));
    }

}