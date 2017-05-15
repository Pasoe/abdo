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
import java.util.List;

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
                .url("http://abdoapi.azurewebsites.net/api/allergy")
                .build();

        Response response = client.newCall(request).execute();
        HttpUrl url = response.networkResponse().request().url();

        //0 = "api", 1 = {controller name}, 2..n = arguments
        List<String> pathSegments = url.encodedPathSegments();
        System.out.println("Controller name: "+pathSegments.get(1));
    }


    @Test
    public void GetAllergies() throws Exception {
        final Request request = new Request.Builder()
                .url("http://abdoapi.azurewebsites.net/api/allergy")
                .build();

        Response response = client.newCall(request).execute();
        String result = response.body().string();
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
    }


}