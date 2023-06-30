package com.example.medb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.medb.model.Movie;
import com.example.medb.repository.PopularRepository;

import java.util.List;

public class PopularViewModel extends AndroidViewModel {

    private PopularRepository popularRepository;

    public PopularViewModel(@NonNull Application application) {
        super(application);
        popularRepository = new PopularRepository(application);
    }

    // Live Data
    public LiveData<List<Movie>> getAllMovies(){
        return popularRepository.getMutableLiveData();
    }
}
