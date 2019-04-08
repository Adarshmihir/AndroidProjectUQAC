package com.example.rapstargo;

public class Ability {
    private int id;
    private String name;
    private int effect;
    private float effectMultiplier;
    private int lastTimeUsed;
    private int cooldown;

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

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public float getEffectMultiplier() {
        return effectMultiplier;
    }

    public void setEffectMultiplier(float effectMultiplier) {
        this.effectMultiplier = effectMultiplier;
    }

    public int getLastTimeUsed() {
        return lastTimeUsed;
    }

    public void setLastTimeUsed(int lastTimeUsed) {
        this.lastTimeUsed = lastTimeUsed;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", effect=" + effect +
                ", effectMultiplier=" + effectMultiplier +
                ", lastTimeUsed=" + lastTimeUsed +
                ", cooldown=" + cooldown +
                '}';
    }
}
