package com.example.rapstargo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /* Pour tester */
        Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(myIntent);

    }
}
