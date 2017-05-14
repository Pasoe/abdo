package com.abdo.patrick.abdo.Domain;

import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.ChildMedicine;
import com.abdo.patrick.abdo.Models.Feces;
import com.abdo.patrick.abdo.Models.Food;
import com.abdo.patrick.abdo.Models.FoodCategory;
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
    private ArrayList<Feces> _fecesList;
    private ArrayList<Food> _foodList;
    private ArrayList<FoodCategory> _foodCategoryList;

    public ArrayList<Feces> get_fecesList() {
        return _fecesList;
    }

    public void set_fecesList(ArrayList<Feces> _fecesList) {
        this._fecesList = _fecesList;
    }

    public ArrayList<Food> get_foodList() {
        return _foodList;
    }

    public void set_foodList(ArrayList<Food> _foodList) {
        this._foodList = _foodList;
    }

    public ArrayList<FoodCategory> get_foodCategoryList() {
        return _foodCategoryList;
    }

    public void set_foodCategoryList(ArrayList<FoodCategory> _foodCategoryList) {
        this._foodCategoryList = _foodCategoryList;
    }

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

        InitiateCurrentRegistration();
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

    public Food FindFood(int id) {
        for(Food food : _foodList) {
            if(food.getId() == id) {
                return food;
            }
        }
        return null;
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


    public void AddItemToPreference(String itemName, Object item){
        SharedPreferences settings = getSharedPreferences("Abdo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        Gson gson = new Gson();
        String json = gson.toJson(item);

        Long now = new Date().getTime();
        editor.putString(itemName, json);
        editor.putLong("DataTimestamp", now);

        editor.apply();
        editor.commit();

        Log.i("INFO", "SharedPreference: " +itemName+ " = " +json+ "+" +
                "\nDataTimestamp = " +now);

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

    public void updateCurrentChildData(Child child){
        _anonymous.updateChild(child);
        updateChildListInSharedPreferences(_anonymous.getChildren());
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


    public ArrayList<Food> get_foodList(String type)
    {
        Log.d("Food list: get_foodList", "Invoker()");
        Log.d("Food list: get_foodList", "Type = " +type);
        ArrayList<Food> foods = new ArrayList<>();
        int categoryId = getCategoryId(type);
        Log.d("Food list: get_foodList", "Category id = " +categoryId);

        if (categoryId == -1) return foods;

        Log.d("Food list: get_foodList", "Fishing correct food list");
        Log.d("Food list: get_foodList", "Current food list count: " +_foodList.size());
        for (Food food : _foodList)
        {
            Log.d("Food list: get_foodList", "Current food:" +food.toString());
            if (food.getFoodCategoryId() == categoryId)
            {
                foods.add(food);
                Log.d("Food list: get_foodList", "Food added!");
            }
            else
                Log.d("Food list: get_foodList", "Food discarded!");

        }
        Log.d("Food list: get_foodList", "Returning "+type+" list:" +foods.toString());
        return foods;
    }


    public ArrayList<ListItem> getFoodListView(ArrayList<Food> list, ArrayList<Food> regFoods){
        ArrayList<ListItem> listView = new ArrayList<>();
        Food current;

        for(int i = 0; i < list.size(); i++)
        {
            current = list.get(i);
            listView.add(
                    new ListItem(current.getId(), current.getType(), regFoods.contains(current)));
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

    public int getCategoryId(String categoryName)
    {
        for (FoodCategory category: _foodCategoryList)
        {
            if (category.getIdByType(categoryName) == -1) continue;
            return  category.getIdByType(categoryName);
        }
        return -1;
    }

    public void InitiateCurrentRegistration(){
        _currentRegistration = new Registration();
        _currentRegistration.setFoods(new ArrayList<Food>());
    }

    public Registration FindRegistration(int clickedId) {
        //TODO - returer korrekt registrerng
        return new Registration();
    }
}
