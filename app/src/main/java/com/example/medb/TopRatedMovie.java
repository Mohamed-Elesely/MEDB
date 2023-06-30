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
import com.example.medb.databinding.ActivityTopRatedMovieBinding;
import com.example.medb.model.Movie;
import com.example.medb.viewmodel.TopRatedViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopRatedMovie extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private TopRatedViewModel topRatedViewModel;
    private ActivityTopRatedMovieBinding topRatedMovieBinding;
    TextView textView,textView2,textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated_movie);

        topRatedMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_top_rated_movie);

        topRatedViewModel = new ViewModelProvider(this).get(TopRatedViewModel.class);
        getPopularMovies();

        textView = findViewById(R.id.UpcomingTR);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopRatedMovie.this, UpComingMovie.class);
                startActivity(intent);
                finish();
            }
        });

        textView2 = findViewById(R.id.PopularTR);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopRatedMovie.this, PopularMovie.class);
                startActivity(intent);
                finish();
            }
        });

        textView3 = findViewById(R.id.NowplayingTR);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopRatedMovie.this, NowPlayingMovie.class);
                startActivity(intent);
                finish();
            }
        });


    }


    private void getPopularMovies() {
        topRatedViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                ShowOnRecyclerView();
            }
        });

    }

    private void ShowOnRecyclerView() {

        recyclerView = topRatedMovieBinding.rvMoviesTR;
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