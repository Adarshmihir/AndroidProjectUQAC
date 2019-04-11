package com.example.rapstargo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyLobbiesAdapter extends RecyclerView.Adapter<MyLobbiesAdapter.MyViewHolder> {

    List<Room> lobbies;

    MyLobbiesAdapter(List<Room> lobbies) {
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

    public void ChangeRoomList(List<Room> _newList)
    {
        Log.i("DIM", "AllNewRooms : " + _newList.toString());
        lobbies = _newList;
        Log.i("DIM", "AllRooms : " + lobbies.toString());
        notifyDataSetChanged();
    }

    public void AddRoom(Room _newRoom)
    {
        List<Room> _temp = lobbies;
        _temp.add(_newRoom);
        ChangeRoomList(_temp);

        //notifyDataSetChanged();
    }

    public void RemoveRoom(String p_RoomId)
    {
        boolean b_Find = false;
        List<Room> _tempList = lobbies;
        Log.i("DIM", "Remove : " + _tempList.toString());
        Room _temp = new Room();

        for(Room room : _tempList)
        {
            Log.i("DIM", "TestRoom : " + room.getId() + " == " + p_RoomId);
            if(room.getId().equals(p_RoomId))
            {
                Log.i("DIM", "Test est passé");
                _temp = room;
                b_Find = true;
                break;
            }
        }
        if(b_Find) {
            _tempList.remove(_temp);
            Log.i("DIM", "RemoveFind : " + _tempList.toString());

            ChangeRoomList(_tempList);
        }
    }

    @Override
    public int getItemCount() {
        return lobbies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView owner;
        private Button But_join;

        MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameLobby);
            owner = itemView.findViewById(R.id.owner);
            But_join = itemView.findViewById(R.id.Button_JoinLobby);


        }

        void display(final Room lobby) {
            name.setText(lobby.getId());
            owner.setText(lobby.getUser_id_owner());

            But_join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("DIM", "Lobby clicked id : " + lobby.getId());
                    SocketManager.getInstance().JoinRoom(lobby.getId());
                }
            });
        }
    }
}
