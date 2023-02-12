package com.project.appmovie;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import Adapter.FeaturedAdapter;
import Adapter.SeriesAdapter;
import Adapter.SliderAdapter;
import Adapter.TrendingSlideAdapter;
import Model.DataModel;
import Model.FeaturedModel;
import Model.SeriesModel;
import Model.TrendingModel;

public class MainActivity extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<DataModel> dataModels;
    private SliderAdapter sliderAdapter;

    // trending
    private List<TrendingModel> trendingModels;
    private TrendingSlideAdapter trendingSlideAdapter;


    private RecyclerView featureRecyclecler, webSeriesRecycleView;
    private FeaturedAdapter featuredAdapter;
    private SeriesAdapter seriesAdapter;
    private List<FeaturedModel> featuredModels; // models class for featured data
    private List<SeriesModel> seriesModels; // models class for series data


    TabLayout categoryTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryTab = findViewById(R.id.tabLayoutMain);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HD movies");

        FirebaseApp.initializeApp(this);
        SliderView sliderView = findViewById(R.id.sliderView);

        sliderAdapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(sliderAdapter);


//        trendingSlideAdapter = new TrendingSlideAdapter(this);
//        sliderView.setSliderAdapter(trendingSlideAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setScrollTimeInSec(6);
        renewItems(sliderView);

        loadFireBaseForSlider();






        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        loadFireBaseForSlider();
                        renewItems(sliderView);

                        return;

                    case 2:
                        loadFireBaseForSliderTreding();
                        renewItems(sliderView);

                        return;

                    case 3:
                        return;

                    default:
                        loadFireBaseForSlider();
                        renewItems(sliderView);

                        return;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//        // load dữ liệu từ fire base
//        loadFireBaseForSlider();
        // load featured for data
//        loadData();

        loadFeaturedData();

        // khi featured adapter đã được load xong series adapter sau đó
        LoadSeriesData();


    }


    // hiển thị trailer  lên activity
    private void loadFireBaseForSlider() {
        myRef.child("Trailer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSlider : snapshot.getChildren()) {
                    DataModel sliderItem = contentSlider.getValue(DataModel.class);
                    dataModels.add(sliderItem);
                }
                sliderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void loadFireBaseForSliderTreding() {
        SliderView sliderView = findViewById(R.id.sliderView);
        trendingSlideAdapter = new TrendingSlideAdapter(this);
        sliderView.setSliderAdapter(trendingSlideAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setScrollTimeInSec(6);
        myRef.child("trending").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot trendingContent : snapshot.getChildren()) {
                    TrendingModel trendingModel = trendingContent.getValue(TrendingModel.class);
                    trendingModels.add(trendingModel);
                }
                trendingSlideAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }


//    private void loadData() {
//        loadFeaturedData();
//        loadMovieData();
//
//    }

    private void loadMovieData() {
    }

    private void loadFeaturedData() {
        // load data từ firebase
        DatabaseReference FRef = database.getReference("featured");
        featureRecyclecler = findViewById(R.id.recyclerViewFeaturedMovie);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL); // mac dinh no la vertical

        // cần phải chuyển đổi layout đảo ngược layout hiển thị từ 0 1 2 3 sang 3 2 1 0
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);

        featureRecyclecler.setLayoutManager(layoutManager);

        //featured adapter
        featuredModels = new ArrayList<>();
//        List<DataModel> models = new ArrayList<>();
        featuredAdapter = new FeaturedAdapter(featuredModels);
        featureRecyclecler.setAdapter(featuredAdapter);

        FRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    FeaturedModel dataModel = contentSnapShot.getValue(FeaturedModel.class);
                    featuredModels.add(dataModel);
                }
                featuredAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // load series web data
    private void LoadSeriesData() {
        DatabaseReference SRef = database.getReference("series");

        webSeriesRecycleView = findViewById(R.id.recycleViewWebSeries);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
        webSeriesRecycleView.setLayoutManager(layoutManager);

        seriesModels = new ArrayList<>();
        seriesAdapter = new SeriesAdapter(seriesModels);

        webSeriesRecycleView.setAdapter(seriesAdapter);
        SRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    SeriesModel newSeriesModel = contentSnapShot.getValue(SeriesModel.class);
                    seriesModels.add(newSeriesModel);
                }
                seriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//              code error
            }
        });


    }


    public void renewItems(View view) {
        dataModels = new ArrayList<>();
        DataModel dataItems = new DataModel();
        dataModels.add(dataItems);

        sliderAdapter.renewItems(dataModels);
        sliderAdapter.deleteItems(0);
    }
}