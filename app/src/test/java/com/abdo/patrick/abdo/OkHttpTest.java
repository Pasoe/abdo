package com.abdo.patrick.abdo;

import com.abdo.patrick.abdo.Api.OkHttp;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Child;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class OkHttpTest {
    @Test
    public void GetAllergies() throws Exception {
        OkHttp httpController = new OkHttp();
        String result = httpController.get("http://abdoapi.azurewebsites.net/api/Allergy");
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

    @Test
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
    }


}