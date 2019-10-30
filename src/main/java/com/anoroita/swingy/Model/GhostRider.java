package com.anoroita.swingy.Model;

public class GhostRider extends GameCharacter {

    public GhostRider(String name) {
        this.setAttack(40.0f);
        this.setDefense(10.0f);
        this.setHealth(90.0f);
        this.setLevel(1);
        this.setHitPoints(90.0f);
        this.setExperience(0.0f);
        this.setHeroType("GhostRider");
        this.setName(name);
    }
}
