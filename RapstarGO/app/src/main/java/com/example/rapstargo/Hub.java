package com.example.rapstargo;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

public class Hub {

    private int id;
    private String name;
    private LocationHub location;
    private Marker mMarker;
    private List<Room> rooms_list;

    public Hub() {
        rooms_list = new ArrayList<Room>();
        mMarker = null;
    }

    public Hub(LocationHub locationHub) {
        rooms_list = new ArrayList<Room>();
        mMarker = null;
        setLocation(locationHub);
    }

    public List<Room> getRooms_list() {
        return rooms_list;
    }

    public void setRooms_list(List<Room> rooms_list) {
        this.rooms_list = rooms_list;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationHub getLocation() {
        return location;
    }

    public void setLocation(LocationHub location) {
        this.location = location;
    }

    public void removeMarker() {

        if(mMarker != null)
            mMarker.remove();

        mMarker = null;
    }


    public void setmMarker(Marker marker) {
        removeMarker();
        mMarker = marker;
    }

    public Marker getmMarker() {
        return this.mMarker;
    }

    @Override
    public String toString() {
        return "id : " + getId() + " Nom : " + getName() + " Location : " + location.toString() + " nbRoom = " + getRooms_list().toString();
    }

    void AddRoom(Room _room)
    {
        rooms_list.add(_room);
    }

    void RemoveRoom(String p_RoomId)
    {
        boolean b_Find = false;
        List<Room> _tempList = rooms_list;
        Room _temp = new Room();

        for(Room room : _tempList)
        {
            if(room.getId().equals(p_RoomId))
            {
                Log.i("DIM", "Test est passé");
                _temp = room;
                b_Find = true;
                break;
            }
        }
        if(b_Find) {
            rooms_list.remove(_temp);
        }
    }
}