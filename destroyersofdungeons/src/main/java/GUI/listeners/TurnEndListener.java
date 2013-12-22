package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.TurnPanel;
import java.awt.event.ActionEvent;

/**
 *
 */
public final class TurnEndListener extends AbstractGUIListener {
    
    public TurnEndListener(SwingGUI gui) {
        super(gui);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getGame().nextPlayer();
        gui.setPanel(new TurnPanel(gui));
    }
}
