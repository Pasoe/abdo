package com.abdo.patrick.abdo.Models;

import java.util.ArrayList;

/**
 * Created by Khaled on 27-03-2017.
 */

public class FoodCategory {
    private int id;
    private String type;
    private String createdTime;
    private String modifiedTime;
    private ArrayList<Food> foods;

    public FoodCategory() {
    }

    public FoodCategory(int id, String type){
        this.id = id;
        this.type = type;
    }

    public void setFoods(ArrayList<Food> food){
        foods = food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "FoodCategory{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                '}';
    }

    //Return the id if the type is equal or -1
    public int getIdByType(String type)
    {
        return this.type.equals(type) ? this.id : -1;
    }

}
