package com.abdo.patrick.abdo.Models;

import java.util.ArrayList;

/**
 * Created by Khaled on 27-03-2017.
 */

public class Food {
    private int id;
    private String type;
    private int foodCategoryId;
    private String createdTime;
    private String modifiedTime;

    public Food() {
    }

    public Food(int id, String type, int foodCategoryId){
        this.id = id;
        this.type = type;
        this.foodCategoryId = foodCategoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(int foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
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

    public String getType(){
        return type;
    }

    public int getId(){
        return id;
    };

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", foodCategoryId=" + foodCategoryId +
                '}';
    }

}
