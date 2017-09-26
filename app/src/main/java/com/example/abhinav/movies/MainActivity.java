package com.example.abhinav.movies;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoadItem,OpenMovieActivity {

    private RecyclerView recyclerView;
    private String jsonUrl="https://api.themoviedb.org/3/discover/movie?api_key=e11324cc13908a3befcf286c572aaa0e&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";
    private RecyclerGridAdapter recyclerGridAdapter;
    private GridLayoutManager layoutManager;
    private int count=0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new OnScrollListener(){
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
        String s=jsonUrl+Integer.toString(count);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, s, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Gson gson= new GsonBuilder().create();
                MoviePage moviePage=gson.fromJson(response.toString(),MoviePage.class);
                if(recyclerGridAdapter==null) {
                    recyclerGridAdapter = new RecyclerGridAdapter(moviePage.getMovies(),MainActivity.this,MainActivity.this);
                    recyclerGridAdapter.addLoadingFooter();
                    recyclerView.setAdapter(recyclerGridAdapter);

                }
                else{
                    recyclerGridAdapter.removeLoadingFooter();
                    isLoading = false;
                    recyclerGridAdapter.addAll(moviePage.getMovies());
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

    @Override
    public void openActivity(Movies movies) {
        Intent intent=new Intent(this,MovieActivity.class);
        intent.putExtra("movies",movies);
        startActivity(intent);
    }
}
