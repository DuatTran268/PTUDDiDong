package com.example.truyencuoi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.classes.ItemClickListener;
import com.example.truyencuoi.R;
import com.example.truyencuoi.activities.SecondActivity;
import com.example.truyencuoi.classes.StoryEntity;
import com.example.truyencuoi.activities.ThirdActivity;

public class ListStoryAdapter extends RecyclerView.Adapter<ListStoryAdapter.ViewHolder> {
    private StoryEntity[] localDataset;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView thumbnail;
        private ItemClickListener itemClickListener;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.txtStoryTitle);
            thumbnail = view.findViewById(R.id.imgStoryThumbnail);
            view.setOnClickListener(this);
        }

        public TextView getTitle() {
            return title;
        }

        public ImageView getThumbnail() {
            return thumbnail;
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }

    public ListStoryAdapter(StoryEntity[] dataSet, Context context) {
        localDataset = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        StoryEntity e = localDataset[position];
        viewHolder.getTitle().setText(e.getTitle());
        viewHolder.getThumbnail().setImageResource(R.drawable.ic_face);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent thirdScreen = new Intent(context, ThirdActivity.class);
                thirdScreen.putExtra(SecondActivity.STORY_TITLE, e.getTitle());
                thirdScreen.putExtra(SecondActivity.STORY_CONTENT, e.getContent());
                context.startActivity(thirdScreen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataset.length;
    }
}
