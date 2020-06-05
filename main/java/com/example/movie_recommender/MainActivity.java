package com.example.movie_recommender;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.theList);
        ArrayList<String> titles = new ArrayList<>();
       // EditText rating = (EditText)findViewById(R.id.editid) ;
        Button rate = (Button) findViewById(R.id.button);

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveRating();
            }
        });


        //Create the movie objects
        Movie mv1 = new Movie("Dracula", "Horror");
        Movie mv2 = new Movie("Mr.Wrong", "Comedy");
        Movie mv3 = new Movie("Jupiter's Wife", "Documentary");
        Movie mv4 = new Movie("Amazing Panda Adventure", "Adventure|Children");
        Movie mv5 = new Movie("Mad love", "Romance");
        Movie mv6 = new Movie("Strange Days", "Crime");
        Movie mv7 = new Movie("My Family", "Drama");
        //Add the Movie objects to an ArrayList
        ArrayList<Movie> movieslist = new ArrayList<>();
        movieslist.add(mv1);
        movieslist.add(mv2);
        movieslist.add(mv3);
        movieslist.add(mv4);
        movieslist.add(mv5);
        movieslist.add(mv6);
        movieslist.add(mv7);

        MovieListAdapter adapter = new MovieListAdapter(this,R.layout.adapter_view_layout,movieslist);
        mListView.setAdapter(adapter);


    }
    public void giveRating(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
