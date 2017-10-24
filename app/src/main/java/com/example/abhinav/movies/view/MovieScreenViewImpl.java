package com.example.abhinav.movies.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhinav.movie.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Abhinav on 10/20/2017.
 */

public class MovieScreenViewImpl implements MovieScreenView {

    Activity activity;
    ImageView imageView;
    TextView title;
    TextView rating;
    TextView duration;
    TextView overview;
    RecyclerView recyclerView;
    public MovieScreenViewImpl(AppCompatActivity activity) {
        this.activity=activity;

    }
    @Override
    public void setContentView(int layout) {
        activity.setContentView(layout);
        initialize(activity);
    }

    private void initialize(Activity activity) {
        imageView=(ImageView)activity.findViewById(R.id.imageView2);
        title=(TextView)activity.findViewById(R.id.textView);
        duration=(TextView)activity.findViewById(R.id.textView2);
        rating=(TextView)activity.findViewById(R.id.textView3);
        overview=(TextView)activity.findViewById(R.id.textView5);
        recyclerView=(RecyclerView)activity.findViewById(R.id.recyclerView2);
    }

    @Override
    public void setImage(String url) {
        Picasso.with(imageView.getContext()).load(url).resize(180,180).into(imageView);
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
        this.title.setSelected(true);
    }

    @Override
    public void setRating(String rating) {
        this.rating.setText(rating);
    }

    @Override
    public void setDuration(String duration) {
        this.duration.setText(duration);
    }

    @Override
    public void setRecyclerViewLayout(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        this.duration.setText(releaseDate);
    }

    @Override
    public void setOverview(String overview) {
        this.overview.setText(overview);
    }
}
