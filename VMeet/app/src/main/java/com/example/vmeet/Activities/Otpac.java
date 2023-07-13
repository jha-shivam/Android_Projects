package com.example.vmeet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vmeet.ExtraClass.CheckNetworkConnectivity.CheckInternetConnection1;
import com.example.vmeet.R;
import com.google.android.material.textfield.TextInputLayout;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.Objects;

public class Otpac extends AppCompatActivity {
    CountryCodePicker ccp;
    TextInputLayout e1;
    EditText ee1;
    String Full_Name, User_Email, User_Name, PassWord, User_Type, Phone_Number, Number, Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpac);
        ccp = findViewById(R.id.country_code_picker_otpAc);
        e1 = findViewById(R.id.e1_otpAc);
        ee1 = findViewById(R.id.ee1_otpAc);
        Full_Name = getIntent().getStringExtra("NAME");
        User_Email = getIntent().getStringExtra("EMAIL");
        User_Name = getIntent().getStringExtra("USERNAME");
        PassWord = getIntent().getStringExtra("PASSWORD");
        User_Type = getIntent().getStringExtra("TYPE");
        Token = getIntent().getStringExtra("Token");
    }

    public void LoginOtp(View view) {
        Number = Objects.requireNonNull(ee1.getText().toString().trim());
        Phone_Number = ccp.getSelectedCountryCodeWithPlus() + Number;
        if (Phone_Number.length() != 12) {
            ee1.setError("Please Enter Valid Number");

        } else {
            ee1.setError("Please enter the number");
        }
        CheckInternetConnection1 checkInternetConnection1 = new CheckInternetConnection1(Otpac.this);
        boolean check = checkInternetConnection1.check1();
        if (check) {


            Intent intent = new Intent(Otpac.this, OtpValidate.class);
            intent.putExtra("Full_Name", Full_Name);
            intent.putExtra("User_Email", User_Email);
            intent.putExtra("User_Name", User_Name);
            intent.putExtra("PassWord", PassWord);
            intent.putExtra("User_Type", User_Type);
            intent.putExtra("Phone_Number", Phone_Number);
            intent.putExtra("Token", Token);
            startActivity(intent);


        } else {
            Toast.makeText(this, "Please make sure you have internet connection", Toast.LENGTH_SHORT).show();
        }
    }


    public void goToHomeFromOTP(View view) {
        startActivity(new Intent(Otpac.this, Signups_activity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Otpac.this, Signups_activity.class));
    }
}