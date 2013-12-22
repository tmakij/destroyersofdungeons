package GUI.listeners;

import GUI.SwingGUI;
import java.awt.event.ActionEvent;

/**
 * Listen quit game button on main menu. Terminates the game on click.
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
