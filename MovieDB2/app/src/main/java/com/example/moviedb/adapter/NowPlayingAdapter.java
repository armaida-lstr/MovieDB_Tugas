package com.example.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.view.MovieDetailsActivity;


import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.CardViewHolder>{

    private Context context;
    private List<NowPlaying.Results> listNowPlaying;
    private List<NowPlaying.Results> getListNowPlaying(){
        return listNowPlaying;
    }
    public void setListNowPlaying(List<NowPlaying.Results> listNowPlaying){
        this.listNowPlaying = listNowPlaying;
    }
    public NowPlayingAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public NowPlayingAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);
      return  new NowPlayingAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingAdapter.CardViewHolder holder, int position) {
        final NowPlaying.Results results = getListNowPlaying().get(position);
        holder.title_card.setText(results.getTitle());
        holder.overview_card.setText(results.getOverview());
        holder.release_card.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.IMAGE_PATH+ results.getPoster_path())
                .into(holder.imageView);

        holder.cv_card_nowplaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie_id", ""+results.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListNowPlaying().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title_card,overview_card,release_card;
        CardView cv_card_nowplaying;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imageView);
            title_card = itemView.findViewById(R.id.title_card);
            overview_card = itemView.findViewById(R.id.overview_card);
            release_card = itemView.findViewById(R.id.release_card);
            cv_card_nowplaying = itemView.findViewById(R.id.cv_card_nowplaying);
        }
    }
}
