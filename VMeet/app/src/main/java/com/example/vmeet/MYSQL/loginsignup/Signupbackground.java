package com.example.vmeet.MYSQL.loginsignup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

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

public class Signupbackground extends AsyncTask<String, String, String> {
    @SuppressLint("StaticFieldLeak")
    Context context;


    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        if (aVoid.equals("Account created")) {
            Toast.makeText(context, aVoid, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, aVoid, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Toast.makeText(context, "Making your account", Toast.LENGTH_SHORT).show();
    }

    public Signupbackground(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... voids) {
        String urL = "https://helloshivamhello.000webhostapp.com/RegisterUser.php";
        String Method = voids[0];
        if (Method.equals("register")) {
            String Full_Name = voids[1];
            String User_Email = voids[2];
            String User_Name = voids[3];
            String PassWord = voids[4];
            String User_Type = voids[5];
            String Phone_Number = voids[6];
            String token = voids[7];
            try {
                URL url = new URL(urL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("Full_Name", "UTF-8") + "=" + URLEncoder.encode(Full_Name, "UTF-8") + "&" +
                        URLEncoder.encode("User_Email", "UTF-8") + "=" + URLEncoder.encode(User_Email, "UTF-8") + "&" +
                        URLEncoder.encode("User_Name", "UTF-8") + "=" + URLEncoder.encode(User_Name, "UTF-8") + "&" +
                        URLEncoder.encode("PassWord", "UTF-8") + "=" + URLEncoder.encode(PassWord, "UTF-8") + "&" +
                        URLEncoder.encode("User_Type", "UTF-8") + "=" + URLEncoder.encode(User_Type, "UTF-8") + "&" +
                        URLEncoder.encode("Phone_Number", "UTF-8") + "=" + URLEncoder.encode(Phone_Number, "UTF-8") + "&" +
                        URLEncoder.encode("Token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
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
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
