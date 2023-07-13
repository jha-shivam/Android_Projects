package com.example.vmeet.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vmeet.MYSQL.UpdateUserData.UpdateMainProfileData;
import com.example.vmeet.R;
import com.example.vmeet.SqliteData.UserDBHelperProfile;

import java.util.Calendar;

public class Main_Profile extends AppCompatActivity {
    ImageView i1;
    Button b1;
    EditText e1, e2, e3, e5;
    Spinner ss1, ss2;
    TextView t1, t2, t3, t4;
    String HomeAddress, City, State, Country, PinCode;
    SQLiteDatabase sqLiteDatabase;
    UserDBHelperProfile userDBHelperProfile;
    Cursor cursor;
    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__profile);
        b1 = findViewById(R.id.b1_MainProfile);
        i1 = findViewById(R.id.i1_Mainprofile);
        b1 = findViewById(R.id.b2_MainProfile);
        t1 = findViewById(R.id.t1_Mainprofile);
        t2 = findViewById(R.id.t2_Mainprofile);
        ss2 = findViewById(R.id.s2_Mainprofile);
        e1 = findViewById(R.id.ee1_Mainprofile);
        e2 = findViewById(R.id.ee2_Mainprofile);
        e3 = findViewById(R.id.ee3_Mainprofile);
        e5 = findViewById(R.id.ee5_Mainprofile);
        t4 = findViewById(R.id.t4_Mainprofile);
        t3 = findViewById(R.id.t3_Mainprofile);
        ss1 = findViewById(R.id.s1_Mainprofile);
        editprofilegetdata();
        setDropDownMenu();

    }

    private void setDropDownMenu() {
        //Set The Options For First Spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Main_Profile.this, android.R.layout.simple_list_item_1
                , getResources().getStringArray(R.array.numbers));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ss1.setAdapter(arrayAdapter);
        //Set The Options For First Spinner
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(Main_Profile.this, android.R.layout.simple_list_item_1
                , getResources().getStringArray(R.array.esehi));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ss2.setAdapter(arrayAdapter1);
        //First Spinner Selecting
        ss1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t1.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Main_Profile.this, "Please choose your usertype", Toast.LENGTH_SHORT).show();
            }
        });
        //Second Spinner Selecting
        ss2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t2.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Main_Profile.this, "Please choose your gender", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editprofilegetdata() {
        String FullName, UserEmail, UserName, Phone, UserType, Gender, DOB;
        userDBHelperProfile = new UserDBHelperProfile(this);
        sqLiteDatabase = userDBHelperProfile.getReadableDatabase();
        cursor = userDBHelperProfile.getInformation(sqLiteDatabase);
        if (cursor.moveToFirst()) {
            do {
                FullName = cursor.getString(0);
                UserEmail = cursor.getString(2);
                UserName = cursor.getString(1);
                Phone = cursor.getString(3);
                UserType = cursor.getString(4);
                Gender = cursor.getString(5);
                DOB = cursor.getString(6);
                HomeAddress = cursor.getString(7);
                City = cursor.getString(8);
                State = cursor.getString(9);
                Country = cursor.getString(10);
                PinCode = cursor.getString(11);
                if (DOB.equals("N-A")) {
                    DOB = "Birth-Date";
                }
                e1.setText(FullName);
                e2.setText(UserEmail);
                e3.setText(UserName);
                t1.setText(UserType);
                e5.setText(Phone);
                t2.setText(Gender);
                t3.setText(DOB);


            } while (cursor.moveToNext());
        }

    }

    public void Updateprofile() {
        String FullName = e1.getText().toString();
        String User_Email = e2.getText().toString();
        String User_Name = e3.getText().toString();
        String User_Type = ss1.getSelectedItem().toString();
        String Phone_Number = e5.getText().toString();
        String Gender = ss2.getSelectedItem().toString();
        String Dob = t3.getText().toString();
        String Method = "Update";
        UpdateMainProfileData updateMainProfileData = new UpdateMainProfileData(Main_Profile.this);
        updateMainProfileData.execute(Method, FullName, User_Email, User_Name, User_Type, Phone_Number, Gender, Dob);
        editprofilegetdata();
    }


    public void b1click(View view) {
        Updateprofile();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Main_Profile.this, Profile.class));
    }


    public void LocationAccepter(View view) {
        Intent intent = new Intent(Main_Profile.this, Location.class);
        intent.putExtra("HomeAddress", HomeAddress);
        intent.putExtra("City", City);
        intent.putExtra("State", State);
        intent.putExtra("Country", Country);
        intent.putExtra("PinCode", PinCode);
        startActivity(intent);
    }

    public void SelectDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int Year = calendar.get(Calendar.YEAR);
        int Month = calendar.get(Calendar.MONTH);
        int Day = calendar.get(Calendar.DAY_OF_MONTH);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(Main_Profile.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    dateSetListener, Year, Month, Day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        }
        dateSetListener = (view1, year, month, dayOfMonth) -> {
            month = month + 1;
            String Date = dayOfMonth + "/" + month + "/" + year;
            t3.setText(Date);
        };
    }
}