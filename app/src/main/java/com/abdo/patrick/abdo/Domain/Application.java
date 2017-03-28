package com.abdo.patrick.abdo.Domain;

import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.Feces;
import com.abdo.patrick.abdo.Models.Food;
import com.abdo.patrick.abdo.Models.Supplement;
import com.abdo.patrick.abdo.ViewModels.ListItem;
import com.abdo.patrick.abdo.Views.MainActivity;
import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Khaled on 21-03-2017.
 */


public class Application extends android.app.Application {

    private static Application instance;

    private Anonymous _anonymous;

    private ArrayList<Allergy> _allergyList;
    private ArrayList<Supplement> _supplementList;
    private ArrayList<Feces> _fecesList;
    private ArrayList<Food> _foodList;

    //Device properties
    private boolean _registered;

    @Override
    public void onCreate() {  super.onCreate();

        instance = this;

        _allergyList = new ArrayList<>();
        _supplementList = new ArrayList<>();
        _fecesList = new ArrayList<>();
        _foodList = new ArrayList<>();

    }

    public static Application getInstance() {
        return instance;
    }

    public static String getAndroidId(Context context)
    {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    public ArrayList<Allergy> get_allergyList() {
        return _allergyList;
    }

    public void set_allergyList(ArrayList<Allergy> _allergyList) {
        this._allergyList = _allergyList;
    }

    public ArrayList<Supplement> get_supplementList() {
        return _supplementList;
    }

    public void set_supplementList(ArrayList<Supplement> _supplementsList) {
        this._supplementList = _supplementsList;
    }

    public Anonymous get_anonymous() {
        return _anonymous;
    }

    public void set_anonymous(Anonymous _anonymous) {
        this._anonymous = _anonymous;
    }

    public void AddAllergiesToPreference(){
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(_allergyList);

        Long now = new Date().getTime();
        editor.putString("Allergies", json);
        editor.putLong("DataTimestamp", now);

        editor.apply();
        editor.commit();

        Log.i("INFO", "SharedPreference: Allergies = " +json+ ", DataTimestamp = " +now);
    }

    public void AddSupplementsToPreference(){
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(_supplementList);

        Long now = new Date().getTime();
        editor.putString("Supplements", json);
        editor.putLong("DataTimestamp", now);

        editor.apply();
        editor.commit();

        Log.i("INFO", "SharedPreference: Supplements = " +json+ ", DataTimestamp = " +now);
    }

    public void AddAnonymousToPreference(){

        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(_anonymous);

        _registered = !json.isEmpty();

        editor.putString("Anonymous", json);
        editor.putBoolean("REG", _registered);

        editor.apply();
        editor.commit();

        Log.i("INFO", "SharedPreference: REG = " +_registered+ ", Anonymous = " +json);
    }


    public ArrayList<ListItem> getAllergyListView(ArrayList<Allergy> list){

        ArrayList<ListItem> listView = new ArrayList<>();
        Allergy current;

        for(int i = 0; i < list.size(); i++)
        {
            current = list.get(i);
            listView.add(
                    new ListItem(current.getId(), current.getType()));
        }

        return listView;
    }

    public ArrayList<ListItem> getSupplementListView(ArrayList<Supplement> list){

        ArrayList<ListItem> listView = new ArrayList<>();
        Supplement current;

        for(int i = 0; i < list.size(); i++)
        {
            current = list.get(i);
            listView.add(
                    new ListItem(current.getId(), current.getDescription()));
        }

        return listView;
    }



}
