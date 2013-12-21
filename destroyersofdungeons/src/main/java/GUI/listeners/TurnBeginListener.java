package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.MainPanel;
import java.awt.event.ActionEvent;

/**
 *
 */
public final class TurnBeginListener extends AbstractGUIListener {

    public TurnBeginListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPanel(new MainPanel(gui));
    }
}
