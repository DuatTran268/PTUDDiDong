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
import Model.DataModel;
import com.project.appmovie.PlayerActivity;
import com.project.appmovie.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyViewHolder> {

    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    private List<DataModel> dataModels = new ArrayList<>();

    public void renewItems(List<DataModel> dataModels){
        this.dataModels = dataModels;
        notifyDataSetChanged();
    }

    public void deleteItems(int position){
        this.dataModels.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        // inflate layout here for slide items
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.MyViewHolder viewHolder, int position) {
        viewHolder.trailer_title.setText(dataModels.get(position).getTtitle());

        Glide.with(viewHolder.itemView).load(dataModels.get(position).getTurl()).into(viewHolder.slider_img);

        // mở ra activity mới khi click vào button play video để xem video
        viewHolder.play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trailer_video = new Intent(context, PlayerActivity.class);
                // get video
                trailer_video.putExtra("vid", dataModels.get(position).getTvid());
                // lấy title
                trailer_video.putExtra("title", dataModels.get(position).getTtitle());
                view.getContext().startActivity(trailer_video);

            }
        });

    }

    @Override
    public int getCount() {
        return dataModels.size();
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
