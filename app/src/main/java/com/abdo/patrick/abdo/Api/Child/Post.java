package com.abdo.patrick.abdo.Api.Child;

import android.os.AsyncTask;
import android.util.Log;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Post extends AsyncTask<Child, Void, Integer> {

    protected void onPreExecute() {

    }

    protected Integer doInBackground(Child... child) {
        // Do some validation here

        try {
            URL url = new URL("http://abdoapi.azurewebsites.net/api/Child/"+Application.getAndroidId(Application.getInstance().getApplicationContext())+"/"+child[0].getGuid());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("charset", "utf-8");
            try {
                urlConnection.connect();
                Gson gson = new Gson();
                String json = gson.toJson(child[0]);
                Log.i("INFO", "Json der sendes: "+json);

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

            Application.getInstance().removeNewChild();
            Log.i("INFO", "Removed NewChild from shared preferences");
        }

        Log.i("SERVER", "Server returned: "+response);

    }
}
