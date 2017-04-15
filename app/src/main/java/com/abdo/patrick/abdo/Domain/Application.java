package com.abdo.patrick.abdo.Domain;

import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.ChildMedicine;
import com.abdo.patrick.abdo.Models.Feces;
import com.abdo.patrick.abdo.Models.Food;
import com.abdo.patrick.abdo.Models.PainLevel;
import com.abdo.patrick.abdo.Models.Registration;
import com.abdo.patrick.abdo.Models.Supplement;
import com.abdo.patrick.abdo.ViewModels.ListItem;
import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings.Secure;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Khaled on 21-03-2017.
 */


public class Application extends android.app.Application {

    private static Application instance;

    private Anonymous _anonymous;

    private String currentChildGuid;
    private Registration _currentRegistration;

    private ArrayList<Allergy> _allergyList;
    private ArrayList<Supplement> _supplementList;
    private ArrayList<ChildMedicine> _childMedicineList;
    private ArrayList<Feces> _fecesList;
    private ArrayList<Food> _foodList;

    private ArrayList<PainLevel> _painLevel;

    //Device properties
    private boolean _registered;

    @Override
    public void onCreate() {  super.onCreate();
  Fabric.with(this, new Crashlytics());

        instance = this;

        _allergyList = new ArrayList<>();
        _supplementList = new ArrayList<>();
        _fecesList = new ArrayList<>();
        _foodList = new ArrayList<>();
        _painLevel = new ArrayList<>();

        _currentRegistration = new Registration();
    }

    public static Application getInstance() {
        return instance;
    }

