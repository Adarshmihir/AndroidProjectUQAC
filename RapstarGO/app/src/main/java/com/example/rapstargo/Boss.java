package com.example.tdufo.sockettry;

public class Boss {
    private int life;
    private int damage_per_attack;
    private int cooldown_value;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getDamage_per_attack() {
        return damage_per_attack;
    }

    public void setDamage_per_attack(int damage_per_attack) {
        this.damage_per_attack = damage_per_attack;
    }

    public int getCooldown_value() {
        return cooldown_value;
    }

    public void setCooldown_value(int cooldown_value) {
        this.cooldown_value = cooldown_value;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "life=" + life +
                ", damage_per_attack=" + damage_per_attack +
                ", cooldown_value=" + cooldown_value +
                '}';
    }
}
