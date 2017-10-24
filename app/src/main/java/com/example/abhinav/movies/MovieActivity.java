package com.example.abhinav.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.abhinav.movie.R;
import com.example.abhinav.movies.adapters.RecyclerTrailerAdapter;
import com.example.abhinav.movies.model.Movie;
import com.example.abhinav.movies.model.Trailer;
import com.example.abhinav.movies.model.TrailerDetails;
import com.example.abhinav.movies.utils.DeveloperKey;
import com.example.abhinav.movies.utils.JsonRequest;
import com.example.abhinav.movies.view.MovieScreenView;
import com.example.abhinav.movies.view.MovieScreenViewImpl;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Abhinav on 9/21/2017.
 */

public class MovieActivity extends AppCompatActivity implements OpenMovieActivity {

    MovieScreenView movieScreenView;
    RecyclerTrailerAdapter recyclerTrailerAdapter;
    Movie movie;
    List<TrailerDetails> trailerDetailsList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieScreenView=new MovieScreenViewImpl(this);
        movieScreenView.setContentView(R.layout.movie_screen);
        movie=(Movie)getIntent().getExtras().get("movie");
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, JsonRequest.getJsonForTrailers(movie.getId()), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson= new GsonBuilder().create();
                Trailer trailer=gson.fromJson(response.toString(),Trailer.class);
                trailerDetailsList=trailer.getTrailerDetailsList();
                movieScreenView.setRecyclerViewLayout(new LinearLayoutManager(MovieActivity.this));
                movieScreenView.setRecyclerViewAdapter(getRecyclerTrailerAdapter(trailerDetailsList));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        movieScreenView.setImage("https://image.tmdb.org/t/p/original"+movie.getPoster_path());
        movieScreenView.setTitle(movie.getTitle());
        movieScreenView.setRating(Double.toString(movie.getVoteAvg()));
        movieScreenView.setReleaseDate(movie.getReleaseDate());
        movieScreenView.setOverview(movie.getOverview());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public RecyclerTrailerAdapter getRecyclerTrailerAdapter(List<TrailerDetails> trailerDetailsList) {
        recyclerTrailerAdapter=new RecyclerTrailerAdapter(trailerDetailsList,this);
        return recyclerTrailerAdapter;
    }

    @Override
    public void openYoutubePlayer(String videoId)
    {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(this, DeveloperKey.DEVELOPER_KEY, videoId);
        startActivity(intent);
    }
}
