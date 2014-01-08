package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.SettingsPanel;
import java.awt.event.ActionEvent;

/**
 * Listens settings button. Switches to SettingsPanel on click.
 */
public final class SettingsListener extends AbstractGUIListener {

    /**
     * Creates a new SettingsListener.
     *
     * @param gui SwingGUI that handles the game.
     */
    public SettingsListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPanel(new SettingsPanel(gui));
    }
}
