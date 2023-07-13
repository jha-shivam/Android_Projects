package com.example.vmeet.MYSQL.loginsignup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vmeet.Activities.LoadingData;

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

import static android.content.Context.MODE_PRIVATE;

/*
email id: ritikjha2101@gmail.com
 */
public class LoginBackGround extends AsyncTask<String, Void, String> {
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    @SuppressLint("StaticFieldLeak")
    private final EditText e1;
    @SuppressLint("StaticFieldLeak")
    private final EditText e2;

    public LoginBackGround(Context context, EditText e1, EditText e2) {
        this.context = context;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://helloshivamhello.000webhostapp.com/login.php";
        if (type.equals("Login")) {
            try {
                String User_Name = params[1];
                String PassWord = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                }
                String post_data = URLEncoder.encode("User_Name", "UTF-8") + "=" + URLEncoder.encode(User_Name, "UTF-8") + "&" + URLEncoder.encode("PassWord", "UTF-8") + "=" + URLEncoder.encode(PassWord, "UTF-8");
                if (bufferedWriter != null) {
                    bufferedWriter.write(post_data);
                }
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                }
                String result = "";
                String line = "";
                while (true) {
                    assert bufferedReader != null;
                    if (!((line = bufferedReader.readLine()) != null)) break;
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

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Login Success")) {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = context.getSharedPreferences("Shivam", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("User_Name", e1.getText().toString());
            editor.apply();
            context.startActivity(new Intent(context, LoadingData.class));

        } else {
            e1.setText("");
            e2.setText("");
            SharedPreferences sp = context.getSharedPreferences("Shivam", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("User_Name");
            editor.apply();
            editor.commit();
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }

    }

}

