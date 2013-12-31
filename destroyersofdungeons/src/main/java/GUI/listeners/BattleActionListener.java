package GUI.listeners;

import GUI.SwingGUI;
import GUI.panels.MoveResultPanel;
import GUI.panels.TurnPanel;
import gameobjects.actors.Actor;
import java.awt.event.ActionEvent;
import logic.Battle;
import logic.BattleAction;
import logic.DestroyersOfDungeons;

/**
 * Class for all listeners of BattleActions buttons which are created on start
 * of a battle in the BattlePanel.
 */
public final class BattleActionListener extends AbstractGUIListener {

    private final Battle battle;
    private final BattleAction act;

    /**
     * Creates a new instance of the listener.
     *
     * @param battle Battle which it is attached to.
     * @param act The act which is performed on click.
     * @param gui The SwingGUI which will be updated accordingly on battle end.
     */
    public BattleActionListener(Battle battle, BattleAction act, SwingGUI gui) {
        super(gui);
        this.battle = battle;
        this.act = act;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DestroyersOfDungeons game = gui.getGame();
        Actor attacker = battle.getAttacker();
        Actor defender = battle.getDefender();
        Actor current = game.getCurrentPlayer();
        if (battle.takeAction(act)) {
            if (attacker.isAlive() && current.equals(attacker) && !defender.isAlive()) {
                gui.setPanel(new MoveResultPanel(gui));
            } else if (defender.isAlive() && current.equals(defender) && !attacker.isAlive()) {
                game.nextPlayer();
                gui.setPanel(new TurnPanel(gui));
            } else {
                game.nextPlayer();
                gui.setPanel(new TurnPanel(gui));
            }
        }
    }
}
