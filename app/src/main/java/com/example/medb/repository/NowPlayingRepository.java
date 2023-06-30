package com.example.medb.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.medb.R;
import com.example.medb.model.Movie;
import com.example.medb.model.Result;
import com.example.medb.service.MovieDataService;
import com.example.medb.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingRepository {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public NowPlayingRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<Movie>> getMutableLiveData(){
        MovieDataService movieDataService = RetrofitInstance.getService();

        Call<Result> call = movieDataService.getNowPlayingMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                if (result != null && result.getResults() != null){
                    movies = (ArrayList<Movie>) result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
