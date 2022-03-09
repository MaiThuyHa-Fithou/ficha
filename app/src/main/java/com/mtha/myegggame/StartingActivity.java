package com.mtha.myegggame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class StartingActivity extends AppCompatActivity {
    private boolean soundOn;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        //set typeface to textview from assets
        Typeface typeface = Typeface.createFromAsset(getAssets(),
                "fonts/roostheavy.ttf");
        ((TextView) findViewById(R.id.titleView)).setTypeface(typeface);

        //loads animation to chicken
        Animation swayingAnim = AnimationUtils.loadAnimation(this, R.anim.sway_main);
        ImageView playButton = (ImageView) findViewById(R.id.btn_play);
        playButton.startAnimation(swayingAnim);

        //get sounds preferences
        pref = this.getSharedPreferences("com.mtha.eggs", Context.MODE_PRIVATE);
        soundOn = pref.getBoolean("soundfx", true);

        //play button click listener
        playButton.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(StartingActivity.this,
                        MainActivity.class);
                if(Build.VERSION.SDK_INT > 14){
                    Bundle translateBundle = ActivityOptions.makeCustomAnimation(
                            StartingActivity.this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
                    startActivity(intent, translateBundle);
                }else{
                    startActivity(intent);

                }

            }
        });

        //sounds toggle view
        final ImageButton btn_settings = (ImageButton) findViewById(R.id.btn_soundfx);
        if(soundOn){
            btn_settings.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
        }else{
            btn_settings.setImageResource(android.R.drawable.ic_lock_silent_mode);
        }
        //sounds toggle listener
        btn_settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //also edits application preference
                if(soundOn){
                    soundOn = false;
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("soundfx", false);
                    editor.commit();
                    btn_settings.setImageResource(android.R.drawable.ic_lock_silent_mode);

                }else{
                    soundOn = true;
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("soundfx", true);
                    editor.commit();
                    btn_settings.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
                }
            }
        });
    }

}