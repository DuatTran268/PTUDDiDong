package com.example.movie_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Slide> lstSlides;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView movieRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderPager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        movieRV = findViewById(R.id.rv_movie);



        lstSlides = new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.slide1, "Slide title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide2, "Slide title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide1, "Slide title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide2, "Slide title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide1, "Slide title \nmore text here"));

        SliderPagerAdapter adapters = new SliderPagerAdapter(this, lstSlides);

        sliderPager.setAdapter(adapters);

//        set up time out
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(), 3000, 3000);

        indicator.setupWithViewPager(sliderPager,true);


//        Chế độ xem lặp đi lặp lại
//        data

        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("Movie 2", R.drawable.movie2));
        lstMovies.add(new Movie("Movie 3", R.drawable.movie3));
        lstMovies.add(new Movie("Movie 4", R.drawable.movie4));
        lstMovies.add(new Movie("Movie 5", R.drawable.movie5));
        lstMovies.add(new Movie("Movie 2", R.drawable.movie2));
        lstMovies.add(new Movie("Movie 3", R.drawable.movie3));


        MovieAdapter movieAdapter = new MovieAdapter(this, lstMovies, this);
        movieRV.setAdapter(movieAdapter);
        movieRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));



    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
//         gửi thông tin chi tiết của phim lên màn hình này
        Intent intent = new Intent(this, MovieDetailActivity.class);

//        gửi thông tin của phim tới màn hình chi tiết phim detailActivity
        intent.putExtra("Title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        startActivity(intent);

//      tạo ra chuyển động amination giữa hai màn hình

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, movieImageView, "sharedName");
        startActivity(intent, options.toBundle());

//        hiển thị toast khi click lên
        Toast.makeText(this, "film: " + movie.getTitle(), Toast.LENGTH_LONG).show();
    }


    //    Class timer of slider banner
    class  SliderTimer extends TimerTask{
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem() < lstSlides.size() -1 ){
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                    }
                    else{
                        sliderPager.setCurrentItem(0);
                    }

                }
            });
        }
    }
}