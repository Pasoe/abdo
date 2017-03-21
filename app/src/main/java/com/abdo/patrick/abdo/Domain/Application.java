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

    @Override
    public void onCreate() {  super.onCreate();

        instance = this;
        _anonymous = new ArrayList<>();
        _children = new ArrayList<>();

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
}
