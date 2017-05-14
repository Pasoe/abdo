package com.abdo.patrick.abdo.Models;

/**
 * Created by Khaled on 27-03-2017.
 */

public class Food {
    private int Id;
    private String Type;
    private int FoodCategoryId;
    private String CreatedTime;
    private String ModifiedTime;

    public Food() {
    }

    public Food(int id, String type, int foodCategoryId){
        this.Id = id;
        this.Type = type;
        this.FoodCategoryId = foodCategoryId;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public int getFoodCategoryId() {
        return FoodCategoryId;
    }

    public void setFoodCategoryId(int foodCategoryId) {
        this.FoodCategoryId = foodCategoryId;
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

    public String getType(){
        return Type;
    }

    public int getId(){
        return Id;
    };

    @Override
    public String toString() {
        return "Food{" +
                "Id=" + Id +
                ", Type='" + Type + '\'' +
                ", FoodCategoryId=" + FoodCategoryId +
                '}';
    }

}
