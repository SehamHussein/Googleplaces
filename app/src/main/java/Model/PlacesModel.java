package Model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
}
