package actors;

import logic.Tunnel;

/**
 * Player for the game
 */
public final class Player {

    private final String name;
    private Tunnel myBlock;

    public Player(String name) {
        this.name = name;
    }

    public final Tunnel getMyBlock() {
        return myBlock;
    }

    public final void setMyBlock(Tunnel myBlock) {
        this.myBlock = myBlock;
    }

    @Override
    public final String toString() {
        return name;
    }
}
