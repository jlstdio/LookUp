package com.leejoonhee.lookup;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.leejoonhee.lookup.BackgroundService.ClipBoardService;
import com.leejoonhee.lookup.Setting.SettingActivity;

public class Popup extends Activity {

    private final int MY_PERMISSIONS_REQUEST_ReceiveSMS = 1001;
    private final int MY_PERMISSIONS_REQUEST_BOOT = 1000;

    String clipcontent = "";
    SharedPreferences sharedPreferences;

    String searchengine = "";
    String dictionary = "";
    String currency = "";
    String language = "";

    LinearLayout Call;
    LinearLayout Search;
    LinearLayout Dictionary;
    LinearLayout Calculation;

    private AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        WindowSet();
        super.onCreate(savedInstanceState);

        adviewinit();

        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= 21) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.WHITE);
        }

        PermissionCheck();

        startService(new Intent(Popup.this, ClipBoardService.class)); // ClipBoard Service boot up

        sharedPreferences = getSharedPreferences("ClipBoard", MODE_PRIVATE);
        clipcontent = sharedPreferences.getString("clip", "nothing");
        searchengine = sharedPreferences.getString("searchengine", "Google");
        dictionary = sharedPreferences.getString("dictionary", "Google Translator");
        currency = sharedPreferences.getString("currency", "KRW");
        language = sharedPreferences.getString("language", "English");

        Call = (LinearLayout)findViewById(R.id.call);
        Search = (LinearLayout)findViewById(R.id.search);
        Dictionary = (LinearLayout)findViewById(R.id.dictionary);
        Calculation = (LinearLayout)findViewById(R.id.calculation);

        Call.setVisibility(View.GONE);
        Search.setVisibility(View.GONE);
        Dictionary.setVisibility(View.GONE);
        Calculation.setVisibility(View.GONE);

        popupcondition();
    }

    public void call(View V) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + clipcontent));
        startActivity(intent);
    }

    public void search(View V){

        Intent intent;

        if (clipcontent.contains("http://") || clipcontent.contains("www.")) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(clipcontent));
            startActivity(intent);
        }

        else{

            if(language.matches("Korean")){

                if(searchengine.matches("Google")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.co.kr/search?complete=1&hl=ko&q=" + clipcontent));
                    startActivity(intent);
                }

                if(searchengine.matches("DuckDuckGo")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://duckduckgo.com/?q=" + clipcontent));
                    startActivity(intent);
                }

                if(searchengine.matches("Yahoo")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.yahoo.com/search?p=" + clipcontent));
                    startActivity(intent);
                }

                if(searchengine.matches("Naver")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=" + clipcontent));
                    startActivity(intent);
                }

                if(searchengine.matches("Daum")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&sug=&sugo=&q=" + clipcontent));
                    startActivity(intent);
                }

                if(searchengine.matches("Bing")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bing.com/search?q=" + clipcontent));
                    startActivity(intent);
                }
            }

            if(language.matches("English")){

                if(searchengine.matches("Google")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.co.kr/search?complete=1&hl=ko&q=" + clipcontent));
                    startActivity(intent);
                }

                if(searchengine.matches("DuckDuckGo")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://duckduckgo.com/?q=" + clipcontent));
                    startActivity(intent);
                }

                if(searchengine.matches("Yahoo")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.yahoo.com/search?p=" + clipcontent));
                    startActivity(intent);
                }

                if(searchengine.matches("Naver")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=" + clipcontent));
                    startActivity(intent);
                }

                if(searchengine.matches("Daum")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&sug=&sugo=&q=" + clipcontent));
                    startActivity(intent);
                }

                if(searchengine.matches("Bing")){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bing.com/search?q=" + clipcontent));
                    startActivity(intent);
                }
            }
        }
    }

    public void dictionary(View V){

        Intent intent;

        if(language.matches("Korean")){
            if(dictionary.matches("Google Translator")){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://translate.google.com/#view=home&op=translate&sl=auto&tl=ko&text=" + clipcontent));
                startActivity(intent);
            }

            if(dictionary.matches("Naver Papago")){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://papago.naver.com/?sk=auto&tk=ko&st=" + clipcontent));
                startActivity(intent);
            }

            if(dictionary.matches("Macmillan Dictionary")){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.macmillandictionary.com/spellcheck/british/?q=" + clipcontent));
                startActivity(intent);
            }

            if(dictionary.matches("Urban Dictionary")){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.urbandictionary.com/define.php?term=" + clipcontent));
                startActivity(intent);
            }

            if(dictionary.matches("The Online Slang Dictionary")){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://onlineslangdictionary.com/search/?q=" + clipcontent));
                startActivity(intent);
            }
        }

        if(language.matches("English")){

            if(dictionary.matches("Google Translator")){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://translate.google.com/#view=home&op=translate&sl=auto&tl=en&text=" + clipcontent));
                startActivity(intent);
            }

            if(dictionary.matches("Naver Papago")){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://papago.naver.com/?sk=auto&tk=en&st=" + clipcontent));
                startActivity(intent);
            }

            if(dictionary.matches("Macmilan Dictionary")){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.macmillandictionary.com/spellcheck/british/?q=" + clipcontent));
                startActivity(intent);
            }

            if(dictionary.matches("Urban Dictionary")){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.urbandictionary.com/define.php?term=" + clipcontent));
                startActivity(intent);
            }

            if(dictionary.matches("The Online Slang Dictionary")){
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://onlineslangdictionary.com/search/?q=" + clipcontent));
                startActivity(intent);
            }
        }
    }

    public void calculation(View V){

        String[] arr = clipcontent.split("(?<!^)");
        String[] numbers = clipcontent.split("|-|\\+|/");

        int t = 0;
        int result = 0;

        String[] mark = new String[10];
        for(int i = 0; i < mark.length; i++){
            mark[i] = "+";
        }

        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals("+") || arr[i].equals("-") || arr[i].equals("/") || arr[i].equals("*")){
                mark[t] = arr[i];
                t++;
            }
        }

        for(int i = 0; i < t; i++){
            int a = Integer.getInteger(numbers[i]);

            if(mark[i].equals("+")){
                result += a;
            }

            if(mark[i].equals("-")){
                result -= a;
            }

            if(mark[i].equals("*")){
                result = 0;
            }

            if(mark[i].equals("/")){
                result = 0;
            }
        }
    }

    public void setting(View V){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void PermissionCheck(){
        //Receive_SMS permission check and get a confirmation from user
        int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if (permssionCheck != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "권한 승인이 필요합니다", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {
                Toast.makeText(this, "문자정보 처리를 위해 RECEIVE_SMS권한이 필요합니다.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        MY_PERMISSIONS_REQUEST_ReceiveSMS);
                Toast.makeText(this, "문자정보 처리를 위해 RECEIVE_SMS권한이 필요합니다.", Toast.LENGTH_LONG).show();

            }
        }

        permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_BOOT_COMPLETED);
        if (permssionCheck != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "권한 승인이 필요합니다", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_BOOT_COMPLETED)) {
                Toast.makeText(this, "부팅시 자동실행을 위해 권한이 필요합니다", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        MY_PERMISSIONS_REQUEST_BOOT);
                Toast.makeText(this, "부팅시 자동실행을 위해 권한이 필요합니다", Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ReceiveSMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "승인이 허가되어 있습니다.", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "아직 승인받지 않았습니다.", Toast.LENGTH_LONG).show();
                }
                return;
            }

            case MY_PERMISSIONS_REQUEST_BOOT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "승인이 허가되어 있습니다.", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "아직 승인받지 않았습니다.", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void WindowSet(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 팝업이 올라오면 배경 블러처리
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.7f;
        getWindow().setAttributes(layoutParams);
        // 레이아웃 설정
        setContentView(R.layout.popup);
        // 사이즈조절
        // 1. 디스플레이 화면 사이즈 구하기
        Display dp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        // 2. 화면 비율 설정
        int width = (int) (dp.getWidth());
        int height = (int) (dp.getHeight());
        // 3. 현재 화면에 적용
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;
    }

    public void adviewinit(){
        MobileAds.initialize(this, getString(R.string.app_id));
        mAdView = findViewById(R.id.adView);

        //TODO Change this when on publish
        //TODO ad size to 468 * 60
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice("my_test_device_id").build();
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void popupcondition(){
        if (clipcontent.contains("http://") || clipcontent.contains("www.") || clipcontent.contains(".com")
                || clipcontent.contains(".co.") || clipcontent.contains(".org") || clipcontent.contains(".do")
                || clipcontent.contains(".io") || clipcontent.contains(".me") || clipcontent.contains(".net")
                || clipcontent.contains(".za") || clipcontent.contains(".ajax") || clipcontent.contains(".html")) {
            //url검색
            Search.setVisibility(View.VISIBLE);
        }

        if (clipcontent.startsWith("010")) {
            //전화 연락
            if(clipcontent.length() == 11){
                Call.setVisibility(View.VISIBLE);
                Search.setVisibility(View.VISIBLE);
            }

            if(8 < clipcontent.length() && clipcontent.length() < 14 && clipcontent.length() != 11){
                Toast.makeText(getApplicationContext(),"전화번호가 완전하지 못합니다",Toast.LENGTH_SHORT).show();
            }
        }

        if (clipcontent.startsWith("02")) {

            if(clipcontent.length() == 9){
                Call.setVisibility(View.VISIBLE);
                Search.setVisibility(View.VISIBLE);
            }

            if(6 < clipcontent.length() && clipcontent.length() < 12 && clipcontent.length() != 9){
                Toast.makeText(getApplicationContext(),"전화번호가 완전하지 못합니다",Toast.LENGTH_SHORT).show();
            }
        }

        if (clipcontent.startsWith("051") || clipcontent.startsWith("053") || clipcontent.startsWith("032") || clipcontent.startsWith("062")
                || clipcontent.startsWith("042") || clipcontent.startsWith("052") || clipcontent.startsWith("044") || clipcontent.startsWith("031")
                || clipcontent.startsWith("031") || clipcontent.startsWith("033") || clipcontent.startsWith("043") || clipcontent.startsWith("041")
                || clipcontent.startsWith("063") || clipcontent.startsWith("061") || clipcontent.startsWith("054") || clipcontent.startsWith("055")
                || clipcontent.startsWith("064")) {

            if(clipcontent.length() == 10){
                Call.setVisibility(View.VISIBLE);
                Search.setVisibility(View.VISIBLE);
            }

            if(7 < clipcontent.length() && clipcontent.length() < 13 && clipcontent.length() != 10){
                Toast.makeText(getApplicationContext(),"전화번호가 완전하지 못합니다",Toast.LENGTH_SHORT).show();
            }

        }

        if (clipcontent.contains("+") || clipcontent.contains("-") || clipcontent.contains("/") || clipcontent.contains("*") || clipcontent.contains("%")) {
            //사칙연산 그리고 퍼센트

            Search.setVisibility(View.VISIBLE);
            //Calculation.setVisibility(View.VISIBLE);
        }

        else{
            Search.setVisibility(View.VISIBLE);
            Dictionary.setVisibility(View.VISIBLE);
        }
    }

}
