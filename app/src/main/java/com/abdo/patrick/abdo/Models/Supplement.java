package com.abdo.patrick.abdo.Models;

/**
 * Created by Khaled on 26-03-2017.
 */

public class Supplement {

    private int id;
    private String description;
    private String createdTime;
    private String modifiedTime;

    @Override
    public String toString() {
        return "Supplement{" +
                "id=" + id +
                ", description='" + description + '\'' +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
