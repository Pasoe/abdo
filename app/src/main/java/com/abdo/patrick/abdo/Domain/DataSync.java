package com.abdo.patrick.abdo.Domain;

import android.content.SharedPreferences;
import android.util.Log;

import com.abdo.patrick.abdo.Api.OkHttp;
import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.Feces;
import com.abdo.patrick.abdo.Models.Food;
import com.abdo.patrick.abdo.Models.FoodCategory;
import com.abdo.patrick.abdo.Models.Supplement;
import com.abdo.patrick.abdo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.Period;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Patrick on 16-05-2017.
 */

public class DataSync {

    private SharedPreferences settings;
    private Gson gson;
    private OkHttp okHttp;

    public DataSync(){
        settings = Application.getInstance().getApplicationContext().getSharedPreferences("Abdo", MODE_PRIVATE);
        gson = new Gson();
        okHttp = new OkHttp();
    }

    public void syncAllData(){
        seedAnonymousData();
        seedChildData();
        syncStaticData();
    }

    public void seedAnonymousData(){
        String anonFromPref = settings.getString("Anonymous", "");
        boolean registered = settings.getBoolean("REG", false);
        Anonymous anonymous;

        if(Application.getInstance().get_anonymous() != null){
            Log.i("INFO", "Anonymous already set in application.");
            if(!registered){
                Log.i("INFO", "Anonymous not registered on server. Registering anonymous.");
                postNewAnonymous(Application.getInstance().get_anonymous());
            }
        }else{
            if(anonFromPref == null || anonFromPref == ""){
                Log.i("INFO", "No anonymous found in prefs. Creating new.");
                anonymous = new Anonymous(Application.getAndroidId(Application.getInstance().getApplicationContext()), android.os.Build.MODEL);
                postNewAnonymous(anonymous);
                Application.getInstance().addItemToPreference("Anonymous", anonymous);
            }else{
                String json = settings.getString("Anonymous", "");
                anonymous = gson.fromJson(json, Anonymous.class);
                Log.i("INFO", "Retrieved anonymous from shared preferences");
            }
            Application.getInstance().set_anonymous(anonymous);
        }
    }

    private void postNewAnonymous(Anonymous anonymous){
        String json = gson.toJson(anonymous);
        try
        {
            okHttp.post(Application.getInstance().getString(R.string.api_anonymous), json);
        }
        catch (IOException e)
        {
            //TODO:
            e.printStackTrace();
        }
        Log.i("INFO", "Anonymous created on server");
    }

    public void seedChildData(){
        //Get child data from pref.
        String children_json = settings.getString("Children", "");
        String currentChild_json = settings.getString("CurrentChild", "");
        ArrayList<Child> children = gson.fromJson(children_json, new TypeToken<ArrayList<Child>>(){}.getType());

        if(Application.getInstance().get_anonymous() != null){
            if(Application.getInstance().get_anonymous().childrenExists()){
                Log.i("INFO", "Children already set in application.");
            }else{
                if(children_json.isEmpty()){
                    Log.i("INFO", "No children found in shared preferences");
                } else {
                    Application.getInstance().set_children(children);
                    Log.i("INFO", "Children fetched from shared preferences");
                    Log.i("INFO", "Children fetched: " + children_json);
                }
            }

            if(Application.getInstance().getCurrentChild() != null){
                Log.i("INFO", "Current child already set in application.");
            }else{
                if (currentChild_json.isEmpty()) {
                    Application.getInstance().set_currentChild(children.get(0).getGuid());
                    Log.i("INFO", "Current child not found in shared preferences. Setting first child to current.");
                } else {
                    Application.getInstance().set_currentChild(currentChild_json);
                    Log.i("INFO", "Current child fetched from shared preferences");
                }
            }
        }else{
            Log.i("INFO", "No user initialized in application. Please seed anonymous data first");
        }

    }

