package GUI.listeners;

import GUI.SwingGUI;
import java.awt.event.ActionEvent;

/**
 *
 */
public final class MainMenuQuitListener extends AbstractGUIListener {

    public MainMenuQuitListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.dispose();
    }
}
