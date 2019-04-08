package com.example.rapstargo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class HubActivity extends AppCompatActivity implements SocketEvent {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        SocketManager.getInstance().mCurrentActivity = this;

        RecyclerView mRecyclerView = findViewById(R.id.recyclerViewHub);

        /*List<Lobby> lobbies = new ArrayList<>();

        lobbies.add(new Lobby("Lobby1","Owner1"));
        lobbies.add(new Lobby("Lobby2","Owner2"));
        lobbies.add(new Lobby("Lobby3","Owner3"));
        lobbies.add(new Lobby("Lobby4","Owner4"));
        lobbies.add(new Lobby("Lobby5","Owner5"));
        lobbies.add(new Lobby("Lobby6","Owner6"));
        lobbies.add(new Lobby("Lobby7","Owner7"));
        lobbies.add(new Lobby("Lobby8","Owner8"));
        lobbies.add(new Lobby("Lobby9","Owner9"));
        lobbies.add(new Lobby("Lobby10","Owner10"));
        lobbies.add(new Lobby("Lobby11","Owner11"));
        lobbies.add(new Lobby("Lobby12","Owner12"));
        lobbies.add(new Lobby("Lobby13","Owner13"));
        lobbies.add(new Lobby("Lobby14","Owner14"));
        lobbies.add(new Lobby("Lobby15","Owner15"));*/

        MyLobbiesAdapter adapter = new MyLobbiesAdapter(SocketManager.getInstance().getmCurrentHub().getRooms_list());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getParent()));

        mRecyclerView.setAdapter(adapter);

        Button button_CreateRoom = (Button) findViewById(R.id.button_CreateRoom);

        button_CreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CreateLobby = new Intent(HubActivity.this, CreateLobbyActivity.class);
                startActivity(CreateLobby);
            }
        });
    }

    @Override
    public void onAPIDisconnection(String p_ErrorMsg) {

    }

    @Override
    public void onUserDisconnection(String p_ErrorMsg) {

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
    protected void onDestroy() {
        SocketManager.getInstance().ExitCurrentHub();
        super.onDestroy();

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
        Log.i("DIM", "AddRoom Success : " + p_Room.toString());
        RecyclerView mConnectedRecycler = findViewById(R.id.recyclerViewHub);
        ((MyLobbiesAdapter)mConnectedRecycler.getAdapter()).AddRoom(p_Room);
    }

    @Override
    public void onRemoveRoomToHub(String p_RoomId) {
        Log.i("DIM", "RemoveRoom Success : " + p_RoomId);
        RecyclerView mConnectedRecycler = findViewById(R.id.recyclerViewHub);
        ((MyLobbiesAdapter)mConnectedRecycler.getAdapter()).RemoveRoom(p_RoomId);
    }

    @Override
    public void onJoinRoomSuccess() {
        Log.i("DIM", "JoinRoom success : " + SocketManager.getInstance().getmCurrentRoom().toString());
        Intent Lobby = new Intent(HubActivity.this, LobbyActivity.class);
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
