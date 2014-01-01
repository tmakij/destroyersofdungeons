package GUI.listeners;

import GUI.SwingGUI;
import java.awt.event.ActionListener;

/**
 * The base class for all GUIListeners, implements ActionListener.
 */
abstract class AbstractGUIListener implements ActionListener {

    /**
     * The SwingGUI which runs the game.
     */
    protected final SwingGUI gui;

    /**
     * Sets the SwingGUI field.
     *
     * @param gui The SwingGUI which runs the game.
     */
    protected AbstractGUIListener(SwingGUI gui) {
        this.gui = gui;
    }
}
