package com.abdo.patrick.abdo.Models;

/**
 * Created by Khaled on 27-03-2017.
 */

public class ChildMedicine {

    private int Id;
    private String Type;
    private String Dosage;
    private String CreatedTime;
    private String ModifiedTime;

    public ChildMedicine(){

    }

    public ChildMedicine(String type, String dosage){
        this.Type = type;
        this.Dosage = type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        this.Dosage = dosage;
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

    @Override
    public String toString(){
        return Type +" ("+ Dosage +")";
    }
}

