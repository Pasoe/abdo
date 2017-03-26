package com.abdo.patrick.abdo.Domain;

import com.abdo.patrick.abdo.Models.Allergy;
import com.abdo.patrick.abdo.Models.Anonymous;
import com.abdo.patrick.abdo.Models.Child;
import com.abdo.patrick.abdo.Models.Supplement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khaled on 21-03-2017.
 */


public class Application extends android.app.Application {

    private static Application instance;

    private ArrayList<Anonymous> _anonymous;
    private ArrayList<Child> _children;
    private ArrayList<Allergy> _allergyList;
    private ArrayList<Supplement> _supplementsList;

    @Override
    public void onCreate() {  super.onCreate();

        instance = this;

        _anonymous = new ArrayList<>();
        _children = new ArrayList<>();
        _allergyList = new ArrayList<>();
        _supplementsList = new ArrayList<>();

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

    public ArrayList<Allergy> get_allergyList() {
        return _allergyList;
    }

    public void set_allergyList(ArrayList<Allergy> _allergyList) {
        this._allergyList = _allergyList;
    }

    public ArrayList<Supplement> get_supplementsList() {
        return _supplementsList;
    }

    public void set_supplementsList(ArrayList<Supplement> _supplementsList) {
        this._supplementsList = _supplementsList;
    }

    //    private void PopulateAlleryList(){
//        _allergyList.add("Pollen");
//        _allergyList.add("Laktose");
//        _allergyList.add("Støv");
//        _allergyList.add("Græs");
//        _allergyList.add("Ananas");
//        _allergyList.add("Latex");
//        _allergyList.add("Uld");
//        _allergyList.add("Hår");
//        _allergyList.add("Hund");
//        _allergyList.add("Kat");
//        _allergyList.add("Hest");
//        _allergyList.add("Birk");
//        _allergyList.add("Lakrids");
//    }


//    private void PopulateSupplementsList(){
//        _supplementsList.add("A-vitamin");
//        _supplementsList.add("B-vitamin");
//        _supplementsList.add("C-vitamin");
//        _supplementsList.add("D-vitamin");
//        _supplementsList.add("E-vitamin");
//        _supplementsList.add("K-vitamin");
//        _supplementsList.add("Kalk");
//    }


}
