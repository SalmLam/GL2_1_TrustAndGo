package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.EnumMap;


public class MainActivity extends AppCompatActivity {

    private EditText editEmail, editPassword ;
   // private Button signUp_button ;
    private CallbackManager mCallBackManager ;
    private FirebaseAuth mFireBaseAuth;
    private TextView textViewUser ;
    private ImageView mLogo ;
    private LoginButton loginButton ;
    private FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;
    private Button signup;
    private static final String TAG="FacebookAuthentification";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPass);


        final MediaPlayer mp =MediaPlayer.create(this,R.raw.sound);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();
                mp.start();
            }
        });

       signup = findViewById(R.id.sign_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         openSignUpActivity();
              mp.start();
            }
    });
        mFireBaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        textViewUser = findViewById(R.id.textView);
        mLogo = findViewById(R.id.image_logo);

        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email","public_profile");
        mCallBackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(mCallBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel" );
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError" + error);
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {




            }
        };
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken == null){
                    mFireBaseAuth.signOut();
                }
            }
        };
    }

    private void  handleFacebookToken(AccessToken token){
        //Log.d(TAG, "handleFacebookToken" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFireBaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //  Log.d(TAG , "Sign in with credential : successful");
                    FirebaseUser user = mFireBaseAuth.getCurrentUser();
                    //add chatbot actovity here ************************************************************************************************
                    Intent inten = new Intent(MainActivity.this,WelcomeActivity.class);
                    startActivity(inten);
                    // UpdateUI(user);


                }else{
                    //       Log.d(TAG , "Sign in with credential : failure" + task.getException());
                    Toast.makeText(MainActivity.this , "Authentifictaion failed " , Toast.LENGTH_SHORT).show();
                    UpdateUI(null);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallBackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void UpdateUI(FirebaseUser user){
        if(user != null){
            textViewUser.setText(user.getDisplayName());
            if(user.getPhotoUrl() != null){
//                String photoUrl = user.getPhotoUrl().toString();
//                photoUrl = photoUrl + "?type=large";
//                Picasso.get().load(photoUrl).into(mLogo);
//
//
          }
        }else {
            textViewUser.setText("");
            //  mLogo.setImageResource(R.drawable.com_facebook_profile_picture_blank_portrait);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mFireBaseAuth.getCurrentUser();
        UpdateUI(user);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            mFireBaseAuth.removeAuthStateListener(authStateListener);
        }
    }
    public void openSignUpActivity(){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    private void userLogin(){
        final String password = editPassword.getText().toString().trim();
        final String email = editEmail.getText().toString().trim();
        if(email.isEmpty()){
            editEmail.setError("email required");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editPassword.setError("password required");
            editPassword.requestFocus();
            return;
        }
        mFireBaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //here change welcome activity to chatbot activity
                    Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
                    // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
