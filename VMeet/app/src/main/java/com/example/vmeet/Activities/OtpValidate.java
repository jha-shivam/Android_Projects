package com.example.vmeet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.vmeet.ExtraClass.CheckNetworkConnectivity.CheckInternetConnection1;
import com.example.vmeet.MYSQL.loginsignup.Signupbackground;
import com.example.vmeet.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/*
email id: shivamjha2101@gmail.com(firebase)
 */
public class OtpValidate extends AppCompatActivity {
    Button b1;
    String codeBySystem;
    ProgressBar progressBar;
    PinView pinView;
    FirebaseAuth mAuth;
    String Full_Name, User_Email, User_Name, PassWord, User_Type, Token, Phone_Number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_validate);
        b1 = findViewById(R.id.button1_otpValidate);
        pinView = findViewById(R.id.PinView_otpValidate);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar_otpValidate);
        Full_Name = getIntent().getStringExtra("Full_Name");
        User_Email = getIntent().getStringExtra("User_Email");
        User_Name = getIntent().getStringExtra("User_Name");
        PassWord = getIntent().getStringExtra("PassWord");
        User_Type = getIntent().getStringExtra("User_Type");
        Phone_Number = getIntent().getStringExtra("Phone_Number");
        Token = getIntent().getStringExtra("Token");

        CheckInternetConnection1 checkInternetConnection1 = new CheckInternetConnection1(OtpValidate.this);
        boolean check = checkInternetConnection1.check1();
        if (check) {
            progressBar.setVisibility(View.VISIBLE);
            sendVerificationCode(Phone_Number);
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please make sure you have internet connection", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(OtpValidate.this, Otpac.class));
        }


    }

    private void sendVerificationCode(String s6) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(s6)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                pinView.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OtpValidate.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(phoneAuthCredential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        String Method = "register";
                        Signupbackground signupbackground = new Signupbackground(OtpValidate.this);
                        signupbackground.execute(Method, Full_Name, User_Email, User_Name, PassWord, User_Type, Phone_Number, Token);
                        startActivity(new Intent(OtpValidate.this, MainActivity2.class));
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(OtpValidate.this, "Please enter correct OTP", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(OtpValidate.this, Otpac.class));
                        }
                    }
                });
    }

    public void goToOtpAcFromOtpValidate(View view) {
        startActivity(new Intent(OtpValidate.this, Otpac.class));
    }

    public void callNextScreenFromOTP(View view) {
        String code = Objects.requireNonNull(pinView.getText()).toString();
        if (code.isEmpty()) {
            pinView.setError("Please enter otp");
        } else {
            verifyCode(code);
        }

    }
}