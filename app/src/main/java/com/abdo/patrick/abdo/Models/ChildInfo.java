package com.abdo.patrick.abdo.Models;

import android.util.Log;

/**
 * Created by Khaled on 27-03-2017.
 */

public class ChildInfo {
    private String Name;
    private String Birthdate;
    private int Gender;
    private String CreatedTime;
    private String ModifiedTime;

    public ChildInfo(){
        Name = "";
    }

    public ChildInfo(String name, String birthdate, int gender) {
        Name = name;
        Birthdate = birthdate;
        Gender = gender;
    }

    @Override
    public String toString() {
        return "ChildInfo{" +
                "Name='" + Name + '\'' +
                ", Birthdate='" + Birthdate + '\'' +
                ", Gender=" + Gender +
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

        int spaceIndex = Birthdate.indexOf("T");
        if (spaceIndex != -1)
        {
            Birthdate = Birthdate.substring(0, spaceIndex);
        }

        String[] datearray = Birthdate.split("-");
        Log.d("DEBUG", "Birthdate: " +Birthdate);
        Log.d("DEBUG", "Bithdate array: " +datearray[0] +" "+ datearray[1] +" "+ datearray[2]);
        datearray[1] = Integer.toString(Integer.parseInt(datearray[1])+1);
        return datearray[2]+"-"+datearray[1]+"-"+datearray[0];
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
