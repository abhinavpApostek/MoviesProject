package com.example.abhinav.movies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.abhinav.movie.R;
import com.example.abhinav.movies.LoadItem;
import com.example.abhinav.movies.MainActivity;
import com.example.abhinav.movies.OpenMovieActivity;
import com.example.abhinav.movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Abhinav on 9/18/2017.
 */

public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.ViewHolder> {

    List<Movie> movieList;
    LoadItem loadItem;
    private boolean isLoadingAdded = false;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    public RecyclerGridAdapter(List<Movie> movieList, LoadItem loadItem, OpenMovieActivity openMovieActivity) {
        this.movieList = movieList;
        this.loadItem=loadItem;
    }
    @Override
    public RecyclerGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // Log.v("oncreateholder",Integer.toString(viewType));
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v;
        if(viewType==0) {
            v = inflater.inflate(R.layout.grid_layout, parent, false);

        }
        else{
            v=inflater.inflate(R.layout.footer,parent,false);

        }
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public int getItemViewType(int position) {
        //Log.v("getitemviewtype",Integer.toString(position));
        return (position == movieList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerGridAdapter.ViewHolder holder, final int position) {

       // Log.v("onbindholder",Integer.toString(position));
        if(getItemViewType(position)==0) {
            Picasso.with((MainActivity) loadItem).load("https://image.tmdb.org/t/p/original" + movieList.get(position).getPoster_path()).resize(200,300).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) loadItem).openActivity(movieList.get(position));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
       // Log.v("getItemCount",Integer.toString(movieList.size()));
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
        }
    }

    private void add(Movie mc) {
        movieList.add(mc);
        notifyItemInserted(movieList.size() - 1);
    }

    public void addAll(List<Movie> mcList) {
        for (Movie mc : mcList) {
            add(mc);
        }
    }
    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Movie());
    }
    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movieList.size() - 1;
        Movie item = getItem(position);

        if (item != null) {
            movieList.remove(position);
            notifyItemRemoved(position);
        }
    }
    public Movie getItem(int position) {
        return movieList.get(position);
    }
}
