package com.example.vmeet.MYSQL.FetchUserData;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchImageForProfile {
    Context context;
    ImageView i1;

    public FetchImageForProfile(Context context, ImageView i1) {
        this.context = context;
        this.i1 = i1;
    }

    public void fetch() {
        final String mm = "https://myvidmeet.000webhostapp.com/fetchImage.php";
        StringRequest request11 = new StringRequest(Request.Method.POST, mm, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String success = jsonObject.getString("success");
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if (success.equals("1")) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String Ss = object.getString("User_Image");
                        Picasso.get().load(Ss).centerCrop().fit().into(i1);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue1 = Volley.newRequestQueue(context);
        requestQueue1.add(request11);
    }
}
