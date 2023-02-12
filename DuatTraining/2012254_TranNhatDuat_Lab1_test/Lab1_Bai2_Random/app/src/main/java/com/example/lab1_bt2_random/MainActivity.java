package com.example.lab1_bt2_random;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtResult;
    Button btnRandom;


//    phương thức cầu nối để chuyển đối tượng từ Java sang XML
    private void innitControl(){
        txtResult = findViewById(R.id.txtResult);
        btnRandom = findViewById(R.id.btn_Random);
//     sự kiện nút nhấn
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int min = 0;
                final int max = 99;
                final int random = new Random().nextInt((max - min) + 1) + min;
                txtResult.setText(String.valueOf(random));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        gọi phương thức innitControl()
        innitControl();
    }
}