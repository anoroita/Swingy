package com.anoroita.swingy.Controller;

import com.anoroita.swingy.Database.DatabaseConfig;
import com.anoroita.swingy.Model.GameCharacter;
import com.anoroita.swingy.Model.Watchman;
import com.anoroita.swingy.Model.Playground;
import com.anoroita.swingy.Model.GhostRider;

public class GameCharacterGenerator {

    public GameCharacter createHero(String name, int type) throws Exception {

        GameCharacter hero;

        try {
            switch(type) {
                case 1:
                    hero = new GhostRider(name);
                    break ;
                case 2:
                    hero = new Watchman(name);
                    break ;
                default:
                    hero = null;
                    break ;
            }
            hero.validateHero();

        } catch (Exception e) {
            throw new Exception(e.toString());
        }

        int id = DatabaseConfig.insert(hero.getName(), hero.getHeroType(), (int) hero.getLevel(), (int) hero.getExperience(), (int) hero.getAttack(), (int) hero.getDefense(), (int) hero.getHitPoints());
        hero.setId(id);
        return hero;


    }


    public GameCharacter getAllHeroes() {

        GameCharacter hero = null;
        boolean loop = true;

        System.out.println("Available heroes:");
        int i = 0;
        for (String e: DatabaseConfig.selectAll()){
            System.out.println(Playground.ANSI_GREEN + e + Playground.ANSI_RESET);
            i++;
        }
        if (i == 0) {
            System.out.println(Playground.ANSI_RED + "No heroes are available, Please create one" + Playground.ANSI_RESET);
            return hero;
        }
        while (loop) {
            System.out.print("Enter your hero by ID: ");
            int choice = Assorted.scan.nextInt();
            hero = DatabaseConfig.selectHeroById(choice);

            if (hero != null) {
                System.out.println(Playground.ANSI_GREEN + hero.getName() + "'s " + Playground.ANSI_RESET + "stats:\n"+hero.toString());
                System.out.println("Do you accept " + Playground.ANSI_GREEN + hero.getName() + Playground.ANSI_RESET + " as your hero?\n1 - Yes\n2 - No");
                int accept = Assorted.scan.nextInt();
                if (accept == 1) {
                    loop = false;
                }
            }
        }

        return hero;
    }
}
