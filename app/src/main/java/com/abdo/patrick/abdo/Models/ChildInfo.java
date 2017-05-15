package com.abdo.patrick.abdo.Models;

/**
 * Created by Khaled on 27-03-2017.
 */

public class ChildInfo {
    private String Name;
    private String Birthdate;
    private int Gender;
    private String CreatedTime;
    private String ModifiedTime;

    @Override
    public String toString() {
        return "ChildInfo{" +
                "Name='" + Name + '\'' +
                ", Birthdate='" + Birthdate + '\'' +
                ", Gender=" + Gender +
                ", CreatedTime='" + CreatedTime + '\'' +
                ", ModifiedTime='" + ModifiedTime + '\'' +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public String getBirthdateToView() {
        String[] datearray = Birthdate.split("-");
        datearray[1] = Integer.toString(Integer.parseInt(datearray[1])+1);
        return datearray[0]+"-"+datearray[1]+"-"+datearray[2];
    }

    public void setBirthdate(String birthdate) {
        this.Birthdate = birthdate;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        this.Gender = gender;
    }

    public String getGenderString(){
        if(Gender == 1){
            return "Dreng";
        }else if(Gender == 2){
            return "Pige";
        }
        return "";
    }
}
