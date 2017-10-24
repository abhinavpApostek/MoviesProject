package com.example.abhinav.movies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhinav on 10/23/2017.
 */

public class TrailerDetails {

    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("key")
    String key;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
