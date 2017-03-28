package com.abdo.patrick.abdo.Models;

/**
 * Created by Khaled on 26-03-2017.
 */

public class Supplement {

    private int id;
    private String description;
    private String createdTime;
    private String modifiedTime;

    public Supplement() {
    }

    public Supplement(int id, String description) {
        this.id = id;
        this.description = description;
    }

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

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Supplement))
            return false;

        Supplement supp = (Supplement) obj;
        if (supp.getId() == this.id && supp.getDescription().equals(this.description))
            return true;

        return false;
    }
}
