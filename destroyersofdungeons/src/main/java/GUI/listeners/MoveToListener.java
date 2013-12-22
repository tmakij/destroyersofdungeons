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

    private final int moveTo;

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
