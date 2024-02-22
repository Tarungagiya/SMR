package com.example.sharemyride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class spalshscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalshscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(login.PREFS_NAME,0);
                boolean hasloggedin = sharedPreferences.getBoolean("hasloggedin",false);

                if (hasloggedin){
                    Intent intent = new Intent(spalshscreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    startActivity(new Intent(getApplicationContext(),login.class));
                }
            }
        },1000);
    }
}