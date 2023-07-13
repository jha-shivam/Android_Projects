package com.example.vmeet.MYSQL.UpdateUserData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static android.content.Context.MODE_PRIVATE;

public class UpdateMainProfileData extends AsyncTask<String, Void, String> {
    public static final String MyUrl1 = "https://helloshivamhello.000webhostapp.com/UpdateUserProfile.php";
    @SuppressLint("StaticFieldLeak")
    Context context;
    String User_Name;

    public UpdateMainProfileData(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        if (method.equals("Update")) {
            String Full_Name = params[1];
            String User_Email = params[2];
            User_Name = params[3];
            String User_Type = params[4];
            String Phone_Number = params[5];
            String Gender = params[6];
            String Dob = params[7];
            URL url;
            try {
                url = new URL(MyUrl1);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                }
                String data = URLEncoder.encode("Full_Name", "UTF-8") + "=" + URLEncoder.encode(Full_Name, "UTF-8") + "&" +
                        URLEncoder.encode("User_Email", "UTF-8") + "=" + URLEncoder.encode(User_Email, "UTF-8") + "&" +
                        URLEncoder.encode("User_Name", "UTF-8") + "=" + URLEncoder.encode(User_Name, "UTF-8") + "&" +
                        URLEncoder.encode("User_Type", "UTF-8") + "=" + URLEncoder.encode(User_Type, "UTF-8") + "&" +
                        URLEncoder.encode("Phone_Number", "UTF-8") + "=" + URLEncoder.encode(Phone_Number, "UTF-8") + "&" +
                        URLEncoder.encode("Gender", "UTF-8") + "=" + URLEncoder.encode(Gender, "UTF-8") + "&" +
                        URLEncoder.encode("Dob", "UTF-8") + "=" + URLEncoder.encode(Dob, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                }
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Toast.makeText(context, "Updating your profile please wait", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

    }
}
