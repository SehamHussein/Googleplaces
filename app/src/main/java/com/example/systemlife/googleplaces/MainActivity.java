package com.example.systemlife.googleplaces;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;

import Adapter.PlacesAdapter;
import Model.PlacesModel;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    Button search;
    TextView searchTxt;
    ListView listPlace;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ProgressDialog=new ProgressDialog(MainActivity.this);
        progressDialog=new ProgressDialog(MainActivity.this);

        search = (Button) findViewById(R.id.button);
        searchTxt = (TextView) findViewById(R.id.textView);
        listPlace = (ListView) findViewById(R.id.listview);
//final String searchType=searchTxt.getText().toString();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetPlacesFormatAPI getPlacesFormatAPI=new GetPlacesFormatAPI();
                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBIAa0kYCBEtmt1xWbikqhmq5IzPW5NvXo";
                getPlacesFormatAPI.execute(url);
                //executeWebService(url);
            }
        });
    }

class GetPlacesFormatAPI extends AsyncTask<String ,Void,PlacesModel[]>{

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("loading....... ");
        progressDialog.show();
    }

OkHttpClient client=new OkHttpClient();

    String run(String url) throws IOException {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();

        okhttp3.Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    protected PlacesModel[] doInBackground(String... url) {

        try {
            String s = run(url[0]);
            Log.d("yla b2a", s);
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            final PlacesModel[] placesModels;
            placesModels=new Gson().fromJson(jsonArray.toString(),PlacesModel[].class);
            return placesModels;
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(final PlacesModel[] placesModels) {
        progressDialog.dismiss();
        if (placesModels!=null){
            PlacesAdapter placesAdapter=new PlacesAdapter(MainActivity.this,placesModels);
            listPlace.setAdapter(placesAdapter);
            listPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(MainActivity.this, placesModels[i].getTypes(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}



//    void executeWebService(String url) {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        //      mTextView.setText("Response is: "+ response.substring(0,500));
//                        Log.d("barka ", "onResponse: " + response);
//                        try {
//
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray jsonArray = jsonObject.getJSONArray("results");
//                            //mTextView.setText(jsonArray.toString());
//                            Log.d("barka ", "onResponse: " + jsonArray.toString());
//                            final PlacesModel[] placeModel;
//                            placeModel = new Gson().fromJson(jsonArray.toString(), PlacesModel[].class);
//                            PlacesAdapter movieAdapter = new PlacesAdapter(MainActivity.this, placeModel);
//                            listPlace.setAdapter(movieAdapter);
//                            listPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                                }
//                            });
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //       mTextView.setText("That didn't work!");
//                Log.d("fff", "onErrorResponse: " + error.getMessage());
//            }
//        });
//        queue.add(stringRequest);
//    }
}
