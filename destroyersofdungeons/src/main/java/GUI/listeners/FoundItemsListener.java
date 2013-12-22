package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.TurnPanel;
import gameobjects.actors.Player;
import gameobjects.dungeon.Tunnel;
import java.awt.event.ActionEvent;

/**
 * Listens the resume button when finding items. Moves all items from the tunnel
 * into the actor and changes turn.
 */
public final class FoundItemsListener extends AbstractGUIListener {

    public FoundItemsListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Player p = gui.getGame().getCurrentPlayer();
        Tunnel myBlock = p.getMyBlock();
        p.addItems(myBlock.getItems());
        myBlock.removeAllItems();
        gui.getGame().nextPlayer();
        gui.setPanel(new TurnPanel(gui));
    }
}
