package com.abdo.patrick.abdo.Models;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Khaled on 21-03-2017.
 */

public class Child implements Cloneable {

    private int Id;
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

    public Child(String guid, ArrayList<ChildAllergy> alg, ArrayList<ChildMedicine> mdcn,
                 ArrayList<ChildSupplement> supp, String name, String birthdate, int gender) {
        this.Guid = guid;
        this.ChildAllergies = alg;
        this.ChildSupplements = supp;
        this.ChildMedicines = mdcn;
        this.ChildInfo = new ChildInfo(name, birthdate, gender);
    }

    public Child() {
        ChildInfo = new ChildInfo();
        ChildAllergies = new ArrayList<ChildAllergy>();
        ChildMedicines = new ArrayList<ChildMedicine>();
        ChildSupplements = new ArrayList<ChildSupplement>();
        Registrations = new ArrayList<Registration>();
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
                ", ChildInfo=" + ChildInfo +
                ", ChildAllergies=" + ChildAllergies +
                ", ChildMedicines=" + ChildMedicines +
                ", ChildSupplements=" + ChildSupplements +
                '}';
    }

    public ShareCode getShareCode() {
        return this.ShareCode != null ? this.ShareCode: (this.ShareCode = new ShareCode());
    }

    public void setShareCode(com.abdo.patrick.abdo.Models.ShareCode shareCode) {
        ShareCode = shareCode;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getGuid() {
        return Guid;
    }

    public ChildInfo getChildInfo() {
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
        Registrations.add(0, reg);
    }

    public Registration getRegistration(String guid){
        if (Registrations == null) return null;
        Log.d("DEBUG", "Child registrations: "+Registrations.toString());
        for(Registration reg : Registrations){
            if(reg.getGuid().equals(guid)){
                return reg;
            }
        }
        return null;
    }

    public void updateRegistration(Registration reg){
        int index = Registrations.indexOf(reg);
        Registrations.remove(index);
        Registrations.add(index, reg);
    }

    public ArrayList<Registration> getRegistrations(){
        return Registrations;
    }

    public void setChildAllergies(ArrayList<ChildAllergy> childAllergies) {
        ChildAllergies = childAllergies;
    }

    public void setChildMedicines(ArrayList<ChildMedicine> childMedicines) {
        ChildMedicines = childMedicines;
    }

    public void setChildSupplements(ArrayList<ChildSupplement> childSupplements) {
        ChildSupplements = childSupplements;
    }

    @Override
    public boolean equals(Object obj) {

        Child c = (Child) obj;

        Log.d("Equals","this: " +this.toString());
        Log.d("Equals","obj : " + obj.toString());

        if (this.Guid.equals(c.getGuid()))
            if (this.ChildInfo.getName().equals(c.getInfo().getName()))
                if (this.ChildInfo.getBirthdate().equals(c.getInfo().getBirthdate()))
                    if (this.ChildInfo.getGender() == c.getInfo().getGender())
                        if (isTwoArrayListsWithSameValues(this.ChildAllergies, c.ChildAllergies))
                            if (isTwoArrayListsWithSameValues(this.ChildMedicines, c.ChildMedicines))
                                if (isTwoArrayListsWithSameValues(this.ChildSupplements, c.ChildSupplements)) return true;
        return false;
    }

    private boolean isTwoArrayListsWithSameValues(ArrayList<?> list1, ArrayList<?> list2)
    {
        //null checking
        if(list1==null && list2==null)
            return true;
        if((list1 == null && list2 != null) || (list1 != null && list2 == null))
            return false;

        if(list1.size()!=list2.size())
            return false;
        for(Object itemList1: list1)
        {
            if(!list2.contains(itemList1))
                return false;
        }

        return true;
    }
}
