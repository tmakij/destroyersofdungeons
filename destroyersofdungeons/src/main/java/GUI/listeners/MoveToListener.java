package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.MoveResultPanel;
import java.awt.event.ActionEvent;

/**
 * Listen the move button on main panel. On click, it moves the player into the
 * tunnel which has been attached to this listener and switches to the
 * moveresult panel.
 */
public final class MoveToListener extends AbstractGUIListener {

    /**
     * The number of the tunnel where to move.
     */
    private final int moveTo;

    /**
     * Creates a new MoveToListener.
     *
     * @param gui SwingGUI that handles the game.
     * @param moveTo The tunnel where to move. Will be picked from the player's
     * block's nextTo list of tunnels.
     */
    public MoveToListener(SwingGUI gui, int moveTo) {
        super(gui);
        this.moveTo = moveTo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getGame().movePlayerTo(moveTo);
        gui.setPanel(new MoveResultPanel(gui));
    }
}
