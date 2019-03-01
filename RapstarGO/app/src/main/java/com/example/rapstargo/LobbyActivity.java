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
        setContentView(R.layout.activity_hub);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);

        List<Player> players = new ArrayList<>();

        players.add(new Player("Player1","XP1"));
        players.add(new Player("Player2","XP2"));
        players.add(new Player("Player3","XP3"));
        players.add(new Player("Player4","XP4"));
        players.add(new Player("Player5","XP5"));
        players.add(new Player("Player6","XP6"));
        players.add(new Player("Player7","XP7"));
        players.add(new Player("Player8","XP8"));
        players.add(new Player("Player9","XP9"));
        players.add(new Player("Player10","XP10"));
        players.add(new Player("Player11","XP11"));
        players.add(new Player("Player12","XP12"));
        players.add(new Player("Player13","XP13"));
        players.add(new Player("Player14","XP14"));
        players.add(new Player("Player15","XP15"));

        MyPlayersAdapter adapter = new MyPlayersAdapter(players);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getParent()));

        mRecyclerView.setAdapter(adapter);
    }
}
