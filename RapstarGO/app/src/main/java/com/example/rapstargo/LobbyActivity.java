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

        RecyclerView mRecyclerView = findViewById(R.id.recyclerViewLobby);

        List<Player> players = new ArrayList<>();

        players.add(new Player("Player1",123));
        players.add(new Player("Player2",123));
        players.add(new Player("Player3",123));
        players.add(new Player("Player4",123));
        players.add(new Player("Player5",123));
        players.add(new Player("Player6",123));
        players.add(new Player("Player7",123));
        players.add(new Player("Player8",123));
        players.add(new Player("Player9",123));
        players.add(new Player("Player10",123));
        players.add(new Player("Player11",123));
        players.add(new Player("Player12",123));
        players.add(new Player("Player13",123));
        players.add(new Player("Player14",123));
        players.add(new Player("Player15",123));

        MyPlayersAdapter adapter = new MyPlayersAdapter(players);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getParent()));

        mRecyclerView.setAdapter(adapter);
    }
}
