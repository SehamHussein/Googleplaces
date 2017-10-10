package Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.systemlife.googleplaces.R;
import com.squareup.picasso.Picasso;

import Model.PlacesModel;

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
        Picasso.with(getContext()).load("").into(image);
        TextView mapType = (TextView) convertView.findViewById(R.id.textView);


        return convertView;
    }
}
//AIzaSyBIAa0kYCBEtmt1xWbikqhmq5IzPW5NvXo