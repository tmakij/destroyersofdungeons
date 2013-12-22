package GUI.panels;

import GUI.SwingGUI;
import gameobjects.actors.Actor;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import localisation.Dictionary;
import logic.BattleAction;

/**
 * The panel that handles all the battles between a player and another actor.
 */
public final class BattlePanel extends AbstractPanel {

    private final Actor defender;
    private final Actor attacker;
    private final SwingGUI gui;
    private final JLabel attackerStatus = new JLabel();
    private final JLabel defenderStatus = new JLabel();

    public BattlePanel(SwingGUI gui, Actor defender) {
        this.defender = defender;
        this.attacker = gui.getGame().getCurrentPlayer();
        this.gui = gui;

        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        setStatusFields(layout);
        listActions(layout);

        BattleAction[] actions = BattleAction.values();
        for (BattleAction action : actions) {
            createAction(action, layout);
        }
    }

    private void setStatusFields(SpringLayout layout) {
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, attackerStatus,
                -125,
                SpringLayout.HORIZONTAL_CENTER, panel
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, attackerStatus,
                50,
                SpringLayout.VERTICAL_CENTER, panel
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
    }

    private void updateStatusField() {
        attackerStatus.setText(attacker + " health " + attacker.getHealth());
        defenderStatus.setText(defender + " health " + defender.getHealth());
    }

    private void listActions(SpringLayout layout) {
        JLabel allActions = new JLabel(Dictionary.getValue("POSSIBLE_ACTS"));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, allActions,
                -75,
                SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, allActions,
                50,
                SpringLayout.VERTICAL_CENTER, panel);
        panel.add(allActions);
    }

    private void createAction(BattleAction b, SpringLayout layout) {
        Component last = getLastComponent();
        JButton act = new JButton(Dictionary.getValue(b.toString()));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, act,
                0,
                SpringLayout.HORIZONTAL_CENTER, last);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, act,
                25,
                SpringLayout.VERTICAL_CENTER, last);
        panel.add(act);
    }
}
