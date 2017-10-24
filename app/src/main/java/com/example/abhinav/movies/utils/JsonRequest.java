package com.example.abhinav.movies.utils;

/**
 * Created by Abhinav on 10/16/2017.
 */

public final class JsonRequest {

    public final static String api_key ="e11324cc13908a3befcf286c572aaa0e";
    public static String jsonUrl="https://api.themoviedb.org/3/discover/movie?api_key="+ api_key +"&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";
    public static String getJsonForPage(int pageNo) {
        return jsonUrl+Integer.toString(pageNo);
    }
    public static String getJsonForTrailers(int video_id)
    {
        String jsonUrl="https://api.themoviedb.org/3/movie/";
        jsonUrl=jsonUrl+Integer.toString(video_id)+"/videos?api_key="+api_key+"&language=en-US";
        return  jsonUrl;
    }
}
