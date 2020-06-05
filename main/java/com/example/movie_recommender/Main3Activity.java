package com.example.movie_recommender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import android.content.Intent;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    Interpreter tflite;
    View v, view;
    String[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final TextView out;
        Intent intent = getIntent();
        String rt= intent.getStringExtra(Main2Activity.EXTRA_TEXT);
        out = (TextView) findViewById(R.id.out);
        out.setText("" + rt);
       String[] array = rt.split(" ");


        try {
            tflite = new Interpreter(loadModelFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        float prediction = doInference(rt);
        out.setText(Float.toString(prediction));
    }

        public float doInference (String inputString){
        float [] rts ={1,1,1,1,1};
            //for(String i : array){
               // float f = Float.parseFloat(i);
               // rts.(f);
           // }


            float[][] input = {rts};
            float[][] output = new float[1][20];


            tflite.run(input, output);

            float inferredVal = output[0][0];

            return inferredVal;
        }


   // load the tflite file witch contains the model
    private MappedByteBuffer loadModelFile () throws IOException {
        AssetFileDescriptor fd = this.getAssets().openFd("converted_model.tflite");
        FileInputStream inputStream = new FileInputStream(fd.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fd.getStartOffset();
        long declaredLength = fd.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);

    }


}
