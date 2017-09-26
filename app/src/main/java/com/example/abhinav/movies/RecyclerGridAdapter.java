package com.example.abhinav.movies;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Abhinav on 9/18/2017.
 */

public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.ViewHolder> {

    List<Movies> moviesList;
    LoadItem loadItem;
    private boolean isLoadingAdded = false;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    public RecyclerGridAdapter(List<Movies> moviesList,LoadItem loadItem,OpenMovieActivity openMovieActivity) {
        this.moviesList=moviesList;
        this.loadItem=loadItem;
    }
    @Override
    public RecyclerGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("oncreateholder",Integer.toString(viewType));
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
        Log.v("getitemviewtype",Integer.toString(position));
        return (position == moviesList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerGridAdapter.ViewHolder holder, int position) {

        Log.v("onbindholder",Integer.toString(position));
        if(getItemViewType(position)==0) {
            Picasso.with((MainActivity) loadItem).load("https://image.tmdb.org/t/p/original" + moviesList.get(position).getPoster_path()).resize(200,300).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        Log.v("getItemCount",Integer.toString(moviesList.size()));
        return moviesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
        }
    }

    private void add(Movies mc) {
        moviesList.add(mc);
        notifyItemInserted(moviesList.size() - 1);
    }

    public void addAll(List<Movies> mcList) {
        for (Movies mc : mcList) {
            add(mc);
        }
    }
    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Movies());
    }
    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = moviesList.size() - 1;
        Movies item = getItem(position);

        if (item != null) {
            moviesList.remove(position);
            notifyItemRemoved(position);
        }
    }
    public Movies getItem(int position) {
        return moviesList.get(position);
    }
}
