package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.SetPlayerCountPanel;
import java.awt.event.ActionEvent;

/**
 * Listen start game button on main menu. Switches to player count selection on
 * click. Calls the SwingGUI to create a new game.
 */
public final class MainMenuStartListener extends AbstractGUIListener {

    /**
     * Creates a new MainMenuStartListener.
     *
     * @param gui SwingGUI that handles the game.
     */
    public MainMenuStartListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.createNewGame();
        gui.setPanel(new SetPlayerCountPanel(gui));
    }
}
