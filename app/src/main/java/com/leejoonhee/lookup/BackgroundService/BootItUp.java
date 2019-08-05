package com.leejoonhee.lookup.BackgroundService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.leejoonhee.lookup.Popup;

public class BootItUp extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String action = intent.getAction();
        if(action.equals("android.intent.action.BOOT_COMPLETED")) {

            intent = new Intent(context, Popup.class);
            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            Log.w("jumper", "bootedup");
        }
    }
}
