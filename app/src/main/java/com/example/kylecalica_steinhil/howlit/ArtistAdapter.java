package com.example.kylecalica_steinhil.howlit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.sql.Array;

/**
 * Created by kyle.calica-steinhil on 7/27/16.
 */
public class ArtistAdapter extends BaseAdapter{

    private Activity activity;
    private static LayoutInflater inflater = null;
    //test data
    private String[] artist_lsit = new String[4];

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView artistImg = (ImageView) view.findViewById(R.id.artist_img);






        return null;
    }


}
