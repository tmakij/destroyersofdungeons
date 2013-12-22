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
        fields[0] = createPlayerFields(Dictionary.getValue("PLAYER") + 1, layout, 0);
        fields[1] = createPlayerFields(Dictionary.getValue("PLAYER") + 2, layout, 1);
        createStart(fields, layout, gui);
    }

    private void createStart(JTextField[] fields, SpringLayout layout, SwingGUI gui) {
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
        int ver = (50 * m) - 50;
        createPlayerField(text, layout, ver);
        return createNameField(text, layout, ver);
    }

    private void createPlayerField(String text, SpringLayout layout, int ver) {
        JLabel fieldPlayer = new JLabel(text);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fieldPlayer,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, fieldPlayer,
                ver,
                SpringLayout.VERTICAL_CENTER, panel);
        panel.add(fieldPlayer);
    }

    private JTextField createNameField(String text, SpringLayout layout, int ver) {
        JTextField nameFieldPlayer = new JTextField(text);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameFieldPlayer,
                75,
                SpringLayout.HORIZONTAL_CENTER, getLastComponent());
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, nameFieldPlayer,
                ver,
                SpringLayout.VERTICAL_CENTER, panel);

        panel.add(nameFieldPlayer);
        return nameFieldPlayer;
    }
}
