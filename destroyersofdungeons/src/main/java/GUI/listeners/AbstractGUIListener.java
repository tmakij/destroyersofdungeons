package GUI.listeners;

import GUI.SwingGUI;
import java.awt.event.ActionListener;

/**
 *
 */
abstract class AbstractGUIListener implements ActionListener {

    protected final SwingGUI gui;

    public AbstractGUIListener(SwingGUI gui) {
        this.gui = gui;
    }
}
