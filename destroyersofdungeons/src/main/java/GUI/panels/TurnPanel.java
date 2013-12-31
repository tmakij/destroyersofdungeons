package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.TurnBeginListener;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import localisation.Dictionary;

/**
 * Appears when the turn is changed. Has only the option to start the turn.
 */
public final class TurnPanel extends AbstractPanel {

    public TurnPanel(SwingGUI gui) {
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        addWhoseTurn(gui, layout);
        addStartTurn(gui, layout);
    }

    private void addWhoseTurn(SwingGUI gui, SpringLayout layout) {
        JLabel whoseTurn = new JLabel(Dictionary.getValue("CURRENT_TURN", gui.getGame().getCurrentPlayer()));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, whoseTurn,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, whoseTurn,
                0,
                SpringLayout.VERTICAL_CENTER, panel);
        panel.add(whoseTurn);
    }

    private void addStartTurn(SwingGUI gui, SpringLayout layout) {
        JButton beginTurn = new JButton(Dictionary.getValue("START_TURN"));
        Component whoseTurn = getLastComponent();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, beginTurn,
                0,
                SpringLayout.HORIZONTAL_CENTER, whoseTurn);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, beginTurn,
                whoseTurn.getHeight() + 50,
                SpringLayout.VERTICAL_CENTER, whoseTurn);
        TurnBeginListener listen = new TurnBeginListener(gui);
        beginTurn.addActionListener(listen);
        panel.add(beginTurn);
    }
}
