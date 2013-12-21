package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.TurnPanel;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**
 *
 */
public final class PlayerNameListener extends AbstractGUIListener {
    
    private final JTextField[] fields;
    
    public PlayerNameListener(SwingGUI gui, JTextField... fields) {
        super(gui);
        this.fields = fields;
    }
    
    private boolean fieldsAreValid() {
        for (JTextField field : fields) {
            String f = field.getText();
            if (f.isEmpty() || f.length() > 32) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (fieldsAreValid()) {
            for (JTextField field : fields) {
                gui.getGame().addPlayer(field.getText());
            }
        }
        gui.setPanel(new TurnPanel(gui));
    }
}
