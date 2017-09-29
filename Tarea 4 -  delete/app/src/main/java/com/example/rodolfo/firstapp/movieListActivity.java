package com.example.rodolfo.firstapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rodolfo.firstapp.models.Movie;
import com.example.rodolfo.firstapp.models.MovieAdapter;

import com.example.rodolfo.firstapp.models.Movie;
import com.example.rodolfo.firstapp.models.MovieAdapter;
import com.example.rodolfo.firstapp.models.singleton;

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

        oListView = (ListView) findViewById(R.id.lv_movieList);
        oMovieAdapter = new MovieAdapter(this);
        oListView.setAdapter(oMovieAdapter);
        oListView.setClickable(true);

        oListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(movieListActivity.this);
                final int itempos = position;
                alerta.setMessage("YOU JUST DELETED "  );


                alerta.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        singleton.getInstance().getMovies().remove(itempos);
                        updateListM(singleton.getInstance().getMovies());
                    }});
                alerta.show();
                oMovieAdapter.notifyDataSetChanged();
            }
        });


        ArrayList<Movie> movieArray = singleton.getInstance().getMovies();
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

        for(Movie oMovie: lMovies)
        {
            oMovieAdapter.add(oMovie);
        }

        oMovieAdapter.notifyDataSetChanged();;
    }

    public void updateListM (ArrayList<Movie> lMovies) {
        oMovieAdapter.clear();

        for(Movie oMovie: lMovies)
        {
            oMovieAdapter.add(oMovie);
        }

        oMovieAdapter.notifyDataSetChanged();
    }
}