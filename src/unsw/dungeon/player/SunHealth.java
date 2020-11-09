package unsw.dungeon.player;
import unsw.dungeon.*;
import unsw.dungeon.player.*;

import java.util.Iterator;

public class SunHealth extends Entity {

    public SunHealth(int x, int y) {
        super(x, y);
        
    }

    @Override
    public void interactionWithPlayer(Player player, String direction, Entity items, Iterator<Entity> entites) {
        
        player.changeHealth(1);
        player.move(direction);
        player.collect(items);
    }
    
}