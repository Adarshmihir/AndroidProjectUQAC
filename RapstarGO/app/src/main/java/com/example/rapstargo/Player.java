package com.example.rapstargo;

public class Player {
    private String name;
    private int xp;

    Player(String name, int xp) {
        this.name = name;
        this.xp = xp;
    }

    public String getName() {
        return this.name;
    }

    public int getXp() {
        return xp;
    }
}
