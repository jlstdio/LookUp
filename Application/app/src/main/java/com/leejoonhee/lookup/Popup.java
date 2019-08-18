package com.leejoonhee.lookup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.leejoonhee.lookup.BackgroundService.ClipBoardService;
import com.leejoonhee.lookup.Setting.Help;
import com.leejoonhee.lookup.Setting.SettingActivity;

public class Popup extends Activity {

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
    LinearLayout Setting;

    Animation slidein;
    Animation slideout;

    int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        WindowSet();
        super.onCreate(savedInstanceState);

        adviewinit();

        PermissionCheck();

        startService(new Intent(Popup.this, ClipBoardService.class)); // ClipBoard Service boot up

        sharedPreferences = getSharedPreferences("ClipBoard", MODE_PRIVATE);
        clipcontent = sharedPreferences.getString("clip", "nothing");
        searchengine = sharedPreferences.getString("searchengine", "Google");
        dictionary = sharedPreferences.getString("dictionary", "Google Translator");
        currency = sharedPreferences.getString("currency", "KRW");
        language = sharedPreferences.getString("language", "English");

        count = sharedPreferences.getInt("first", 0);

        Call = (LinearLayout)findViewById(R.id.call);
        Search = (LinearLayout)findViewById(R.id.search);
        Dictionary = (LinearLayout)findViewById(R.id.dictionary);
        Calculation = (LinearLayout)findViewById(R.id.calculation);
        Setting = (LinearLayout)findViewById(R.id.setting);

        Call.setVisibility(View.GONE);
        Search.setVisibility(View.GONE);
        Dictionary.setVisibility(View.GONE);
        Calculation.setVisibility(View.GONE);

        slidein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidein);   // 에니메이션 설정 파일
        Setting.startAnimation(slidein);

        slideout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideout);

        PermissionCheck();

        popupcondition();

        if(count == 0){
            Intent intent = new Intent(this, Help.class);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            count++;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("first", 1);
            editor.commit();
        }
        else{

        }
    }

    public void call(View V) {

        Setting.startAnimation(slideout);
        Call.startAnimation(slideout);
        Dictionary.startAnimation(slideout);
        Search.startAnimation(slideout);

        Call.setVisibility(View.GONE);
        Search.setVisibility(View.GONE);
        Dictionary.setVisibility(View.GONE);
        Calculation.setVisibility(View.GONE);
        Setting.setVisibility(View.GONE);

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + clipcontent));
        startActivity(intent);
    }

    public void search(View V){

        Setting.startAnimation(slideout);
        Call.startAnimation(slideout);
        Dictionary.startAnimation(slideout);
        Search.startAnimation(slideout);

        Call.setVisibility(View.GONE);
        Search.setVisibility(View.GONE);
        Dictionary.setVisibility(View.GONE);
        Calculation.setVisibility(View.GONE);
        Setting.setVisibility(View.GONE);

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

        Setting.startAnimation(slideout);
        Call.startAnimation(slideout);
        Dictionary.startAnimation(slideout);
        Search.startAnimation(slideout);

        Call.setVisibility(View.GONE);
        Search.setVisibility(View.GONE);
        Dictionary.setVisibility(View.GONE);
        Calculation.setVisibility(View.GONE);
        Setting.setVisibility(View.GONE);

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

        Setting.startAnimation(slideout);
        Call.startAnimation(slideout);
        Dictionary.startAnimation(slideout);
        Search.startAnimation(slideout);

        Call.setVisibility(View.GONE);
        Search.setVisibility(View.GONE);
        Dictionary.setVisibility(View.GONE);
        Calculation.setVisibility(View.GONE);
        Setting.setVisibility(View.GONE);

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

        Setting.startAnimation(slideout);
        Call.startAnimation(slideout);
        Dictionary.startAnimation(slideout);
        Search.startAnimation(slideout);

        Call.setVisibility(View.GONE);
        Search.setVisibility(View.GONE);
        Dictionary.setVisibility(View.GONE);
        Calculation.setVisibility(View.GONE);
        Setting.setVisibility(View.GONE);

        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void PermissionCheck(){ }

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
        MobileAds.initialize(this, getString(R.string.ad_id));
        AdView mAdView = findViewById(R.id.adView);
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "G"); // 앱이 3세 이상 사용가능이라면 광고레벨을 설정해줘야 한다
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        mAdView.loadAd(adRequest);
    }

    public void popupcondition(){
        if (clipcontent.contains("http://") || clipcontent.contains("www.") || clipcontent.contains(".com")
                || clipcontent.contains(".co.") || clipcontent.contains(".org") || clipcontent.contains(".do")
                || clipcontent.contains(".io") || clipcontent.contains(".me") || clipcontent.contains(".net")
                || clipcontent.contains(".za") || clipcontent.contains(".ajax") || clipcontent.contains(".html")) {
            //url검색
            Search.setVisibility(View.VISIBLE);
            Search.startAnimation(slidein);
        }

        if (clipcontent.startsWith("010")) {
            //전화 연락
            if(clipcontent.length() == 11){
                Call.setVisibility(View.VISIBLE);
                Search.setVisibility(View.VISIBLE);

                Search.startAnimation(slidein);
                Call.startAnimation(slidein);
            }

            if(8 < clipcontent.length() && clipcontent.length() < 14 && clipcontent.length() != 11){
                Toast.makeText(getApplicationContext(),"전화번호가 완전하지 못합니다",Toast.LENGTH_SHORT).show();
            }
        }

        if (clipcontent.startsWith("02")) {

            if(clipcontent.length() == 9){
                Call.setVisibility(View.VISIBLE);
                Search.setVisibility(View.VISIBLE);

                Search.startAnimation(slidein);
                Call.startAnimation(slidein);
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

                Search.startAnimation(slidein);
                Call.startAnimation(slidein);
            }

            if(7 < clipcontent.length() && clipcontent.length() < 13 && clipcontent.length() != 10){
                Toast.makeText(getApplicationContext(),"전화번호가 완전하지 못합니다",Toast.LENGTH_SHORT).show();
            }

        }

        if (clipcontent.contains("+") || clipcontent.contains("-") || clipcontent.contains("/") || clipcontent.contains("*") || clipcontent.contains("%")) {
            //사칙연산 그리고 퍼센트

            Search.setVisibility(View.VISIBLE);

            Search.startAnimation(slidein);
            //Calculation.setVisibility(View.VISIBLE);
        }

        else{
            Search.setVisibility(View.VISIBLE);
            Dictionary.setVisibility(View.VISIBLE);

            Search.startAnimation(slidein);
            Dictionary.startAnimation(slidein);
        }
    }

}
