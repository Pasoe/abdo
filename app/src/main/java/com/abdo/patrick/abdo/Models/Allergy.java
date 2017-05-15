package com.abdo.patrick.abdo.Models;

/**
 * Created by Khaled on 26-03-2017.
 */

public class Allergy {

    private int Id;
    private String Type;
    private String CreatedTime;
    private String ModifiedTime;

    public Allergy(){

    }

    public Allergy(int id, String type){
        this.Id = id;
        this.Type = type;
    }

    @Override
    public String toString() {
        return "Allergy{" +
                "Id=" + Id +
                ", Type='" + Type + '\'' +
                ", CreatedTime='" + CreatedTime + '\'' +
                ", ModifiedTime='" + ModifiedTime + '\'' +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
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

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Allergy))
            return false;

        Allergy allergy = (Allergy) obj;
        if (allergy.getId() == this.Id && allergy.getType().equals(this.Type))
            return true;

        return false;
    }

}
