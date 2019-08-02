package com.leejoonhee.lookup.Setting;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.leejoonhee.lookup.R;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class LanguageSet extends AppCompatActivity {

    List<String> datas = new ArrayList<>();
    ScrollChoice scrollChoice;
    TextView language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.languageset);

        initviews();
        loaddata();

        scrollChoice.addItems(datas, 2);
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                language.setText("Selected : " + name);
            }
        });
    }

    private void loaddata(){
        datas.add("Korean");
        datas.add("English");
        datas.add("Japanese");
    }

    private void initviews(){
        scrollChoice = (ScrollChoice)findViewById(R.id.scrollchoice);
        language = (TextView)findViewById(R.id.engineselected);
    }
}
