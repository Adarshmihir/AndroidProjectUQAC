package com.example.rapstargo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent signup = new Intent(MainActivity.this, SignupActivity.class);

        /* Pour tester */
        startActivity(signup);

    }
}
