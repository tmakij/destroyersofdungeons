package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.SettingsPanel;
import java.awt.event.ActionEvent;

public final class SettingsListener extends AbstractGUIListener {
    
    public SettingsListener(SwingGUI gui) {
        super(gui);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPanel(new SettingsPanel(gui));
    }
}
