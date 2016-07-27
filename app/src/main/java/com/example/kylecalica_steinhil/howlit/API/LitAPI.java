package com.example.kylecalica_steinhil.howlit.API;

/**
 * Created by kyle.calica-steinhil on 7/27/16.
 */

import com.example.kylecalica_steinhil.howlit.Models.ArtistModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;


public interface LitAPI {

    @GET("/artists")
    public void getListofArtists(@Path("artists") String user, Callback<ArtistModel> response);

}



