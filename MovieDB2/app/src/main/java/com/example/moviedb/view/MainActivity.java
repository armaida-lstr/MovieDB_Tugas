package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

private MovieViewModel viewModel;
private Button btn_hit_main;
private TextView txtshow;
private TextInputLayout textinput_inp;
private ImageView image_mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   viewModel= new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);

       btn_hit_main = findViewById(R.id.btn_hit_main);
        txtshow = findViewById(R.id.txtshow);
        textinput_inp= findViewById(R.id.textinput_inp);
        image_mv = findViewById(R.id.image_mv);

   btn_hit_main.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String  movieId = textinput_inp.getEditText().getText().toString().trim();
           if(movieId.isEmpty()){
               textinput_inp.setError("fill!");

           }else {
                  viewModel.getMovieById(movieId);
               //   viewModel.getMovieById("350");
                  viewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
                  textinput_inp.setError("");
           }
       }
   });
    }
    private Observer<Movies> showResultMovie = new Observer<Movies>(){
        @Override
        public  void onChanged(Movies movies){
            if (movies == null){
                txtshow.setText("movie id is not available");
            }else{
               String JudulMovieGetFromAPIthatfromViewModelandRepository = movies.getTitle();
                String PosterMovieGetFromAPIthatfromViewModelandRepository = movies.getPoster_path().toString();
            String full_path = "https://image.tmdb.org/t/p/w500/" + PosterMovieGetFromAPIthatfromViewModelandRepository;

            Glide.with(MainActivity.this).load(full_path).into(image_mv);
            txtshow.setText(JudulMovieGetFromAPIthatfromViewModelandRepository);
           }
        String title =movies.getTitle();
        txtshow.setText(title);
        }
    };
}