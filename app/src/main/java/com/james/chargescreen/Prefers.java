package com.james.chargescreen;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.gc.materialdesign.widgets.ColorSelector;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;


public class Prefers extends AppCompatActivity {

    SwitchCompat enabledSwitch, darkSwitch, fullScreenSwitch;

    int backgroundColor, progressBarColor, systemBarColor;
    View background, progressBar, systemBar;
    ImageView backgroundImage, progressBarImage, systemBarImage;

    Toolbar toolbar;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        enabledSwitch = (SwitchCompat) findViewById(R.id.enabledSwitch);
        darkSwitch = (SwitchCompat) findViewById(R.id.darkSwitch);
        fullScreenSwitch = (SwitchCompat) findViewById(R.id.fullScreenSwitch);
        background = findViewById(R.id.backgroundColor);
        progressBar = findViewById(R.id.progressBarColor);
        systemBar = findViewById(R.id.systemBarColor);
        backgroundImage = (ImageView) findViewById(R.id.backgroundColorImage);
        progressBarImage = (ImageView) findViewById(R.id.progressBarImage);
        systemBarImage = (ImageView) findViewById(R.id.systemBarImage);

        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_prefs);

        ((ImageView) findViewById(R.id.backgroundImage)).setImageDrawable(WallpaperManager.getInstance(this).getDrawable());

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        enabledSwitch.setChecked(prefs.getBoolean("isEnabled", false));
        darkSwitch.setChecked(prefs.getBoolean("isDark", false));
        fullScreenSwitch.setChecked(prefs.getBoolean("isFullScreen", false));

        enabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefs.edit().putBoolean("isEnabled", isChecked).apply();
            }
        });

        darkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefs.edit().putBoolean("isDark", isChecked).apply();
            }
        });

        fullScreenSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefs.edit().putBoolean("isFullScreen", isChecked).apply();
            }
        });

        backgroundColor = prefs.getInt("backgroundColor", ContextCompat.getColor(this, R.color.light));
        progressBarColor = prefs.getInt("progressBarColor", ContextCompat.getColor(this, R.color.teal));
        systemBarColor = prefs.getInt("systemBarColor", ContextCompat.getColor(this, R.color.lightblu));

        backgroundImage.setImageDrawable(new ColorDrawable(backgroundColor));
        progressBarImage.setImageDrawable(new ColorDrawable(progressBarColor));
        progressBarImage.setImageDrawable(new ColorDrawable(progressBarColor));

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ColorSelector(Prefers.this, backgroundColor, new ColorSelector.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {
                        prefs.edit().putInt("backgroundColor", i).apply();
                        backgroundImage.setImageDrawable(new ColorDrawable(i));
                        backgroundColor = i;
                    }
                }).show();
            }
        });

        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ColorSelector(Prefers.this, progressBarColor, new ColorSelector.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {
                        prefs.edit().putInt("progressBarColor", i).apply();
                        progressBarImage.setImageDrawable(new ColorDrawable(i));
                        progressBarColor = i;
                    }
                }).show();
            }
        });

        systemBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ColorSelector(Prefers.this, systemBarColor, new ColorSelector.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {
                        prefs.edit().putInt("systemBarColor", i).apply();
                        systemBarImage.setImageDrawable(new ColorDrawable(i));
                        systemBarColor = i;
                    }
                }).show();
            }
        });

        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColor = ContextCompat.getColor(Prefers.this, R.color.light);
                progressBarColor = ContextCompat.getColor(Prefers.this, R.color.teal);
                systemBarColor = ContextCompat.getColor(Prefers.this, R.color.lightblu);

                prefs.edit().putInt("backgroundColor", backgroundColor).apply();
                prefs.edit().putInt("progressBarColor", progressBarColor).apply();
                prefs.edit().putInt("systemBarColor", systemBarColor).apply();
                backgroundImage.setImageDrawable(new ColorDrawable(backgroundColor)) ;
                progressBarImage.setImageDrawable(new ColorDrawable(progressBarColor)) ;
                systemBarImage.setImageDrawable(new ColorDrawable(systemBarColor)) ;

                Toast.makeText(Prefers.this, "Reset", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.previewButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Prefers.this, Ripples.class));
            }
        });

        if(!prefs.getBoolean("tutorial", false)){
            startActivity(new Intent(Prefers.this, FirstTime.class));
            prefs.edit().putBoolean("tutorial", true).apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_prefs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_tutorial:
                startActivity(new Intent(Prefers.this, FirstTime.class));
                break;
            case R.id.action_libraries:
                new LibsBuilder().withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR).start(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
