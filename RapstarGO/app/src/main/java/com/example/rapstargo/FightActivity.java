package com.example.rapstargo;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class FightActivity extends AppCompatActivity {

    ImageButton firstAttackButton;
    ImageButton secondAttackButton;


    private final int attackCooldown = 2000; // 1 Second
    private Handler cooldownHandler = new Handler();
    private Runnable resetFirstButton = new Runnable(){
        public void run() {
            firstAttackButton.setEnabled(true);
            firstAttackButton.setImageResource(R.drawable.attack_icon);
            Log.i("RPGO", "btn 1 reset");
        }
    };

    private Runnable resetSecondButton = new Runnable(){
        public void run() {
            secondAttackButton.setEnabled(true);
            secondAttackButton.setImageResource(R.drawable.heal_icon);
            Log.i("RPGO", "btn 2 reset");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_fight);

        firstAttackButton = findViewById(R.id.firstAttackButton);
        secondAttackButton = findViewById(R.id.secondAttackButton);




    }

    public void firstButtonClick(View v){
        Log.i("RPGO", "btn 1");
        firstAttackButton.setEnabled(false);
        firstAttackButton.setImageResource(R.drawable.attack_icon_inactive);
        cooldownHandler.postAtTime(resetFirstButton, System.currentTimeMillis()+attackCooldown);
        cooldownHandler.postDelayed(resetFirstButton, attackCooldown);
    }

    public void secondButtonClick(View v){
        Log.i("RPGO", "btn 2");
        secondAttackButton.setEnabled(false);
        secondAttackButton.setImageResource(R.drawable.heal_icon_inactive);
        cooldownHandler.postAtTime(resetSecondButton, System.currentTimeMillis()+attackCooldown);
        cooldownHandler.postDelayed(resetSecondButton, attackCooldown);
    }
}
