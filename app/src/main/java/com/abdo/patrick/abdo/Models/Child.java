package com.abdo.patrick.abdo.Models;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Child {

    private int _id;
    private String _createdTime;
    private String _modifiedTime;
    private JSONArray _anonymous;

    public Child() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_createdTime() {
        return _createdTime;
    }

    public void set_createdTime(String _createdTime) {
        this._createdTime = _createdTime;
    }

    public String get_modifiedTime() {
        return _modifiedTime;
    }

    public void set_modifiedTime(String _modifiedTime) {
        this._modifiedTime = _modifiedTime;
    }

    public JSONArray get_anonymous() {
        return _anonymous;
    }

    public void set_anonymous(JSONArray _anonymous) {
        this._anonymous = _anonymous;
    }

    @Override
    public String toString() {
        return "Child{" +
                "_id=" + _id +
                ", _createdTime='" + _createdTime + '\'' +
                ", _modifiedTime='" + _modifiedTime + '\'' +
                ", _anonymous=" + _anonymous +
                '}';
    }
}
