package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.PlayerNamePanel;
import java.awt.event.ActionEvent;

/**
 * Listen start game button on main menu. Switches to player creation on click.
 */
public final class MainMenuStartListener extends AbstractGUIListener {

    public MainMenuStartListener(SwingGUI gui) {
        super(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPanel(new PlayerNamePanel(gui));
    }
}