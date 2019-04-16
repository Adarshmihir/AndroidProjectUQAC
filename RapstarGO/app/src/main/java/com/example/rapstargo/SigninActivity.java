package com.example.rapstargo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class SigninActivity extends AppCompatActivity implements SocketEvent {

    private EditText loginText;
    private EditText passwordText;
    private TextView LoginErrorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        SocketManager.getInstance().mCurrentActivity = this;

        loginText = (EditText) findViewById(R.id.UsernameField);
        passwordText = (EditText) findViewById(R.id.PasswordField);
        LoginErrorTxt = (TextView) findViewById(R.id.LoginErrorTxt);

        final Button but_Connect = (Button) findViewById(R.id.signInButton);
        Button but_CreateAccount = (Button) findViewById(R.id.createAccountButton);

        but_Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginErrorTxt.setText("");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        but_Connect.setVisibility(View.GONE);
                    }
                });
                SocketManager.getInstance().Login(loginText.getText(),passwordText.getText());
            }
        });

        but_CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Signup = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(Signup);
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
        final Button but_Connect = (Button) findViewById(R.id.signInButton);
        but_Connect.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        SocketManager.getInstance().ExitCurrentHub();
        super.onDestroy();

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
        Log.i("DIM", "Connection Success : " + SocketManager.getInstance().getmPseudo());
        SocketManager.getInstance().ExitCurrentHub();
        SocketManager.getInstance().SelectCharacter(0);

    }

    @Override
    public void onUserConnectionFailed(String p_ErrorMsg) {
        final Button but_Connect = (Button) findViewById(R.id.signInButton);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                but_Connect.setVisibility(View.VISIBLE);
            }
        });
        Log.i("DIM", "Connection Failed : " + p_ErrorMsg);
        LoginErrorTxt.setText(p_ErrorMsg);
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
        Log.i("DIM", "Connection Succes : " + p_CurrentCharacter.toString());
        Intent Map = new Intent(SigninActivity.this, MapsActivity.class);
        startActivity(Map);
    }

    @Override
    public void onCharacterSelectionFailed(String p_ErrorMsg) {
        Log.i("DIM", "Connection Failed : " + p_ErrorMsg);
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