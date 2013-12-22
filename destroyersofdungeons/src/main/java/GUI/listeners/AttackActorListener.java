package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.BattlePanel;
import gameobjects.actors.Actor;
import java.awt.event.ActionEvent;

/**
 * Listen the attack button and creates a battle on click.
 */
public final class AttackActorListener extends AbstractGUIListener {

    private final Actor enemy;

    public AttackActorListener(SwingGUI gui, Actor enemy) {
        super(gui);
        this.enemy = enemy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setPanel(new BattlePanel(gui, enemy));
    }
}
