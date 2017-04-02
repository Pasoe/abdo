package com.abdo.patrick.abdo.Models;

import com.abdo.patrick.abdo.Domain.Application;

/**
 * Created by Khaled on 27-03-2017.
 */

public class ChildSupplement {
    private int id;
    private int supplementId;
    private String createdTime;
    private String modifiedTime;

    public ChildSupplement(int id){
        supplementId = id;
    }

    public String getDescription(){
        for(Supplement supplement : Application.getInstance().get_supplementList()){
            if(supplement.getId() == this.supplementId){
                return supplement.getDescription();
            }
        }
        return null;
    }

    public int getId(){
        return supplementId;
    }
}
