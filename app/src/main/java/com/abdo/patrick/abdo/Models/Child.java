package com.abdo.patrick.abdo.Models;

import java.util.ArrayList;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Child {

    private int id;
    private String Guid;
    private String CreatedTime;
    private String ModifiedTime;
    private ChildInfo ChildInfo;
    private ShareCode ShareCode;
    private ArrayList<ChildAllergy> ChildAllergies;
    private ArrayList<ChildMedicine> ChildMedicines;
    private ArrayList<ChildSupplement> ChildSupplements;
    private ArrayList<Anonymous> Anonymous;
    private ArrayList<Registration> Registrations;

    public Child() {
        ChildInfo = new ChildInfo();
        ChildAllergies = new ArrayList<ChildAllergy>();
        ChildMedicines = new ArrayList<ChildMedicine>();
        ChildSupplements = new ArrayList<ChildSupplement>();
        registrations = new ArrayList<Registration>();
    }

    public Child(String guid) {
        this.Guid = guid;
        ChildInfo = new ChildInfo();
        ChildAllergies = new ArrayList<ChildAllergy>();
        ChildMedicines = new ArrayList<ChildMedicine>();
        ChildSupplements = new ArrayList<ChildSupplement>();
    }

    @Override
    public String toString() {
        return "Child{" +
                "Guid='" + Guid + '\'' +
                ", CreatedTime='" + CreatedTime + '\'' +
                ", ModifiedTime='" + ModifiedTime + '\'' +
                ", ChildInfo=" + ChildInfo +
                ", ShareCode=" + ShareCode +
                ", ChildAllergies=" + ChildAllergies +
                ", ChildMedicines=" + ChildMedicines +
                ", ChildSupplements=" + ChildSupplements +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuid() {
        return Guid;
    }

    public com.abdo.patrick.abdo.Models.ChildInfo getChildInfo() {
        return ChildInfo;
    }

    public void setGuid(String guid) {
        this.Guid = guid;
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

    public ArrayList<ChildAllergy> getAllergies(){
        return ChildAllergies;
    }

    public void addAllergy(int id){
        ChildAllergy allergy = new ChildAllergy(id);
        ChildAllergies.add(allergy);
    }

    public void removeAllergy(int id){
        for(ChildAllergy allergy : ChildAllergies){
            if(allergy.getId() == id){
                ChildAllergies.remove(allergy);
                return;
            }
        }
    }

    public boolean allergyExists(int id){
        for(ChildAllergy allergy : ChildAllergies){
            if(allergy.getId() == id){
                return true;
            }
        }
        return false;
    }

    public ArrayList<ChildSupplement> getSupplements(){
        return ChildSupplements;
    }

    public void addSupplement(int id){
        ChildSupplement supplement = new ChildSupplement(id);
        ChildSupplements.add(supplement);
    }

    public void removeSupplement(int id){
        for(ChildSupplement supplement : ChildSupplements){
            if(supplement.getId() == id){
                ChildSupplements.remove(supplement);
                return;
            }
        }
    }

    public boolean supplementExists(int id){
        for(ChildSupplement supplement : ChildSupplements){
            if(supplement.getId() == id){
                return true;
            }
        }
        return false;
    }

    public ArrayList<ChildMedicine> getMedicineList(){
        return ChildMedicines;
    }

    public void addMedicine(String type, String dosage){
        ChildMedicine newMedicine = new ChildMedicine();
        newMedicine.setType(type);
        newMedicine.setDosage(dosage);
        ChildMedicines.add(newMedicine);
    }

    public void updateMedicine(String oldType, String oldDosage, String type, String dosage){
        for(ChildMedicine medicine : ChildMedicines){
            if(medicine.getType().equals(oldType) && medicine.getDosage().equals(oldDosage)){
                medicine.setType(type);
                medicine.setDosage(dosage);
                return;
            }
        }
    }

    public void removeMedicine(String type, String dosage){
        for(ChildMedicine medicine : ChildMedicines){
            if(medicine.getType().equals(type) && medicine.getDosage().equals(dosage)){
                ChildMedicines.remove(medicine);
                return;
            }
        }
    }

    public void setInfo(ChildInfo info){
        ChildInfo = info;
    }

    public ChildInfo getInfo(){
        return ChildInfo;
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
