package com.example.abhinav.movies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhinav on 9/18/2017.
 */

public class Movie implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    public Movie() {

    }

    @SerializedName("title")
    String title;
    @SerializedName("id")
    int id;
    @SerializedName("poster_path")
    String poster_path;
    @SerializedName("vote_average")
    double voteAvg;
    @SerializedName("overview")
    String overview;
    @SerializedName("release_date")
    String releaseDate;
    @SerializedName("runtime")
    int runtime;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(int voteAvg) {
        this.voteAvg = voteAvg;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public Movie(Parcel in){
        this.title = in.readString();
        this.id = in.readInt();
        this.poster_path =  in.readString();
        this.voteAvg = in.readDouble();
        this.overview=in.readString();
        this.releaseDate=in.readString();
        this.runtime=in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getTitle());
        dest.writeInt(getId());
        dest.writeString(getPoster_path());
        dest.writeDouble(getVoteAvg());
        dest.writeString(getOverview());
        dest.writeString(getReleaseDate());
        dest.writeInt(getRuntime());
    }
}
