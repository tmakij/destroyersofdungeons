package logic;

import GUI.panels.IUpdate;
import gameobjects.actors.Actor;
import java.util.Random;

/**
 * A battle can occur between two different actors. In case the other one is not
 * player controlled, its actions are taken by java.util.Random instead.
 */
public final class Battle {

    private final IUpdate gui;
    private final Actor attacker;
    private final Actor defender;
    private final Random rand;
    private Actor current;
    private BattleAction lastAction;

    /**
     * Creates a new battle.
     *
     * @param attacker The attacker of the battle
     * @param defender The defender of the battle
     * @param gui The gui that is updated after every performed BattleAction.
     */
    public Battle(Actor attacker, Actor defender, IUpdate gui) {
        this.attacker = attacker;
        this.defender = defender;
        this.current = attacker;
        this.gui = gui;
        this.lastAction = BattleAction.DO_NOTHING;
        Random r = null;
        if (!attacker.isPlayerControlled() || !defender.isPlayerControlled()) {
            r = new Random();
        }
        rand = r;
    }

    /**
     * Makes the actor to perform the selected BattleAction. Also switches the
     * turn.
     *
     * @param act The action to perform.
     * @return Whether the battle is aborted.
     */
    public boolean takeAction(BattleAction act) {
        Actor nextActor = current == attacker ? defender : attacker;
        switch (act) {
            case ATTACK:
                current.attack(nextActor, BattleAction.ATTACK);
                if (lastAction == BattleAction.DEFEND) {
                    nextActor.attack(current, lastAction);
                }
                break;
            case FLEE:
                current.retreat();
                return true;
            case DO_NOTHING:
                break;
            case CAST_SPELL:
                current.attack(nextActor, BattleAction.CAST_SPELL);
                break;
            case DEFEND:
                break;
        }
        lastAction = act;
        current = nextActor;

        boolean abortBattle = attacker.die() || defender.die();
        if (!abortBattle && !current.isPlayerControlled()) {
            abortBattle = takeAction(aiChooseAction());
        }
        gui.update();
        return abortBattle;
    }

    private BattleAction aiChooseAction() {
        BattleAction act;
        double c = rand.nextDouble();
        if (c <= 0.33D) {
            act = BattleAction.ATTACK;
        } else if (c > 0.33D && c <= 0.67D) {
            act = BattleAction.DEFEND;
        } else {
            act = BattleAction.CAST_SPELL;
        }
        //There is no retreat!
        return act;
    }

    /**
     * Get the defender of the battle.
     *
     * @return The defending actor of the battle.
     */
    public Actor getDefender() {
        return defender;
    }

    /**
     * Get the attacker of the battle.
     *
     * @return The attacking actor of the battle.
     */
    public Actor getAttacker() {
        return attacker;
    }

    /**
     * Get the actor whose turn it is.
     *
     * @return The current actor.
     */
    public Actor getCurrent() {
        return current;
    }
}
