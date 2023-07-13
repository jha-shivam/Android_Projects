package com.example.vmeet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vmeet.ExtraClass.CheckNetworkConnectivity.CheckInternetConnection1;
import com.example.vmeet.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Objects;
import java.util.regex.Pattern;

public class Signups_activity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +        //at least 1 digit
                    // "(?=.*[a-z])" +        //at least 1 lower case letter
                    "(?=.*[A-Z])" +        //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +     //any letter
                    "(?=.*[@#$%^&+=])" +   //at least 1 special character
                    "(?=\\S+$)" +        //no white spaces
                    ".{6,50}" +              //at least 4 characters
                    "$");
    TextInputLayout e1, e2, e3, e4;
    EditText ee1, ee2, ee3, ee4;
    TextView t1;
    Spinner s1;
    Button b1;
    String Full_Name, User_Email, User_Name, PassWord, User_Type, Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signups_activity);
        t1 = findViewById(R.id.t1_signup);
        s1 = findViewById(R.id.s1_signup);
        e1 = findViewById(R.id.e1_signup);
        e2 = findViewById(R.id.e2_signup);
        e3 = findViewById(R.id.e3_signup);
        e4 = findViewById(R.id.e4_signup);
        ee1 = findViewById(R.id.ee1_signup);
        ee2 = findViewById(R.id.ee2_signup);
        ee3 = findViewById(R.id.ee3_signup);
        ee4 = findViewById(R.id.ee4_signup);
        b1 = findViewById(R.id.b1_signup);
        t1.setOnClickListener(view -> {
            Intent intent = new Intent(Signups_activity.this, Logins.class);
            startActivity(intent);
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Signups_activity.this, android.R.layout.simple_list_item_1
                , getResources().getStringArray(R.array.numbers));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(arrayAdapter);
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Token = Objects.requireNonNull(task.getResult()).getToken();
            } else {
                Toast.makeText(Signups_activity.this, "Token Is Not Generated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validateEmail() {
        User_Email = ee2.getText().toString();
        if (User_Email.isEmpty()) {
            e2.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(User_Email).matches()) {
            e2.setError("Please enter valid email address");
            return false;
        } else {
            e2.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        User_Name = ee3.getText().toString().trim();
        if (User_Name.isEmpty()) {
            e3.setError("Field can't be empty");
            return false;
        } else if (User_Name.length() > 30) {
            e3.setError("Username is too long");
            return false;
        } else {
            e3.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        PassWord = ee4.getText().toString().trim();
        if (PassWord.isEmpty()) {
            e4.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(PassWord).matches()) {
            e4.setError("Please Enter Correct password");
            return false;
        } else {
            e4.setError(null);
            return true;
        }
    }


    public void goToMainActivity2FromSignUp(View view) {
        startActivity(new Intent(Signups_activity.this, MainActivity2.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Signups_activity.this, MainActivity2.class));
    }


    public void SignUpOperation(View view) {
        Full_Name = ee1.getText().toString();
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }
        User_Type = (String) s1.getSelectedItem();
        CheckInternetConnection1 checkInternetConnection1 = new CheckInternetConnection1(Signups_activity.this);
        boolean check = checkInternetConnection1.check1();
        if (check) {
            String s = Full_Name+" "+User_Email+" "+User_Name+" "+User_Type+" "+Token;
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Signups_activity.this, Otpac.class);
            intent.putExtra("NAME", Full_Name);
            intent.putExtra("EMAIL", User_Email);
            intent.putExtra("USERNAME", User_Name);
            intent.putExtra("PASSWORD", PassWord);
            intent.putExtra("TYPE", User_Type);
            intent.putExtra("Token", Token);
            startActivity(intent);


        } else {
            Toast.makeText(this, "Please make sure you have internet connection", Toast.LENGTH_SHORT).show();
        }
    }
}