    public static String getAndroidId(Context context)
    {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    public Registration getCurrentRegistration() {
        return _currentRegistration;
    }

    public void addPainLevel(PainLevel painLevel)
    {
        _painLevel.add(painLevel);
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


    public ArrayList<ListItem> getAllergyListView(ArrayList<Allergy> list, Child newChild){

        ArrayList<ListItem> listView = new ArrayList<>();
        Allergy current;

        for(int i = 0; i < list.size(); i++)
        {
            current = list.get(i);
            listView.add(
                    new ListItem(current.getId(), current.getType(), newChild.allergyExists(current.getId())));
        }

        return listView;
    }

    public ArrayList<ListItem> getPainLevelListView(ArrayList<Allergy> list, Child newChild){

        ArrayList<ListItem> listView = new ArrayList<>();
        Allergy current;

        for(int i = 0; i < list.size(); i++)
        {
            current = list.get(i);
            listView.add(
                    new ListItem(current.getId(), current.getType(), newChild.allergyExists(current.getId())));
        }

        return listView;
    }

    public ArrayList<ListItem> getSupplementListView(ArrayList<Supplement> list, Child newChild){

        ArrayList<ListItem> listView = new ArrayList<>();
        Supplement current;

        for(int i = 0; i < list.size(); i++)
        {
            current = list.get(i);
            listView.add(
                    new ListItem(current.getId(), current.getDescription(), newChild.supplementExists(current.getId())));
        }

        return listView;
    }

    public ArrayList<ListItem> getMedicineListView(ArrayList<ChildMedicine> list, Child newChild){

        ArrayList<ListItem> listView = new ArrayList<>();
        ChildMedicine current;

        for(int i = 0; i < list.size(); i++)
        {
            current = list.get(i);
            listView.add(
                    new ListItem(current.getId(), current.getType()+" ("+current.getDosage()+")", false));
        }

        return listView;
    }


    public void setNewChild(Child newChild){
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(newChild);

        editor.putString("NewChild", json);

        editor.apply();
        editor.commit();

        Log.i("INFO", "SharedPreference: New Child updated - "+json);
    }

    public Child getNewChild(){
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        String json = settings.getString("NewChild", "");

        Gson gson = new Gson();

        Child newChild = gson.fromJson(json, Child.class);
        return newChild;
    }

    public void removeNewChild(){
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("NewChild");
    }

    public void addNewChildToAnonymous(Child child){
        _anonymous.addNewChild(child);
        currentChildGuid = child.getGuid();

        updateChildListInSharedPreferences(_anonymous.getChildren());
        updateCurrentChildInSharedPreferences(currentChildGuid);
    }

    private void updateCurrentChildInSharedPreferences(String currentChildGuid) {
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("CurrentChild",currentChildGuid);

        editor.apply();
        editor.commit();
    }

    public void updateChildListInSharedPreferences(ArrayList<Child> children){
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(_anonymous.getChildren());

        editor.putString("Children",json);

        editor.apply();
        editor.commit();
    }

    public void set_children(ArrayList<Child> children){
        _anonymous.setChildren(children);
    }

    public boolean newChildExists(){
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        return settings.contains("NewChild");
    }

    public ArrayList<Food> get_foodList(String foodType){

        ArrayList<Food> foodlist = new ArrayList<Food>();
        if(foodType.equals("breakfast")){
            int foodCategoyType = 1;
            foodlist.add(new Food(1, "Toast", foodCategoyType));
            foodlist.add(new Food(2, "Æg", foodCategoyType));
            foodlist.add(new Food(3, "Bacon", foodCategoyType));
            foodlist.add(new Food(4, "Havregryn", foodCategoyType));
            foodlist.add(new Food(5, "Guldkorn", foodCategoyType));
        }
        if(foodType.equals("lunch")){
            int foodCategoyType = 2;
            foodlist.add(new Food(1, "Leverpostejsmad", foodCategoyType));
            foodlist.add(new Food(2, "Baconmad", foodCategoyType));
            foodlist.add(new Food(3, "Makrelmad", foodCategoyType));
            foodlist.add(new Food(4, "Ostemad", foodCategoyType));
            foodlist.add(new Food(5, "Skinke", foodCategoyType));
        }
        if(foodType.equals("dinner")){
            int foodCategoyType = 3;
            foodlist.add(new Food(1, "Hakkebøffer", foodCategoyType));
            foodlist.add(new Food(2, "Baconbøffer", foodCategoyType));
            foodlist.add(new Food(3, "Pasta med kødsovs", foodCategoyType));
            foodlist.add(new Food(4, "Boller i karry", foodCategoyType));
            foodlist.add(new Food(5, "Vegetartarteletter", foodCategoyType));
        }
        if(foodType.equals("fruit")){
            int foodCategoyType = 4;
            foodlist.add(new Food(1, "Æble", foodCategoyType));
            foodlist.add(new Food(2, "Banan", foodCategoyType));
            foodlist.add(new Food(3, "Pære", foodCategoyType));
            foodlist.add(new Food(4, "Kiwi", foodCategoyType));
            foodlist.add(new Food(5, "Vindrue", foodCategoyType));
        }
        if(foodType.equals("candy")){
            int foodCategoyType = 5;
            foodlist.add(new Food(1, "Vingummi", foodCategoyType));
            foodlist.add(new Food(2, "Chokolade", foodCategoyType));
            foodlist.add(new Food(3, "Chips", foodCategoyType));
            foodlist.add(new Food(4, "Oregano", foodCategoyType));
            foodlist.add(new Food(5, "Ostehaps", foodCategoyType));
        }
        return foodlist;


    }

    public ArrayList<ListItem> getFoodListView(ArrayList<Food> list, Child child){
        ArrayList<ListItem> listView = new ArrayList<>();
        Food current;

        for(int i = 0; i < list.size(); i++)
        {
            current = list.get(i);
            listView.add(
                    //TODO - Set false to be "if food exists in childs list of selected foods"
                    new ListItem(current.getId(), current.getType(), false));
        }

        return listView;
    }

    public Child getCurrentChild(){
        Child child = _anonymous.getChild(currentChildGuid);
        return child;
    }

    public void set_currentChild(String childguid){
        currentChildGuid = childguid;
    }
}
