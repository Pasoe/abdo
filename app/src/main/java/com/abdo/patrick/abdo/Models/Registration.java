package com.abdo.patrick.abdo.Models;

import android.support.annotation.IntegerRes;

import com.abdo.patrick.abdo.Domain.Application;

import java.util.ArrayList;

/**
 * Created by Khaled on 27-03-2017.
 */

public class Registration {

    private int Id;
    private String Guid;
    private int ChildId;
    private Integer FecesId;
    private Integer ActivityId;
    private Integer PainPlacementId;
    private Integer PainLevelId;
    private String CreatedTime;
    private String ModifiedTime;
    private Integer MoodId;
    private Integer SleepId;
    private boolean hasNoFood = false;
    private ArrayList<Food> Foods;

    //Virtual
    private Child child;
    private Feces feces;
    private Sleep sleep;
    private Mood mood;
    private Activity activity;
    private PainPlacement painPlacement;
    private PainLevel painLevel;


    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        this.Guid = guid;
    }

    public void addPainPlacement(int painPlacementId)
    {
        this.PainPlacementId = painPlacementId;
    }

    public void addPainLevel(Integer painLevelId)
    {
        this.PainLevelId = painLevelId;
    }

    public void addSleep(Integer sleepId)
    {
        this.SleepId = sleepId;
    }

    public void addActivity(Integer activityId)
    {
        this.ActivityId = activityId;
    }

    public void addFeces(int fecesId)
    {
        this.FecesId = fecesId;
    }

    public void addMood(Integer moodId)
    {
        this.MoodId = moodId;
    }

    public Integer getFecesId() {
        return FecesId;
    }

    public Integer getActivityId() {
        return ActivityId;
    }

    public Integer getMoodId() {
        return MoodId;
    }

    public Integer getSleepId() {
        return SleepId;
    }

    public Integer getPainLevelId() {return PainLevelId; };

    public boolean hasNoFood() {
        return hasNoFood;
    }

    public void setHasNoFood(boolean hasNoFood) {
        this.hasNoFood = hasNoFood;
    }

    public ArrayList<Food> getFoods() {
        return Foods;
    }

    public void setFoods(ArrayList<Food> list) {
        Foods = list;
    }

    public int addFood(Food food)
    {
        if (Foods.contains(food) && Foods.remove(food)) return -1; //return -1 if already exists
        hasNoFood = false;
        Foods.add(food);
        return 0;
    }

    public String getCreatedTime() {
        int spaceIndex = CreatedTime.indexOf("T");
        if (spaceIndex != -1)
        {
            CreatedTime = CreatedTime.substring(0, spaceIndex);
        }
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        this.CreatedTime = createdTime;
    }

    public boolean RegisteredFoodContainsCategory(String category) {

        int categoryId = Application.getInstance().getCategoryId(category);
        if (categoryId == -1) return false;

        for(Food food : Foods) {
            if(food.getFoodCategoryId() == categoryId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "Id=" + Id +
                ", Guid='" + Guid + '\'' +
                ", FecesId=" + FecesId +
                ", ActivityId=" + ActivityId +
                ", MoodId=" + MoodId +
                ", SleepId=" + SleepId +
                ", PainPlacementId=" + PainPlacementId +
                ", PainLevelId=" + PainLevelId +
                ", hasNoFood=" + hasNoFood +
                ", Foods=" + Foods +
                '}';
    }
}
