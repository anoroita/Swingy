package com.anoroita.swingy.Controller;

import com.anoroita.swingy.Model.GameCharacter;
import com.anoroita.swingy.Model.Playground;

/**
 * PlaygroundGenerator
 */

public class PlaygroundGenerator {

    public static Playground generatePlayground(GameCharacter hero) {
            int PlaygroundSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);

            if (PlaygroundSize > 19) {
                PlaygroundSize = 19;
            }
            Playground squarePlayground = new Playground(PlaygroundSize);
            squarePlayground.registerHero(hero);
            System.out.println(hero.getName() + " arrived in a new hostile environment");
            squarePlayground.generateVillians();
            return (squarePlayground);
    }
}
