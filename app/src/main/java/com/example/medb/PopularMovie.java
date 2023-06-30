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
import com.example.medb.databinding.ActivityMainBinding;
import com.example.medb.model.Movie;
import com.example.medb.viewmodel.PopularViewModel;

import java.util.ArrayList;
import java.util.List;

public class PopularMovie extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private PopularViewModel popularViewModel;
    private ActivityMainBinding activityMainBinding;
    TextView textView,textView2,textView3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        popularViewModel = new ViewModelProvider(this).get(PopularViewModel.class);

        getPopularMovies();

        textView = findViewById(R.id.UpcomingPo);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularMovie.this, UpComingMovie.class);
                startActivity(intent);
                finish();
            }
        });

        textView2 = findViewById(R.id.NowplayingPo);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PopularMovie.this, NowPlayingMovie.class);
                startActivity(intent);
                finish();
            }
        });

        textView3 = findViewById(R.id.TopratedPo);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PopularMovie.this, TopRatedMovie.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void getPopularMovies() {
        popularViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                ShowOnRecyclerView();
            }
        });


    }

    private void ShowOnRecyclerView() {

        recyclerView = activityMainBinding.rvMovies;
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