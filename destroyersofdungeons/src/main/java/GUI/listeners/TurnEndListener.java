package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.TurnPanel;
import java.awt.event.ActionEvent;

/**
 * Listen to the ok button in the moveresult panel. Switches turn and the panel
 * to TurnPanel.
 */
public final class TurnEndListener extends AbstractGUIListener {

    /**
     * Creates a new TurnEndListener.
     *
     * @param gui SwingGUI that handles the game.
     */
    public TurnEndListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getGame().nextPlayer();
        gui.setPanel(new TurnPanel(gui));
    }
}
