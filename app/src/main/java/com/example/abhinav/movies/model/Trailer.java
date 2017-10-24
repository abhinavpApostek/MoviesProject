package com.example.abhinav.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abhinav on 10/23/2017.
 */

public class Trailer {
    @SerializedName("results")
    List<TrailerDetails> trailerDetailsList;

    public List<TrailerDetails> getTrailerDetailsList() {
        return trailerDetailsList;
    }
}
