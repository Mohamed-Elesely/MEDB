package com.example.medb.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.medb.model.Movie;
import com.example.medb.repository.UpComingRepository;

import java.util.List;

public class UpComingViewModel extends AndroidViewModel {

    public UpComingRepository upComingRepository;

    public UpComingViewModel(Application application) {
        super(application);
        upComingRepository = new UpComingRepository(application);
    }

    // Live Data
    public LiveData<List<Movie>> getAllMovies(){
        return upComingRepository.getMutableLiveData();
    }
}
