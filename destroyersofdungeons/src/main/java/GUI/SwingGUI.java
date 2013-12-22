package GUI;

import GUI.panels.IDungeonPanel;
import GUI.panels.MainMenuPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import logic.DestroyersOfDungeons;
import localisation.Dictionary;

/**
 * The GUI made with swing. Handles the game.
 */
public final class SwingGUI {

    private final DestroyersOfDungeons game;
    private final JFrame frame;
    private IDungeonPanel currentPanel;

    public SwingGUI() {
        game = new DestroyersOfDungeons();
        frame = new JFrame(Dictionary.getValue("TITLE"));
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
    }

    public void start() {
        setPanel(new MainMenuPanel(this));
        frame.setVisible(true);
    }

    public void setPanel(IDungeonPanel panel) {
        frame.setContentPane(panel.getPanel());
        if (currentPanel != null) {
            currentPanel.dispose();
            frame.revalidate();
        }
        currentPanel = panel;
    }

    public DestroyersOfDungeons getGame() {
        return game;
    }

    public void dispose() {
        System.exit(0);
    }
}
