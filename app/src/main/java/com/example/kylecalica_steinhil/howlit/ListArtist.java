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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListArtist extends AppCompatActivity {

    private String[] artist_list = new String[2];
    private String chosen_artist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artist);

        final ListView listing = (ListView) findViewById(R.id.ArtistList);

        artist_list[0] = "The Weekend";
        artist_list[1] = "Iggy Azella";

        final List<String> artists = new ArrayList<String>(Arrays.asList(artist_list));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, artists);
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
