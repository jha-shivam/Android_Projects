package com.example.vmeet.MYSQL.FetchUserData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.vmeet.Activities.MainActivity3;
import com.example.vmeet.SqliteData.UserDBHelperProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GetProfileData extends AsyncTask<String, Void, String> {
    String Url = "https://helloshivamhello.000webhostapp.com/getUserProfileData.php";
    @SuppressLint("StaticFieldLeak")
    Context context;
    UserDBHelperProfile userDBHelperProfile;
    SQLiteDatabase sqLiteDatabase;
    @SuppressLint("StaticFieldLeak")
    ProgressBar progressBar;

    public GetProfileData(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    String FullName;
    String UserEmail;
    String UserName;
    String Phone;
    String UserType;
    String Gender;
    String DOB;
    String HomeAddress;
    String City;
    String State;
    String Country;
    String PinCode;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... params) {
        String Username = params[0];
        try {
            URL url = new URL(Url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            String data = URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String response = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                response += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Fetching your data", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jo = new JSONObject(s);
            String success = jo.getString("success");
            JSONArray ja = jo.getJSONArray("data");
            if (success.equals("1")) {
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jsonObject = ja.getJSONObject(i);
                    FullName = jsonObject.getString("FullName");
                    UserEmail = jsonObject.getString("UserEmail");
                    UserName = jsonObject.getString("UserName");
                    Phone = jsonObject.getString("PhoneNumber");
                    UserType = jsonObject.getString("UserType");
                    Gender = jsonObject.getString("Gender");
                    DOB = jsonObject.getString("Dob");
                    HomeAddress = jsonObject.getString("HomeAddress");
                    City = jsonObject.getString("City");
                    State = jsonObject.getString("State");
                    Country = jsonObject.getString("Country");
                    PinCode = jsonObject.getString("PinCode");
                    userDBHelperProfile = new UserDBHelperProfile(context);
                    sqLiteDatabase = userDBHelperProfile.getWritableDatabase();
                    userDBHelperProfile.insertDataInDataBase(FullName, UserEmail, UserName, Phone, UserType, Gender, DOB, HomeAddress, City, State
                            , Country, PinCode, sqLiteDatabase);
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(context, MainActivity3.class);
                    context.startActivity(intent);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
