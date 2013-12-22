package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.MainMenuQuitListener;
import GUI.listeners.MainMenuStartListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import localisation.Dictionary;

/**
 *
 */
public final class MainMenuPanel extends AbstractPanel {

    public MainMenuPanel(SwingGUI gui) {
        panel.setLayout(new GridBagLayout());
        createStart(gui);
        createQuit(gui);
        createMainTitle();
    }

    private void createStart(SwingGUI gui) {
        GridBagConstraints sConstrain = new GridBagConstraints();
        sConstrain.gridy = 1;
        sConstrain.fill = GridBagConstraints.VERTICAL;
        sConstrain.insets = new Insets(15, 0, 15, 0);
        JButton start = new JButton(Dictionary.getValue("START_GAME"));
        MainMenuStartListener mListener = new MainMenuStartListener(gui);
        start.addActionListener(mListener);
        panel.add(start, sConstrain);
    }

    private void createQuit(SwingGUI gui) {
        GridBagConstraints qConstrain = new GridBagConstraints();
        qConstrain.gridy = 2;
        qConstrain.ipadx = 40;
        qConstrain.fill = GridBagConstraints.VERTICAL;
        MainMenuQuitListener qListener = new MainMenuQuitListener(gui);
        JButton quit = new JButton(Dictionary.getValue("QUIT_GAME"));
        quit.addActionListener(qListener);
        panel.add(quit, qConstrain);
    }

    private void createMainTitle() {
        GridBagConstraints mmConstrain = new GridBagConstraints();
        mmConstrain.gridy = 0;
        JLabel mainmenu = new JLabel(Dictionary.getValue("MAIN_MENU"));
        mainmenu.setFont(new Font("Arail", Font.PLAIN, 40));
        mainmenu.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(mainmenu, mmConstrain);
    }
}
