package GUI.panels;

import javax.swing.JPanel;

/**
 * The interface for all panels in the gui.
 */
public interface IDungeonPanel {

    /**
     * Clears all the resources that the panel has reserved. Call before losing
     * refrence to prevent memoryleaks.
     */
    public void dispose();

    /**
     * Returns the panel which has been constructed in the class.
     *
     * @return Constructed panel.
     */
    public JPanel getPanel();
}
