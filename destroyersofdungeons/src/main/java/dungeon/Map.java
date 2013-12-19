package dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The map holds all the corridors.
 */
public final class Map {

    private final int minSize;
    private final int maxSize;
    private final List<Corridor> corridors = new ArrayList<>();
    
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
