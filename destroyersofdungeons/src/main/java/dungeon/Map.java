package dungeon;

import java.util.ArrayList;

/**
 * The map holds all the corridors.
 */
public final class Map {

    private final int minSize;
    private final int maxSize;
    private final ArrayList<Corridor> corridors = new ArrayList<>();
    
    public Map(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }
    
    public Tunnel getFirstBlock(){
        return corridors.get(0).getStartBlock();
    }
    
    public void generate(){
        Corridor c = new Corridor(10);
        c.generate();
        corridors.add(c);
    }
}
