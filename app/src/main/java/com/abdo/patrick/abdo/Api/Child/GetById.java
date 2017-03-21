package com.abdo.patrick.abdo.Api.Child;

import android.os.AsyncTask;
import android.util.Log;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;

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

    protected void onPreExecute() {
        //Set a progressbar visible??
    }

    protected String doInBackground(String... id) {
        // Do some validation here

        try {
            URL url = new URL("http://abdoapi.azurewebsites.net/api/Child/" + id[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod( "GET" );
            urlConnection.setRequestProperty( "Content-Type", "application/json");
            urlConnection.setRequestProperty( "charset", "utf-8");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        else{

            try {
                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();

                Child child = new Child();
                child.setId(object.getInt("Id"));
                child.setCreatedTime(object.getString("CreatedTime"));
                child.setModifiedTime(object.getString("ModifiedTime"));
                child.setAnonymous(object.getJSONArray("Anonymous"));

                Log.d("DATA", child.toString());
                Application.getInstance().add_child(child);

                response = "DATA FETCHED INTO MODEL";

            } catch (JSONException e) {
                response = "JSONException occurred:" + e.getMessage();
            }

        }
        Log.i("INFO", response);
    }
}
