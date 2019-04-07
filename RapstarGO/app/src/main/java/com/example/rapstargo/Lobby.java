package com.example.rapstargo;

public class Lobby {
    private String name;
    private String owner;

    Lobby(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() { return this.name; }

    public String getOwner() { return this.owner; }
}