package com.leejoonhee.lookup.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.leejoonhee.lookup.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactivity);
    }

    public void SearchEngine(View V){
        Intent intent = new Intent(this, SearchEngine.class);
        startActivity(intent);
    }

    public void DictionSet(View V){
        Intent intent = new Intent(this, DictionarySet.class);
        startActivity(intent);
    }

    public void CurrencySet(View V){
        Intent intent = new Intent(this, CurrencySet.class);
        startActivity(intent);
    }

    public void LanguageSet(View V){
        Intent intent = new Intent(this, LanguageSet.class);
        startActivity(intent);
    }

    public void Help(View V){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    public void KnockKnock(View V){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        // email setting 배열로 해놔서 복수 발송 가능
        String[] address = {"neo81389@gmail.com"};
        email.putExtra(Intent.EXTRA_EMAIL, address);
        email.putExtra(Intent.EXTRA_SUBJECT,"LookUp 개선사항 문의");
        email.putExtra(Intent.EXTRA_TEXT,"이매일 제목에 LoockUp이란 단어는 지우지 말아주세요 스팸으로 분류될 수 있습니다\n개발자에게 보내고 싶은 개선사항이나 조언을 적어주세요!\n");
        startActivity(email);

    }

}
