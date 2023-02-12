package com.example.chooselanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    Button btn_vn, btn_us, btn_fr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

//    function unit
    private void initUI(){
        this.btn_vn = (Button) findViewById(R.id.btn_vietnamese);
        this.btn_us = (Button) findViewById(R.id.btn_english);
        this.btn_fr = (Button) findViewById(R.id.btn_french);

        btn_vn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLanguage("vi", "VN");
            }
        });
        btn_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLanguage("en", "US");
            }
        });
        btn_fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLanguage("fr", "FR");
            }
        });
     }

     public void ChangeLanguage(String language, String lan){
         Locale locale = new Locale(language, lan);
         Configuration config = new Configuration();
         config.locale = locale;
         getBaseContext().getResources().updateConfiguration(
                 config, getBaseContext().getResources().getDisplayMetrics()
         );
         Intent intent = new Intent(MainActivity.this, MainActivity.class);
         startActivity(intent);
         finish();
     }
}