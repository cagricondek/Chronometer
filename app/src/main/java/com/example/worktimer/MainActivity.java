package com.example.worktimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ImageView circle_clock;
    private Handler customHandler=new Handler();
    private Button reset_time_button;
    private TextView timerValue;
    private ToggleButton toggleButton;
    private boolean isPlaying=false;
    private Toolbar toolbar;

    private long startTime=0L,timeInMilliseconds=0L,timeSwapBuff=0L,updateTime=0L;






    Runnable updateTimerThread=new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds=SystemClock.uptimeMillis()-startTime;
            updateTime=timeSwapBuff+timeInMilliseconds;
            int secs=(int)(updateTime/1000);
            int mins=secs/60;
            secs%=60;
            int milliseconds=(int)(updateTime%1000);
            timerValue.setText(String.format(""+String.format("%02d", mins)+":"+String.format("%02d", secs)+":"+String.format("%3d",milliseconds)));
            customHandler.postDelayed(this,20);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        circle_clock=findViewById(R.id.circle_clock);
        timerValue=findViewById(R.id.timerValue);
        reset_time_button=findViewById(R.id.reset_time_button);
        toggleButton=findViewById(R.id.start_time_button);
        toggleButton.setText(null);
        toggleButton.setTextOn(null);
        toggleButton.setTextOff(null);



        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    startTime=SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread,0);
                    isPlaying=true;
                }
                else {

                    timeSwapBuff+=timeInMilliseconds;
                    customHandler.removeCallbacks(updateTimerThread);
                    isPlaying=false;

                }
            }
        });


        reset_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff=0;
                timeInMilliseconds=0;
                updateTime=0;
                customHandler.removeCallbacks(updateTimerThread);
                isPlaying=false;
                timerValue.setText(String.format("00:"+"00:"+"000"));
                toggleButton.setChecked(isPlaying=false);
            }
        });

    }



    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.settings,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.settings_option)
        {
            SendUserToSettingsActivity();
        }
        return true;
    }

    private void SendUserToSettingsActivity() {
        Intent settingsIntent=new Intent(MainActivity.this,SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
