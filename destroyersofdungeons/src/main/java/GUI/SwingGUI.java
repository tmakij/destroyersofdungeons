package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import logic.DestroyersOfDungeons;
import localisation.Dictionary;

/**
 * The GUI made with swing.
 */
public final class SwingGUI {

    private final DestroyersOfDungeons game;
    private final JFrame frame;
    private JPanel tab;

    public SwingGUI() {
        game = new DestroyersOfDungeons();
        frame = new JFrame(Dictionary.getValue("TITLE"));
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tab = getMainMenu();
        frame.setContentPane(tab);
        //frame.pack();
        //frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setPane(JPanel panel) {
        for (Component c : tab.getComponents()) {
            if (c.getClass() == JButton.class) {
                JButton j = (JButton) c;
                for (ActionListener a : j.getActionListeners()) {
                    j.removeActionListener(a);
                }
            }
        }
        frame.setContentPane(panel);
        tab = panel;
        frame.revalidate();
    }

    JPanel getMainMenu() {
        JPanel menu = new JPanel();

        //top, left, bottom, right
        menu.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));

        //rows, columns, horizontal and vertical gaps
        menu.setLayout(new GridLayout(3, 1, 15, 15));

        JButton start = new JButton(Dictionary.getValue("START_GAME"));
        MainMenuListener mListener = new MainMenuListener(this);
        start.addActionListener(mListener);
        start.setPreferredSize(new Dimension(3, 3));
        JButton quit = new JButton(Dictionary.getValue("QUIT_GAME"));
        JLabel mainmenu = new JLabel(Dictionary.getValue("MAIN_MENU"));
        mainmenu.setFont(new Font("Arail", Font.PLAIN, 50));
        mainmenu.setHorizontalAlignment(SwingConstants.CENTER);
        menu.add(mainmenu);
        menu.add(start);
        menu.add(quit);
        return menu;
    }

    JPanel getGivePlayerNames() {
        JPanel namePanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        //namePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        namePanel.setLayout(layout);

        createPlayerFields(namePanel, "Player 1", layout, 1);
        createPlayerFields(namePanel, "Player 2", layout, 2);

        JButton start = new JButton("Start");
        PlayerNameListener al = new PlayerNameListener(this);
        start.addActionListener(al);
        namePanel.add(start);

        layout.putConstraint(SpringLayout.WEST, start,
                90,
                SpringLayout.WEST, namePanel);
        layout.putConstraint(SpringLayout.NORTH, start,
                135,
                SpringLayout.NORTH, namePanel);

        return namePanel;
    }

    private static void createPlayerFields(JPanel namePanel, String text, SpringLayout layout, int m) {
        JTextField nameFieldPlayer = new JTextField(text);
        JLabel fieldPlayer = new JLabel(text);

        layout.putConstraint(SpringLayout.WEST, fieldPlayer,
                25,
                SpringLayout.WEST, namePanel);
        layout.putConstraint(SpringLayout.NORTH, fieldPlayer,
                50 * m,
                SpringLayout.NORTH, namePanel);
        layout.putConstraint(SpringLayout.WEST, nameFieldPlayer,
                100,
                SpringLayout.WEST, namePanel);
        layout.putConstraint(SpringLayout.NORTH, nameFieldPlayer,
                50 * m,
                SpringLayout.NORTH, namePanel);

        namePanel.add(fieldPlayer);
        namePanel.add(nameFieldPlayer);
    }

    private static void putConst(SpringLayout layout, int m, String dir, JPanel namePanel, Component c) {
        layout.putConstraint(dir, c,
                50 * m,
                dir, namePanel);
    }
}
