package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.MainMenuPanel;
import java.awt.event.ActionEvent;
import localisation.Dictionary;

public final class ChangeLanguageListener extends AbstractGUIListener {

    private final String lang;

    public ChangeLanguageListener(SwingGUI gui, String lang) {
        super(gui);
        this.lang = lang;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dictionary.loadText(lang);
        gui.updateTitle();
        gui.setPanel(new MainMenuPanel(gui));
    }
}
