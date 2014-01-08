package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.GoToMainMenuListener;
import GUI.listeners.SetPlayerCountListener;
import constants.Constants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import localisation.Dictionary;

/**
 * The panel that allows the player to set player count for the game.
 */
public final class SetPlayerCountPanel extends AbstractPanel {

    /**
     * Create a new instance of the SetPlayerCountPanel.
     *
     * @param gui The SwingGUI which holds the program.
     */
    public SetPlayerCountPanel(SwingGUI gui) {
        super(gui);
        createInstructions();
        createPlayerCount();
        createAccept();
        createBack("RETURN_MAINMENU", new GoToMainMenuListener(gui));
    }

    private void createInstructions() {
        JLabel ins = new JLabel(Dictionary.getValue("PLAYER_COUNT"));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, ins,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, ins,
                -50,
                SpringLayout.VERTICAL_CENTER, panel
        );
        panel.add(ins);
    }

    private void createPlayerCount() {
        SpinnerModel mdl = new SpinnerNumberModel(Constants.MINIMUM_PLAYER_COUNT, Constants.MINIMUM_PLAYER_COUNT,
                Constants.MAXIMUM_PLAYER_COUNT, 1);

        JSpinner count = new JSpinner(mdl);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, count,
                0,
                SpringLayout.HORIZONTAL_CENTER, getLastComponent()
        );
        layout.putConstraint(SpringLayout.NORTH, count,
                10,
                SpringLayout.SOUTH, getLastComponent()
        );
        panel.add(count);
    }

    private void createAccept() {
        JButton ac = new JButton(Dictionary.getValue("CONTINUE"));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, ac,
                0,
                SpringLayout.HORIZONTAL_CENTER, getLastComponent()
        );
        layout.putConstraint(SpringLayout.NORTH, ac,
                10,
                SpringLayout.SOUTH, getLastComponent()
        );
        ac.addActionListener(new SetPlayerCountListener(gui, (JSpinner) getLastComponent()));
        panel.add(ac);
    }
}
