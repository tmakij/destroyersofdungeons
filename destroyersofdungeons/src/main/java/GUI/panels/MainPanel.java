package GUI.panels;

import GUI.SwingGUI;
import GUI.listeners.MoveToListener;
import gameobjects.dungeon.Tunnel;
import gameobjects.items.Item;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
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
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        JLabel myLocation = setMyLocation(gui, layout);
        JLabel itemsTitle = setItemsTitle(layout, myLocation);

        createItemList(gui, layout, itemsTitle);

        JLabel titleLabel = setMoveToTitle(layout);
        createButtonsForTunnels(gui, layout, titleLabel);
    }

    private void createItemList(SwingGUI gui, SpringLayout layout, JLabel itemsTitle) {
        List<Item> p_items = gui.getGame().getCurrentPlayer().getItems();
        JList items = new JList(p_items.toArray());
        JScrollPane scroll = new JScrollPane(items);
        layout.putConstraint(SpringLayout.NORTH, scroll,
                0,
                SpringLayout.SOUTH, itemsTitle);
        layout.putConstraint(SpringLayout.EAST, scroll,
                0,
                SpringLayout.VERTICAL_CENTER, panel);
        layout.putConstraint(SpringLayout.WEST, scroll,
                0,
                SpringLayout.VERTICAL_CENTER, itemsTitle);
        items.setMaximumSize(new Dimension(50, 50));
        panel.add(scroll);
    }

    private JLabel setItemsTitle(SpringLayout layout, JLabel myLocation) {
        JLabel itemsTitle = new JLabel(Dictionary.getValue("ITEMS_TITLE"));
        layout.putConstraint(SpringLayout.NORTH, itemsTitle,
                0,
                SpringLayout.SOUTH, myLocation);
        panel.add(itemsTitle);
        return itemsTitle;
    }

    private JLabel setMyLocation(SwingGUI gui, SpringLayout layout) {
        JLabel myLocation = new JLabel(Dictionary.getValue("MY_LOCATION") + gui.getGame().getCurrentPlayer().getMyBlock());
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, myLocation,
                0,
                SpringLayout.HORIZONTAL_CENTER, panel);
        panel.add(myLocation);
        return myLocation;
    }

    private JLabel setMoveToTitle(SpringLayout layout) {
        JLabel label = new JLabel(Dictionary.getValue("MOVE_TO"));
        layout.putConstraint(SpringLayout.NORTH, label,
                5,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, label,
                -25,
                SpringLayout.EAST, panel);
        panel.add(label);
        return label;
    }

    private void createButtonsForTunnels(SwingGUI gui, SpringLayout layout, Component c) {
        List<Tunnel> tunnels = gui.getGame().getCurrentPlayer().getMyBlock().getNextTo();
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
