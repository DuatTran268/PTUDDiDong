package com.project.appmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.CastAdapter;
import Adapter.PartAdapter;
import Model.CastModel;
import Model.PartModel;

public class DetailsActivity extends AppCompatActivity {

    private List<CastModel> castModels;
    private List<PartModel> partModels;
    private CastAdapter castAdapter;
    private PartAdapter partAdapter;
    private RecyclerView partRecyclerView, castRecyclerView;

    private ImageView thumb, cover;
    private TextView title, desc;
    private FloatingActionButton actionButton;

    private String title_movie;
    private String des_movie;
    private String thumb_movie;
    private String link_movie;
    private String cover_movie;
    private String cast_movie;
    private String trailer_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // ánh xạ từ intent
        thumb = findViewById(R.id.imgThumbDetails);
        cover = findViewById(R.id.imgCoverDetails);
        title = findViewById(R.id.txtTitleDetails);
        desc = findViewById(R.id.txt_descDetails);
        actionButton = findViewById(R.id.btnActionPlayDetails);
        castRecyclerView = findViewById(R.id.recyclerViewCast);
        partRecyclerView = findViewById(R.id.recyclerViewPast);


        // lấy dữ liệu từ intent
        title_movie = getIntent().getStringExtra("title");
        des_movie = getIntent().getStringExtra("desc");
        thumb_movie = getIntent().getStringExtra("thumb");
        link_movie = getIntent().getStringExtra("link");
        cover_movie = getIntent().getStringExtra("cover");
        cast_movie = getIntent().getStringExtra("cast");
        trailer_movie = getIntent().getStringExtra("t_link");

        // toolbar của màn hìn details activity
        Toolbar toolbar = findViewById(R.id.tool_barDetails);
        setSupportActionBar(toolbar);
        // lấy title của film hiển thị lên trên
        getSupportActionBar().setTitle(title_movie);
        // hiển thị nút quay trở về
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Glide.with(this).load(thumb_movie).into(thumb);
        Glide.with(this).load(cover_movie).into(cover);

        thumb.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        cover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

        title.setText(title_movie);
        desc.setText(des_movie);

        // click vao button xem video
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                myRef.child("link").child(trailer_movie).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // bug vl
                        String vidUrl = snapshot.getValue(String.class);
                        Intent intent = new Intent(DetailsActivity.this, PlayerActivity.class);
                        intent.putExtra("vid", vidUrl);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(DetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // hien thi cast
        loadCast();
        
        // hien thi tung phan phim
        loadPart();

    }

    private void loadPart() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference partRef = database.getReference();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        partRecyclerView.setLayoutManager(layoutManager);

        partModels = new ArrayList<>();
        partAdapter = new PartAdapter(partModels);
        partRecyclerView.setAdapter(partAdapter);

        partRef.child("link").child(link_movie).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot content:snapshot.getChildren()){
                    PartModel partModel = content.getValue(PartModel.class);
                    partModels.add(partModel);
                }
                partAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadCast() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference castRef = database.getReference();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        castRecyclerView.setLayoutManager(layoutManager);

        castModels = new ArrayList<>();
        castAdapter = new CastAdapter(castModels);
        castRecyclerView.setAdapter(castAdapter);

        castRef.child("cast").child(cast_movie).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot content:snapshot.getChildren()){
                    CastModel castModel = content.getValue(CastModel.class);
                    castModels.add(castModel);
                }
                castAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    // khi click button back thì thực hiện quay trở lại trang chinhs
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}