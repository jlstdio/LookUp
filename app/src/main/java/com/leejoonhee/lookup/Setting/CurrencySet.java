package com.leejoonhee.lookup.Setting;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.leejoonhee.lookup.R;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class CurrencySet extends AppCompatActivity {

    List<String> data = new ArrayList<>();
    ScrollChoice scrollChoice_from;
    ScrollChoice scrollChoice_to;
    TextView currencyset;

    String namefrom;
    String nameto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currencyset);

        initviews();
        loaddata();

        scrollChoice_from.addItems(data, 2);
        scrollChoice_from.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice_from, int position, String name) {
                namefrom = name;
                currencyset.setText(namefrom + " -> " + nameto);
            }
        });


        scrollChoice_to.addItems(data, 2);
        scrollChoice_to.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                nameto = name;
                currencyset.setText(namefrom + " -> " + nameto);
            }
        });
    }

    private void loaddata(){
        data.add("South.Korea");
        data.add("UnitedStates");
        data.add("China");
        data.add("Japan");
        data.add("EU");
        data.add("UnitedKingdom");
        data.add("India");
        data.add("HongKong");
        data.add("Canada");
    }

    private void initviews(){
        scrollChoice_from = (ScrollChoice)findViewById(R.id.scrollchoice_from);
        scrollChoice_to = (ScrollChoice)findViewById(R.id.scrollchoice_to);
        currencyset = (TextView)findViewById(R.id.engineselected);
    }
}
