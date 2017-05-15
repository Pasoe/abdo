package com.abdo.patrick.abdo;

import android.util.Log;

import com.abdo.patrick.abdo.Api.OkHttp;
import com.abdo.patrick.abdo.Domain.Application;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.Feces;
import com.abdo.patrick.abdo.Models.Food;
import com.abdo.patrick.abdo.Models.FoodCategory;
import com.abdo.patrick.abdo.Models.Supplement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class OkHttpTest {

    private OkHttpClient client = new OkHttpClient();

    @Test
    public void GetControllerName() throws Exception {
        final Request request = new Request.Builder()
                .url("http://abdoapi.azurewebsites.net/api/Allergy")
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
                    LogSuccess(response, "GET", response.body().string());
                    HttpUrl url = response.networkResponse().request().url();
                    System.out.println(url.toString());
                }
            }
        });
    }


    /*@Test
    public void GetAllergies() throws Exception {
        OkHttp httpController = new OkHttp();
        String result = httpController.get("http://abdoapi.azurewebsites.net/api/Allergy", Allergy.class);
        System.out.println(result);

        //Option 1: Fetch by JsonArray/JsonObject
        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println("Allergy type: " + jsonObject.getString("Type"));
        }

        //Option 2: Fetch by Gson()
        Gson gson = new Gson();
        ArrayList<Allergy> list = gson.fromJson(result, new TypeToken<ArrayList<Allergy>>(){}.getType());
        System.out.println(list.toString());


        //Check result for unit test
        assertNotEquals(result, "");
    }*/

    /*@Test
    public void GetChildById() throws Exception {
        //Child GUID: 73585885-6BBD-43EA-A74D-C6D4FCECB602
        //DeviceId: DBC0191D-4E9A-41C8-9BDC-AEE8867F34D8
        //Name: Jens Jensen

        String guid = "73585885-6BBD-43EA-A74D-C6D4FCECB602";
        String deviceId = "DBC0191D-4E9A-41C8-9BDC-AEE8867F34D8";
        String name = "";
        OkHttp httpController = new OkHttp();
        String result = httpController.get("http://abdoapi.azurewebsites.net/api/Child/"+deviceId+"/"+guid);
        System.out.println(result);

        Gson gson = new Gson();
        Child child = gson.fromJson(result, Child.class);
        System.out.println(child.toString());
        name = child.getChildInfo().getName();

        assertEquals(name, "Jens Jensen");
    }*/


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