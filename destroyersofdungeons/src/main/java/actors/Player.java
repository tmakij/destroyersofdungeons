package actors;

import logic.Tunnel;

/**
 * Player for the game
 */
public final class Player {

    private final String name;
    private Tunnel myBlock;
    private static int ids = 0;
    private final int id;

    public Player(String name) {
        this.name = name;
        this.id = ++ids;
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

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.id;
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (Player.class != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return this.id == other.id;
    }
}
