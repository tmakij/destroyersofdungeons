package GUI;

import java.awt.event.ActionEvent;

/**
 *
 */
public final class PlayerNameListener extends AbstractGUIListener {

    public PlayerNameListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPane(gui.getMainMenu());
    }
}
