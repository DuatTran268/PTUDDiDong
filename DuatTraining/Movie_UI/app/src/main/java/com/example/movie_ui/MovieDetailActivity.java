package com.example.movie_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

//        lấy dữ liệu
        String movieTitle = getIntent().getExtras().getString("Title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");

        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        MovieThumbnailImg.setImageResource(imageResourceId);
    }
}