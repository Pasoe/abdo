package com.abdo.patrick.abdo.Models;

import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Child {

    private int id;
    private String guid;
    private String createdTime;
    private String modifiedTime;
    private ChildInfo childInfo;
    private ShareCode shareCode;
    private ArrayList<ChildAllergy> childAllergies;
    private ArrayList<ChildMedicine> childMedicines;
    private ArrayList<ChildSupplement> childSupplements;
    private ArrayList<Anonymous> anonymous;
    private ArrayList<Registration> registrations;

    public Child() {
        childInfo = new ChildInfo();
        childAllergies = new ArrayList<ChildAllergy>();
        childMedicines = new ArrayList<ChildMedicine>();
        childSupplements = new ArrayList<ChildSupplement>();
        registrations = new ArrayList<Registration>();
    }

    public Child(String guid) {
        this.guid = guid;
        childInfo = new ChildInfo();
        childAllergies = new ArrayList<ChildAllergy>();
        childMedicines = new ArrayList<ChildMedicine>();
        childSupplements = new ArrayList<ChildSupplement>();
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", createdTime='" + createdTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", anonymous=" + anonymous +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public ArrayList<ChildAllergy> getAllergies(){
        return childAllergies;
    }

    public void addAllergy(int id){
        ChildAllergy allergy = new ChildAllergy(id);
        childAllergies.add(allergy);
    }

    public void removeAllergy(int id){
        for(ChildAllergy allergy : childAllergies){
            if(allergy.getId() == id){
                childAllergies.remove(allergy);
                return;
            }
        }
    }

    public boolean allergyExists(int id){
        for(ChildAllergy allergy : childAllergies){
            if(allergy.getId() == id){
                return true;
            }
        }
        return false;
    }

    public ArrayList<ChildSupplement> getSupplements(){
        return childSupplements;
    }

    public void addSupplement(int id){
        ChildSupplement supplement = new ChildSupplement(id);
        childSupplements.add(supplement);
    }

    public void removeSupplement(int id){
        for(ChildSupplement supplement : childSupplements){
            if(supplement.getId() == id){
                childSupplements.remove(supplement);
                return;
            }
        }
    }

    public boolean supplementExists(int id){
        for(ChildSupplement supplement : childSupplements){
            if(supplement.getId() == id){
                return true;
            }
        }
        return false;
    }

    public ArrayList<ChildMedicine> getMedicineList(){
        return childMedicines;
    }

    public void addMedicine(String type, String dosage){
        ChildMedicine newMedicine = new ChildMedicine();
        newMedicine.setType(type);
        newMedicine.setDosage(dosage);
        childMedicines.add(newMedicine);
    }

    public void updateMedicine(String oldType, String oldDosage, String type, String dosage){
        for(ChildMedicine medicine : childMedicines){
            if(medicine.getType().equals(oldType) && medicine.getDosage().equals(oldDosage)){
                medicine.setType(type);
                medicine.setDosage(dosage);
                return;
            }
        }
    }

    public void removeMedicine(String type, String dosage){
        for(ChildMedicine medicine : childMedicines){
            if(medicine.getType().equals(type) && medicine.getDosage().equals(dosage)){
                childMedicines.remove(medicine);
                return;
            }
        }
    }

    public void setInfo(ChildInfo info){
        childInfo = info;
    }

    public ChildInfo getInfo(){
        return childInfo;
    }

    public boolean isBoy() {
        if(getInfo().getGender() == 1)
            return true;
        return false;
    }

    public void addRegistration(Registration reg){
        registrations.add(reg);
    }

    public ArrayList<Registration> getRegistrations(){
        return registrations;
    }
}
