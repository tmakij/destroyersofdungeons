package GUI.panels;

import GUI.SwingGUI;
import gameobjects.actors.Actor;
import gameobjects.dungeon.Tunnel;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import localisation.Dictionary;
import logic.DestroyersOfDungeons;

/**
 *
 */
public final class MoveResultPanel extends AbstractPanel {

    public MoveResultPanel(SwingGUI gui) {
        DestroyersOfDungeons game = gui.getGame();
        if (game.lastMoveCreatedCollisions()) {
            collisions(game);
        } else {
            noCollisions(game, gui);
        }
    }

    private void collisions(DestroyersOfDungeons game) {
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        Tunnel t = game.getCurrentPlayer().getMyBlock();
        List<Actor> others = t.getOtherActors(game.getCurrentPlayer());
        if (!others.isEmpty()) {
            JLabel youCollided = new JLabel(Dictionary.getValue("YOU_COLLIDED"));
            panel.add(youCollided);
            for (int i = 0; i < others.size(); i++) {
                JLabel name = new JLabel(others.get(i).toString());
                panel.add(name);
            }
        }
    }

    private void noCollisions(DestroyersOfDungeons game, SwingGUI gui) {
        game.nextPlayer();
        gui.setPanel(null);
    }
}
