package com.example.rodolfo.firstapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rodo on 9/1/17.
 */


public class Movie implements Parcelable {

    public Movie(String movieName, String runtime, String director, String genre, String releaseDate) {
        this.movieName = movieName;
        this.runtime = runtime;
        this.director = director;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public Movie(Parcel in){
        movieName = in.readString();
        runtime =  in.readString();
        director = in.readString();
        genre = in.readString();
        releaseDate = in.readString();

    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    private String movieName;

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    private String runtime;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    private String director;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    private String genre;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    private String releaseDate;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieName);
        dest.writeString(runtime);
        dest.writeString(director);
        dest.writeString(genre);
        dest.writeString(releaseDate);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Movie createFromParcel (Parcel in){
            return new Movie (in);
        }

        public Movie[] newArray(int size){
            return new Movie[size];
        }
    };
}
