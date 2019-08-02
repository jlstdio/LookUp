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
        Intent intent = new Intent(this, KnockKnock.class);
        startActivity(intent);
    }

}
