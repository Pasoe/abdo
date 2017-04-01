package com.abdo.patrick.abdo.Models;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Child {

    private int id;
    private String createdTime;
    private String modifiedTime;
    private ChildInfo childInfo;
    private ShareCode shareCode;
    private ArrayList<ChildAllergy> childAllergies;
    private ArrayList<ChildMedicine> childMedicines;
    private ArrayList<ChildSupplement> childSupplements;
    private ArrayList<Anonymous> anonymous;
    private ArrayList<Registration> registrations;

    public Child() {
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", createdTime='" + createdTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", anonymous=" + anonymous +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
