package GUI.panels;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Base class for all panels. Implements IDungeonPanel.
 */
public abstract class AbstractPanel implements IDungeonPanel {

    protected final JPanel panel = new JPanel();
    private boolean disposed = false;

    @Override
    public final void dispose() {
        for (Component c : panel.getComponents()) {
            if (c.getClass() == JButton.class) {
                JButton j = (JButton) c;
                for (ActionListener a : j.getActionListeners()) {
                    j.removeActionListener(a);
                }
            }
        }
        disposed = true;
    }

    @Override
    public final JPanel getPanel() {
        return panel;
    }

    protected final Component getLastComponent() {
        return panel.getComponents()[panel.getComponents().length - 1];
    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected final void finalize() throws Throwable {
        super.finalize();
        if (!disposed) {
            dispose();
        }
    }
}
