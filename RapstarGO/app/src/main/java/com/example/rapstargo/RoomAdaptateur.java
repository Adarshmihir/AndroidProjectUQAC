package com.example.tdufo.sockettry;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

public class RoomAdaptateur extends BaseAdapter {
    private final Context mContext;
    private List<Room> mRooms;

    // 1
    public RoomAdaptateur(Context context, List<Room> rooms) {
        this.mContext = context;
        this.mRooms = rooms;
    }

    public void UpdateRoomList(List<Room> _newList)
    {
        this.mRooms = _newList;
        notifyDataSetChanged();
        Log.i("DIM", "UpdateRoomList: rooms " + this.mRooms.toString());
    }

    public void AddRoomToList(Room _newRoom)
    {
        this.mRooms.add(_newRoom);
        notifyDataSetChanged();
    }

    public void RemoveRoomToList(String _roomId)
    {
        for (Room _room: mRooms) {
            if(_room.getId() == _roomId)
            {
                mRooms.remove(_room);
                break;
            }
        }
        notifyDataSetInvalidated();
    }

    // 2
    @Override
    public int getCount() {
        return mRooms.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 1;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return mRooms.get(position);
    }

    // 5
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button dummyTextView = new Button(mContext);
        String res = mRooms.get(position).toString();
        dummyTextView.setText(res);
        dummyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DIM", getItem(position).toString());
                SocketManager.getInstance().JoinRoom(((Room) getItem(position)).getId());
            }
        });
        return dummyTextView;
    }
}
