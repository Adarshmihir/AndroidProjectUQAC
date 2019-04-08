package com.example.rapstargo;

import java.util.List;

public class Character {

    private int id;
    private float current_life;
    private String name;
    private int level;
    private String class_name;
    private String user_id;
    private boolean alive;
    private List<Ability> abilities;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public float getCurrent_life() {
        return current_life;
    }

    public void setCurrent_life(float current_life) {
        this.current_life = current_life;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    @Override
    public String toString() {
        return getId() + " " + getName() + " " + getClass_name() + " " + getLevel() + " Life : " + getCurrent_life() + " alive : " + isAlive()/* + " " + getAbility1().toString() + " " + getAbility2().toString()*/;
    }



}
