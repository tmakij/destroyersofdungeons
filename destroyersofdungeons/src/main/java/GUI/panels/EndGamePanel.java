package GUI.panels;

import GUI.SwingGUI;
import gameobjects.actors.Player;
import java.awt.Component;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import localisation.Dictionary;
import logic.DestroyersOfDungeons;

public final class EndGamePanel extends AbstractPanel {

    public EndGamePanel(SwingGUI gui) {
        DestroyersOfDungeons game = gui.getGame();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        addEnd(layout);
        addWinner(layout, game);
        makePlayerList(game, layout);
        addReturn(layout, gui);
    }

    private void addReturn(SpringLayout layout, SwingGUI gui) {
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
        panel.add(returnMainMenu);
    }

    private void addEnd(SpringLayout layout) {
        JLabel end = new JLabel(Dictionary.getValue("GAME_END"));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, end,
                50,
                SpringLayout.NORTH, panel
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, end,
                -50,
                SpringLayout.VERTICAL_CENTER, panel
        );
        panel.add(end);
    }

    private void addWinner(SpringLayout layout, DestroyersOfDungeons game) {
        JLabel winner = new JLabel();
        if (game.hasPlayers()) {
            winner.setText(Dictionary.getValue("WINNER", game.getWinner()));
        } else {
            winner.setText(Dictionary.getValue("ALL_LOST"));
        }
        Component last = getLastComponent();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, winner,
                0,
                SpringLayout.NORTH, last
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, winner,
                25,
                SpringLayout.VERTICAL_CENTER, last
        );
        panel.add(winner);
    }

    private void makePlayerList(DestroyersOfDungeons game, SpringLayout layout) {
        Map<Player, Integer> deaths = game.getDeathTimes();
        for (Player p : game.getPlayers()) {
            int turn = deaths.containsKey(p) ? deaths.get(p) : -1;
            listPlayer(p, turn, layout);
        }
    }

    private void listPlayer(Player p, int turn, SpringLayout layout) {
        Component last = getLastComponent();
        JLabel player = new JLabel(p.toString());
        JLabel status = new JLabel((turn == -1 ? Dictionary.getValue("ALIVE_END") : Dictionary.getValue("DIED_TURN") + turn));

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, player,
                0,
                SpringLayout.NORTH, last
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, player,
                25,
                SpringLayout.VERTICAL_CENTER, last
        );
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, status,
                0,
                SpringLayout.NORTH, player
        );
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, status,
                25,
                SpringLayout.VERTICAL_CENTER, player
        );

        panel.add(player);
        panel.add(status);
    }
}
