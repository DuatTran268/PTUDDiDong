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
import com.project.appmovie.R;
import Model.SeriesModel;

import java.util.List;

// model class adapter class
public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyViewHolder>{

    private List<SeriesModel> modelList;

    public SeriesAdapter(List<SeriesModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(modelList.get(position).getStitle());
        Glide.with(holder.imageView.getContext()).load(modelList.get(position).getSthumb()).into(holder.imageView);


        // code tuong tu web series nhu code featured
        // open new intent activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // khi ckick vao movie iem thi se mo ra man hinh details activity
                Intent sendDataToDetailActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataToDetailActivity.putExtra("title", modelList.get(position).getStitle());
                sendDataToDetailActivity.putExtra("link", modelList.get(position).getSlink());
                sendDataToDetailActivity.putExtra("cover", modelList.get(position).getScover());
                sendDataToDetailActivity.putExtra("thumb", modelList.get(position).getSthumb());
                sendDataToDetailActivity.putExtra("desc", modelList.get(position).getSdesc());
                sendDataToDetailActivity.putExtra("cast", modelList.get(position).getScast());
                sendDataToDetailActivity.putExtra("t_link", modelList.get(position).getTlink());

//                trước khi gửi dữ liệu cần phải tạo transition animation
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)holder.itemView.getContext(), holder.imageView, "imageMain");

                // gửi phần tử tương tự như file xml (imageMain)
                holder.itemView.getContext().startActivity(sendDataToDetailActivity, optionsCompat.toBundle());

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            imageView = itemView.findViewById(R.id.imgMainFilm);  //note error

        }
    }
}
