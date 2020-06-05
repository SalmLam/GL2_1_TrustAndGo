package com.example.movie_recommender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.*;
import java.io.*;
import android.os.*;
import java.util.*;


import static com.example.movie_recommender.R.raw.mvresults;


public class Main4Activity extends AppCompatActivity {


    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listView = (ListView) findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.mvresults);
        CSVFile csvFile = new CSVFile(inputStream);
        List scoreList = csvFile.read();

        for(Object scoreData:scoreList ) {
            itemArrayAdapter.add(scoreData);
        }
    }
}
