package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;
import com.example.moviedb.model.NowPlaying;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView textViewid,textView_tmpl_jdl,textView_tmpl_subjdl,textView_tmpl_rls,textViewgenre;
    private ImageView imageViewtampil;
    private ImageButton imageButton_back;
    private String movieId="";
    private MovieViewModel viewmodeltampil;
    private String Tgenre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

       Intent intentt = getIntent();
        viewmodeltampil = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
       movieId = intentt.getStringExtra("movie_id");
        viewmodeltampil.getMovieById(movieId);

        viewmodeltampil.getResultGetMovieById().observe(MovieDetailsActivity.this, showResultMovie);

        //Toast.makeText(getApplicationContext(), String.valueOf(movieId), Toast.LENGTH_SHORT).show();
        textViewid = findViewById(R.id.textViewid);
        textView_tmpl_jdl = findViewById(R.id.textView_tmpl_jdl);
        textView_tmpl_subjdl = findViewById(R.id.textView_tmpl_subjdl);
        textView_tmpl_rls = findViewById(R.id.textView_tmpl_rls);
        imageViewtampil = findViewById(R.id.imageViewtampil);
        imageButton_back = findViewById(R.id.imageButton_back);
        textViewgenre = findViewById(R.id.textViewgenre);
       ;





        imageButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(), NowPlayingActivity.class);
                  startActivity(in);
            }
        });
    }

        private Observer<Movies>showResultMovie = new Observer<Movies>() {
            @Override
            public void onChanged(Movies movies) {

                String Tjudul = movies.getTitle();
                String Tovw = movies.getOverview();
                String Trelease = movies.getRelease_date();
                String Tfoto = movies.getPoster_path().toString();
                //String Tnegarap = movies.getProduction_countries().toString();

                Tgenre = "";
                for (int i = 0; i < movies.getGenres().size(); i++) {
                    Tgenre += movies.getGenres().get(i).getName();
                    if (i != movies.getGenres().size() - 1) {
                        Tgenre += ", ";
                    }
                }

               
                    textViewgenre.setText(Tgenre);
                    textViewid.setText(movieId);
                    textView_tmpl_jdl.setText(Tjudul);
                    textView_tmpl_subjdl.setText(Tovw);
                    textView_tmpl_rls.setText(Trelease);
                    Glide.with(MovieDetailsActivity.this).load(Const.IMAGE_PATH + Tfoto).into(imageViewtampil);
                }

    };



    @Override
    public void onBackPressed() {
        finish();
    }
}

