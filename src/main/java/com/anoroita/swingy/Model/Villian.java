package com.anoroita.swingy.Model;

public class Villian extends GameCharacter{

    public Villian() {

        this.setAttack(20.0f);
        this.setDefense(20.0f);
        this.setHealth(10.0f);
        this.setLevel(1);
        this.setHitPoints(50.0f);
        this.setExperience(0.0f);
        this.setName("Enemy");
        this.setHeroType("Villian");
    }

}
