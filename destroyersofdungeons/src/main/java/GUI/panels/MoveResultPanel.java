package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.TurnEndListener;
import gameobjects.actors.Actor;
import gameobjects.dungeon.Tunnel;
import java.awt.Component;
import java.util.List;
import javax.swing.JButton;
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
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        if (game.lastMoveCreatedCollisions()) {
            collisions(game);
        } else {
            noCollisions(game, gui, layout);
        }
    }

    private void collisions(DestroyersOfDungeons game) {
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

    private void noCollisions(DestroyersOfDungeons game, SwingGUI gui, SpringLayout layout) {
        addYouMoved(game, layout);
        addOK(gui, layout);
    }

    private void addOK(SwingGUI gui, SpringLayout layout) {
        Component youMovedTo = panel.getComponents()[panel.getComponents().length - 1];
        JButton ok = new JButton(Dictionary.getValue("OK"));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, ok,
                0,
                SpringLayout.HORIZONTAL_CENTER, youMovedTo);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, ok,
                35,
                SpringLayout.VERTICAL_CENTER, youMovedTo);
        ok.addActionListener(new TurnEndListener(gui));
        panel.add(ok);
    }

    private void addYouMoved(DestroyersOfDungeons game, SpringLayout layout) {
        JLabel youMovedTo = new JLabel(Dictionary.getValue("YOU_MOVED_TO") + game.getCurrentPlayer().getMyBlock());
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, youMovedTo,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, youMovedTo,
                0,
                SpringLayout.VERTICAL_CENTER, panel);
        panel.add(youMovedTo);
    }
}
