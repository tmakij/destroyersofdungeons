package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.ChangeLanguageListener;
import GUI.listeners.GoToMainMenuListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import localisation.Dictionary;

public final class SettingsPanel extends AbstractPanel {

    public SettingsPanel(SwingGUI gui) {
        super(gui);
        createBack();
        createChangeLanguage();
        createChangeLanguage();
        for (String l : Dictionary.getLanguages()) {
            createChangeLanguage(l);
        }
    }

    private void createBack() {
        JButton backToMainMenu = new JButton(Dictionary.getValue("RETURN_MAINMENU"));
        layout.putConstraint(SpringLayout.WEST, backToMainMenu,
                50,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, backToMainMenu,
                -75,
                SpringLayout.SOUTH, panel);
        backToMainMenu.addActionListener(new GoToMainMenuListener(gui));
        panel.add(backToMainMenu);
    }

    private void createChangeLanguage() {
        JLabel lang = new JLabel(Dictionary.getValue("CHANGE_LANGUAGE"));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lang,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, lang,
                -50,
                SpringLayout.VERTICAL_CENTER, panel);
        panel.add(lang);
    }

    private void createChangeLanguage(String language) {
        JButton lang = new JButton(language);
        layout.putConstraint(SpringLayout.NORTH, lang,
                15,
                SpringLayout.SOUTH, getLastComponent());
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lang,
                0,
                SpringLayout.HORIZONTAL_CENTER, getLastComponent());
        lang.addActionListener(new ChangeLanguageListener(gui, language));
        panel.add(lang);
    }
}
