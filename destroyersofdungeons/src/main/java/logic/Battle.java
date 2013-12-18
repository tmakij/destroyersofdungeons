package logic;

import actors.Actor;
import java.util.Random;

/**
 * A battle can occur between to different actors. In case the other one is not
 * player controlled, its actions are taken by java.util.Random instead.
 */
public final class Battle {
    
    private final Actor attacker;
    private final Actor defender;
    private final Random rand;
    private Actor current;
    private BattleAction lastAction;
    
    public Battle(Actor attacker, Actor defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.current = defender;
        this.rand = new Random();
    }
    
    public Actor getNextActor() {
        return current == attacker ? defender : attacker;
    }
    
    public void nextTurn() {
        current = getNextActor();
    }
    
    public void takeAction(int action) {
        BattleAction act = BattleAction.values()[action];
        switch (act) {
            case ATTACK:
                current.attack(getNextActor());
                break;
            case FLEE:
                break;
            case DO_NOTHING:
                break;
            case CAST_SPELL:
                break;
            case DEFEND:
                break;
            default:
                throw new AssertionError(act.name());
        }
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
