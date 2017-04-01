package com.abdo.patrick.abdo.Models;

import android.util.Log;

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
        childInfo = new ChildInfo();
        childAllergies = new ArrayList<ChildAllergy>();
        childMedicines = new ArrayList<ChildMedicine>();
        childSupplements = new ArrayList<ChildSupplement>();
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

    public void addAllergy(int id){
        ChildAllergy allergy = new ChildAllergy(id);
        childAllergies.add(allergy);
    }

    public void removeAllergy(int id){
        for(ChildAllergy allergy : childAllergies){
            if(allergy.getId() == id){
                childAllergies.remove(allergy);
                return;
            }
        }
    }

    public boolean allergyExists(int id){
        for(ChildAllergy allergy : childAllergies){
            if(allergy.getId() == id){
                return true;
            }
        }
        return false;
    }

    public void addSupplement(int id){
        ChildSupplement supplement = new ChildSupplement(id);
        childSupplements.add(supplement);
    }

    public void removeSupplement(int id){
        for(ChildSupplement supplement : childSupplements){
            if(supplement.getId() == id){
                childSupplements.remove(supplement);
                return;
            }
        }
    }

    public boolean supplementExists(int id){
        for(ChildSupplement supplement : childSupplements){
            if(supplement.getId() == id){
                return true;
            }
        }
        return false;
    }
}
