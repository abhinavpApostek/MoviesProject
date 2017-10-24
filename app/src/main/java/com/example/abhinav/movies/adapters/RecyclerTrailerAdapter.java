package com.example.abhinav.movies.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhinav.movie.R;
import com.example.abhinav.movies.OpenMovieActivity;
import com.example.abhinav.movies.model.TrailerDetails;
import com.example.abhinav.movies.utils.DeveloperKey;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

/**
 * Created by Abhinav on 10/23/2017.
 */

public class RecyclerTrailerAdapter extends RecyclerView.Adapter<RecyclerTrailerAdapter.ViewHolder> {

    List<TrailerDetails> trailerDetailsList;
    OpenMovieActivity openMovieActivity;
    public RecyclerTrailerAdapter(List<TrailerDetails> trailerDetailsList, OpenMovieActivity openMovieActivity){
        this.trailerDetailsList=trailerDetailsList;
        this.openMovieActivity=openMovieActivity;
    }

    @Override
    public RecyclerTrailerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.trailer_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerTrailerAdapter.ViewHolder holder, int position) {
        final int position1=position;
        YouTubeThumbnailView youTubeThumbnailView=holder.youTubeThumbnailView;
        if(!holder.isLoaded()) {
            youTubeThumbnailView.initialize(DeveloperKey.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    youTubeThumbnailLoader.setVideo(trailerDetailsList.get(position1).getKey());
                    Log.v("initialization success", "video id set");
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                        @Override
                        public void onThumbnailLoaded(final YouTubeThumbnailView youTubeThumbnailView, String s) {
                            holder.setLoaded(true);
                            youTubeThumbnailLoader.release();
                            youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    openMovieActivity.openYoutubePlayer(trailerDetailsList.get(position1).getKey());
                                }
                            });
                            Log.v("thumbnailloader", "success");
                        }

                        @Override
                        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                            youTubeThumbnailLoader.release();
                            Log.v("thumbnailloader", "error");
                        }
                    });
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });
        }
        holder.textView.setText(trailerDetailsList.get(position1).getName());
    }

    @Override
    public int getItemCount() {
        return trailerDetailsList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        private boolean loaded=false;
        YouTubeThumbnailView youTubeThumbnailView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            youTubeThumbnailView=(YouTubeThumbnailView)itemView.findViewById(R.id.thumbnail);
            textView=(TextView)itemView.findViewById(R.id.textView5);
        }

        public boolean isLoaded() {
            return loaded;
        }

        public void setLoaded(boolean loaded) {
            this.loaded = loaded;
        }
    }

}
