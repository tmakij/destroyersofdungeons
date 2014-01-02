package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.GoToMainMenuListener;
import gameobjects.actors.Player;
import java.awt.Component;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import localisation.Dictionary;
import logic.DestroyersOfDungeons;

/**
 * The panel that shows end game results. Should be created when the game is
 * wanted to be ended.
 */
public final class EndGamePanel extends AbstractPanel {

    /**
     * Create a new instance of the EndGamePanel.
     *
     * @param gui The SwingGUI which holds the program.
     */
    public EndGamePanel(SwingGUI gui) {
        super(gui);
        DestroyersOfDungeons game = gui.getGame();

        addEnd();
        Player winner = game.getWinner();
        addWinner(winner);
        makePlayerList(game, winner);
        addReturn();
    }

    private void addReturn() {
        JButton returnMainMenu = new JButton(Dictionary.getValue("RETURN_MAINMENU"));
        Component last = getLastComponent();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, returnMainMenu,
                125,
                SpringLayout.NORTH, last
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, returnMainMenu,
                50,
                SpringLayout.VERTICAL_CENTER, last
        );
        returnMainMenu.addActionListener(new GoToMainMenuListener(gui));
        panel.add(returnMainMenu);
    }

    private void addEnd() {
        JLabel end = new JLabel(Dictionary.getValue("GAME_END"));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, end,
                -250,
                SpringLayout.HORIZONTAL_CENTER, panel
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, end,
                -50,
                SpringLayout.VERTICAL_CENTER, panel
        );
        panel.add(end);
    }

    private void addWinner(Player winner) {
        JLabel winnerAnnounce = new JLabel();
        if (winner != null) {
            winnerAnnounce.setText(Dictionary.getValue("WINNER", winner));
        } else {
            winnerAnnounce.setText(Dictionary.getValue("ALL_LOST"));
        }
        Component last = getLastComponent();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, winnerAnnounce,
                150,
                SpringLayout.HORIZONTAL_CENTER, last
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, winnerAnnounce,
                30,
                SpringLayout.VERTICAL_CENTER, last
        );
        panel.add(winnerAnnounce);
    }

    private void makePlayerList(DestroyersOfDungeons game, Player winner) {
        Map<Player, Integer> deaths = game.getDeathTimes();
        for (Player p : game.getAllOtherPlayers(winner)) {
            if (!p.equals(winner)) {
                int turn = deaths.containsKey(p) ? deaths.get(p) : -1;
                listPlayer(p, turn);
            }
        }
    }

    private void listPlayer(Player p, int turn) {
        Component last = getLastComponent();
        JLabel player = new JLabel(p.toString());
        JLabel status = new JLabel((turn == -1 ? Dictionary.getValue("ALIVE_END") : Dictionary.getValue("DIED_TURN") + turn));

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, player,
                0,
                SpringLayout.WEST, last
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, player,
                40,
                SpringLayout.VERTICAL_CENTER, last
        );
        layout.putConstraint(SpringLayout.WEST, status,
                20,
                SpringLayout.EAST, player
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, status,
                0,
                SpringLayout.VERTICAL_CENTER, player
        );

        panel.add(player);
        panel.add(status);
    }
}