    public void syncStaticData(){
        //Get allergies and supplements from pref.
        String allergies_json = settings.getString("Allergies", "");
        String supplements_json = settings.getString("Supplements", "");
        String foods_json = settings.getString("Foods", "");
        String foodCategories_json = settings.getString("FoodCategories", "");
        String feces_json = settings.getString("Feces", "");

        //Get last time data was saved to pref
        long lastLogin = settings.getLong("DataTimestamp", Long.MIN_VALUE);
        boolean update = true;

        if (lastLogin != Long.MIN_VALUE)
        {
            Period interval = new Period(lastLogin, new Date().getTime());
            update = interval.getHours() > 6;
        }

        Log.i("INFO", update ? "Data is out of date, asking server for new." : "Using existing data");

        if (!update && !allergies_json.isEmpty()){
            if(Application.getInstance().get_allergyList().isEmpty()){
                ArrayList<Allergy> allergies =
                        gson.fromJson(allergies_json, new TypeToken<ArrayList<Allergy>>(){}.getType());
                Application.getInstance().set_allergyList(allergies);
                Log.i("INFO", "Fetched allergies from pref");
                Log.d("DATA", Application.getInstance().get_allergyList().toString());
            }else{
                Log.i("INFO", "Allergies already loaded");
            }
        }
        else
        {
            try {
                okHttp.get(Application.getInstance().getApplicationContext().getString(R.string.api_allergy), Allergy.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //else new com.abdo.patrick.abdo.Api.Allergy.Get().execute();

        if (!update && !supplements_json.isEmpty()){
            if(Application.getInstance().get_supplementList().isEmpty()){
                ArrayList<Supplement> supplements =
                        gson.fromJson(supplements_json, new TypeToken<ArrayList<Supplement>>(){}.getType());
                Application.getInstance().set_supplementList(supplements);
                Log.i("INFO", "Fetched supplements from pref");
                Log.d("DATA", Application.getInstance().get_supplementList().toString());
            }else{
                Log.i("INFO", "Supplements already loaded");
            }
        }
        else
        {
            try {
                okHttp.get(Application.getInstance().getApplicationContext().getString(R.string.api_supplement), Supplement.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //else new com.abdo.patrick.abdo.Api.Supplement.Get().execute();

        if (!update && !foods_json.isEmpty()){
            if(Application.getInstance().get_foodList().isEmpty()){
                ArrayList<Food> foods =
                        gson.fromJson(foods_json, new TypeToken<ArrayList<Food>>(){}.getType());
                Application.getInstance().set_foodList(foods);
                Log.i("INFO", "Fetched foods from pref");
                Log.d("DATA", Application.getInstance().get_foodList().toString());
            }else{
                Log.i("INFO", "Foods already loaded");
            }
        }
        else
        {
            try {
                okHttp.get(Application.getInstance().getApplicationContext().getString(R.string.api_food), Food.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //else new com.abdo.patrick.abdo.Api.Food.Get().execute();

        if (!update && !foodCategories_json.isEmpty()){
            if(Application.getInstance().get_foodCategoryList().isEmpty()){
                ArrayList<FoodCategory> foodCategories =
                        gson.fromJson(foodCategories_json, new TypeToken<ArrayList<FoodCategory>>(){}.getType());
                Application.getInstance().set_foodCategoryList(foodCategories);
                Log.i("INFO", "Fetched food categories from pref");
                Log.d("DATA", Application.getInstance().get_foodCategoryList().toString());
            }else{
                Log.i("INFO", "Foodcategories already loaded");
            }
        }
        else
        {
            try {
                okHttp.get(Application.getInstance().getApplicationContext().getString(R.string.api_foodcategory), FoodCategory.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //else new com.abdo.patrick.abdo.Api.FoodCategory.Get().execute();

        if (!update && !feces_json.isEmpty()){
            if(Application.getInstance().get_fecesList().isEmpty()){
                ArrayList<Feces> feces =
                        gson.fromJson(feces_json, new TypeToken<ArrayList<Feces>>(){}.getType());
                Application.getInstance().set_fecesList(feces);
                Log.i("INFO", "Fetched feces from pref");
                Log.d("DATA", Application.getInstance().get_fecesList().toString());
            }else{
                Log.i("INFO", "Feces already loaded");
            }
        }
        else
        {
            try {
                okHttp.get(Application.getInstance().getApplicationContext().getString(R.string.api_feces), Feces.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //else new com.abdo.patrick.abdo.Api.Feces.Get().execute();
    }

    public void seedStaticData(){
        SharedPreferences.Editor editor = settings.edit();

        String allergies_json = settings.getString("Allergies", "");
        String supplements_json = settings.getString("Supplements", "");
        String foods_json = settings.getString("Foods", "");
        String foodCategories_json = settings.getString("FoodCategories", "");
        String feces_json = settings.getString("Feces", "");

        if(allergies_json.isEmpty()){
            Log.i("INFO", "Allergies not found in prefs. Seeding allergies.");

            ArrayList<Allergy> allergies = new ArrayList<>();
            allergies.add(new Allergy(1, "Pollen"));
            allergies.add(new Allergy(2, "Laktose"));
            allergies.add(new Allergy(3, "Støv"));
            allergies.add(new Allergy(4, "Græs"));
            allergies.add(new Allergy(5, "Ananas"));
            allergies.add(new Allergy(6, "Latex"));
            allergies.add(new Allergy(7, "Uld"));
            allergies.add(new Allergy(8, "Hår"));
            allergies.add(new Allergy(9, "Hund"));
            allergies.add(new Allergy(10, "Kat"));
            allergies.add(new Allergy(11, "Hest"));
            allergies.add(new Allergy(12, "Birk"));

            Application.getInstance().set_allergyList(allergies);
            String json = gson.toJson(allergies);
            editor.putString("Allergies", json);

            Log.i("INFO", "Allergies saved in prefs and application: "+json);
        }else{
            Log.i("INFO", "Allergies found in prefs. No seeding needed");
        }

        if(supplements_json.isEmpty()){
            Log.i("INFO", "Supplements not found in prefs. Seeding supplements.");

            ArrayList<Supplement> supplements = new ArrayList<>();
            supplements.add(new Supplement(1, "A-vitamin"));
            supplements.add(new Supplement(2, "B-vitamin"));
            supplements.add(new Supplement(3, "C-vitamin"));
            supplements.add(new Supplement(4, "D-vitamin"));
            supplements.add(new Supplement(5, "E-vitamin"));
            supplements.add(new Supplement(6, "K-vitamin"));

            Application.getInstance().set_supplementList(supplements);
            String json = gson.toJson(supplements);
            editor.putString("Supplements", json);

            Log.i("INFO", "Supplements saved in prefs and application: "+json);
        }else{
            Log.i("INFO", "Supplements found in prefs. No seeding needed");
        }

        if(foodCategories_json.isEmpty()){
            Log.i("INFO", "Food categories not found in prefs. Seeding food categories.");

            ArrayList<FoodCategory> foodcategories = new ArrayList<>();
            foodcategories.add(new FoodCategory(1, "Morgenmad"));
            foodcategories.add(new FoodCategory(2, "Frokost"));
            foodcategories.add(new FoodCategory(3, "Aftensmad"));
            foodcategories.add(new FoodCategory(4, "Sødt"));
            foodcategories.add(new FoodCategory(5, "Frugt"));

            Application.getInstance().set_foodCategoryList(foodcategories);
            String json = gson.toJson(foodcategories);
            editor.putString("FoodCategories", json);

            Log.i("INFO", "Food categories saved in prefs and application: "+json);
        }else{
            Log.i("INFO", "Food categories found in prefs. No seeding needed");
        }

        if(foods_json.isEmpty()){
            Log.i("INFO", "Food not found in prefs. Seeding food.");

            ArrayList<Food> foods = new ArrayList<>();
            foods.add(new Food(1, "Havregryn", 1));
            foods.add(new Food(2, "Yoghurt", 1));
            foods.add(new Food(3, "Brød", 1));
            foods.add(new Food(4, "Æg", 1));
            foods.add(new Food(6, "Leverpostejsmad", 2));
            foods.add(new Food(7, "Kartoffelmad", 2));
            foods.add(new Food(8, "Tomatmad", 2));
            foods.add(new Food(9, "Agurkmad", 2));
            foods.add(new Food(11, "Boller i karry", 3));
            foods.add(new Food(12, "Bruger", 3));
            foods.add(new Food(13, "Pizza", 3));
            foods.add(new Food(14, "Kebab", 3));
            foods.add(new Food(16, "Lakrids", 4));
            foods.add(new Food(17, "Vingummi", 4));
            foods.add(new Food(18, "Oreo", 4));
            foods.add(new Food(19, "Is", 4));
            foods.add(new Food(21, "Banan", 5));
            foods.add(new Food(22, "Æble", 5));
            foods.add(new Food(23, "Pære", 5));
            foods.add(new Food(24, "Dragefrugt", 5));

            Application.getInstance().get_foodCategoryList().get(0).setFoods(new ArrayList<>(foods.subList(0, 3)));
            Application.getInstance().get_foodCategoryList().get(1).setFoods(new ArrayList<>(foods.subList(4, 7)));
            Application.getInstance().get_foodCategoryList().get(2).setFoods(new ArrayList<>(foods.subList(8, 11)));
            Application.getInstance().get_foodCategoryList().get(3).setFoods(new ArrayList<>(foods.subList(12, 15)));
            Application.getInstance().get_foodCategoryList().get(4).setFoods(new ArrayList<>(foods.subList(16, 19)));

            Application.getInstance().set_foodList(foods);
            String json = gson.toJson(foods);
            editor.putString("Foods: ", json);

            Log.i("INFO", "Foods saved in prefs and application: "+json);
        }else{
            Log.i("INFO", "Foods found in prefs. No seeding needed");
        }


        if(feces_json.isEmpty()){
            Log.i("INFO", "Feces not found in prefs. Seeding feces.");

            ArrayList<Feces> feces = new ArrayList<>();
            feces.add(new Feces(1, "Ingen afføring"));
            feces.add(new Feces(2, "Hårde klumper der ligner nødder"));
            feces.add(new Feces(3, "Pølseformet, men med klumper"));
            feces.add(new Feces(4, "Pølseformet men med revner på ydersiden"));
            feces.add(new Feces(5, "Ligner en pølse eller orm, smidig og blød"));
            feces.add(new Feces(6, "Bløde klumper med skarpe kanter"));
            feces.add(new Feces(7, "Iturevne småstykker"));
            feces.add(new Feces(8, "Vandig uden klumper. Kun væske"));

            Application.getInstance().set_fecesList(feces);
            String json = gson.toJson(feces);
            editor.putString("Feces", json);

            Log.i("INFO", "Feces saved in prefs and application: "+json);
        }else{
            Log.i("INFO", "Feces found in prefs. No seeding needed");
        }
    }


}
