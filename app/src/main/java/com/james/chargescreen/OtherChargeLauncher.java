package com.james.chargescreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OtherChargeLauncher extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final SharedPreferences settings = context.getSharedPreferences("com.james.chargescreen", 0);
        if (settings.getBoolean("isEnabled", false)) {
            context.startActivity(new Intent(context, Ripples.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}
