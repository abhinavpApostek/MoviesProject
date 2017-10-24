package com.example.abhinav.movies;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.abhinav.movie.R;
import com.example.abhinav.movies.adapters.RecyclerGridAdapter;
import com.example.abhinav.movies.model.MoviesList;
import com.example.abhinav.movies.model.Movie;
import com.example.abhinav.movies.utils.JsonRequest;
import com.example.abhinav.movies.view.MovieListView;
import com.example.abhinav.movies.view.MovieListViewImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoadItem,OpenMovieActivity {

    private RecyclerView recyclerView;
    private RecyclerGridAdapter recyclerGridAdapter;
    private GridLayoutManager layoutManager;
    private int count=0;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    MovieListView movieListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieListView=new MovieListViewImpl(this);
        movieListView.setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main);
        //recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
       // layoutManager=new GridLayoutManager(this,2);
        //recyclerView.setLayoutManager(layoutManager);
       movieListView.getRecyclerView().addOnScrollListener(new OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = ((GridLayoutManager)layoutManager).findFirstVisibleItemPosition();

                if (!isLoading()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isLoading=true;
                                loadItems();
                            }
                        }, 100);
                    }
                }

            }

            public boolean isLastPage() {
                return isLastPage;
            }

            public boolean isLoading() {
                return isLoading;
            }
        });
        loadItems();
    }


    @Override
    public void loadItems() {

        count++;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, JsonRequest.getJsonForPage(count), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Gson gson= new GsonBuilder().create();
                MoviesList moviesList =gson.fromJson(response.toString(),MoviesList.class);
                if(recyclerGridAdapter==null) {
                    recyclerGridAdapter = new RecyclerGridAdapter(moviesList.getMovies(),MainActivity.this,MainActivity.this);
                    recyclerGridAdapter.addLoadingFooter();
                    movieListView.setRecyclerAdapter(recyclerGridAdapter);

                }
                else{
                    recyclerGridAdapter.removeLoadingFooter();
                    isLoading = false;
                    recyclerGridAdapter.addAll(moviesList.getMovies());
                    recyclerGridAdapter.addLoadingFooter();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    public void openActivity(Movie movie) {
        Intent intent=new Intent(this,MovieActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    @Override
    public void openYoutubePlayer(String videoId) {

    }
}
