package main;

import GUI.SwingGUI;
import gameobjects.actors.monsters.Monster;
import java.io.IOException;
import localisation.Dictionary;

enum App {

    ;

    public static void main(String[] args) {
        try {
            Dictionary.loadText("english");
        } catch (IOException | UnsupportedOperationException ex) {
        }
        Monster.loadRaces();
        SwingGUI sgui = new SwingGUI();
        sgui.start();
    }
}
