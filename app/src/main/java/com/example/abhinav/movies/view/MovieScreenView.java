package com.example.abhinav.movies.view;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Abhinav on 10/17/2017.
 */

public interface MovieScreenView {

    public void setContentView(int layout);
    public void setImage(String url);
    public void setTitle(String title);
    public void setRating(String rating);
    public void setDuration(String duration);
    public void setRecyclerViewLayout(RecyclerView.LayoutManager layoutManager);
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter);
    public void setReleaseDate(String releaseDate);
    public void setOverview(String overview);

}
