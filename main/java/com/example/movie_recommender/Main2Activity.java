package com.example.movie_recommender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.tensorflow.lite.Interpreter;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;

import android.widget.ListView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


import org.tensorflow.lite.Interpreter;





public class Main2Activity extends AppCompatActivity {

    public static final String EXTRA_TEXT= "com.example.movie_recommender.EXTRA_TEXT";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText edt = (EditText) findViewById(R.id.editid);
        //final TextView out;




        Button submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });






    }

    public void submit(){



        Intent intent = new Intent(this, Main4Activity.class);
      //  intent.putExtra(EXTRA_TEXT,rt);
        startActivity(intent);

    }
}
