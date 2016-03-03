package com.james.chargescreen;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skyfishjy.library.RippleBackground;


public class Ripples extends Activity {

    boolean isDark, isFull;
    int cColor, bColor, navColor;
    TextView tv;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SharedPreferences settings = getSharedPreferences("com.james.chargescreen", 0);
        isFull = settings.getBoolean("isFull", false);
        if(isFull){
            // remove title
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_ripples);

        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        final RippleBackground rippleBackground = (RippleBackground)findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        tv = (TextView) findViewById(R.id.chargepercent);

        isDark = settings.getBoolean("isDark", false);
        bColor = settings.getInt("bg", getResources().getColor(R.color.light));
        cColor = settings.getInt("charge", getResources().getColor(R.color.teal));
        navColor = settings.getInt("a1", getResources().getColor(R.color.lightblu));

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(cColor, PorterDuff.Mode.SRC_IN);

        Intent batteryIntent = this.getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int rawlevel = batteryIntent.getIntExtra("level", -1);
        double scale = batteryIntent.getIntExtra("scale", -1);
        double level = -1;
        if (rawlevel >= 0 && scale > 0) {
            level = rawlevel / scale;
            String per = String.valueOf(level);
            per = per.replace("0.", "");
            if(per.matches("1.0")){
                per = "100";
            }
            tv.setText("Charging: " + per + "%");
            int progress = Integer.parseInt(per);
            progressBar.setProgress(progress);
        }

        if(isColorDark(bColor)){
            tv.setTextColor(getResources().getColor(R.color.white));
        }else{
            tv.setTextColor(getResources().getColor(R.color.black));
        }

        if(isDark){
            WindowManager.LayoutParams layout = getWindow().getAttributes();
            layout.screenBrightness = 0F;
            getWindow().setAttributes(layout);
        }

        if(android.os.Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(navColor);
            getWindow().setNavigationBarColor(navColor);
        }

        FrameLayout fl = (FrameLayout) findViewById(R.id.fl);
        fl.setBackgroundColor(bColor);
    }

    public boolean isColorDark(int color){
        double darkness = 1-(0.299* Color.red(color) + 0.587*Color.green(color) + 0.114* Color.blue(color))/255;
        return darkness >= 0.5;
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            tv.setText("Charging: " + String.valueOf(level) + "%");
            progressBar.setProgress(level);
        }
    };

}
