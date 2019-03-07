package com.example.rapstargo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MyPlayersAdapter extends RecyclerView.Adapter<MyPlayersAdapter.MyViewHolder> {

    List<Player> players;

    MyPlayersAdapter(List<Player> players) {
        this.players = players;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.player_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.display(players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView level;
        private TextView status;
        private ProgressBar statusProgress;

        MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.playerName);
            level = itemView.findViewById(R.id.playerLevel);
            status = itemView.findViewById(R.id.playerStatus);
            statusProgress = itemView.findViewById(R.id.statusProgressBar);
        }

        void display(Player player) {
            name.setText(player.getName());
            level.setText(player.getLevel());
            status.setText(player.getStatus());

            if(status.getText() == "Invited")
                statusProgress.setVisibility(View.VISIBLE);
            else
                statusProgress.setVisibility(View.INVISIBLE);

        }
    }
}
