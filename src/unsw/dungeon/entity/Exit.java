package unsw.dungeon.entity;

import java.util.Iterator;

import unsw.dungeon.Entity;
import unsw.dungeon.player.*;

public class Exit extends Entity {

    public Exit(int x, int y) {
        super(x, y);
        setName("Exit");
    }

    @Override
    public void interactionWithPlayer(Player player, String direction, Entity key, Iterator<Entity> entities) {
        player.move(direction);
    }
    
}
