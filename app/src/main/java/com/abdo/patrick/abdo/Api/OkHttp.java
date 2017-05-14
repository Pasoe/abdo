package com.abdo.patrick.abdo.Api;

import android.util.Log;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Supplement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Khaled on 14-05-2017.
 */

public class OkHttp {

    private Gson gson = new Gson();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    public void post(String url, String json) throws IOException{

        RequestBody body = RequestBody.create(JSON, json);
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful())
                    LogFailure(response, "POST");
                else
                  LogSuccess(response, "POST", null);
            }
        });
    }

    public void get(String url, final Class<?> classOfT) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful())
                        LogFailure(response, "GET");
                else
                {
                    String responseData = response.body().string();
                    LogSuccess(response, "GET", responseData);

                    if (classOfT == Allergy.class){
                        ArrayList<Allergy> list = gson.fromJson(responseData,
                                new TypeToken<ArrayList<Allergy>>(){}.getType());

                        Application.getInstance().set_allergyList(list);
                        Application.getInstance().AddItemToPreference("Allergies", list);
                        Log.i("OkHttp", "Retrieved " +list.size()+" allergies.");
                    }
                    //Allergies

                    if (classOfT == Supplement.class){
                        ArrayList<Supplement> list = gson.fromJson(responseData,
                                new TypeToken<ArrayList<Supplement>>(){}.getType());

                        Application.getInstance().set_supplementList(list);
                        Application.getInstance().AddItemToPreference("Supplements", list);
                        Log.i("OkHttp", "Retrieved " +list.size()+" supplements.");
                    }
                    //Supplement
                }
            }
        });
    }


    private void LogFailure(final Response response, String call){
        Log.i("OkHttp",
                "Failure : {"+call+"}             \n" +
                "Protocol: "+response.protocol()+"\n" +
                "Code    : "+response.code()    +"\n" +
                "Message : "+response.message() +"\n" +
                "URL     : "+response.networkResponse().request().url());

    }

    private void LogSuccess(final Response response, String call, String responseData){
        Log.i("OkHttp",
                "Success : {"+call+"}             \n" +
                "Code    : "+response.code()    +"\n" +
                "Response: "+responseData       +"\n" +
                "URL     : "+response.networkResponse().request().url());

    }
}
