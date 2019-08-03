package com.leejoonhee.lookup.Setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.leejoonhee.lookup.R;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine extends AppCompatActivity {

    List<String> datas = new ArrayList<>();
    ScrollChoice scrollChoice;
    TextView enginename;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchengine);

        initviews();
        loaddata();

        sharedPreferences = getSharedPreferences("ClipBoard", MODE_PRIVATE);
        String name = sharedPreferences.getString("searchengine", "nothing");
        enginename.setText("Engine : " + name);

        scrollChoice.addItems(datas, 2);
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                enginename.setText("Eegine : " + name);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("searchengine", name); // key, value를 이용하여 저장하는 형태
                editor.commit();
            }
        });
    }

    private void loaddata(){
        datas.add("Google");
        datas.add("DuckDuckGo");
        datas.add("Yahoo");
        datas.add("Naver");
        datas.add("Daum");
        datas.add("Bing");
    }

    private void initviews(){
        scrollChoice = (ScrollChoice)findViewById(R.id.scrollchoice);
        enginename = (TextView)findViewById(R.id.engineselected);
    }
}
