package com.example.medb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.medb.model.Movie;
import com.example.medb.repository.NowPlayingRepository;
import com.example.medb.repository.TopRatedRepository;

import java.util.List;

public class TopRatedViewModel extends AndroidViewModel {

    private TopRatedRepository topRatedRepository;

    public TopRatedViewModel(@NonNull Application application) {
        super(application);
        topRatedRepository = new TopRatedRepository(application);
    }

    // Live Data
    public LiveData<List<Movie>> getAllMovies(){
        return topRatedRepository.getMutableLiveData();
    }
}
