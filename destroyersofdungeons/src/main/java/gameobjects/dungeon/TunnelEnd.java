package gameobjects.dungeon;

import localisation.Dictionary;

final class TunnelEnd extends Tunnel {

    TunnelEnd(int id) {
        super(id, Dictionary.getValue("TUNNEL_START", id));
    }

    @Override
    protected boolean isEndBlock() {
        return true;
    }
}
