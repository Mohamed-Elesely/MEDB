package com.example.medb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView imageView=findViewById(R.id.imagelogo);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        imageView.startAnimation(myanim);
        final Intent i = new Intent(SplashScreen.this,PopularMovie.class);
        Thread timer = new Thread(){
            public  void run(){
                try {
                    sleep(2000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}