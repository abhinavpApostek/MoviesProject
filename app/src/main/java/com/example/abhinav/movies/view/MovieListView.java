package com.example.abhinav.movies.view;

import android.support.v7.widget.RecyclerView;

import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Created by Abhinav on 10/24/2017.
 */

public interface MovieListView {

    public void setContentView(int layout);
    public RecyclerView getRecyclerView();
    public void setRecyclerAdapter(RecyclerView.Adapter adapter);

}
