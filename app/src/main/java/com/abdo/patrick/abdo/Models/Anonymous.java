package com.abdo.patrick.abdo.Models;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Anonymous {

    private int _id;
    private String _deviceId;
    private String _deviceName;
    private String _createdTime;
    private String _modifiedTime;
    private JSONArray _children;

    public Anonymous() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_deviceId() {
        return _deviceId;
    }

    public void set_deviceId(String _deviceId) {
        this._deviceId = _deviceId;
    }

    public String get_deviceName() {
        return _deviceName;
    }

    public void set_deviceName(String _deviceName) {
        this._deviceName = _deviceName;
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

    public JSONArray get_children() {
        return _children;
    }

    public void set_children(JSONArray _children) {
        this._children = _children;
    }


    @Override
    public String toString() {
        return "Anonymous{" +
                "_id=" + _id +
                ", _deviceId='" + _deviceId + '\'' +
                ", _deviceName='" + _deviceName + '\'' +
                ", _createdTime='" + _createdTime + '\'' +
                ", _modifiedTime='" + _modifiedTime + '\'' +
                ", _children=" + _children +
                '}';
    }
}
