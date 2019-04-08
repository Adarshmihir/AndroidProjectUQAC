package com.example.rapstargo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class CreateLobbyActivity extends AppCompatActivity implements SocketEvent {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lobby);
        SocketManager.getInstance().mCurrentActivity = this;

        Button button_CreateLobby = (Button) findViewById(R.id.createLobbyButton);

        button_CreateLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent CreateLobby = new Intent(HubActivity.this, CreateLobbyActivity.class);
                startActivity(CreateLobby);*/
                SocketManager.getInstance().CreateRoom();
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
        Log.i("DIM", "CreateRoom Success : " + SocketManager.getInstance().getmCurrentRoom().toString());

    }

    @Override
    public void onCreateRoomFailed(String p_ErrorMsg) {
        Log.i("DIM", "CreateRoom Failed : " + p_ErrorMsg);
    }

    @Override
    public void onAddRoomToHub(Room p_Room) {

    }

    @Override
    public void onRemoveRoomToHub(String p_RoomId) {

    }

    @Override
    public void onJoinRoomSuccess() {
        Log.i("DIM", "JoinRoom success : " + SocketManager.getInstance().getmCurrentRoom().toString());
        Intent Lobby = new Intent(CreateLobbyActivity.this, LobbyActivity.class);
        startActivity(Lobby);
    }

    @Override
    public void onJoinRoomFailed(String p_ErrorMsg) {
        Log.i("DIM", "JoinRoom Failed : " + p_ErrorMsg);
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
