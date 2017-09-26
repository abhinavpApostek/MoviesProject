package com.example.abhinav.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abhinav on 9/18/2017.
 */

public class MoviePage {

    @SerializedName("page")
    int page;

    @SerializedName("results")
    List<Movies> movies;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }
}
