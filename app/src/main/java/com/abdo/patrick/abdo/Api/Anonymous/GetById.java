package com.abdo.patrick.abdo.Api.Anonymous;

import android.os.AsyncTask;
import android.util.Log;

import com.abdo.patrick.abdo.Views.ApiTestAcitivty;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Anonymous;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Khaled on 21-03-2017.
 */

public class GetById extends AsyncTask<String, Void, String> {

    private ApiTestAcitivty _apiTestAcitivty;

    public GetById(){}

    public GetById(ApiTestAcitivty apiTestAcitivty) {
        _apiTestAcitivty = apiTestAcitivty;
    }

    protected void onPreExecute() {

    }

    protected String doInBackground(String... deviceId) {
        // Do some validation here

        try {
            URL url = new URL("http://abdoapi.azurewebsites.net/api/Anonymous/?deviceId=" + deviceId[0]);
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
                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();

                Anonymous anonymous = new Anonymous();
                anonymous.setId(object.getInt("Id"));
                anonymous.setDeviceId(object.getString("DeviceId"));
                anonymous.setDeviceName(object.getString("DeviceName"));
                anonymous.setCreatedTime(object.getString("CreatedTime"));
                anonymous.setModifiedTime(object.getString("ModifiedTime"));
                anonymous.setChildren(object.getJSONArray("Children"));

                Log.d("DATA", anonymous.toString());
                Application.getInstance().add_anonymous(anonymous);

                if (_apiTestAcitivty != null) _apiTestAcitivty.getTextView().setText(anonymous.toString());

                response = "DATA FETCHED INTO MODEL";

            } catch (JSONException e) {
                response = "JSONException occurred:" + e.getMessage();
            }

        }
        Log.i("INFO", response);
    }

}
