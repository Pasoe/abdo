package com.abdo.patrick.abdo.Models;

import com.abdo.patrick.abdo.Domain.Application;

/**
 * Created by Khaled on 27-03-2017.
 */

public class ChildAllergy {

    private int Id;
    private int AllergyId;
    private String CreatedTime;
    private String ModifiedTime;

    public ChildAllergy(int id){
        AllergyId = id;
    }

    public String getType(){
        for(Allergy allergy : Application.getInstance().get_allergyList()){
            if(allergy.getId() == this.AllergyId){
                return allergy.getType();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ChildAllergy{" +
                "Id=" + Id +
                ", AllergyId=" + AllergyId +
                ", CreatedTime='" + CreatedTime + '\'' +
                ", ModifiedTime='" + ModifiedTime + '\'' +
                '}';
    }

    public int getId(){
        return AllergyId;
    }
}

