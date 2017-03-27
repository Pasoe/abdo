package com.abdo.patrick.abdo.ViewModels;

/**
 * Created by Khaled on 27-03-2017.
 */

public class ListItem {

    private int id;
    private String name;

    public ListItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
