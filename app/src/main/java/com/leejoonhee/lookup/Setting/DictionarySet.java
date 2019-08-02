package com.leejoonhee.lookup.Setting;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.leejoonhee.lookup.R;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class DictionarySet extends AppCompatActivity {

    List<String> datas = new ArrayList<>();
    ScrollChoice scrollChoice;
    TextView dictionaryname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionaryset);

        initviews();
        loaddata();

        scrollChoice.addItems(datas, 2);
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                dictionaryname.setText("Selected : " + name);
            }
        });
    }

    private void loaddata(){
        datas.add("Google Translator");
        datas.add("Naver Papago");
        datas.add("Oxford Dictionary");
        datas.add("Macmilan Dictionary");
        datas.add("Longman");
        datas.add("Collins Cobuild");
        datas.add("Urban Dictionary");
        datas.add("The Online Slang Dictionary");
    }

    private void initviews(){
        scrollChoice = (ScrollChoice)findViewById(R.id.scrollchoice);
        dictionaryname = (TextView)findViewById(R.id.engineselected);
    }
}
