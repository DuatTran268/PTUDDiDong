package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import Model.TrendingModel;
import com.project.appmovie.PlayerActivity;
import com.project.appmovie.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;



public class TrendingSlideAdapter extends SliderViewAdapter<TrendingSlideAdapter.MyViewHolder> {

    private Context context;

    public TrendingSlideAdapter(Context context) {
        this.context = context;
    }

    private List<TrendingModel> trendingModels = new ArrayList<>();

    public void renewItems(List<TrendingModel> trendingModels){
        this.trendingModels = trendingModels;
        notifyDataSetChanged();
    }

    public void deleteItems(int position){
        this.trendingModels.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        // inflate layout here for slide items
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {

        viewHolder.trailer_title.setText(trendingModels.get(position).getTtitle());

        Glide.with(viewHolder.itemView).load(trendingModels.get(position).getTurl()).into(viewHolder.slider_img);

        // mở ra activity mới khi click vào button play video để xem video
        viewHolder.play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trailer_video = new Intent(context, PlayerActivity.class);
                // get video
                trailer_video.putExtra("vid", trendingModels.get(position).getTvid());
                // lấy title
                trailer_video.putExtra("title", trendingModels.get(position).getTtitle());
                view.getContext().startActivity(trailer_video);

            }
        });
    }

    @Override
    public int getCount() {
        return trendingModels.size();
    }

    public class MyViewHolder extends SliderViewAdapter.ViewHolder{
        ImageView slider_img;
        TextView trailer_title;
        FloatingActionButton play_btn;

        public MyViewHolder(View itemView) {
            super(itemView);

            // ánh xạ
            slider_img = itemView.findViewById(R.id.img_thumbnail);
            trailer_title = itemView.findViewById(R.id.trailer_title);
            play_btn = itemView.findViewById(R.id.icon_play_video);

        }
    }
}
