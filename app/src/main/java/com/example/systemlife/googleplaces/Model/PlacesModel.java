package com.example.systemlife.googleplaces.Model;

import java.io.Serializable;

/**
 * Created by System.Life on 10/8/2017.
 */

public class PlacesModel implements Serializable {
    String name="";
    private String number="";
    private String address="";
    private static final long serialVersionUID = 46543445;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public String getNumber()
    {
        return number;
    }

    public String getAddress()
    {
        return address;
    }

    float rating;
    String photo_reference ,types;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
