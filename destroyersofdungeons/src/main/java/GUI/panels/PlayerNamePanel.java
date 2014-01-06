package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.MainMenuStartListener;
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
     * @param gui The SwingGUI which holds the program.
     * @param PlayerCount The amount of players in the game.
     */
    public PlayerNamePanel(SwingGUI gui, int PlayerCount) {
        super(gui);
        JTextField[] fields = new JTextField[PlayerCount];
        createPlayerFields(PlayerCount, fields);
        createStart(fields);
        createBack("RETURN_PLAYER_COUNT", new MainMenuStartListener(gui));
    }

    private void createStart(JTextField[] fields) {
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

    private void createPlayerFields(int count, JTextField[] fields) {
        createPlayers(50 - (count * 20));
        Component last = getLastComponent();
        for (int i = 0; i < count; i++) {
            String text = Dictionary.getValue("PLAYER_N", i + 1);
            createPlayerField(text, 50 * i, last);
            last = getLastComponent();
            fields[i] = createNameField(text);
        }
    }

    private void createPlayers(int m) {
        JLabel lab = new JLabel(Dictionary.getValue("PLAYER_NAMES"));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lab,
                -50,
                SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, lab,
                m,
                SpringLayout.VERTICAL_CENTER, panel);
        panel.add(lab);
    }

    private void createPlayerField(String text, int ver, Component last) {
        JLabel fieldPlayer = new JLabel(text);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fieldPlayer,
                0,
                SpringLayout.HORIZONTAL_CENTER, last);
        layout.putConstraint(SpringLayout.NORTH, fieldPlayer,
                30,
                SpringLayout.NORTH, last);
        panel.add(fieldPlayer);
    }

    private JTextField createNameField(String text) {
        JTextField nameFieldPlayer = new JTextField(text);
        layout.putConstraint(SpringLayout.WEST, nameFieldPlayer,
                15,
                SpringLayout.EAST, getLastComponent());
        layout.putConstraint(SpringLayout.NORTH, nameFieldPlayer,
                0,
                SpringLayout.NORTH, getLastComponent());
        layout.putConstraint(SpringLayout.EAST, nameFieldPlayer,
                -25,
                SpringLayout.EAST, panel);
        panel.add(nameFieldPlayer);
        return nameFieldPlayer;
    }
}
