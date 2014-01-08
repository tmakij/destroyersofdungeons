package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.EndGamePanel;
import GUI.panels.TurnPanel;
import gameobjects.actors.Player;
import java.awt.event.ActionEvent;
import logic.DestroyersOfDungeons;

/**
 * Listens the resume button when finding items. Makes the actor to pick all
 * items from the tunnel and changes turn.
 */
public final class FoundItemsListener extends AbstractGUIListener {

    /**
     * Creates a new FoundItemsListener.
     *
     * @param gui SwingGUI that handles the game.
     */
    public FoundItemsListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DestroyersOfDungeons game = gui.getGame();
        game.getCurrentPlayer().pickUpItems();
        Player p = game.getWinner();
        if (p == null) {
            game.nextPlayer();
            gui.setPanel(new TurnPanel(gui));
        } else {
            gui.setPanel(new EndGamePanel(gui));
        }
    }
}
