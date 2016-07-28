package com.example.kylecalica_steinhil.howlit;

import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kylecalica_steinhil.howlit.API.LitAPI;
import com.example.kylecalica_steinhil.howlit.Models.ArtistModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListArtist extends AppCompatActivity {
    //test struct
    private String[] artist_list = new String[4];

    //for selected artist
    private String chosen_artist;


    //API string
    private String API = "";


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("artists.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artist);

         //test data and structures
        artist_list[0] = "The Weekend";
        artist_list[1] = "Iggy Azella";
        artist_list[2] = "Ice Cube";
        artist_list[3] = "Eazy E";
        final List<String> artists = new ArrayList<String>(Arrays.asList(artist_list));

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API).build();

        LitAPI lit = restAdapter.create(LitAPI.class);

        //REST API call
        lit.getListofArtists(new Callback<ArtistModel>() {
            @Override
            public void success(ArtistModel artistModel, Response response) {
                //store artist data into structures
                //data structres will be an array of the class




            }

            @Override
            public void failure(RetrofitError error) {
                    //print out errors to log

                    //scrap list view and load an error page

            }
        });


        //fill out layout with model
        final ListView listing = (ListView) findViewById(R.id.ArtistList);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.artist_box_layout, artists);
        listing.setAdapter(adapter);

        //button onClickListener to select the artist
        listing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(ListArtist.this, "Clicked", Toast.LENGTH_SHORT).show();
                chosen_artist = artist_list[i];
                Log.v("ARTIST", chosen_artist);
                Intent litTracker = new Intent(ListArtist.this, MainActivity.class).putExtra("artist", chosen_artist);
                startActivity(litTracker);
            }
        });







    }
}
