package com.example.movie_recommender;

import java.lang.reflect.TypeVariable;

public class Movie {
    private String title;
    private String kind;



    public Movie(String title, String kind  ) {
        this.title = title;
        this.kind = kind;

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }


}
