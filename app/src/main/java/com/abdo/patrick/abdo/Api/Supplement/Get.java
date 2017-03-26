package com.abdo.patrick.abdo.Api.Supplement;

import android.os.AsyncTask;
import android.util.Log;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Supplement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Khaled on 26-03-2017.
 */

public class Get extends AsyncTask<Void, Void, String> {

    protected void onPreExecute() {

    }

    protected String doInBackground(Void... params) {
        // Do some validation here

        try {
            URL url = new URL("http://abdoapi.azurewebsites.net/api/Supplement");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("charset", "utf-8");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
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
        if (response == null) {
            response = "THERE WAS AN ERROR";
        } else {

            try {
                JSONArray array = new JSONArray(response);
                ArrayList<Supplement> list = new ArrayList<>();

                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject object = array.getJSONObject(i);
                    Supplement supplement = new Supplement();
                    supplement.setId(object.getInt("Id"));
                    supplement.setDescription(object.getString("Description"));
                    supplement.setCreatedTime(object.getString("CreatedTime"));
                    supplement.setModifiedTime(object.getString("ModifiedTime"));

                    list.add(supplement);
                    Log.d("DATA, iterate: "+i, supplement.toString());

                }

                Application.getInstance().set_supplementsList(list);
                response = "DATA FETCHED INTO MODEL";

            } catch (JSONException e) {
                response = "JSONException occurred:" + e.getMessage();
            }

        }
        Log.i("INFO", response);
    }
}
