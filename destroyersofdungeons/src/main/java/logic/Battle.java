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
    private boolean isEveryoneAlive;

    public Battle(Actor attacker, Actor defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.current = attacker;
        this.rand = new Random();
        this.lastAction = BattleAction.DO_NOTHING;
        this.isEveryoneAlive = true;
    }

    public Actor getNextActor() {
        return current == attacker ? defender : attacker;
    }

    public void endBattle(DestroyersOfDungeons game) {
        attacker.die(game);
        defender.die(game);
    }

    public void nextTurn() {
        current = getNextActor();
    }

    public boolean resume() {
        return isEveryoneAlive;
    }

    public void takeAction(int action) {
        BattleAction act = BattleAction.values()[action];
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
        isEveryoneAlive = getNextActor().isAlive() && current.isAlive();
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
