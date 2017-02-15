package com.example.erickmwazonga.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

       // ImageView imageView= (ImageView) findViewById(R.id.imageView);

        // Animation anim= AnimationUtils.loadAnimation(this,R.anim.slide_up);
        //imageView.setAnimation(anim);

//        Animation anim1= AnimationUtils.loadAnimation(this,R.anim.zoom_out);
//        imageView.setAnimation(anim1);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },4000);
    }
}
