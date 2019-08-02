package com.leejoonhee.lookup.BackgroundService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSMMSReceiver extends BroadcastReceiver{

    int i;
    static String code = "";

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        String str = ""; // 출력할 문자열 저장
        if (bundle != null) { // 수신된 내용이 있으면
            // 실제 메세지는 Object타입의 배열에 PDU 형식으로 저장됨

            Object[] pdus = (Object[])bundle.get("pdus");

            //TODO NullPointerException warning : pdus
            SmsMessage[] msgs
                    = new SmsMessage[pdus.length];
            for (i = 0; i < msgs.length; i++) {
                // PDU 포맷으로 되어 있는 메시지를 복원합니다.
                msgs[i] = SmsMessage
                        .createFromPdu((byte[]) pdus[i]);
                str += msgs[i].getOriginatingAddress()
                        + "," +
                        msgs[i].getMessageBody();/*.toString();//use it if you need*/
            }

            String buff[] = str.split(",");

            Log.i("CLipBoardService", "sms received");

            if(buff[1].contains("본인확인") || buff[1].contains("인증 코드") || buff[1].contains("인증코드")
                    || buff[1].contains("code") || buff[1].contains("Code") || buff[1].contains("본인확인")
                    || buff[1].contains("인증번호") || buff[1].contains("verification") || buff[1].contains("인증 번호")
                    || buff[1].contains("본인 확인") || buff[1].contains("Verification")){

                String[] ContentArr = buff[1].split("(?<!^)");
                int count = 0;

                for(int i = 0; i < ContentArr.length; i++){
                    if(ContentArr[i].matches("[0-9]+")){
                        code = code + ContentArr[i];
                        count++;
                    }
                    else{ if(count > 3){ } else {code = ""; count = 0;} }
                }

                Log.i("CLipBoardService", "clip data changed : " + code);

            }
        }
    }
}