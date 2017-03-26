package com.abdo.patrick.abdo.Domain;

import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khaled on 21-03-2017.
 */


public class Application extends android.app.Application {

    private static Application instance;

    private ArrayList<Anonymous> _anonymous;
    private ArrayList<Child> _children;
    private ArrayList<String> _allergyList;
    private ArrayList<String> _supplementslist;

    @Override
    public void onCreate() {  super.onCreate();

        instance = this;
        _anonymous = new ArrayList<>();
        _children = new ArrayList<>();

        _allergyList = new ArrayList<>();
        PopulateAlleryList();

        _supplementslist = new ArrayList<>();
        PopulateSupplementsList();
    }

    public static Application getInstance() {
        return instance;
    }

    public ArrayList<Anonymous> get_anonymous() {
        return _anonymous;
    }

    public void set_anonymous(ArrayList<Anonymous> _anonymous) {
        this._anonymous = _anonymous;
    }

    public void add_anonymous(Anonymous _anonymous) {
        this._anonymous.add(_anonymous);
    }

    public ArrayList<Child> get_children() {
        return _children;
    }

    public void add_child(Child _child) {
        this._children.add(_child);
    }

    public void set_children(ArrayList<Child> _children) {
        this._children = _children;
    }

    private void PopulateAlleryList(){
        _allergyList.add("Pollen");
        _allergyList.add("Laktose");
        _allergyList.add("Støv");
        _allergyList.add("Græs");
        _allergyList.add("Ananas");
        _allergyList.add("Latex");
        _allergyList.add("Uld");
        _allergyList.add("Hår");
        _allergyList.add("Hund");
        _allergyList.add("Kat");
        _allergyList.add("Hest");
        _allergyList.add("Birk");
        _allergyList.add("Lakrids");
    }

    public ArrayList<String> GetAllergyList(){
        return _allergyList;
    }

    private void PopulateSupplementsList(){
        _supplementslist.add("A-vitamin");
        _supplementslist.add("B-vitamin");
        _supplementslist.add("C-vitamin");
        _supplementslist.add("D-vitamin");
        _supplementslist.add("E-vitamin");
        _supplementslist.add("K-vitamin");
        _supplementslist.add("Kalk");
    }

    public ArrayList<String> GetSupplementslist(){
        return _supplementslist;
    }
}
