package com.example.rapstargo;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MyPlayersAdapter extends RecyclerView.Adapter<MyPlayersAdapter.MyViewHolder> {

    List<Character> players;

    MyPlayersAdapter(List<Character> players) {
        this.players = players;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.player_item, parent, false);
        return new MyViewHolder(view);
    }

    public void ChangeCharacterList(List<Character> _newList)
    {
        players.clear();
        players.addAll(_newList);
        notifyDataSetChanged();
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
        //private ProgressBar statusProgress;

        MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.playerName);
            level = itemView.findViewById(R.id.playerLevel);
            status = itemView.findViewById(R.id.playerStatus);
            //statusProgress = itemView.findViewById(R.id.statusProgressBar);
        }

        void display(Character player) {
            name.setText(player.getName());
            level.setText( Integer.toString(player.getLevel()));
            status.setText(player.getClass_name());

            /*if(status.getText() == "Invited") {
                statusProgress.setVisibility(View.VISIBLE);
                status.setTextColor(Color.RED);
            }
            else {
                statusProgress.setVisibility(View.INVISIBLE);
                status.setTextColor(Color.GREEN);
            }*/

        }
    }
}
