package com.example.rapstargo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class SignupActivity extends AppCompatActivity implements SocketEvent {

    private EditText loginText;
    private EditText passwordText;
    private EditText passwordCheckText;

    private TextView loginErrorTxt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        SocketManager.getInstance().mCurrentActivity = this;

        loginText = (EditText) findViewById(R.id.UsernameFieldSignup);
        passwordText = (EditText) findViewById(R.id.PasswordFieldSignup);
        passwordCheckText = (EditText) findViewById(R.id.ConfirmPasswordFieldSignup);

        loginErrorTxt2 = (TextView) findViewById(R.id.loginErrorTxt2);

        final Button but_SignUp = (Button) findViewById(R.id.signUpButton);
        but_SignUp.setVisibility(View.VISIBLE);



        but_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        but_SignUp.setVisibility(View.GONE);
                        loginErrorTxt2.setText("");
                    }
                });

                if(passwordText.getText().toString().equals(passwordCheckText.getText().toString()))
                {
                    SocketManager.getInstance().CreateAccount(loginText.getText(),passwordText.getText());
                } else {
                    onAccountCreationFailed("Confirm password error : " + passwordText.getText() + " : " + passwordCheckText.getText());
                }
            }
        });
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
        final Button but_SignUp = (Button) findViewById(R.id.signUpButton);
        but_SignUp.setVisibility(View.VISIBLE);
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
        Log.i("DIM", "SignUp Success : " + SocketManager.getInstance().getmPseudo());
        SocketManager.getInstance().CreateCharacter("Hero","0");

    }

    @Override
    public void onAccountCreationFailed(String p_ErrorMsg) {
        Log.i("DIM", "SignUp failed : " + p_ErrorMsg);
        loginErrorTxt2.setText(p_ErrorMsg);

        final Button but_SignUp = (Button) findViewById(R.id.signUpButton);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                but_SignUp.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onLoggedAccountResultSucces(String p_ResultMessage) {

    }

    @Override
    public void onLoggedAccountResultFailed(String p_ErrorMsg) {

    }

    @Override
    public void onCharacterCreationSuccess() {
        Log.i("DIM", "CharacterCreation Success : ");
        SocketManager.getInstance().SelectCharacter(0);
    }

    @Override
    public void onCharacterCreationFailed(String p_ErrorMsg) {
        Log.i("DIM", "CharacterCreation Failed : " + p_ErrorMsg);
    }

    @Override
    public void onGetAllMyCharactersSuccess(List<Character> p_ListOfCharacters) {

    }

    @Override
    public void onGetAllMyCharactersFailed(String p_ErrorMsg) {

    }

    @Override
    public void onCharacterSelectionSuccess(Character p_CurrentCharacter) {
        Log.i("DIM", "CharacterSelection Success : " + p_CurrentCharacter.getName());
        Intent Map = new Intent(SignupActivity.this, MapsActivity.class);
        startActivity(Map);
    }

    @Override
    public void onCharacterSelectionFailed(String p_ErrorMsg) {
        Log.i("DIM", "CharacterSelection Failed : " + p_ErrorMsg);
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

    }

    @Override
    public void onFightIsFinished(boolean p_Victory) {

    }

    @Override
    public void onUseCharacterAbilitySuccess() {

    }

    @Override
    public void onUseCharacterAbilityFailed(String p_ErrorMsg) {

    }

    @Override
    public void onBossTakeDamage() {

    }
}