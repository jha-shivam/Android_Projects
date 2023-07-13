package com.example.vmeet.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vmeet.R;
import com.example.vmeet.SqliteData.UserDBHelperProfile;

public class Profile extends AppCompatActivity {
    public TextView t1, t2, t3, t4, t5;
    ImageView i1;
    UserDBHelperProfile userDBHelperProfile;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    String FullName, UserEmail, UserName, Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        i1 = findViewById(R.id.i1_editpersonalprofile);
        t1 = findViewById(R.id.t2_personalprofile);
        t2 = findViewById(R.id.t3_personalprofile);
        t3 = findViewById(R.id.t4_personalprofile);
        t4 = findViewById(R.id.t5_personalprofile);
        t5 = findViewById(R.id.b2_personalprofile);
        fetchInfo();
        t5.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, Main_Profile.class);
            startActivity(intent);
        });
    }


    public void fetchInfo() {
        userDBHelperProfile = new UserDBHelperProfile(this);
        sqLiteDatabase = userDBHelperProfile.getReadableDatabase();
        cursor = userDBHelperProfile.getInformation(sqLiteDatabase);
        if (cursor.moveToFirst()) {
            do {
                FullName = cursor.getString(0);
                UserEmail = cursor.getString(2);
                UserName = cursor.getString(1);
                Phone = cursor.getString(3);
            } while (cursor.moveToNext());
        }
        t1.setText(FullName);
        t2.setText(UserEmail);
        t3.setText(UserName);
        t4.setText(Phone);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Profile.this, MainActivity3.class));
    }
}