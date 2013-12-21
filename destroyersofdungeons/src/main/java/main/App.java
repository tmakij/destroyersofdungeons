package main;

import GUI.SwingGUI;
import localisation.Dictionary;

final class App {

    public static void main(String[] args) {
        Dictionary.loadText("english");
        SwingGUI sgui = new SwingGUI();
    }
}
