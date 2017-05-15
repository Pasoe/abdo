package com.abdo.patrick.abdo.Models;

/**
 * Created by Khaled on 26-03-2017.
 */

public class Supplement {

    private int Id;
    private String Description;
    private String CreatedTime;
    private String ModifiedTime;

    public Supplement() {
    }

    public Supplement(int id, String description) {
        this.Id = id;
        this.Description = description;
    }

    @Override
    public String toString() {
        return "Supplement{" +
                "Id=" + Id +
                ", Description='" + Description + '\'' +
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
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

        if (!(obj instanceof Supplement))
            return false;

        Supplement supp = (Supplement) obj;
        if (supp.getId() == this.Id && supp.getDescription().equals(this.Description))
            return true;

        return false;
    }
}
