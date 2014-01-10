package gameobjects.dungeon;

import localisation.Dictionary;

/**
 * A tunnel which starts or ends a corridor.
 */
final class TunnelEnd extends Tunnel {

    /**
     * Create a new TunnelEnd with a unique id.
     *
     * @param id A unique id.
     */
    TunnelEnd(int id) {
        super(id);
    }

    @Override
    protected boolean isEndBlock() {
        return true;
    }

    @Override
    public String toString() {
        return Dictionary.getValue("TUNNEL_START", id);
    }
}
