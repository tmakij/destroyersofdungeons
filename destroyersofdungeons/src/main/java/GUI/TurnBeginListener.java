package GUI;

import java.awt.event.ActionEvent;

/**
 *
 */
final class TurnBeginListener extends AbstractGUIListener {

    public TurnBeginListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.switchToMainTab();
    }
}
