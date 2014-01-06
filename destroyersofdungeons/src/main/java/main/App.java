package main;

import GUI.SwingGUI;
import localisation.Dictionary;

enum App {

    ;
    @SuppressWarnings({"UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch"})
    public static void main(String[] args) {
        final SwingGUI sgui;
        try {
            Dictionary.loadText("english");
            sgui = new SwingGUI();
        } catch (Exception ex) {
            System.exit(-1);
            return;
        }
        sgui.start();
    }
}
