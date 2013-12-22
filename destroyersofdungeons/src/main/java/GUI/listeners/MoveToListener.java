package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.MoveResultPanel;
import java.awt.event.ActionEvent;

/**
 *
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
