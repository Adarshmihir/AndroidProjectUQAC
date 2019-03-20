package com.example.rapstargo;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String user_id_owner;
    private List<String> user_list;

    public Room() {
        user_list = new ArrayList<String>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id_owner() {
        return user_id_owner;
    }

    public void setUser_id_owner(String user_id_owner) {
        this.user_id_owner = user_id_owner;
    }

    public List<String> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<String> user_list) {
        this.user_list = user_list;
    }

    @Override
    public String toString() {
        return getId() + " " + getUser_id_owner() + " nbUser " + getUser_list().size();
    }
}