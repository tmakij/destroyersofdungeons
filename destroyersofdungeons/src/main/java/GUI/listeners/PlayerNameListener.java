package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.TurnPanel;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;

/**
 * Listens startgame button on player creation. For each JTextComponent given to
 * this a player is created into the game. All fields must be valid, 1-32
 * characters long that are from A to Z and from 0 to 9. Switches to TurnPanel.
 * Id fields are valid and the button is clicked, a new instance of the game is
 * created.
 */
public final class PlayerNameListener extends AbstractGUIListener {

    private final JTextComponent[] fields;

    /**
     * Creates a new PlayerNameListener.
     *
     * @param gui SwingGUI that handles the game.
     * @param fields The player name text fields.
     */
    public PlayerNameListener(SwingGUI gui, JTextComponent... fields) {
        super(gui);
        this.fields = fields;
    }

    private boolean fieldsAreValid() {
        for (JTextComponent field : fields) {
            String f = field.getText();
            if (f.isEmpty() || f.length() > 32 || !f.matches("[a-zA-Z0-9 ]*")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (fieldsAreValid()) {
            gui.createNewGame();
            for (JTextComponent field : fields) {
                gui.getGame().addPlayer(field.getText());
            }
            gui.setPanel(new TurnPanel(gui));
        }
    }
}
