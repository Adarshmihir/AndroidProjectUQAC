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

        //*****Connected Players*****//

        RecyclerView mConnectedRecycler = findViewById(R.id.connectedPlayersRecycler);

        List<Player> connectedPlayers = new ArrayList<>();

        connectedPlayers.add(new Player("Connected 1","Level 42", "Connected"));
        connectedPlayers.add(new Player("Connected 2","Level 1", "Connected"));
        connectedPlayers.add(new Player("Connected 3","Level 666", "Connected"));
        connectedPlayers.add(new Player("Connected 4","Level 35", "Connected"));

        MyPlayersAdapter connectedAdapter = new MyPlayersAdapter(connectedPlayers);

        mConnectedRecycler.setLayoutManager(new LinearLayoutManager(getParent()));

        mConnectedRecycler.setAdapter(connectedAdapter);

        //*****Invited Players*****//

        RecyclerView mInvitedRecycler = findViewById(R.id.invitedPlayersRecycler);

        List<Player> invitedPlayers = new ArrayList<>();

        invitedPlayers.add(new Player("Invited 1","Level 42", "Invited"));
        invitedPlayers.add(new Player("Invited 2","Level 1", "Invited"));
        invitedPlayers.add(new Player("Invited 3","Level 666", "Invited"));
        invitedPlayers.add(new Player("Invited 4","Level 35", "Invited"));

        MyPlayersAdapter invitedAdapter = new MyPlayersAdapter(invitedPlayers);

        mInvitedRecycler.setLayoutManager(new LinearLayoutManager(getParent()));

        mInvitedRecycler.setAdapter(invitedAdapter);
    }
}
