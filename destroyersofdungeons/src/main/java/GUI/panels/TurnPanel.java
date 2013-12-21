package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.TurnBeginListener;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import localisation.Dictionary;

/**
 *
 */
public final class TurnPanel extends AbstractPanel {

    public TurnPanel(SwingGUI gui) {
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
        panel.setLayout(new GridLayout(2, 1, 15, 15));

        JLabel whoseTurn = new JLabel(Dictionary.getValue("CURRENT_TURN") + gui.getGame().getCurrentPlayer());
        JButton beginTurn = new JButton(Dictionary.getValue("START_TURN"));
        TurnBeginListener listen = new TurnBeginListener(gui);
        beginTurn.addActionListener(listen);

        panel.add(whoseTurn);
        panel.add(beginTurn);
    }
}
