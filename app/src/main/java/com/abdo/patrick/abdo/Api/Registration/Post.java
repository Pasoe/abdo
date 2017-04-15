package com.abdo.patrick.abdo.Api.Registration;

import android.os.AsyncTask;
import android.util.Log;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.Registration;
import com.google.gson.Gson;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.CheckedInputStream;

/**
 * Created by Khaled on 15-04-2017.
 */

public class Post extends AsyncTask<Registration, Void, Integer> {

    private String deviceId;
    private String childGuid;

    protected void onPreExecute() {
        deviceId = Application.getAndroidId(Application.getInstance().getApplicationContext());
        childGuid = Application.getInstance().getNewChild().getGuid();
    }


    protected Integer doInBackground(Registration... registration) {
        // Do some validation here

        try {
            URL url = new URL("http://abdoapi.azurewebsites.net/api/Registration/"+deviceId+"/"+childGuid);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("charset", "utf-8");
            try {
                urlConnection.connect();
                Gson gson = new Gson();
                String json = gson.toJson(registration[0]);
                Log.d("DATA", json);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
                outputStreamWriter.write(json);
                outputStreamWriter.flush();

                return urlConnection.getResponseCode();

            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return -1;
        }
    }

    @Override
    protected void onPostExecute(Integer response) {
        super.onPostExecute(response);

        if(response == HttpURLConnection.HTTP_OK )
            Log.i("Api: Registration", "Registration added successfully");
        else Log.i("Api: Registration", "An error occurred");

        Log.i("Api: Registration", "Server returned: "+response);

    }
}
