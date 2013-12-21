package GUI;

import java.awt.event.ActionEvent;

/**
 *
 */
final class MainMenuStartListener extends AbstractGUIListener {

    public MainMenuStartListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPane(gui.getGivePlayerNames());
    }
}
