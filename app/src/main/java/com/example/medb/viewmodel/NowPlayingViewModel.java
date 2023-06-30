package com.example.medb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.medb.model.Movie;
import com.example.medb.repository.NowPlayingRepository;
import com.example.medb.repository.PopularRepository;

import java.util.List;

public class NowPlayingViewModel extends AndroidViewModel {

    private NowPlayingRepository nowPlayingRepository;

    public NowPlayingViewModel(@NonNull Application application) {
        super(application);
        nowPlayingRepository = new NowPlayingRepository(application);
    }

    // Live Data
    public LiveData<List<Movie>> getAllMovies(){
        return nowPlayingRepository.getMutableLiveData();
    }
}

