package com.abdo.patrick.abdo.Api;

import android.util.Log;

import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.Feces;
import com.abdo.patrick.abdo.Models.Food;
import com.abdo.patrick.abdo.Models.FoodCategory;
import com.abdo.patrick.abdo.Models.ShareCode;
import com.abdo.patrick.abdo.Models.Supplement;
import com.abdo.patrick.abdo.Views.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Khaled on 14-05-2017.
 */

public class OkHttp {

    public OkHttp(){}

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    private Gson gson = new Gson();

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
                {
                    LogSuccess(response, "POST", null);
                    HttpUrl url = response.networkResponse().request().url();

                    //0 = "api", 1 = {controller name}, 2..n = arguments
                    List<String> pathSegments = url.encodedPathSegments();
                    String controllerName = pathSegments.get(1);

                    switch (controllerName)
                    {
                        case "child":
                            //Clear tmp values for the posted child
                            Application.getInstance().removeNewChild();
                            break;
                    }
                }
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
                        Application.getInstance().addItemToPreference("Allergies", list);
                        Log.i("OkHttp", "Retrieved " +list.size()+" allergies.");
                    }
                    //Allergies

                    if (classOfT == Supplement.class){
                        ArrayList<Supplement> list = gson.fromJson(responseData,
                                new TypeToken<ArrayList<Supplement>>(){}.getType());

                        Application.getInstance().set_supplementList(list);
                        Application.getInstance().addItemToPreference("Supplements", list);
                        Log.i("OkHttp", "Retrieved " +list.size()+" supplements.");
                    }
                    //Supplement

                    if (classOfT == Food.class){
                        ArrayList<Food> list = gson.fromJson(responseData,
                                new TypeToken<ArrayList<Food>>(){}.getType());

                        Application.getInstance().set_foodList(list);
                        Application.getInstance().addItemToPreference("Foods", list);
                        Log.i("OkHttp", "Retrieved " +list.size()+" foods.");
                    }
                    //Foods

                    if (classOfT == Feces.class){
                        ArrayList<Feces> list = gson.fromJson(responseData,
                                new TypeToken<ArrayList<Feces>>(){}.getType());

                        Application.getInstance().set_fecesList(list);
                        Application.getInstance().addItemToPreference("Feces", list);
                        Log.i("OkHttp", "Retrieved " +list.size()+" feces.");
                    }
                    //Feces

                    if (classOfT == FoodCategory.class){
                        ArrayList<FoodCategory> list = gson.fromJson(responseData,
                                new TypeToken<ArrayList<FoodCategory>>(){}.getType());

                        Application.getInstance().set_foodCategoryList(list);
                        Application.getInstance().addItemToPreference("FoodCategories", list);
                        Log.i("OkHttp", "Retrieved " +list.size()+" food categories.");
                    }
                    //Food categories

                    if (classOfT == Anonymous.class){
                        Anonymous anonymous = gson.fromJson(responseData, Anonymous.class);

                        Application.getInstance().set_anonymous(anonymous);
                        Log.i("OkHttp", "Retrieved the following anonymous\n" +
                                "Device id  : "+anonymous.getDeviceId()+"\n" +
                                "Device name: "+anonymous.getDeviceName());
                    }
                    //Anonymous

                    if (classOfT == Child.class){
                        Child child = gson.fromJson(responseData, Child.class);
                        Application.getInstance().updateCurrentChildData(child);
                        Log.i("OkHttp", "Retrieved the following child\n" + child.toString());

                    }
                    //Child
                }
            }
        });
    }


    public void put(String url, String json) throws IOException{

        RequestBody body = RequestBody.create(JSON, json);
        final Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful())
                    LogFailure(response, "PUT");
                else
                {
                    LogSuccess(response, "PUT", null);
                    HttpUrl url = response.networkResponse().request().url();

                    //0 = "api", 1 = {controller name}, 2..n = arguments
                    List<String> pathSegments = url.encodedPathSegments();
                    String controllerName = pathSegments.get(1);

                    switch (controllerName)
                    {
                        case "child":
                            //Child updated successfully
                            break;
                    }
                }
            }
        });
    }

    public static void LogFailure(final Response response, String call){
        Log.i("OkHttp",
                "Failure : {"+call+"}             \n" +
                "Protocol: "+response.protocol()+"\n" +
                "Code    : "+response.code()    +"\n" +
                "Message : "+response.message() +"\n" +
                "URL     : "+response.networkResponse().request().url());

    }

    public static void LogSuccess(final Response response, String call, String responseData){
        Log.i("OkHttp",
                "Success : {"+call+"}             \n" +
                "Code    : "+response.code()    +"\n" +
                "Response: "+responseData       +"\n" +
                "URL     : "+response.networkResponse().request().url());

    }
}
