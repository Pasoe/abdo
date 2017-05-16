package com.abdo.patrick.abdo.Models;

import com.abdo.patrick.abdo.Domain.Application;

/**
 * Created by Khaled on 27-03-2017.
 */

public class ChildSupplement {
    private int Id;
    private int SupplementId;
    private String CreatedTime;
    private String ModifiedTime;

    public ChildSupplement(int id){
        SupplementId = id;
    }

    @Override
    public String toString() {
        return "ChildSupplement{" +
                "Id=" + Id +
                ", SupplementId=" + SupplementId +
                ", CreatedTime='" + CreatedTime + '\'' +
                ", ModifiedTime='" + ModifiedTime + '\'' +
                '}';
    }

    public String getDescription(){
        for(Supplement supplement : Application.getInstance().get_supplementList()){
            if(supplement.getId() == this.SupplementId){
                return supplement.getDescription();
            }
        }
        return null;
    }

    public int getId(){
        return SupplementId;
    }
}
