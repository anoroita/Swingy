package com.anoroita.swingy.Model;

public class Watchman extends GameCharacter{

    public Watchman(String name) {
        this.setAttack(30.0f);
        this.setDefense(15.0f);
        this.setHealth(150.0f);
        this.setLevel(1);
        this.setHitPoints(150.0f);
        this.setExperience(0.0f);
        this.setName(name);
        this.setHeroType("Watchman");
    }
}
