package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;

import android.widget.ListView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.tensorflow.lite.Interpreter;

public class MainActivity extends AppCompatActivity {
    Interpreter tflite;
    public View v, view;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setItemsCanFocus(true);

    //
    com.example.myapplication.Movie movie1 = new com.example.myapplication.Movie("Dracula", "Horror");
    com.example.myapplication.Movie movie2 = new com.example.myapplication.Movie("Mr.Wrong", "Comedy");
    com.example.myapplication.Movie movie3 = new com.example.myapplication.Movie("Jupiter's Wife", "Documentary");
    com.example.myapplication.Movie movie4 = new com.example.myapplication.Movie("Amazing Panda Adventure", "Adventure|Children");
    com.example.myapplication.Movie movie5 = new com.example.myapplication.Movie("Mad love", "Romance");
    com.example.myapplication.Movie movie6 = new com.example.myapplication.Movie("Strange Days", "Crime");
    com.example.myapplication.Movie movie7 = new com.example.myapplication.Movie("My Family", "Drama");
    Movie[] movies_list = new Movie[]{movie1, movie2, movie3, movie4, movie5, movie6, movie7};
    ArrayAdapter<com.example.myapplication.Movie> arrayAdapter
            = new ArrayAdapter<com.example.myapplication.Movie>(this, android.R.layout.simple_expandable_list_item_1, movies_list);

        listView.setAdapter(arrayAdapter);


    // android.R.layout.simple_list_item_1 is a constant predefined layout of Android.
    // used to create a ListView with simple ListItem (Only one TextView).

        try {
            tflite = new Interpreter(loadModelFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    // create the inputs ans outputs of the model
    public float doInference(String user) {
        float[] inputVal = new float[1];
        inputVal[0] = Float.valueOf(user);

        float[][] outputVal = new float[1][1];

        tflite.run(inputVal, outputVal);

        float inferredVal = outputVal[0][0];

        return inferredVal;
    }



    //load the tflite file witch contains the model
    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fd = this.getAssets().openFd("converted_model.tflite");
        FileInputStream inputStream = new FileInputStream(fd.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fd.getStartOffset();
        long declaredLength = fd.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);

    }





}
