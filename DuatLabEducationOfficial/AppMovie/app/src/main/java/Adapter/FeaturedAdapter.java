package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.appmovie.DetailsActivity;
import Model.FeaturedModel;
import com.project.appmovie.R;

import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.MyViewHolder> {
    private List<FeaturedModel> dataModels;

    public FeaturedAdapter(List<FeaturedModel> dataModels) {
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txt_MovieTitle.setText(dataModels.get(position).getFtitle());
        // load thumbnail
        Glide.with(holder.itemView.getContext()).load(dataModels.get(position).getFthumb()).into(holder.img_MainFilm);


        // open new intent activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // khi ckick vao movie item thi gui du lieu len man hinh details activity
                Intent sendDataToDetailActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataToDetailActivity.putExtra("title", dataModels.get(position).getFtitle());
                sendDataToDetailActivity.putExtra("link", dataModels.get(position).getFlink());
                sendDataToDetailActivity.putExtra("cover", dataModels.get(position).getFcover());
                sendDataToDetailActivity.putExtra("thumb", dataModels.get(position).getFthumb());
                sendDataToDetailActivity.putExtra("desc", dataModels.get(position).getFdes());
                sendDataToDetailActivity.putExtra("cast", dataModels.get(position).getFcast());
                sendDataToDetailActivity.putExtra("t_link", dataModels.get(position).getTlink());

//                trước khi gửi dữ liệu cần phải tạo transition animation
                ActivityOptionsCompat optionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)holder.itemView.getContext(), holder.img_MainFilm, "imageMain");

                // gửi phần tử tương tự như file xml (imageMain)
                holder.itemView.getContext().startActivity(sendDataToDetailActivity, optionsCompat.toBundle());

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_MainFilm;
        private TextView txt_MovieTitle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_MainFilm = itemView.findViewById(R.id.imgMainFilm);
            txt_MovieTitle = itemView.findViewById(R.id.movie_title);
        }
    }
}
