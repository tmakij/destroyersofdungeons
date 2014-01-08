package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.PlayerNamePanel;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

/**
 * On click starts creates the PlayerNamePanel, and gives the JSpinner value as
 * parameter.
 */
public final class SetPlayerCountListener extends AbstractGUIListener {

    /**
     * The player count.
     */
    private final JSpinner count;

    /**
     * Creates a new PlayerNameListener.
     *
     * @param gui SwingGUI that handles the game.
     * @param count The JSpinner where to take the player count.
     */
    public SetPlayerCountListener(SwingGUI gui, JSpinner count) {
        super(gui);
        this.count = count;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPanel(new PlayerNamePanel(gui, (int) count.getValue()));
    }
}
