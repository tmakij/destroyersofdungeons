package main;

import GUI.SwingGUI;
import localisation.Dictionary;

enum App {

    ;
    public static void main(String[] args) {
        Dictionary.loadText("english");
        final SwingGUI sgui = new SwingGUI();
        sgui.start();
    }
}
