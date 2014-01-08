package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.BattlePanel;
import gameobjects.actors.Actor;
import java.awt.event.ActionEvent;

/**
 * Listen the attack button and creates a battle on click.
 */
public final class AttackActorListener extends AbstractGUIListener {

    /**
     * The enemy actor attached to the listener.
     */
    private final Actor enemy;

    /**
     * Creates a new AttackActorListener.
     *
     * @param gui SwingGUI that handles the game.
     * @param enemy The enemy against which the battle will be created.
     */
    public AttackActorListener(SwingGUI gui, Actor enemy) {
        super(gui);
        this.enemy = enemy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPanel(new BattlePanel(gui, enemy));
    }
}
