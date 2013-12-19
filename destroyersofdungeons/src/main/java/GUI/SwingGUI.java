package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import logic.DestroyersOfDungeons;

/**
 * The GUI made with swing.
 */
public final class SwingGUI {

    private final DestroyersOfDungeons game;
    private final JFrame frame;
    private JPanel tab;

    public SwingGUI() {
        game = new DestroyersOfDungeons();
        frame = new JFrame("Destroyers of Dungeons");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Hello world!");
        frame.add(label);
        tab = getMainMenu();
        frame.setContentPane(tab);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel getMainMenu() {
        JPanel menu = new JPanel();
        menu.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));

        //rows, columns, horizontal and vertical gaps
        menu.setLayout(new GridLayout(5, 1, 15, 15));

        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(3, 3));
        JButton quit = new JButton("Quit");
        JLabel mainmenu = new JLabel("Main menu");
        mainmenu.setFont(new Font("Arail", Font.PLAIN, 50));
        mainmenu.setHorizontalAlignment(SwingConstants.CENTER);
        menu.add(mainmenu);
        menu.add(start);
        menu.add(quit);
        return menu;
    }
}
