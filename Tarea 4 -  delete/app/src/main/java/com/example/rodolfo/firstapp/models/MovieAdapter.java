package com.example.rodolfo.firstapp.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.rodolfo.firstapp.R;
/**
 * Created by rodo on 9/1/17.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    public MovieAdapter(Context context)
    {
        super(context, R.layout.linearlay, R.id.txtMovieName);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);

        TextView txtMovieName = (TextView) oView.findViewById(R.id.txtMovieName);
        TextView txtMovieDirector = (TextView) oView.findViewById(R.id.txtMovieDirector);
        TextView txtMovieGenre = (TextView) oView.findViewById(R.id.txtMovieGenre);
        TextView txtMovieRuntime = (TextView) oView.findViewById(R.id.txtMovieRuntime);
        TextView txtMovieReleaseDate = (TextView) oView.findViewById(R.id.txtMovieReleaseDate);

        Movie oMovie = this.getItem(position);
        txtMovieDirector.setText(oMovie.getDirector());
        txtMovieName.setText(oMovie.getMovieName());
        txtMovieGenre.setText(oMovie.getGenre());
        txtMovieRuntime.setText(oMovie.getRuntime());
        txtMovieReleaseDate.setText(oMovie.getReleaseDate());

        return oView;
    }
}