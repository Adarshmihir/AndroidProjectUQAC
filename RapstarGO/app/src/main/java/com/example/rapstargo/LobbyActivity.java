package com.example.rapstargo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LobbyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        //TODO: populate lists with API

        //*****CONNECTED PLAYERS DISPLAY MANAGEMENT*****//

        List<Player> connectedPlayers = new ArrayList<>();

        //Link activity and layout
        RecyclerView mRecyclerViewConnectedPlayers = findViewById(R.id.recyclerViewConnectedPlayers);

        //Hardcoded connected players
        connectedPlayers.add(new Player("Connected 1",123));
        connectedPlayers.add(new Player("Connected 2",123));
        connectedPlayers.add(new Player("Connected 3",123));
        connectedPlayers.add(new Player("Connected 4",123));

        //Create the adapter for connected players
        MyPlayersAdapter connectedAdapter = new MyPlayersAdapter(connectedPlayers);

        //Set recyclerViewConnectedPlayers adapter
        mRecyclerViewConnectedPlayers.setLayoutManager(new LinearLayoutManager(getParent()));

        mRecyclerViewConnectedPlayers.setAdapter(connectedAdapter);

        //*****INVITED PLAYERS DISPLAY MANAGEMENT*****//

        List<Player> invitedPlayers = new ArrayList<>();

        //Link activity and layout
        RecyclerView mRecyclerViewInvitedPlayers = findViewById(R.id.recyclerViewInvitedPlayers);

        //Hardcoded invited players
        invitedPlayers.add(new Player("Invited 1", 123));
        invitedPlayers.add(new Player("Invited 2", 123));
        invitedPlayers.add(new Player("Invited 3", 123));
        invitedPlayers.add(new Player("Invited 4", 123));

        //Create the adapter for invited players
        MyPlayersAdapter invitedAdapter = new MyPlayersAdapter(invitedPlayers);

        //Set recyclerViewInvitedPlayers adapter
        mRecyclerViewInvitedPlayers.setAdapter(invitedAdapter);
    }
}
