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

    public Food(int id, String type, int foodCategoryId){
        this.id = id;
        this.type = type;
        this.foodCategoryId = foodCategoryId;
    }

    public String getType(){
        return type;
    }

    public int getId(){
        return id;
    };
}
