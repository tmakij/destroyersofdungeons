package GUI;

import java.awt.event.ActionEvent;

/**
 *
 */
final class MainMenuQuitListener extends AbstractGUIListener {

    public MainMenuQuitListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.dispose();
    }
}
