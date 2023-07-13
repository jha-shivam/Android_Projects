package com.example.vmeet.MYSQL.UpdateUserData;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UploadPhotoFromMainProfile {
    Context context;
    String UserName, EncodedImage;

    public UploadPhotoFromMainProfile(Context context, String userName, String encodedImage) {
        this.context = context;
        UserName = userName;
        EncodedImage = encodedImage;
    }

    public void uploadPhoto() {
        String MYY = "https://helloshivamhello.000webhostapp.com/image.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MYY, response -> Toast.makeText(context, response, Toast.LENGTH_SHORT).show(), error -> Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("image", EncodedImage);
                map.put("User_Name", UserName);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        queue.add(stringRequest);
    }
}
