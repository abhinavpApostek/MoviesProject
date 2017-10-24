package com.example.abhinav.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abhinav on 9/18/2017.
 */

public class MoviesList {

    @SerializedName("page")
    int page;

    @SerializedName("results")
    List<Movie> movies;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
