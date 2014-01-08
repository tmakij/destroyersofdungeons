package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.MoveToListener;
import gameobjects.dungeon.Tunnel;
import gameobjects.items.Item;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import localisation.Dictionary;

/**
 * Panel for the player, it shows player status and items, and the possible
 * locations to move into.
 */
public final class MainPanel extends AbstractPanel {

    /**
     * Create a new instance of the MainPanel.
     *
     * @param gui The SwingGUI which holds the program.
     */
    public MainPanel(SwingGUI gui) {
        super(gui);

        JLabel myLocation = setMyLocation();
        createHealthStatus();
        setItemsTitle(myLocation);

        createItemList();

        setMoveToTitle();
        createButtonsForTunnels();
    }

    private JList<Item> getItemList() {
        Collection<Item> p_items = gui.getGame().getCurrentPlayer().getItems();
        DefaultListModel<Item> model = new DefaultListModel<>();
        for (Item i : p_items) {
            model.addElement(i);
        }
        return new JList<>(model);
    }

    private void createItemList() {
        JList<Item> items = getItemList();
        JScrollPane scroll = new JScrollPane(items);
        layout.putConstraint(SpringLayout.NORTH, scroll,
                0,
                SpringLayout.SOUTH, getLastComponent());
        layout.putConstraint(SpringLayout.EAST, scroll,
                0,
                SpringLayout.VERTICAL_CENTER, panel);
        layout.putConstraint(SpringLayout.WEST, scroll,
                0,
                SpringLayout.VERTICAL_CENTER, getLastComponent());
        items.setMaximumSize(new Dimension(50, 50));
        panel.add(scroll);
    }

    private void setItemsTitle(JLabel myLocation) {
        JLabel itemsTitle = new JLabel(Dictionary.getValue("ITEMS_TITLE"));
        layout.putConstraint(SpringLayout.NORTH, itemsTitle,
                0,
                SpringLayout.SOUTH, myLocation);
        panel.add(itemsTitle);
    }

    private JLabel setMyLocation() {
        JLabel myLocation = new JLabel(Dictionary.getValue("MY_LOCATION", gui.getGame().getCurrentPlayer().getMyBlock()));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, myLocation,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(myLocation);
        return myLocation;
    }

    private void setMoveToTitle() {
        JLabel label = new JLabel(Dictionary.getValue("MOVE_TO"));
        layout.putConstraint(SpringLayout.NORTH, label,
                5,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, label,
                -50,
                SpringLayout.EAST, panel);
        panel.add(label);
    }

    private void createHealthStatus() {
        JLabel status = new JLabel(Dictionary.getValue("MY_HEALTH", gui.getGame().getCurrentPlayer().getHealth()));
        layout.putConstraint(SpringLayout.NORTH, status,
                5,
                SpringLayout.SOUTH, getLastComponent());
        layout.putConstraint(SpringLayout.WEST, status,
                0,
                SpringLayout.WEST, getLastComponent());
        panel.add(status);
    }

    private void createButtonsForTunnels() {
        Component c = getLastComponent();
        List<Tunnel> tunnels = gui.getGame().getMovingPossibilities();
        for (int i = 0; i < tunnels.size(); i++) {
            JButton move = new JButton(tunnels.get(i).toString());
            layout.putConstraint(SpringLayout.NORTH, move,
                    5,
                    SpringLayout.SOUTH, c);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, move,
                    0,
                    SpringLayout.HORIZONTAL_CENTER, c);
            c = move;
            MoveToListener listen = new MoveToListener(gui, i);
            move.addActionListener(listen);
            panel.add(move);
        }
    }
}
