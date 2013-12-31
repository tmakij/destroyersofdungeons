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

    private final JFrame frame;
    private DestroyersOfDungeons game;
    private IDungeonPanel currentPanel;

    /**
     * Creates a new instance the gui. Localisation must be loaded before
     * creating.
     */
    public SwingGUI() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        updateTitle();
    }

    /**
     * Starts the game by showing the main menu.
     */
    public void start() {
        setPanel(new MainMenuPanel(this));
        frame.setVisible(true);
    }

    /**
     * Ends the current game, by setting it to null.
     */
    public void endGame() {
        game = null;
    }

    /**
     * Creates a new game.
     */
    public void createNewGame() {
        game = new DestroyersOfDungeons();
    }

    /**
     * Updates the title to match the language in localisation.
     */
    public void updateTitle() {
        frame.setTitle(Dictionary.getValue("TITLE"));
    }

    /**
     * Sets the panel that is shown.
     *
     * @param panel The one that is desired to be shown.
     */
    public void setPanel(IDungeonPanel panel) {
        frame.setContentPane(panel.getPanel());
        if (currentPanel != null) {
            currentPanel.dispose();
            frame.revalidate();
        }
        currentPanel = panel;
    }

    /**
     * Get the instance of the game.
     *
     * @return The instance of the game.
     */
    public DestroyersOfDungeons getGame() {
        return game;
    }

    /**
     * Terminates the program and releases all resources.
     */
    public void dispose() {
        System.exit(0);
    }
}
