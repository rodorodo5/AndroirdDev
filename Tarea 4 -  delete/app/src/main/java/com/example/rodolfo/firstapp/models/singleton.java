package com.example.rodolfo.firstapp.models;

import java.util.ArrayList;

/**
 * Created by rodo on 9/8/17.
 */

public class singleton {


        private static final singleton SELF = new singleton();

        private ArrayList<Movie> movies = new ArrayList<Movie>();

        private singleton() {
        }
    public  ArrayList<Movie> getMovies() {
        return movies;
    }

    public static singleton getInstance() {
            return SELF;
        }


}
