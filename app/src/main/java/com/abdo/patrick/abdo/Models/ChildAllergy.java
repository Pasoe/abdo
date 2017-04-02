package com.abdo.patrick.abdo.Models;

import com.abdo.patrick.abdo.Domain.Application;

/**
 * Created by Khaled on 27-03-2017.
 */

public class ChildAllergy {

    private int id;
    private int allergyId;
    private String createdTime;
    private String modifiedTime;

    public ChildAllergy(int id){
        allergyId = id;
    }

    public String getType(){
        for(Allergy allergy : Application.getInstance().get_allergyList()){
            if(allergy.getId() == this.allergyId){
                return allergy.getType();
            }
        }
        return null;
    }

    public int getId(){
        return allergyId;
    }
}

