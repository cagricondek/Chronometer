package com.example.worktimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

private Toolbar settings_toolbar;
private Switch theme_switch;
private TextView theme_textView,color_texView,background_textView;
private ImageView color_imageView,background_ImageView;
private Button settings_save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        theme_switch=findViewById(R.id.theme_switch);
        theme_textView=findViewById(R.id.theme_textView);
        color_texView=findViewById(R.id.color_texView);
        background_textView=findViewById(R.id.background_textView);
        color_imageView=findViewById(R.id.color_imageView);
        background_ImageView=findViewById(R.id.background_ImageView);
        settings_save_button=findViewById(R.id.settings_save_button);
        settings_toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(settings_toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);







        theme_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    theme_textView.setText("Dark Mode");
                    return;
                }
                else{
                    theme_textView.setText("Light Mode");
                    return;
                }
            }
        });

        settings_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}