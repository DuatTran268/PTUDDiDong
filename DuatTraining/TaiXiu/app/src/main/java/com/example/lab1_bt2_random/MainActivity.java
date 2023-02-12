package com.example.lab1_bt2_random;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtResultNumber;
    Button btnRandom;
    TextView txtTaiXiu;




//    phương thức cầu nối để chuyển đối tượng từ Java sang XML
    private void innitControl(){
        txtResultNumber = findViewById(R.id.txtResult);
        btnRandom = findViewById(R.id.btn_Random);
        txtTaiXiu = findViewById(R.id.txtTaiXiu);


//     sự kiện nút nhấn
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int min = 4;
                final int max = 17;
                final int random = new Random().nextInt((max - min) + 1) + min;
//                string
                String reverse, stringUserEntry = "";
                boolean displayTaiXiuBool = false;
                stringUserEntry= txtTaiXiu.getText().toString();
                reverse = new StringBuilder(stringUserEntry).reverse().toString();

                if ((random >= 11) && (random <= 17)){
                    txtTaiXiu.setText("Tài");

                }
                else if ((random >= 4 ) && (random <= 10)){
                    txtTaiXiu.setText("Xỉu");

                }
                txtResultNumber.setText(String.valueOf(random));
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