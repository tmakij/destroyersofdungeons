package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.MainMenuQuitListener;
import GUI.listeners.MainMenuStartListener;
import GUI.listeners.SettingsListener;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import localisation.Dictionary;

/**
 * The panel that holds the startgame button.
 */
public final class MainMenuPanel extends AbstractPanel {

    /**
     * Creates a new instance of the main menu. Also ends the current game
     * defined in the SwingGUI, if any.
     *
     * @param gui The gui where to add the panel and to end the game.
     */
    public MainMenuPanel(SwingGUI gui) {
        super(gui);
        gui.endGame();

        createMainTitle();
        addMainOption("START_GAME", new MainMenuStartListener(gui));
        addMainOption("SETTINGS", new SettingsListener(gui));
        addMainOption("QUIT_GAME", new MainMenuQuitListener(gui));
    }

    private void addMainOption(String text, ActionListener al) {
        JButton opt = new JButton(Dictionary.getValue(text));
        layout.putConstraint(SpringLayout.NORTH, opt,
                15,
                SpringLayout.SOUTH, getLastComponent());
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, opt,
                0,
                SpringLayout.HORIZONTAL_CENTER, getLastComponent());
        opt.addActionListener(al);
        panel.add(opt);
    }

    private void createMainTitle() {
        JLabel mainmenu = new JLabel(Dictionary.getValue("MAIN_MENU"));
        mainmenu.setFont(new Font("Arail", Font.PLAIN, 40));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainmenu,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, mainmenu,
                -50,
                SpringLayout.VERTICAL_CENTER, panel);
        panel.add(mainmenu);
    }
}
