package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.MainPanel;
import java.awt.event.ActionEvent;

/**
 * Listen start turn button. On click it switches to MainPanel.
 */
public final class TurnBeginListener extends AbstractGUIListener {

    /**
     * Creates a new TurnBeginListener.
     *
     * @param gui SwingGUI that handles the game.
     */
    public TurnBeginListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPanel(new MainPanel(gui));
    }
}
