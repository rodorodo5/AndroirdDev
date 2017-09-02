package com.example.rodolfo.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rodolfo.firstapp.models.Movie;
import com.example.rodolfo.firstapp.models.MovieAdapter;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Created by rodo on 9/1/17.
 */

public class movieListActivity extends AppCompatActivity {

    MovieAdapter oMovieAdapter;
    ListView oListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        oListView=(ListView) findViewById(R.id.lv_movieList);
        oMovieAdapter=new MovieAdapter(this);
        oListView.setAdapter(oMovieAdapter);
        Intent intent = getIntent();
        //Movie oMovie = this.getIntent().getParcelableExtra("MiLLave");
        //oMovie.getMovieName();

        ArrayList<Movie> movieArray = this.getIntent().getParcelableArrayListExtra("movieList");



        fillMovieDatabase(movieArray);
    }

    @Override
    public void onBackPressed() {
        
        retorno();
    }

    public void retorno(){
        Intent intent = new Intent();
        intent.putExtra("resultado",1);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }



    private void fillMovieDatabase(ArrayList<Movie> lMovies)
    {
        oMovieAdapter.clear();

        for(Movie oMovie: lMovies)
        {
            oMovieAdapter.add(oMovie);
        }

        oMovieAdapter.notifyDataSetChanged();;
    }
}