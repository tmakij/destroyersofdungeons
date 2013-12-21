package GUI;

import java.awt.event.ActionEvent;

/**
 *
 */
public final class MainMenuListener extends AbstractGUIListener {

    public MainMenuListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPane(gui.getGivePlayerNames());
    }
}
