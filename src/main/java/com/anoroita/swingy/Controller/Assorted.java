package com.anoroita.swingy.Controller;

import java.util.Scanner;

import com.anoroita.swingy.View.guiView.GameViewGUI;

/**
 * Assorted
 */

public class Assorted {

    public static Scanner scan = new Scanner(System.in);


    public static void closeScanner() {
        scan.close();
    }

    public static void Log(String message, Boolean gui, GameViewGUI gameViewGUI) {
        if (gui == true) {
            gameViewGUI.pushMessage(message);
        } else {
            System.out.println(message);
        }
    }
}
