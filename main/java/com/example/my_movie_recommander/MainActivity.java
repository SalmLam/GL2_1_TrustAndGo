package com.example.my_movie_recommander;

import android.content.res.AssetFileDescriptor;
import android.graphics.Movie;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Interpreter tflite;

    private List<Movie> movies_list= new ArrayList<>();
    ListView listView = (ListView) findViewById(R.id.listView);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





//create tflite object
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

        movie m1 = new movie("Dracula", "Horror");
        movie m2 = new movie("Mr.Wrong", "Comedy");
        movie m3 = new movie("Jupiter's Wife", "Documentary");
        movie m4= new movie("Amazing Panda Adventure", "Adventure|Children");
        movie m5 = new movie("Mad love", "Romance");
        movie m6 = new movie("Strange Days", "Crime");
        movie m7= new movie("My Family", "Drama");
        movie[] movies = new movie[]{m1, m2, m3, m4, m5, m6, m7};

}







