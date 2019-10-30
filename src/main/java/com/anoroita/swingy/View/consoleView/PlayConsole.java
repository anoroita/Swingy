package com.anoroita.swingy.View.consoleView;

import com.anoroita.swingy.Controller.GameCharacterGenerator;
import com.anoroita.swingy.Controller.GameController;
import com.anoroita.swingy.Controller.PlaygroundGenerator;
import com.anoroita.swingy.Controller.Assorted;
import com.anoroita.swingy.Database.DatabaseConfig;
import com.anoroita.swingy.Model.GameCharacter;
import com.anoroita.swingy.Model.Playground;

/**
 * Play
 */

public class PlayConsole extends GameController {

    private boolean running = false;
    public PlayConsole() {

    }

    @Override
    public void runGame() {
        int choice = 0;
        getOrCreateHero();

        if (hero == null) {
            System.out.println("No hero set");
            System.exit(1);
        }
        running = true;

        gamePlayground = PlaygroundGenerator.generatePlayground(hero);
        while (running) {
            gamePlayground.printPlayground();
            running = winCondition();
            if (running == false) {
                System.exit(0);
            }
            display.printDirections();
            while (Assorted.scan.hasNextLine()) {
                String arg = Assorted.scan.next();
                if (arg.matches("\\s*[1-5]\\s*")) {
                    choice = Integer.parseInt(arg.trim());
                    break ;
                } else {
                    System.out.println(Playground.ANSI_RED + ">" + Playground.ANSI_RESET + " Incorrect choice");
                }
            }
            move(choice);
            gamePlayground.updateHeroPosition();
            DatabaseConfig.updateHero(hero);
        }

    }

    private void getOrCreateHero() {
        GameCharacterGenerator factoryChar = new GameCharacterGenerator();

        System.out.println("1 - Create Hero\n2 - Choose Existing Hero");
        int choice = 0;
        while (Assorted.scan.hasNextLine()) {
            String arg = Assorted.scan.next();
            if (arg.matches("\\s*[1-2]\\s*")) {
                choice = Integer.parseInt(arg.trim());
                break ;
            } else {
                System.out.println(Playground.ANSI_RED + ">" + Playground.ANSI_RESET + " Incorrect choice");
            }
        }
        switch(choice) {
            case 1:
                String name;
                int type = 0;
                System.out.println("Select Hero Type:\n1 - GhostRider\n2 - Watchman");
                while (Assorted.scan.hasNextLine()) {
                    String arg = Assorted.scan.next();
                    if (arg.matches("\\s*[1-2]\\s*")) {
                        type = Integer.parseInt(arg.trim());
                        break ;
                    } else {
                        System.out.println(Playground.ANSI_RED + ">" + Playground.ANSI_RESET + " Incorrect choice");
                    }
                }

                System.out.print("Give your hero a name: ");
                name = Assorted.scan.next();

                try {
                    setHero(factoryChar.createHero(name, type));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                setHero(factoryChar.getAllHeroes());
                break;
            default:
                break;
        }
    }

    /**
     * @param hero the hero to set
     */
    public void setHero(GameCharacter hero) {
        this.hero = hero;
    }

    /**
     * @return the hero
     */
    public GameCharacter getHero() {
        return hero;
    }
}