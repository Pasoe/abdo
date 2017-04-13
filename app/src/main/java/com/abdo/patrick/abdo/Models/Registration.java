package com.abdo.patrick.abdo.Models;

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

    //Virtual
    private Child child;
    private ArrayList<Food> foods;
    private Feces feces;
    private Sleep sleep;
    private Mood mood;
    private Activity activity;
    private PainPlacement painPlacement;
    private PainLevel painLevel;


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

    public String getGuid() {
        return guid;
    }

    public Integer getFecesId() {
        return fecesId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public Integer getPainPlacementId() {
        return painPlacementId;
    }

    public Integer getPainLevelId() {
        return painLevelId;
    }

    public Integer getMoodId() {
        return moodId;
    }

    public Integer getSleepId() {
        return sleepId;
    }

    @Override
    public String toString() {
        return "Registration{" +
                ", guid='" + guid + '\'' +
                ", fecesId=" + fecesId +
                ", activityId=" + activityId +
                ", painPlacementId=" + painPlacementId +
                ", painLevelId=" + painLevelId +
                ", moodId=" + moodId +
                ", sleepId=" + sleepId +
                '}';
    }
}
