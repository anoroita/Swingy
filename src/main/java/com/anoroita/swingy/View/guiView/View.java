package com.anoroita.swingy.View.guiView;

import com.anoroita.swingy.Controller.GameController;
import com.anoroita.swingy.Controller.PlaygroundGenerator;
import com.anoroita.swingy.Database.DatabaseConfig;
import com.anoroita.swingy.Model.GameCharacter;

/**
 * View
 */

public class View extends GameController {

    public View(GameCharacter hero, GameViewGUI gameViewGUI) {
        this.hero = hero;
        this.gui = true;
        this.gameViewGUI = gameViewGUI;
    }

    @Override
    public void runGame() {
        if (hero == null) {
            System.out.println("No hero set");
            System.exit(1);
        }
        gameViewGUI.pushMessage(hero.getName() + " arrived in a new hostile environment");
        gamePlayground = PlaygroundGenerator.generatePlayground(hero);
    }

    public void updateHeroPosition() {
        gamePlayground.updateHeroPosition();
        DatabaseConfig.updateHero(hero);
    }

}