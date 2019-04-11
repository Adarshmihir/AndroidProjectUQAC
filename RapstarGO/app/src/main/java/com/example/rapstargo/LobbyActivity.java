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

public class LobbyActivity extends AppCompatActivity implements SocketEvent {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        SocketManager.getInstance().mCurrentActivity = this;

        /*List<Player> connectedPlayers = new ArrayList<>();

        connectedPlayers.add(new Player("Connected 1","Level 42", "Connected"));
        connectedPlayers.add(new Player("Connected 2","Level 1", "Connected"));
        connectedPlayers.add(new Player("Connected 3","Level 666", "Connected"));
        connectedPlayers.add(new Player("Connected 4","Level 35", "Connected"));*/
        RecyclerView mConnectedRecycler = findViewById(R.id.connectedPlayersRecycler);
        MyPlayersAdapter connectedAdapter;
        try
        {
            Log.i("DIM", "Character List : " + SocketManager.getInstance().getmCurrentRoom().getCharacter_list().toString());



            connectedAdapter = new MyPlayersAdapter(SocketManager.getInstance().getmCurrentRoom().getCharacter_list());


        } catch (Exception e)
        {
            List<Character> character_list = new ArrayList<Character>();
            connectedAdapter = new MyPlayersAdapter(character_list);
            Log.i("DIM",  e.toString());
        }

        mConnectedRecycler.setLayoutManager(new LinearLayoutManager(getParent()));

        mConnectedRecycler.setAdapter(connectedAdapter);


        //*****Connected Players*****//

        /*RecyclerView mConnectedRecycler = findViewById(R.id.connectedPlayersRecycler);

        List<Player> connectedPlayers = new ArrayList<>();

        connectedPlayers.add(new Player("Connected 1","Level 42", "Connected"));
        connectedPlayers.add(new Player("Connected 2","Level 1", "Connected"));
        connectedPlayers.add(new Player("Connected 3","Level 666", "Connected"));
        connectedPlayers.add(new Player("Connected 4","Level 35", "Connected"));

        MyPlayersAdapter connectedAdapter = new MyPlayersAdapter(connectedPlayers);

        mConnectedRecycler.setLayoutManager(new LinearLayoutManager(getParent()));

        mConnectedRecycler.setAdapter(connectedAdapter);*/

        //*****Invited Players*****//

        /*RecyclerView mInvitedRecycler = findViewById(R.id.invitedPlayersRecycler);

        List<Player> invitedPlayers = new ArrayList<>();

        invitedPlayers.add(new Player("Invited 1","Level 42", "Invited"));
        invitedPlayers.add(new Player("Invited 2","Level 1", "Invited"));
        invitedPlayers.add(new Player("Invited 3","Level 666", "Invited"));
        invitedPlayers.add(new Player("Invited 4","Level 35", "Invited"));

        MyPlayersAdapter invitedAdapter = new MyPlayersAdapter(invitedPlayers);

        mInvitedRecycler.setLayoutManager(new LinearLayoutManager(getParent()));

        mInvitedRecycler.setAdapter(invitedAdapter);*/

        Button button_LaunchFight = (Button) findViewById(R.id.Button_LaunchFight);

        button_LaunchFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SocketManager.getInstance().LaunchFight();
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
    protected void onDestroy() {
        super.onDestroy();
        SocketManager.getInstance().ExitCurrentRoom();
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
    public void onGetAllCharacterOfRoomSuccess(final List<Character> p_CharacterList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("DIM", "GetAllCharacterOfRoom Success : " + p_CharacterList.toString());
                RecyclerView mConnectedRecycler = findViewById(R.id.connectedPlayersRecycler);
                ((MyPlayersAdapter)mConnectedRecycler.getAdapter()).ChangeCharacterList(p_CharacterList);
            }
        });
        /*List<Player> connectedPlayers = new ArrayList<>();

        connectedPlayers.add(new Player("Connected 1","Level 42", "Connected"));
        connectedPlayers.add(new Player("Connected 2","Level 1", "Connected"));
        connectedPlayers.add(new Player("Connected 3","Level 666", "Connected"));
        connectedPlayers.add(new Player("Connected 4","Level 35", "Connected"));*/

        /*MyPlayersAdapter connectedAdapter = new MyPlayersAdapter(p_CharacterList);

        mConnectedRecycler.setLayoutManager(new LinearLayoutManager(getParent()));

        mConnectedRecycler.setAdapter(connectedAdapter);*/
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
        Intent FightClub = new Intent(LobbyActivity.this, FightActivity.class);
        startActivity(FightClub);
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
