package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.MainMenuPanel;
import java.awt.event.ActionEvent;

/**
 * Listener which only sets the game back into main menu.
 */
public final class GoToMainMenuListener extends AbstractGUIListener {

    /**
     * Creates a new GoToMainMenuListener.
     *
     * @param gui SwingGUI that handles the game.
     */
    public GoToMainMenuListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPanel(new MainMenuPanel(gui));
    }
}
