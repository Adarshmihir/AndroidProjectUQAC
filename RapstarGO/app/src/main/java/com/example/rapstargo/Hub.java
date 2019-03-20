package com.example.rapstargo;

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
}