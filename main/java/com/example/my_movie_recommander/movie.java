package com.example.my_movie_recommander;

import java.io.Serializable;

public class movie implements Serializable {
    private  String title;
    private String kind ;
    //private float rating;

    private boolean active;

    public movie(String title, String kind)  {
        this.title= title;
        this.kind  = kind;
        //this.rating = rating;
    }

    public movie(String title, String kind, float rating, boolean active)  {
        this.title= title;
        this.kind = kind;
        // this.rating=rating;
        this.active= active;
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
    // public float getRating() {
    //  return rating;
    //}

    //   public void setRating(float rating) {
    //  this.rating = rating;
    // }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return this.title +" ("+ this.kind+")";
    }

}



