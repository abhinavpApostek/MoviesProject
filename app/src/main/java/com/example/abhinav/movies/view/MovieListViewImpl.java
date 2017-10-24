package com.example.abhinav.movies.view;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.abhinav.movie.R;
import com.example.abhinav.movies.AppController;
import com.example.abhinav.movies.adapters.RecyclerGridAdapter;
import com.example.abhinav.movies.model.MoviesList;
import com.example.abhinav.movies.utils.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by Abhinav on 10/24/2017.
 */

public class MovieListViewImpl implements MovieListView {

    Activity activity;
    private RecyclerView recyclerView;
    private RecyclerGridAdapter recyclerGridAdapter;
    private GridLayoutManager layoutManager;
    public MovieListViewImpl(Activity activity)
    {
        this.activity=activity;
    }
    @Override
    public void setContentView(int layout) {
        activity.setContentView(layout);
        initialize();
    }

    private void initialize() {
        recyclerView=(RecyclerView)activity.findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(activity,2);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void setRecyclerAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
