package main;

import GUI.SwingGUI;
import gameobjects.actors.monsters.Monster;
import gameobjects.items.Item;
import localisation.Dictionary;

enum App {

    ;
    @SuppressWarnings({"UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch"})
    public static void main(String[] args) {
        final SwingGUI sgui;
        try {
            Dictionary.loadText("finnish");
            Monster.loadRaces();
            Item.loadItemTypes();
            sgui = new SwingGUI();
        } catch (Exception ex) {
            System.exit(-1);
            return;
        }
        sgui.start();
    }
}
