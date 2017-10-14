package com.example.systemlife.googleplaces.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.systemlife.googleplaces.R;
import com.squareup.picasso.Picasso;

import com.example.systemlife.googleplaces.Model.PlacesModel;

/**
 * Created by System.Life on 10/8/2017.
 */

public class PlacesAdapter extends ArrayAdapter<PlacesModel> {
    public PlacesAdapter(@NonNull Context context, @NonNull PlacesModel[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.places_row, parent, false);
        }
        PlacesModel placesModel = getItem(position);

        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(getContext()).load(""+placesModel.getPhoto_reference()).into(image);
        TextView mapType = (TextView) convertView.findViewById(R.id.textView);
        mapType.setText(placesModel.getTypes()+"\n"+placesModel.getAddress());
        RatingBar rating=(RatingBar) convertView.findViewById(R.id.ratingBar);
        float rate=(float) placesModel.getRating();
        rate=rate/2;
        rating.setRating(rate);

        return convertView;
    }
}
//AIzaSyBIAa0kYCBEtmt1xWbikqhmq5IzPW5NvXo