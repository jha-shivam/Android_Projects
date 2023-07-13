package com.example.vmeet.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.vmeet.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Location extends AppCompatActivity {
    Button b1, b2;
    EditText ee1, ee2, ee3, ee4, ee5;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 1;
    String s1, s2, s3, s4, s5;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        s1 = getIntent().getStringExtra("HomeAddress");
        s2 = getIntent().getStringExtra("City");
        s3 = getIntent().getStringExtra("State");
        s4 = getIntent().getStringExtra("Country");
        s5 = getIntent().getStringExtra("PinCode");
        ee1 = findViewById(R.id.ee1_location);
        ee2 = findViewById(R.id.ee2_location);
        ee3 = findViewById(R.id.ee3_location);
        ee4 = findViewById(R.id.ee4_location);
        ee5 = findViewById(R.id.ee5_location);
        b1 = findViewById(R.id.GetCurrentLocation);
        b2 = findViewById(R.id.b2_Location);
        //Initialize fusedlocation providerclient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        ee1.setText(Html.fromHtml("<font color='#6200EE'><b>Address: </b><br></font>"
                + s1
        ));
        ee2.setText(Html.fromHtml("<font color='#6200EE'><b>City: </b><br></font>"
                + s2
        ));
        ee3.setText(Html.fromHtml("<font color='#6200EE'><b>State: </b><br></font>"
                + s3
        ));
        ee4.setText(Html.fromHtml("<font color='#6200EE'><b>Country: </b><br></font>"
                + s4
        ));
        ee5.setText(Html.fromHtml("<font color='#6200EE'><b>Pin: </b><br></font>"
                + s5
        ));

    }

    public void updateLocation(View view) {
    }

    public void getLocation(View view) {
        if (ActivityCompat.checkSelfPermission(Location.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //when permission granted
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                //Intialize location
                android.location.Location location = task.getResult();
                if (location != null) {

                    try {
                        //Initalize geoCoder

                        Geocoder geocoder = new Geocoder(Location.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1);
                        //set Latitude on EditText
                        ee1.setText(Html.fromHtml("<font color='#6200EE'><b>Address: </b><br></font>"
                                + addresses.get(0).getAddressLine(0)
                        ));
                        //set Longitude on EditText
                        ee2.setText(Html.fromHtml("<font color='#6200EE'><b>City: </b><br></font>"
                                + addresses.get(0).getAdminArea()
                        ));
                        //set Country Name
                        ee3.setText(Html.fromHtml("<font color='#6200EE'><b>State: </b><br></font>"
                                + addresses.get(0).getLocality()
                        ));
                        //set locality
                        ee4.setText(Html.fromHtml("<font color='#6200EE'><b>Country: </b><br></font>"
                                + addresses.get(0).getCountryName()
                        ));
                        //set Address
                        ee5.setText(Html.fromHtml("<font color='#6200EE'><b>Pin: </b><br></font>"
                                + addresses.get(0).getPostalCode()
                        ));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        } else {
            //when permission denied
            ActivityCompat.requestPermissions(Location.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSION_LOCATION);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}