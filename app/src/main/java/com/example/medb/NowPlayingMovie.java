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
import com.example.medb.databinding.ActivityNowPlayingMovieBinding;
import com.example.medb.model.Movie;
import com.example.medb.viewmodel.NowPlayingViewModel;
import com.example.medb.viewmodel.PopularViewModel;

import java.util.ArrayList;
import java.util.List;

public class NowPlayingMovie extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private NowPlayingViewModel nowPlayingViewModel;
    private ActivityNowPlayingMovieBinding nowPlayingMovieBinding;
    TextView textView,textView2,textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing_movie);

        nowPlayingMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_now_playing_movie);

        nowPlayingViewModel = new ViewModelProvider(this).get(NowPlayingViewModel.class);
        getPopularMovies();

        textView = findViewById(R.id.UpcomingNP);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NowPlayingMovie.this, UpComingMovie.class);
                startActivity(intent);
                finish();
            }
        });

        textView2 = findViewById(R.id.PopularNP);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NowPlayingMovie.this, PopularMovie.class);
                startActivity(intent);
                finish();
            }
        });

        textView3 = findViewById(R.id.TopratedNP);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NowPlayingMovie.this, TopRatedMovie.class);
                startActivity(intent);
                finish();
            }
        });




    }


    private void getPopularMovies() {
        nowPlayingViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                ShowOnRecyclerView();
            }
        });

    }

    private void ShowOnRecyclerView() {

        recyclerView = nowPlayingMovieBinding.rvMoviesUpNP;
        movieAdapter = new MovieAdapter(this, movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }
}