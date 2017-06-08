package com.abdo.patrick.abdo.Models;

import java.util.ArrayList;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Anonymous {

    private int Id;
    private String DeviceId;
    private String DeviceName;
    private String CreatedTime;
    private String ModifiedTime;
    private ArrayList<Child> Children;
    private String email;

    public Anonymous() {
    }

    public Anonymous(String deviceId, String deviceName) {
        this.DeviceId = deviceId;
        this.DeviceName = deviceName;
        Children = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Anonymous{" +
                "Id=" + Id +
                ", DeviceId='" + DeviceId + '\'' +
                ", DeviceName='" + DeviceName + '\'' +
                ", CreatedTime='" + CreatedTime + '\'' +
                ", ModifiedTime='" + ModifiedTime + '\'' +
                ", Children=" + Children +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        this.DeviceId = deviceId;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        this.DeviceName = deviceName;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        this.CreatedTime = createdTime;
    }

    public String getModifiedTime() {
        return ModifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.ModifiedTime = modifiedTime;
    }


    public Child getChild(String currentChildGuid) {
        Child child = null;
        for(int j = 0; j < Children.size(); j++){
            if(Children.get(j).getGuid().equals(currentChildGuid)) {
                child = Children.get(j);
            }
        }
        return child;
    }

    public void updateChild(Child child){
        for(int j = 0; j < Children.size(); j++){
            if(Children.get(j).getGuid().equals(child.getGuid())) {
                Children.remove(Children.get(j));
            }
        }
        Children.add(child);
    }

    public void addNewChild(Child newChild){
        if(Children == null){
            Children = new ArrayList<>();
        }
        Children.add(newChild);
    }

    public void setChildren(ArrayList<Child> children){
        this.Children = children;
    }

    public ArrayList<Child> getChildren(){
        return Children;
    }

    public boolean childrenExists(){
        if(Children == null){
            return false;
        } else if(Children.isEmpty()){
            return false;
        }
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
