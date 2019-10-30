package com.anoroita.swingy.Model;

import java.util.Random;

/**
 * Playground
 */

public class Playground {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private int[][] Playground;
    public int PlaygroundSize;
    private GameCharacter hero;
    private int[] oldPos = new int[] { -1, -1};;

    public Playground(int PlaygroundSize) {
        this.PlaygroundSize = PlaygroundSize;
        this.Playground = new int[PlaygroundSize][PlaygroundSize];
    }

    public void updateHeroPosition() {
        this.Playground[oldPos[0]][oldPos[1]] = 0;
        oldPos[0] = hero.getX();
        oldPos[1] = hero.getY();
        if (this.Playground[hero.getX()][hero.getY()] == 2) {
            this.Playground[hero.getX()][hero.getY()] = 8;
        } else {
            this.Playground[hero.getX()][hero.getY()] = 1;
        }
    }

    public void registerHero(GameCharacter hero) {
        this.hero = hero;
        //this.hero.register(this);
        this.hero.setX(PlaygroundSize / 2);
        this.hero.setY(PlaygroundSize / 2);
        oldPos[0] = this.hero.getX();
        oldPos[1] = this.hero.getY();
        this.Playground[PlaygroundSize / 2][PlaygroundSize / 2] = 1;
    }

    public void generateVillians() {
        for (int i = 0; i < PlaygroundSize; i++) {
            for (int j = 0; j < PlaygroundSize; j++) {
                if (Playground[i][j] != 1) {
                    int random = new Random().nextInt(3);
                    if (random == 0) {
                        Playground[i][j] = 2;
                    }
                }
            }
        }
    }

    public void printPlayground() {
        for (int[] line : Playground) {
            for (int col : line) {
                String box = col + " ";
                switch (col) {
                    case 1:
                        System.out.print(ANSI_GREEN + box + ANSI_RESET);
                        break;
                    case 2:
                        System.out.print(ANSI_RED + box + ANSI_RESET);
                        break;
                    case 8:
                        System.out.print(ANSI_YELLOW + box + ANSI_RESET);
                        break;
                    default:
                        System.out.print(box);
                        break;
                }
            }
            System.out.println();
        }
    }


    /**
     * @return the Playground
     */
    public int[][] getPlayground() {
        return Playground;
    }

    /**
     * @return the PlaygroundSize
     */
    public int getPlaygroundSize() {
        return PlaygroundSize;
    }
}