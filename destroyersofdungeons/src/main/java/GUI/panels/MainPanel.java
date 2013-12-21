package GUI.panels;

import GUI.SwingGUI;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import localisation.Dictionary;

/**
 *
 */
public final class MainPanel extends AbstractPanel {

    public MainPanel(SwingGUI gui) {
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        JLabel myLocation = new JLabel(Dictionary.getValue("MY_LOCATION") + gui.getGame().getCurrentPlayer().getMyBlock());
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, myLocation,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel);

        JLabel itemsTitle = new JLabel(Dictionary.getValue("ITEMS_TITLE"));
        layout.putConstraint(SpringLayout.NORTH, itemsTitle,
                0,
                SpringLayout.SOUTH, myLocation);

        JList items = new JList(gui.getGame().getCurrentPlayer().getItems().toArray());
        JScrollPane scroll = new JScrollPane(items);
        layout.putConstraint(SpringLayout.NORTH, scroll,
                0,
                SpringLayout.SOUTH, itemsTitle);

        panel.add(myLocation);
        panel.add(itemsTitle);
        panel.add(scroll);
    }
}
