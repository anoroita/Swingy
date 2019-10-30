package com.anoroita.swingy;

import com.anoroita.swingy.View.consoleView.PlayConsole;
import com.anoroita.swingy.View.guiView.PlayGui;

public class Swingy {

    public static void main( String[] args )
    {
        try {
            switch (args[0]) {
                case "console":
                    PlayConsole consoleGame = new PlayConsole();
                    consoleGame.runGame();
                    break;
                case "gui":
                    PlayGui guiGame = new PlayGui();
                    guiGame.runGame();
                    break;
                default:
                    System.out.println("Usage: java -jar target/swingy [console/gui]");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Usage: java -jar target/swingy [console/gui]");
        }
    }
}
