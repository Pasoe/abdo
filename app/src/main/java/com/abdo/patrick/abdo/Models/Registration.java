package com.abdo.patrick.abdo.Models;

import com.abdo.patrick.abdo.Domain.Application;

import java.util.ArrayList;

/**
 * Created by Khaled on 27-03-2017.
 */

public class Registration {

    private int id;
    private String guid;
    private int childId;
    private Integer fecesId;
    private Integer activityId;
    private Integer painPlacementId;
    private Integer painLevelId;
    private String createdTime;
    private String modifiedTime;
    private Integer moodId;
    private Integer sleepId;
    private boolean hasNoFood = false;
    private ArrayList<Food> foods;

    //Virtual
    private Child child;
    private Feces feces;
    private Sleep sleep;
    private Mood mood;
    private Activity activity;
    private PainPlacement painPlacement;
    private PainLevel painLevel;


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void addPainPlacement(int painPlacementId)
    {
        this.painPlacementId = painPlacementId;
    }

    public void addPainLevel(int painLevelId)
    {
        this.painLevelId = painLevelId;
    }

    public void addSleep(int sleepId)
    {
        this.sleepId = sleepId;
    }

    public void addActivity(int activityId)
    {
        this.activityId = activityId;
    }

    public void addFeces(int fecesId)
    {
        this.fecesId = fecesId;
    }

    public void addMood(int moodId)
    {
        this.moodId = moodId;
    }

    public Integer getFecesId() {
        return fecesId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public Integer getMoodId() {
        return moodId;
    }

    public Integer getSleepId() {
        return sleepId;
    }

    public boolean hasNoFood() {
        return hasNoFood;
    }

    public void setHasNoFood(boolean hasNoFood) {
        this.hasNoFood = hasNoFood;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> list) {
        foods = list;
    }

    public int addFood(Food food)
    {
        if (foods.contains(food) && foods.remove(food)) return -1; //return -1 if already exists
        hasNoFood = false;
        foods.add(food);
        return 0;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public boolean RegisteredFoodContainsCategory(String category) {

        int categoryId = Application.getInstance().getCategoryId(category);
        if (categoryId == -1) return false;

        for(Food food : foods) {
            if(food.getFoodCategoryId() == categoryId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", fecesId=" + fecesId +
                ", activityId=" + activityId +
                ", moodId=" + moodId +
                ", sleepId=" + sleepId +
                ", painPlacementId=" + painPlacementId +
                ", painLevelId=" + painLevelId +
                ", hasNoFood=" + hasNoFood +
                ", foods=" + foods +
                '}';
    }
}
