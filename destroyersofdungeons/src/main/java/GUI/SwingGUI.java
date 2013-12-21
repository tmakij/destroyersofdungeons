package GUI;

import java.awt.Component;
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
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        switchToMainMenu();
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void clearActionListeners() {
        for (Component c : tab.getComponents()) {
            if (c.getClass() == JButton.class) {
                JButton j = (JButton) c;
                for (ActionListener a : j.getActionListeners()) {
                    j.removeActionListener(a);
                }
            }
        }
    }

    private void setPane(JPanel panel) {
        clearActionListeners();
        frame.setContentPane(panel);
        tab = panel;
        frame.revalidate();
    }

    private void switchToMainMenu() {
        JPanel menu = new JPanel();

        //top, left, bottom, right
        menu.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));

        //rows, columns, horizontal and vertical gaps
        menu.setLayout(new GridLayout(3, 1, 15, 15));

        JButton start = new JButton(Dictionary.getValue("START_GAME"));
        MainMenuStartListener mListener = new MainMenuStartListener(this);
        start.addActionListener(mListener);

        MainMenuQuitListener qListener = new MainMenuQuitListener(this);
        JButton quit = new JButton(Dictionary.getValue("QUIT_GAME"));
        quit.addActionListener(qListener);

        JLabel mainmenu = new JLabel(Dictionary.getValue("MAIN_MENU"));
        mainmenu.setFont(new Font("Arail", Font.PLAIN, 40));
        mainmenu.setHorizontalAlignment(SwingConstants.CENTER);
        menu.add(mainmenu);
        menu.add(start);
        menu.add(quit);

        frame.setContentPane(menu);
        tab = menu;
    }

    void switchToPlayerNames() {
        JPanel namePanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        namePanel.setLayout(layout);

        JTextField[] fields = new JTextField[2];
        fields[0] = createPlayerFields(namePanel, Dictionary.getValue("PLAYER") + 1, layout, 1);
        fields[1] = createPlayerFields(namePanel, Dictionary.getValue("PLAYER") + 2, layout, 2);

        JButton start = new JButton(Dictionary.getValue("START_GAME"));
        PlayerNameListener al = new PlayerNameListener(this, fields);
        start.addActionListener(al);
        layout.putConstraint(SpringLayout.WEST, start,
                0,
                SpringLayout.WEST, fields[fields.length - 1]);
        layout.putConstraint(SpringLayout.NORTH, start,
                30,
                SpringLayout.SOUTH, fields[fields.length - 1]);
        namePanel.add(start);

        setPane(namePanel);
    }

    void switchToTurnTab() {
        JPanel turnPanel = new JPanel();

        turnPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
        turnPanel.setLayout(new GridLayout(2, 1, 15, 15));

        JLabel whoseTurn = new JLabel(Dictionary.getValue("CURRENT_TURN") + game.getCurrentPlayer());
        turnPanel.add(whoseTurn);

        JButton beginTurn = new JButton(Dictionary.getValue("START_TURN"));
        TurnBeginListener listen = new TurnBeginListener(this);
        beginTurn.addActionListener(listen);
        turnPanel.add(beginTurn);

        setPane(turnPanel);
    }

    void switchToMainTab() {
        JPanel main = new JPanel();
        SpringLayout layout = new SpringLayout();
        main.setLayout(layout);

        JLabel myLocation = new JLabel(Dictionary.getValue("MY_LOCATION") + game.getCurrentPlayer().getMyBlock());
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, myLocation,
                0,
                SpringLayout.HORIZONTAL_CENTER, main);
        main.add(myLocation);

        JLabel itemsTitle = new JLabel(Dictionary.getValue("ITEMS_TITLE"));
        main.add(itemsTitle);
        layout.putConstraint(SpringLayout.NORTH, itemsTitle,
                0,
                SpringLayout.SOUTH, myLocation);

        JList items = new JList(game.getCurrentPlayer().getItems().toArray());
        JScrollPane scroll = new JScrollPane(items);
        layout.putConstraint(SpringLayout.NORTH, scroll,
                0,
                SpringLayout.SOUTH, itemsTitle);
        main.add(scroll);

        setPane(main);
    }

    private static JTextField createPlayerFields(JPanel namePanel, String text, SpringLayout layout, int m) {
        JTextField nameFieldPlayer = new JTextField(text);
        JLabel fieldPlayer = new JLabel(text);

        layout.putConstraint(SpringLayout.WEST, fieldPlayer,
                50,
                SpringLayout.WEST, namePanel);
        layout.putConstraint(SpringLayout.NORTH, fieldPlayer,
                50 * m,
                SpringLayout.NORTH, namePanel);
        layout.putConstraint(SpringLayout.WEST, nameFieldPlayer,
                25,
                SpringLayout.EAST, fieldPlayer);
        layout.putConstraint(SpringLayout.NORTH, nameFieldPlayer,
                50 * m,
                SpringLayout.NORTH, namePanel);

        namePanel.add(fieldPlayer);
        namePanel.add(nameFieldPlayer);

        return nameFieldPlayer;
    }

    DestroyersOfDungeons getGame() {
        return game;
    }

    void dispose() {
        System.exit(0);
    }
}
