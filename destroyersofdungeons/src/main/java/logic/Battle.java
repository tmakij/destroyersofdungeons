package logic;

import actors.Actor;

/**
 *
 */
public final class Battle {

    private final Actor attacker;
    private final Actor defender;
    private Actor current;

    public Battle(Actor attacker, Actor defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.current = defender;
    }

    private Actor getNextActor() {
        return current == attacker ? defender : attacker;
    }

    public String nextTurn() {
        String turn;
        current = getNextActor();
        turn = "Currently in turn is " + current;
        return turn;
    }

    public String getStatus() {
        String status;
        status = "You are fighting against " + getNextActor() + "\n";
        status += "You can do the following:\n";
        status += "[0] Attack";
        status += "[1] Flee";
        return status;
    }
}
