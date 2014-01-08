package GUI.listeners;

import GUI.SwingGUI;
import java.awt.event.ActionEvent;

/**
 * Listen quit game button on main menu. Calls SwingGUI dispose on click.
 */
public final class MainMenuQuitListener extends AbstractGUIListener {

    /**
     * Creates a new MainMenuQuitListener.
     *
     * @param gui SwingGUI that handles the game.
     */
    public MainMenuQuitListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.dispose();
    }
}
