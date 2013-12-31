package logic;

import GUI.panels.IUpdate;
import gameobjects.actors.Actor;
import java.util.Random;

/**
 * A battle can occur between to different actors. In case the other one is not
 * player controlled, its actions are taken by java.util.Random instead.
 */
public final class Battle {

    private final IUpdate gui;
    private final Actor attacker;
    private final Actor defender;
    private final Random rand;
    private Actor current;
    private BattleAction lastAction;

    public Battle(Actor attacker, Actor defender, IUpdate gui) {
        this.attacker = attacker;
        this.defender = defender;
        this.current = attacker;
        this.gui = gui;
        this.rand = new Random();
        this.lastAction = BattleAction.DO_NOTHING;
    }

    protected Actor getNextActor() {
        return current == attacker ? defender : attacker;
    }

    protected void nextTurn() {
        current = getNextActor();
    }

    public boolean takeAction(BattleAction act) {
        switch (act) {
            case ATTACK:
                current.attack(getNextActor(), BattleAction.ATTACK);
                if (lastAction == BattleAction.DEFEND) {
                    getNextActor().attack(current, lastAction);
                }
                break;
            case FLEE:
                break;
            case DO_NOTHING:
                break;
            case CAST_SPELL:
                current.attack(getNextActor(), BattleAction.CAST_SPELL);
                break;
            case DEFEND:
                break;
            default:
                throw new AssertionError(act.name());
        }
        lastAction = act;
        nextTurn();
        gui.update();
        return attacker.die() || defender.die();
    }

    public Actor getDefender() {
        return defender;
    }

    public Actor getAttacker() {
        return attacker;
    }

    public Actor getCurrent() {
        return current;
    }
}
