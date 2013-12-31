package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.MainMenuPanel;
import java.awt.event.ActionEvent;

public final class GoToMainMenuListener extends AbstractGUIListener {
    
    public GoToMainMenuListener(SwingGUI gui) {
        super(gui);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPanel(new MainMenuPanel(gui));
    }
}
