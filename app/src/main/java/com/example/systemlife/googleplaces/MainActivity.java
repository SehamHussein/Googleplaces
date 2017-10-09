package com.example.systemlife.googleplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import Adapter.PlacesAdapter;
import Model.PlacesModel;

public class MainActivity extends AppCompatActivity {
Button search;
    TextView searchTxt;
    ListView listPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=(Button) findViewById(R.id.button);
        searchTxt=(TextView) findViewById(R.id.textView);
        listPlace =(ListView) findViewById(R.id.listview);

search.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBIAa0kYCBEtmt1xWbikqhmq5IzPW5NvXo";
        executeWebService(url);
    }
});
    }

    void executeWebService(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //      mTextView.setText("Response is: "+ response.substring(0,500));
                        Log.d("barka ", "onResponse: "+response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            //mTextView.setText(jsonArray.toString());
                            Log.d("barka ", "onResponse: "+jsonArray.toString());
                            final PlacesModel[] placeModel;
                            placeModel = new Gson().fromJson(jsonArray.toString(), PlacesModel[].class);
                            PlacesAdapter movieAdapter = new PlacesAdapter(MainActivity.this, placeModel);
                            listPlace.setAdapter(movieAdapter);
                            listPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //       mTextView.setText("That didn't work!");
                Log.d("fff", "onErrorResponse: "+error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
}
