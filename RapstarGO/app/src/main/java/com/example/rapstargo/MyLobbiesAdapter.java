package com.example.rapstargo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyLobbiesAdapter extends RecyclerView.Adapter<MyLobbiesAdapter.MyViewHolder> {

    List<Lobby> lobbies;

    MyLobbiesAdapter(List<Lobby> lobbies) {
        this.lobbies = lobbies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lobby_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.display(lobbies.get(position));
    }

    @Override
    public int getItemCount() {
        return lobbies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView owner;

        MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameLobby);
            owner = itemView.findViewById(R.id.owner);
        }

        void display(Lobby lobby) {
            name.setText(lobby.getName());
            owner.setText(lobby.getOwner());
        }
    }
}
