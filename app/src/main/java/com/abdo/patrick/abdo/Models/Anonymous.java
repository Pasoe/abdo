package com.abdo.patrick.abdo.Models;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Anonymous {

    private int id;
    private String deviceId;
    private String deviceName;
    private String createdTime;
    private String modifiedTime;
    private ArrayList<Child> children;

    public Anonymous() {
    }

    public Anonymous(String deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        children = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Anonymous{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", children=" + children +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }


    public Child getChild(String currentChildGuid) {
        Child child = null;
        for(Child c : children){
            if(!c.getGuid().equals(currentChildGuid)) continue;
                child = c;
        }
        return child;
    }

    public void updateChild(Child child){
        for(Child c : children){
            if(!c.getGuid().equals(child.getGuid())) continue;
            children.remove(c);
        }
        children.add(child);
    }

    public void addNewChild(Child newChild){
        if(children == null){
            children = new ArrayList<>();
        }
        children.add(newChild);
    }

    public void setChildren(ArrayList<Child> children){
        this.children = children;
    }

    public ArrayList<Child> getChildren(){
        return children;
    }

    public boolean childrenExists(){
        if(children == null){
            return false;
        } else if(children.isEmpty()){
            return false;
        }
        return true;
    }
}
