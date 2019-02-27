package com.example.rapstargo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent hub = new Intent(MainActivity.this, HubActivity.class);
        Intent main = new Intent(MainActivity.this, MainActivity.class);
        Intent maps = new Intent(MainActivity.this, MapsActivity.class);
        Intent signin = new Intent(MainActivity.this, SigninActivity.class);
        Intent signup = new Intent(MainActivity.this, SignupActivity.class);

        /* Pour tester */
        // startActivity(INTENT_NAME);
        startActivity(signup);

    }
}
