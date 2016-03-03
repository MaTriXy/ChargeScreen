package com.james.chargescreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import com.gc.materialdesign.widgets.ColorSelector;


public class Prefers extends ActionBarActivity {

    int icharge, a1, ibackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_prefs);


        findViewById(R.id.button21726).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Prefers.this, Ripples.class));
            }
        });

        final SharedPreferences settings = getSharedPreferences("com.james.chargescreen", 0);

        settings.edit().putBoolean("installed", true).apply();

        if(!settings.getBoolean("tutorial", false)){
            startActivity(new Intent(Prefers.this, FirstTime.class));
            settings.edit().putBoolean("tutorial", true).apply();
        }

        final Switch enable = (Switch) findViewById(R.id.switch2);
        boolean isEnabled = settings.getBoolean("isEnabled", false);
        enable.setChecked(isEnabled);

        final Switch dark = (Switch) findViewById(R.id.switch1);
        boolean isDark = settings.getBoolean("isDark", false);
        dark.setChecked(isDark);

        final Switch full = (Switch) findViewById(R.id.switchfull);
        boolean isFull = settings.getBoolean("isFull", false);
        full.setChecked(isFull);

        icharge = settings.getInt("charge", getResources().getColor(R.color.teal));
        a1 = settings.getInt("a1", getResources().getColor(R.color.lightblu));
        ibackground = settings.getInt("bg", getResources().getColor(R.color.light));

        findViewById(R.id.imageView).setBackgroundColor(ibackground);
        findViewById(R.id.imageView2).setBackgroundColor(icharge);
        findViewById(R.id.imageView3).setBackgroundColor(a1);

        Button a = (Button) findViewById(R.id.button3);
        Button charge = (Button) findViewById(R.id.button2);
        Button reset = (Button) findViewById(R.id.button21);
        Button bg = (Button) findViewById(R.id.button4);

        ImageView bb = (ImageView) findViewById(R.id.imageView);
        ImageView cc = (ImageView) findViewById(R.id.imageView2);
        ImageView aa = (ImageView) findViewById(R.id.imageView3);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorSelector action = new ColorSelector(Prefers.this, a1, new ColorSelector.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {
                        settings.edit().putInt("a1", i).apply();
                        a1 = i;
                        findViewById(R.id.imageView3).setBackgroundColor(i);
                    }
                });
                action.show();
            }
        });

        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorSelector action = new ColorSelector(Prefers.this, a1, new ColorSelector.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {
                        settings.edit().putInt("a1", i).apply();
                        a1 = i;
                        findViewById(R.id.imageView3).setBackgroundColor(i);
                    }
                });
                action.show();
            }
        });

        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorSelector charge = new ColorSelector(Prefers.this, icharge, new ColorSelector.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {
                        settings.edit().putInt("charge", i).apply();
                        icharge = i;
                        findViewById(R.id.imageView2).setBackgroundColor(i);
                    }
                });
                charge.show();
            }
        });

        cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorSelector charge = new ColorSelector(Prefers.this, icharge, new ColorSelector.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {
                        settings.edit().putInt("charge", i).apply();
                        icharge = i;
                        findViewById(R.id.imageView2).setBackgroundColor(i);
                    }
                });
                charge.show();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().putInt("charge", getResources().getColor(R.color.teal)).apply();
                settings.edit().putInt("a1", getResources().getColor(R.color.lightblu)).apply();
                settings.edit().putInt("bg", getResources().getColor(R.color.light)).apply();
                icharge = getResources().getColor(R.color.teal);
                a1 = getResources().getColor(R.color.lightblu);
                ibackground = getResources().getColor(R.color.light);
                findViewById(R.id.imageView).setBackgroundColor(ibackground);
                findViewById(R.id.imageView2).setBackgroundColor(icharge);
                findViewById(R.id.imageView3).setBackgroundColor(a1);
                Toast.makeText(Prefers.this, "Reset", Toast.LENGTH_SHORT).show();
            }
        });

        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorSelector background = new ColorSelector(Prefers.this, ibackground, new ColorSelector.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {
                        settings.edit().putInt("bg", i).apply();
                        ibackground = i;
                        findViewById(R.id.imageView).setBackgroundColor(i);
                    }
                });
                background.show();
            }
        });

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorSelector background = new ColorSelector(Prefers.this, ibackground, new ColorSelector.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {
                        settings.edit().putInt("bg", i).apply();
                        ibackground = i;
                        findViewById(R.id.imageView).setBackgroundColor(i);
                    }
                });
                background.show();
            }
        });

        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().putBoolean("isEnabled", onToggleClicked(enable)).apply();
            }
        });

        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().putBoolean("isDark", onToggleClicked(dark)).apply();
            }
        });
        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().putBoolean("isFull", onToggleClicked(full)).apply();
            }
        });
    }

    public boolean onToggleClicked(Switch view) {
        return view.isChecked();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prefs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(Prefers.this, About.class));
            return true;
        }

        if(id == R.id.action_tutorial) {
            startActivity(new Intent(Prefers.this, FirstTime.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
