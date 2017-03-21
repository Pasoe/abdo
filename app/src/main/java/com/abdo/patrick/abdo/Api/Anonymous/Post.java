package com.abdo.patrick.abdo.Api.Anonymous;

import android.os.AsyncTask;
import android.util.Log;

import com.abdo.patrick.abdo.Models.Anonymous;
import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Post extends AsyncTask<Anonymous, Void, String> {

    protected void onPreExecute() {

    }

    protected String doInBackground(Anonymous... anonymous) {
        // Do some validation here

        try {
            URL url = new URL("http://abdoapi.azurewebsites.net/api/Anonymous");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("charset", "utf-8");
            try {
                urlConnection.connect();
                Gson gson = new Gson();
                String json = gson.toJson(anonymous[0]);
                Log.d("DATA", json);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
                outputStreamWriter.write(json);
                outputStreamWriter.flush();

                int HttpResult = urlConnection.getResponseCode();
                StringBuilder stringBuilder = new StringBuilder();
                if (HttpResult == HttpURLConnection.HTTP_OK){

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                }
                else{
                    stringBuilder.append(urlConnection.getResponseMessage());
                }

                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {

        Log.i("INFO", response);

    }
}
