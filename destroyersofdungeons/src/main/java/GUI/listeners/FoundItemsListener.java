package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.TurnPanel;
import java.awt.event.ActionEvent;

/**
 * Listens the resume button when finding items. Makes the actor to pick all
 * items from the tunnel and changes turn.
 */
public final class FoundItemsListener extends AbstractGUIListener {

    public FoundItemsListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getGame().getCurrentPlayer().pickUpItems();
        gui.getGame().nextPlayer();
        gui.setPanel(new TurnPanel(gui));
    }
}
