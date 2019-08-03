package com.leejoonhee.lookup.Setting;

import android.content.SharedPreferences;
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

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionaryset);

        initviews();
        loaddata();

        sharedPreferences = getSharedPreferences("ClipBoard", MODE_PRIVATE);
        String name = sharedPreferences.getString("dictionary", "nothing");
        dictionaryname.setText("Selected : " + name);

        scrollChoice.addItems(datas, 2);
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                dictionaryname.setText("Selected : " + name);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("dictionary", name); // key, value를 이용하여 저장하는 형태
                editor.commit();
            }
        });
    }

    private void loaddata(){
        datas.add("Google Translator");
        datas.add("Naver Papago");
        datas.add("Macmillan Dictionary");
        datas.add("Urban Dictionary");
        datas.add("The Online Slang Dictionary");
    }

    private void initviews(){
        scrollChoice = (ScrollChoice)findViewById(R.id.scrollchoice);
        dictionaryname = (TextView)findViewById(R.id.engineselected);
    }
}
