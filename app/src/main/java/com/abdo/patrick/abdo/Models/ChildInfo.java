package com.abdo.patrick.abdo.Models;

/**
 * Created by Khaled on 27-03-2017.
 */

public class ChildInfo {
    private String name;
    private String birthdate;
    private int gender;
    private String createdTime;
    private String modifiedTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getBirthdateToView() {
        String[] datearray = birthdate.split("-");
        datearray[1] = Integer.toString(Integer.parseInt(datearray[1])+1);
        return datearray[0]+"-"+datearray[1]+"-"+datearray[2];
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getGenderString(){
        if(gender == 1){
            return "Dreng";
        }else if(gender == 2){
            return "Pige";
        }
        return "";
    }
}
