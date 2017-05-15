package com.abdo.patrick.abdo.Models;

import java.util.ArrayList;

/**
 * Created by Khaled on 27-03-2017.
 */

public class FoodCategory {
    private int Id;
    private String Type;
    private String CreatedTime;
    private String ModifiedTime;
    private ArrayList<Food> Foods;

    public FoodCategory() {
    }

    public FoodCategory(int id, String type){
        this.Id = id;
        this.Type = type;
    }

    public void setFoods(ArrayList<Food> food){
        Foods = food;
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
    public String toString() {
        return "FoodCategory{" +
                "Id=" + Id +
                ", Type='" + Type + '\'' +
                ", CreatedTime='" + CreatedTime + '\'' +
                ", ModifiedTime='" + ModifiedTime + '\'' +
                '}';
    }

    //Return the Id if the Type is equal or -1
    public int getIdByType(String type)
    {
        return this.Type.equals(type) ? this.Id : -1;
    }

}
