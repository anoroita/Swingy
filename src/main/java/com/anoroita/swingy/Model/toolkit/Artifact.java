package com.anoroita.swingy.Model.toolkit;

public class Artifact {

    private int points;
    protected String name;

    //Contstructor
    public Artifact(String name, int points) {
        this.name = name;
        this.points = points;
    }

    //GetsPonits
    public int getPoints() {
        return points;
    }

    //GetsName
    public String getName() {
        return name;
    }

    //Overrides toString method
    @Override
    public String toString() {
        return name + " (+" + points + ")";
    }
}
