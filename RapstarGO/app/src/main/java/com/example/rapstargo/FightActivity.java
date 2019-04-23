package com.example.rapstargo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FightActivity extends AppCompatActivity implements SocketEvent {

    ImageButton firstAttackButton;
    ImageButton secondAttackButton;
    ProgressBar playerHpBar;
    ProgressBar bossHpBar;
    boolean IsFinished;

    LinearLayout playerList;


    TextView BossImage;
    private int attackCooldown0 = 2000; // 1 Second
    private int attackCooldown1 = 2000; // 1 Second
    private float characterMaxHP = 150; // 1 Second
    private float BossMaxHP = 500; // 1 Second
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
            secondAttackButton.setImageResource(R.drawable.hache);
            Log.i("RPGO", "btn 2 reset");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_fight);
        SocketManager.getInstance().mCurrentActivity = this;
        IsFinished = false;

        firstAttackButton = findViewById(R.id.firstAttackButton);
        secondAttackButton = findViewById(R.id.secondAttackButton);

        playerHpBar = findViewById(R.id.PlayerHealthBar);
        bossHpBar = findViewById(R.id.BossHealthBar);
        BossImage = findViewById(R.id.BossHPText);

        playerList = findViewById(R.id.OtherPlayersList);

        try{
            if(SocketManager.getInstance().getmCurrentRoom().getCharacter_list().size()>0){
                for (Character _char : SocketManager.getInstance().getmCurrentRoom().getCharacter_list()){
                    TextView playerView = new TextView(this,null,R.style.otherplayers);
                    playerView.setText(_char.getName()+"     ");
                    playerList.addView(playerView);
                }

            }

        }catch (Exception e){
            Log.i("Room", e.toString());
        }

        try {
            if(SocketManager.getInstance().getmCharacter() != null )
            {
                if(SocketManager.getInstance().getmCharacter().getAbilities().size() >= 1 )
                {
                    attackCooldown0 = SocketManager.getInstance().getmCharacter().getAbilities().get(0).getCooldown();
                }
                if(SocketManager.getInstance().getmCharacter().getAbilities().size() >= 2 )
                {
                    attackCooldown1 = SocketManager.getInstance().getmCharacter().getAbilities().get(1).getCooldown();
                }
                characterMaxHP = 150;
            }
            if(SocketManager.getInstance().getmCurrentRoom() != null)
            {
                if(SocketManager.getInstance().getmCurrentRoom().getBoss() != null)
                {
                    BossMaxHP = SocketManager.getInstance().getmCurrentRoom().getBoss().getLife();
                }
            }
        } catch (Exception e)
        {
            Log.i("DIM", e.toString());
        }



    }

    public void firstButtonClick(View v){
        try {
            if(!IsFinished)
            {
                Log.i("RPGO", "btn 1");
                firstAttackButton.setEnabled(false);
                firstAttackButton.setImageResource(R.drawable.attack_icon_inactive);
                cooldownHandler.postAtTime(resetFirstButton, System.currentTimeMillis()+attackCooldown0);
                try {
                    if(SocketManager.getInstance().getmCharacter().getAbilities().size() >= 1)
                    {
                        SocketManager.getInstance().UseAbility(Integer.toString(SocketManager.getInstance().getmCharacter().getAbilities().get(0).getId()));
                    }
                } catch (Exception e)
                {
                    Log.i("DIM", e.toString());
                }
                cooldownHandler.postDelayed(resetFirstButton, attackCooldown0);
            }
        } catch (Exception e)
        {
            Log.i("DIM", e.toString());
        }

    }

    public void secondButtonClick(View v){
        try {
            if(!IsFinished)
            {
                Log.i("RPGO", "btn 2");
                secondAttackButton.setEnabled(false);
                secondAttackButton.setImageResource(R.drawable.hache_inactive);
                cooldownHandler.postAtTime(resetSecondButton, System.currentTimeMillis()+attackCooldown1);
                try {
                    if(SocketManager.getInstance().getmCharacter().getAbilities().size() >= 2)
                    {
                        SocketManager.getInstance().UseAbility(Integer.toString(SocketManager.getInstance().getmCharacter().getAbilities().get(1).getId()));
                    }
                } catch (Exception e)
                {
                    Log.i("DIM", e.toString());
                }
                cooldownHandler.postDelayed(resetSecondButton, attackCooldown1);
            }
        } catch (Exception e)
        {
            Log.i("DIM", e.toString());
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        SocketManager.getInstance().mCurrentActivity = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        SocketManager.getInstance().mCurrentActivity = this;
    }

    @Override
    public void onAPIDisconnection(String p_ErrorMsg) {

    }

    @Override
    public void onUserDisconnection(String p_ErrorMsg) {

    }

    @Override
    public void onAPIConnection() {

    }

    @Override
    public void onUserConnectionSuccess() {

    }

    @Override
    public void onUserConnectionFailed(String p_ErrorMsg) {

    }

    @Override
    public void onUserReconnectionSuccess() {

    }

    @Override
    public void onUserReconnectionFailed(String p_ErrorMsg) {

    }

    @Override
    public void onAccountCreationSuccess() {

    }

    @Override
    public void onAccountCreationFailed(String p_ErrorMsg) {

    }

    @Override
    public void onLoggedAccountResultSucces(String p_ResultMessage) {

    }

    @Override
    public void onLoggedAccountResultFailed(String p_ErrorMsg) {

    }

    @Override
    public void onCharacterCreationSuccess() {

    }

    @Override
    public void onCharacterCreationFailed(String p_ErrorMsg) {

    }

    @Override
    public void onGetAllMyCharactersSuccess(List<Character> p_ListOfCharacters) {

    }

    @Override
    public void onGetAllMyCharactersFailed(String p_ErrorMsg) {

    }

    @Override
    public void onCharacterSelectionSuccess(Character p_CurrentCharacter) {

    }

    @Override
    public void onCharacterSelectionFailed(String p_ErrorMsg) {

    }

    @Override
    public void onGetCurrentCharacterSuccess(Character p_CurrentCharacter) {

    }

    @Override
    public void onGetCurrentCharacterFailed(String p_ErrorMsg) {

    }

    @Override
    public void onGetAllHubsSuccess(List<Hub> p_HubsList) {

    }

    @Override
    public void onGetAllHubsFailed(String p_ErrorMsg) {

    }

    @Override
    public void onConnectToHubSuccess(Hub p_Hub) {

    }

    @Override
    public void onConnectToHubFailed(String p_ErrorMsg) {

    }

    @Override
    public void onGetHubConnectedToSuccess(Hub p_Hub) {

    }

    @Override
    public void onGetHubConnectedToFailed(String p_ErrorMsg) {

    }

    @Override
    public void onExitHubSuccess() {

    }

    @Override
    public void onExitHubFailed(String p_ErrorMsg) {

    }

    @Override
    public void onCreateRoomSuccess() {

    }

    @Override
    public void onCreateRoomFailed(String p_ErrorMsg) {

    }

    @Override
    public void onAddRoomToHub(Room p_Room) {

    }

    @Override
    public void onRemoveRoomToHub(String p_RoomId) {

    }

    @Override
    public void onJoinRoomSuccess() {

    }

    @Override
    public void onJoinRoomFailed(String p_ErrorMsg) {

    }

    @Override
    public void onGetAllCharacterOfRoomSuccess(List<Character> p_CharacterList) {

    }

    @Override
    public void onGetAllCharacterOfRoomFailed(String p_ErrorMsg) {

    }

    @Override
    public void onExitCurrentRoomSuccess() {

    }

    @Override
    public void onExitCurrentRoomFailed(String p_ErrorMsg) {

    }

    @Override
    public void onLaunchFightSuccess() {

    }

    @Override
    public void onLaunchFightFailed(String p_ErrorMsg) {

    }

    @Override
    public void onFightIsLaunched() {

    }

    @Override
    public void onBossAttack(List<Character> p_CharacterList) {
        Log.i("DIM", "I take damage");
        if(SocketManager.getInstance().getmCharacter() != null)
        {
            float divid = SocketManager.getInstance().getmCharacter().getCurrent_life()/ characterMaxHP;
            int progress = (int)(divid*100);
            Log.i("DIM", "Divid : " + divid + " pourcent : " + progress);
        }

        try {

            int progress = (int)(((float)SocketManager.getInstance().getmCharacter().getCurrent_life()/(float) characterMaxHP)*100);
            playerHpBar.setProgress(progress, true);
        } catch (Exception e)
        {
            Log.i("DIM", e.toString());
        }

    }

    @Override
    public void onFightIsFinished(boolean p_Victory) {
        try {
            IsFinished = true;
            if(p_Victory)
            {
                bossHpBar.setProgress(0, true);
                Context context = getApplicationContext();
                CharSequence text = "Win!";
                BossImage.setText(text);
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            } else
            {
                Context context = getApplicationContext();
                CharSequence text = "Lose!";
                BossImage.setText(text);
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }
        } catch (Exception e)
        {
            Log.i("DIM", e.toString());
        }



    }

    @Override
    public void onUseCharacterAbilitySuccess() {

    }

    @Override
    public void onUseCharacterAbilityFailed(String p_ErrorMsg) {

    }

    @Override
    public void onBossTakeDamage() {
        try {
            int progress = (int)(((float)SocketManager.getInstance().getmCurrentRoom().getBoss().getLife()/(float) BossMaxHP)*100);
            bossHpBar.setProgress(progress, true);
        } catch (Exception e)
        {
            Log.i("DIM", e.toString());
        }

    }
}
