package com.example.rapstargo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerViewHub);

        List<Lobby> lobbies = new ArrayList<>();

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
        lobbies.add(new Lobby("Lobby15","Owner15"));

        MyLobbiesAdapter adapter = new MyLobbiesAdapter(lobbies);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getParent()));

        mRecyclerView.setAdapter(adapter);
    }
}
