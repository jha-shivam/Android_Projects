package com.example.vmeet.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vmeet.R;
import com.example.vmeet.SqliteData.UserDBHelperProfile;

public class Logout extends AppCompatActivity {
    TextView t1;
    Button b1;
    UserDBHelperProfile userDBHelperProfile;
    SQLiteDatabase sqLiteDatabase;
    String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        t1 = findViewById(R.id.tt);
        b1 = findViewById(R.id.button3);
        SharedPreferences sp = getSharedPreferences("Shivam", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (sp.contains("User_Name")) {
            UserName = sp.getString("User_Name", "");
            t1.setText(UserName);
        }
        b1.setOnClickListener(v -> slogout());

    }

    private void slogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Logout.this);
        builder.setTitle("Logout")
                .setMessage("Are you sure....?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    deleteContact();
                    SharedPreferences sp = getSharedPreferences("Shivam", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    if (sp.contains("User_Name")) {
                        editor.remove("User_Name");
                        editor.apply();
                        editor.commit();
                        Toast.makeText(Logout.this, "Log out succesfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Logout.this, MainActivity2.class));
                        finish();
                    }
                })
                .setNegativeButton("No", (dialog, which) -> Toast.makeText(Logout.this, "Logout cancel", Toast.LENGTH_SHORT).show()).show();
    }

    public void deleteContact() {
        userDBHelperProfile = new UserDBHelperProfile(getApplicationContext());
        sqLiteDatabase = userDBHelperProfile.getReadableDatabase();
        userDBHelperProfile.deleteInformation(UserName, sqLiteDatabase);
    }
}