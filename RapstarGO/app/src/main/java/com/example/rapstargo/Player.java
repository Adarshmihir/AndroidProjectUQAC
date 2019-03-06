package com.example.rapstargo;

public class Player {
    private String name;
    private int level;

    Player(String name, int xp) {
        this.name = name;
        this.level = xp;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return level;
    }
}
