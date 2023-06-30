package com.example.medb.service;


import com.example.medb.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {


    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<Result> getUpComingMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<Result> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<Result> getNowPlayingMovies(@Query("api_key") String apiKey);
}
