package com.abdo.patrick.abdo.ViewModels;

import com.abdo.patrick.abdo.Models.Registration;

/**
 * Created by Patrick on 15-05-2017.
 */

public class RegistrationListItem {
    private String id;
    private String date;


    public RegistrationListItem(String id, String date){
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
