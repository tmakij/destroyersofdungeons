package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.PlayerNameListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import localisation.Dictionary;

/**
 *
 */
public final class PlayerNamePanel extends AbstractPanel {

    public PlayerNamePanel(SwingGUI gui) {
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        JTextField[] fields = new JTextField[2];
        fields[0] = createPlayerFields(Dictionary.getValue("PLAYER") + 1, layout, 1);
        fields[1] = createPlayerFields(Dictionary.getValue("PLAYER") + 2, layout, 2);

        JButton start = new JButton(Dictionary.getValue("START_GAME"));
        PlayerNameListener al = new PlayerNameListener(gui, fields);
        start.addActionListener(al);
        layout.putConstraint(SpringLayout.WEST, start,
                0,
                SpringLayout.WEST, fields[fields.length - 1]);
        layout.putConstraint(SpringLayout.NORTH, start,
                30,
                SpringLayout.SOUTH, fields[fields.length - 1]);
        panel.add(start);
    }

    private JTextField createPlayerFields(String text, SpringLayout layout, int m) {
        JTextField nameFieldPlayer = new JTextField(text);
        JLabel fieldPlayer = new JLabel(text);

        layout.putConstraint(SpringLayout.WEST, fieldPlayer,
                50,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, fieldPlayer,
                50 * m,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameFieldPlayer,
                25,
                SpringLayout.EAST, fieldPlayer);
        layout.putConstraint(SpringLayout.NORTH, nameFieldPlayer,
                50 * m,
                SpringLayout.NORTH, panel);

        panel.add(fieldPlayer);
        panel.add(nameFieldPlayer);

        return nameFieldPlayer;
    }
}
