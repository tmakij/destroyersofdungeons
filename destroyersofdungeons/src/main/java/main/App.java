package main;

import GUI.SwingGUI;
import gameobjects.actors.monsters.Monster;
import localisation.Dictionary;

enum App {

    ;
    @SuppressWarnings({"UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch"})
    public static void main(String[] args) {
        try {
            Dictionary.loadText("finnish");
            Monster.loadRaces();
            SwingGUI sgui = new SwingGUI();
            sgui.start();
        } catch (Exception ex) {
        }
    }
}
