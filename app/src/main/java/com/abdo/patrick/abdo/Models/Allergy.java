package com.abdo.patrick.abdo.Models;

import com.abdo.patrick.abdo.ViewModels.ListItem;

import java.util.ArrayList;

/**
 * Created by Khaled on 26-03-2017.
 */

public class Allergy {

    private int id;
    private String type;
    private String createdTime;
    private String modifiedTime;

    @Override
    public String toString() {
        return "Allergy{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Allergy))
            return false;

        Allergy allergy = (Allergy) obj;
        if (allergy.getId() == this.id && allergy.getType().equals(this.type))
            return true;

        return false;
    }

}
