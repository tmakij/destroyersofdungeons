package GUI;

import GUI.panels.IDungeonPanel;
import GUI.panels.MainMenuPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import logic.DestroyersOfDungeons;
import localisation.Dictionary;

/**
 * The GUI made with swing.
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
        currentPanel = new MainMenuPanel(this);
        frame.setContentPane(currentPanel.getPanel());
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setPanel(IDungeonPanel panel) {
        if (panel != null) {
            currentPanel.dispose();
        }
        currentPanel = panel;
        frame.setContentPane(currentPanel.getPanel());
        frame.revalidate();
    }

    public DestroyersOfDungeons getGame() {
        return game;
    }

    public void dispose() {
        System.exit(0);
    }
}
