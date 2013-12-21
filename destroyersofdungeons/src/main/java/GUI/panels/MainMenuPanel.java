package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.MainMenuQuitListener;
import GUI.listeners.MainMenuStartListener;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import localisation.Dictionary;

/**
 *
 */
public final class MainMenuPanel extends AbstractPanel {

    public MainMenuPanel(SwingGUI gui) {
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
        panel.setLayout(new GridLayout(3, 1, 15, 15));

        JButton start = new JButton(Dictionary.getValue("START_GAME"));
        MainMenuStartListener mListener = new MainMenuStartListener(gui);
        start.addActionListener(mListener);

        MainMenuQuitListener qListener = new MainMenuQuitListener(gui);
        JButton quit = new JButton(Dictionary.getValue("QUIT_GAME"));
        quit.addActionListener(qListener);

        JLabel mainmenu = new JLabel(Dictionary.getValue("MAIN_MENU"));
        mainmenu.setFont(new Font("Arail", Font.PLAIN, 40));
        mainmenu.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(mainmenu);
        panel.add(start);
        panel.add(quit);
    }
}
