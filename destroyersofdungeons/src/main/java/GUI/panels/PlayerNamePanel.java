package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.GoToMainMenuListener;
import GUI.listeners.PlayerNameListener;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import localisation.Dictionary;

/**
 * The panel which asks the names of the players. Starts a new game when
 * startgame is clicked.
 */
public final class PlayerNamePanel extends AbstractPanel {

    /**
     * Create a new instance of the PlayerNamePanel.
     *
     * @param gui The gui which holds the program.
     */
    public PlayerNamePanel(SwingGUI gui) {
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        JTextField[] fields = new JTextField[2];
        createPlayerFields(Dictionary.getValue("PLAYER_N", 1), layout, 0, fields);
        createPlayerFields(Dictionary.getValue("PLAYER_N", 2), layout, 1, fields);
        createStart(fields, layout, gui);
        createBack(layout, gui);
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

    private void createPlayerFields(String text, SpringLayout layout, int m, JTextField[] fields) {
        int ver = (50 * m) - 50;
        createPlayerField(text, layout, ver);
        fields[m] = createNameField(text, layout, ver);
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

    private void createBack(SpringLayout layout, SwingGUI gui) {
        JButton backToMainMenu = new JButton(Dictionary.getValue("RETURN_MAINMENU"));
        Component last = getLastComponent();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, backToMainMenu,
                -175,
                SpringLayout.HORIZONTAL_CENTER, last);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, backToMainMenu,
                0,
                SpringLayout.VERTICAL_CENTER, last);
        backToMainMenu.addActionListener(new GoToMainMenuListener(gui));
        panel.add(backToMainMenu);
    }
}
