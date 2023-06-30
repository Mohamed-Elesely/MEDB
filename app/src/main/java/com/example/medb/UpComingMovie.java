package com.example.medb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.medb.adapter.MovieAdapter;
import com.example.medb.databinding.ActivityMovieUpComingBinding;
import com.example.medb.model.Movie;
import com.example.medb.viewmodel.UpComingViewModel;

import java.util.ArrayList;
import java.util.List;

public class UpComingMovie extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter upComingAdapter;
    private UpComingViewModel upComingViewModel;
    private ActivityMovieUpComingBinding activityMainBinding;
    TextView textView,textView2,textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_up_coming);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_up_coming);
        upComingViewModel = new ViewModelProvider(this).get(UpComingViewModel.class);

        getPopularMovies();

        textView = findViewById(R.id.PopularUp);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpComingMovie.this, PopularMovie.class);
                startActivity(intent);
            }
        });

        textView2 = findViewById(R.id.TopratedUp);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpComingMovie.this, TopRatedMovie.class);
                startActivity(intent);
                finish();
            }
        });

        textView3 = findViewById(R.id.NowplayingUp);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpComingMovie.this, NowPlayingMovie.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void getPopularMovies() {
        upComingViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                ShowOnRecyclerView();
            }
        });


    }

    private void ShowOnRecyclerView() {

        recyclerView = activityMainBinding.rvMoviesUp;
        upComingAdapter = new MovieAdapter(this, movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(upComingAdapter);
        upComingAdapter.notifyDataSetChanged();

    }
}