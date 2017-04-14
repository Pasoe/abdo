package com.abdo.patrick.abdo.Api.Anonymous;

import android.os.AsyncTask;
import android.util.Log;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.google.gson.Gson;


import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Post extends AsyncTask<Anonymous, Void, Integer> {

    protected void onPreExecute() {

    }
    private Anonymous _anonymous;

    protected Integer doInBackground(Anonymous... anonymous) {
        // Do some validation here
        _anonymous = anonymous[0];

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

        //If anonymous is created or anonymous already exists
        if(response == HttpURLConnection.HTTP_OK ||
                response == HttpURLConnection.HTTP_CONFLICT)
        {
            if(response == HttpURLConnection.HTTP_CONFLICT)
                Log.i("INFO", "User already exists");

            Application.getInstance().AddItemToPreference("Anonymous", _anonymous);
        }


        Log.i("SERVER", "Server returned: "+response);

    }
}
