package com.leejoonhee.lookup.BackgroundService;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.leejoonhee.lookup.Popup;

import java.util.Timer;
import java.util.TimerTask;

public class ClipBoardService extends Service implements ClipboardManager.OnPrimaryClipChangedListener {

    ClipboardManager mManager;
    SharedPreferences sharedPreferences;

    Timer timer = new Timer();
    String buffcode = "";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.i("ClipBoard", "ServiceStarted");
        super.onCreate();

        sharedPreferences = getSharedPreferences("ClipBoard",MODE_PRIVATE);

        mManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        mManager.addPrimaryClipChangedListener(this);

        TimerTask TT = new TimerTask() {
            @Override
            public void run() {

                if(buffcode.matches(SMSMMSReceiver.code)){}

                else{
                    save();
                }
            }
        };
        timer.schedule(TT, 0, 500); //Timer 실행
    }

    public void save(){

        buffcode = SMSMMSReceiver.code;

        ClipData clip = ClipData.newPlainText("text", buffcode);

        ClipboardManager save;

        save = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        save.setPrimaryClip(clip);

       // Toast.makeText(getApplicationContext(), "New Code Copied", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 리스너 해제
        mManager.removePrimaryClipChangedListener(this);
    }

    @Override
    public void onPrimaryClipChanged() {

        if (mManager != null && mManager.getPrimaryClip() != null) {
            ClipData data = mManager.getPrimaryClip();

            // 한번의 복사로 복수 데이터를 넣었을 수 있으므로, 모든 데이터를 가져온다.
            int dataCount = data.getItemCount();
            for (int i = 0 ; i < dataCount ; i++) {
                Log.i("CLipBoardService", "clip data changed : " + data.getItemAt(i).coerceToText(this));

                String content = String.valueOf(data.getItemAt(i).coerceToText(this));

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("clip", content); // key, value를 이용하여 저장하는 형태
                editor.commit();

                if (content.endsWith("었어") || content.endsWith("겠다") || content.endsWith("으니") ||
                        content.endsWith("었어") || content.endsWith("있지") || content.endsWith("었어")){ }

                else lookup(String.valueOf(data.getItemAt(i).coerceToText(this)));
            }
        }
        else {
            Log.w("Alart", "No Manager or No Clip data");
        }

    }

    public void lookup(String clipboard){
        Log.i("ClipBoardService", clipboard);

        Intent intent = new Intent(getApplicationContext(), Popup.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
