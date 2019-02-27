package com.example.rapstargo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         Intent main = new Intent(MainActivity.this, MainActivity.class);

        /* Pour tester */
        startActivity(main);

    }
}
