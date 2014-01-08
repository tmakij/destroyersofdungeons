package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.BattleActionListener;
import gameobjects.actors.Actor;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import localisation.Dictionary;
import logic.Battle;
import logic.BattleAction;
import logic.IUpdate;

/**
 * The panel that handles all the battles between a player and another actor.
 * Implements IUpdate. Update method should be called whenever turn or actors'
 * healths are changed.
 */
public final class BattlePanel extends AbstractPanel implements IUpdate {

    private final Battle battle;
    private final JLabel attackerStatus = new JLabel();
    private final JLabel defenderStatus = new JLabel();
    private final JLabel turn = new JLabel();

    public BattlePanel(SwingGUI gui, Actor defender) {
        super(gui);
        battle = new Battle(gui.getGame().getCurrentPlayer(), defender, this);
        setStatusFields();
        setTurn();

        listActions();

        BattleAction[] actions = BattleAction.values();
        for (BattleAction action : actions) {
            createAction(action);
        }
    }

    private void setStatusFields() {
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, attackerStatus,
                -125,
                SpringLayout.HORIZONTAL_CENTER, panel
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, attackerStatus,
                50,
                SpringLayout.NORTH, panel
        );
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, defenderStatus,
                0,
                SpringLayout.HORIZONTAL_CENTER, attackerStatus
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, defenderStatus,
                50,
                SpringLayout.VERTICAL_CENTER, attackerStatus
        );
        panel.add(attackerStatus);
        panel.add(defenderStatus);
        update();
    }

    private void setTurn() {
        Component last = getLastComponent();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, turn,
                0,
                SpringLayout.HORIZONTAL_CENTER, last);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, turn,
                40,
                SpringLayout.VERTICAL_CENTER, last);
        panel.add(turn);
    }

    @Override
    public void update() {
        setStatus(attackerStatus, battle.getAttacker());
        setStatus(defenderStatus, battle.getDefender());
        turn.setText(Dictionary.getValue("CURRENT_TURN", battle.getCurrent()));
    }

    private void setStatus(JLabel label, Actor a) {
        label.setText(Dictionary.getValue("HEALTH", a, a.getHealth()));
    }

    private void listActions() {
        JLabel allActions = new JLabel(Dictionary.getValue("POSSIBLE_ACTS"));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, allActions,
                -75,
                SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, allActions,
                50,
                SpringLayout.VERTICAL_CENTER, panel);
        panel.add(allActions);
    }

    private void createAction(BattleAction b) {
        Component last = getLastComponent();
        JButton act = new JButton(Dictionary.getValue(b.toString()));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, act,
                0,
                SpringLayout.HORIZONTAL_CENTER, last);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, act,
                25,
                SpringLayout.VERTICAL_CENTER, last);
        act.addActionListener(new BattleActionListener(battle, b, gui));
        panel.add(act);
    }
}
