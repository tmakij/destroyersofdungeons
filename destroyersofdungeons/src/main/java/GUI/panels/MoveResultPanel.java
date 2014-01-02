package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.AttackActorListener;
import GUI.listeners.FoundItemsListener;
import GUI.listeners.TurnEndListener;
import gameobjects.GameObject;
import gameobjects.actors.Actor;
import gameobjects.dungeon.Tunnel;
import gameobjects.items.Item;
import java.awt.Component;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import localisation.Dictionary;
import logic.DestroyersOfDungeons;

/**
 * Is created when the actor moves. Shows the results of the move, such as found
 * items and enemies.
 */
public final class MoveResultPanel extends AbstractPanel {

    /**
     * Create a new instance of the MoveResultPanel.
     *
     * @param gui The SwingGUI which holds the program.
     */
    public MoveResultPanel(SwingGUI gui) {
        super(gui);
        DestroyersOfDungeons game = gui.getGame();
        if (game.lastMoveCreatedCollisions()) {
            collisions(game);
        } else {
            noCollisions(game);
        }
    }

    private void collisions(DestroyersOfDungeons game) {
        Tunnel t = game.getCurrentPlayer().getMyBlock();
        List<Actor> others = t.getOtherActors(game.getCurrentPlayer());
        if (!others.isEmpty()) {
            foundEnemies(others);
        } else {
            foundItems(game);
        }
    }

    private void foundItems(DestroyersOfDungeons game) {
        addEventResult("FOUND_ITEMS");
        List<Item> items = game.getCurrentPlayer().getMyBlock().getItems();
        for (int i = 0; i < items.size(); i++) {
            addEventResultObject(items.get(i), i);
        }
        addResume();
    }

    private void addResume() {
        JButton resume = new JButton(Dictionary.getValue("FOUND_ITEMS_RESUME"));
        Component lastItem = getLastComponent();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, resume,
                25,
                SpringLayout.HORIZONTAL_CENTER, lastItem
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, resume,
                25,
                SpringLayout.VERTICAL_CENTER, lastItem
        );
        resume.addActionListener(new FoundItemsListener(gui));
        panel.add(resume);
    }

    private void foundEnemies(List<Actor> others) {
        addEventResult("YOU_COLLIDED");
        for (int i = 0; i < others.size(); i++) {
            Actor enemy = others.get(i);
            addEventResultObject(enemy, i);
            addAttack(enemy);
        }
    }

    private void addAttack(Actor enemy) {
        Component name = getLastComponent();
        JButton attack = new JButton(Dictionary.getValue("ATTACK"));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, attack,
                75,
                SpringLayout.HORIZONTAL_CENTER, name
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, attack,
                0,
                SpringLayout.VERTICAL_CENTER, name
        );
        attack.addActionListener(new AttackActorListener(gui, enemy));
        panel.add(attack);
    }

    private void addEventResultObject(GameObject obj, int iter) {
        JLabel name = new JLabel(obj.toString());
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, name,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, name,
                iter * 20,
                SpringLayout.VERTICAL_CENTER, panel
        );
        panel.add(name);
    }

    private void addEventResult(String key) {
        JLabel youCollided = new JLabel(Dictionary.getValue(key));
        panel.add(youCollided);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, youCollided,
                -35,
                SpringLayout.HORIZONTAL_CENTER, panel
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, youCollided,
                -35,
                SpringLayout.VERTICAL_CENTER, panel
        );
    }

    private void noCollisions(DestroyersOfDungeons game) {
        addYouMoved(game);
        addOK();
    }

    private void addOK() {
        Component youMovedTo = getLastComponent();
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

    private void addYouMoved(DestroyersOfDungeons game) {
        JLabel youMovedTo = new JLabel(Dictionary.getValue("YOU_MOVED_TO", game.getCurrentPlayer().getMyBlock()));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, youMovedTo,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, youMovedTo,
                0,
                SpringLayout.VERTICAL_CENTER, panel);
        panel.add(youMovedTo);
    }
}
