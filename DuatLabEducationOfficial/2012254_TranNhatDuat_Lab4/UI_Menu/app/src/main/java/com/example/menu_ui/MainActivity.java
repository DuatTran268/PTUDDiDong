package com.example.menu_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int[] ID_DRAWABLES = {
            R.drawable.ic_mess, R.drawable.ic_plane, R.drawable.ic_hospital,
            R.drawable.ic_holtel, R.drawable.ic_restaurant, R.drawable.ic_bar,
            R.drawable.ic_store, R.drawable.ic_work, R.drawable.ic_time,
            R.drawable.ic_education, R.drawable.ic_movie
    };

    private static final int[] ID_TEXTS = {
            R.string.txt_mess, R.string.txt_flight,
            R.string.txt_hospital, R.string.txt_hotel, R.string.txt_restaurant,
            R.string.txt_atTheBar, R.string.txt_store, R.string.txt_work,
            R.string.txt_time, R.string.txt_education, R.string.txt_movie
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        LinearLayout LnMain = findViewById(R.id.In_main);
        LnMain.removeAllViews();

        for (int i = 0; i < ID_DRAWABLES.length; i++){
            View v = LayoutInflater.from(this).inflate(R.layout.activity_item_topic, null);
            ImageView ivTopic = v.findViewById(R.id.iv_topic);
            TextView tvTopic = v.findViewById(R.id.tv_topic);
            ivTopic.setImageResource(ID_DRAWABLES[i]);
            tvTopic.setText(ID_TEXTS[i]);

//            quy dinh khong gian chiem
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,1.0f
            );
            v.setLayoutParams(params);
            LnMain.addView(v);
        }
    }
}