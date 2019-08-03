package com.leejoonhee.lookup.BackgroundService;

import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class BootItUp extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String action = intent.getAction();
        if(action.equals("android.intent.action.BOOT_COMPLETED")) {

            Intent clipboardintent = new Intent(context, ClipboardManager.class);
            Intent SMSreceiverintent = new Intent(context, ClipboardManager.class);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                context.startForegroundService(clipboardintent);
                context.startForegroundService(SMSreceiverintent);
            }
            else {
                context.startService(clipboardintent);
                context.startService(SMSreceiverintent);
            }

            Log.w("jumper", "bootedup");
        }
    }
}
