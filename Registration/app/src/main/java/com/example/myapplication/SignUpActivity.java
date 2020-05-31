package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Button Save_btn , Back_toLog;
    private EditText editName,editEmail,editAdress,editAge,editPassword;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editPassword = findViewById(R.id.editPassword1);
        editName = findViewById(R.id.etAddName);
        editEmail = findViewById(R.id.etAddPhone);
        editAdress = findViewById(R.id.etAddress);
        editAge = findViewById(R.id.editText2);
//        editPass = (EditText) findViewById(R.id.pass);
        findViewById(R.id.savebtn).setOnClickListener(this);
       // findViewById(R.id.Back_login).setOnClickListener(this);
       Back_toLog = findViewById(R.id.Back_login);
       Back_toLog.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent0 = new Intent(SignUpActivity.this,MainActivity.class);
           }
       });

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){

        }
    }

    private void registerUser(){
        final String name =editName.getText().toString().trim();
        final String email = editEmail.getText().toString().trim();
        final String address = editAdress.getText().toString().trim();
        final String age = editAge.getText().toString().trim();
      //  final String studies = editStudies.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();
        if(name.isEmpty()){
            editName.setError("name required");
            editName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editEmail.setError("email required");
            editEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("email is not valid");
            editEmail.requestFocus();
            return;
        }
        if(address.isEmpty()){
            editAdress.setError("address required");
            editAdress.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editAge.setError("age required");
            editAge.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editPassword.setError("password required");
            editPassword.requestFocus();
            return;
       }
        mAuth.createUserWithEmailAndPassword(email , password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //if registration is successful

                            User user = new User(email, name, age, address);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                       // Toast.makeText(SignUpActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();

                                    }else{
                                        //display failure message
                                       // tryAgain();

                                    }
                                }
                            });
                        }else{
                            //display a toast
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.savebtn :
               registerUser() ;

                break;

        }
    }


}
