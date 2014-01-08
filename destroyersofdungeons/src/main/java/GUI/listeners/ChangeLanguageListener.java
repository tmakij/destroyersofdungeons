package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.MainMenuPanel;
import java.awt.event.ActionEvent;
import localisation.Dictionary;

/**
 * Listen the change language buttons. Switches the program language on click.
 */
public final class ChangeLanguageListener extends AbstractGUIListener {

    /**
     * The language where to switch.
     */
    private final String lang;

    /**
     * Creates a new instance of the listener.
     *
     * @param gui The SwingGUI which will be updated accordingly on battle end.
     * @param lang The language which to load.
     */
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
