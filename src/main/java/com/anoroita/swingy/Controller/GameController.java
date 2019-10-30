package com.anoroita.swingy.Controller;

import java.util.Random;

import com.anoroita.swingy.Model.Villian;
import com.anoroita.swingy.Model.GameCharacter;
import com.anoroita.swingy.Model.Playground;
import com.anoroita.swingy.View.consoleView.display;
import com.anoroita.swingy.View.guiView.GameViewGUI;

public abstract class GameController {

    private final static int NORTH = 1;
    private final static int EAST = 2;
    private final static int SOUTH = 3;
    private final static int WEST = 4;
    private static int[] oldMove = new int[2];

    protected GameCharacter hero;
    protected GameCharacter Villian;
    protected Playground gamePlayground;
    protected boolean gui = false;
    protected GameViewGUI gameViewGUI = null;

    public abstract void runGame();

    public boolean winCondition() {
        if (hero.getX() == gamePlayground.getPlaygroundSize() - 1 ||
                hero.getY() == gamePlayground.getPlaygroundSize() - 1 ||
                hero.getX() == 0 || hero.getY() == 0) {
            if (gui == true) {
                gameViewGUI.showMessage("You reached your goal!!");
            } else {
                System.out.println("You reached your goal!!");
            }

            return false;
        }
        return true;
    }

    public void fight(boolean fled) {
        if (fled) {
            Assorted.Log("Enemy starts !", gui, gameViewGUI);
            while (hero.getHitPoints() > 0 && Villian.getHitPoints() > 0) {
                Assorted.Log(Villian.attack(hero), gui, gameViewGUI);
                Assorted.Log(Villian.attack(hero), gui, gameViewGUI);
                if (hero.getHitPoints() > 0) {
                    Assorted.Log(hero.attack(Villian), gui, gameViewGUI);
                }
            }
        } else {
            Assorted.Log(hero.getName() + " starts!", gui, gameViewGUI);
            while (hero.getHitPoints() > 0 && Villian.getHitPoints() > 0) {
                Assorted.Log(hero.attack(Villian), gui, gameViewGUI);
                if (Villian.getHitPoints() > 0) {
                    Assorted.Log(Villian.attack(hero), gui, gameViewGUI);
                }
            }
        }
        if (hero.getHitPoints() <= 0) {
            hero.setHitPoints(0);
            if (gui == true) {
                gameViewGUI.showMessage("Game Over");
                gameViewGUI.gameFinished();
            } else {
                System.out.println("Game Over");
                System.exit(0);
            }



        } else if (Villian.getHitPoints() <= 0) {
            hero.setPosition(0, 0);
            if (gui == true) {
                gameViewGUI.showMessage("You win!");
            } else {
                System.out.println("You win!");
            }

            hero.setExperience(hero.getExperience() + 100);
            hero.setExperience(hero.getHitPoints() + 10);
        }
        if (hero.getExperience() >= (hero.getLevel() * 1000 + Math.pow(hero.getLevel() - 1, 2) * 450)) {
            hero.setLevel(hero.getLevel() + 1);
            hero.setAttack(hero.getAttack() + 2);
            hero.setHitPoints(hero.getHitPoints() + 100);
            hero.setExperience(hero.getExperience() + 200);

            Assorted.Log("Hero leveled up!\nhp + 100\nxp + 200", gui, gameViewGUI);
        }
    }

    public void run() {
        int random = new Random().nextInt(2);
        switch (random) {
            case 0:
                Assorted.Log("Escape failed!", gui, gameViewGUI);
                fight(true);
                break;
            case 1:
                Assorted.Log("You fled from battle.", gui, gameViewGUI);
                hero.setPosition(oldMove[0] * -1, oldMove[1] * -1);
                break;
        }
    }

    private void fightOrRun() {
        display.printFightOptions();
        while (Assorted.scan.hasNextLine()) {
            String arg = Assorted.scan.next();
            if (arg.matches("\\s*[1-2]\\s*")) {
                Integer nb = Integer.parseInt(arg.trim());
                switch (nb) {
                    case 1:
                        fight(false);
                        return;
                    case 2:
                        run();
                        return;
                }
            } else {
                System.out.println(Playground.ANSI_RED + ">" + Playground.ANSI_RESET + " Incorrect choice");
                display.printFightOptions();
            }
        }
    }

    public void move(int direction) {
        switch (direction) {
            case NORTH:
                hero.setPosition(-1, 0);
                oldMove[0] = -1;
                oldMove[1] = 0;
                break;
            case EAST:
                hero.setPosition(0, 1);
                oldMove[0] = 0;
                oldMove[1] = 1;
                break;
            case SOUTH:
                hero.setPosition(1, 0);
                oldMove[0] = 1;
                oldMove[1] = 0;
                break;
            case WEST:
                hero.setPosition(0, -1);
                oldMove[0] = 0;
                oldMove[1] = -1;
                break;
            case 5:
                System.out.println(hero.toString());
        }
        if (gamePlayground.getPlayground()[hero.getX()][hero.getY()] == 2) {
            Villian = new Villian();
            Villian.setLevel(hero.getLevel());
            if (gui == false) {
                gamePlayground.updateHeroPosition();
                gamePlayground.printPlayground();

                System.out.println("Enemy encounter : \"" + Villian.getName() + "\" level " + Villian.getLevel() + " !");
                fightOrRun();
            }
            else if (gui == true){

                if (gameViewGUI != null) {
                    gameViewGUI.pushMessage("Enemy encounter : \"" + Villian.getName() + "\" level " + Villian.getLevel() + " !");
                    gameViewGUI.getVillainCollisionInput();
                }
            }
        }
    }
}
