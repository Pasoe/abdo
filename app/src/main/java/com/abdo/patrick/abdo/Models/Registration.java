package com.abdo.patrick.abdo.Models;

import java.util.ArrayList;

/**
 * Created by Khaled on 27-03-2017.
 */

public class Registration {

    private int id;
    private int childId;
    private Integer fecesId;
    private Integer activityId;
    private Integer painPlacementId;
    private Integer painLevelId;
    private String createdTime;
    private String modifiedTime;
    private Child child;
    private ArrayList<Food> foods;
    private Feces feces;
    private Sleep sleep;
    private Mood mood;
    private Activity activity;
    private PainPlacement painPlacement;
    private PainLevel painLevel;
}
