package unsw.dungeon.entity;

import java.util.Iterator;

import unsw.dungeon.Entity;
import unsw.dungeon.player.*;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
        setName("Wall");
    }
    /**
     * player cannot move through the wall
     */
    @Override
    public void interactionWithPlayer(Player player, String direction, Entity items, Iterator<Entity> entites) {
        if (player.getMoveThroughWall().get()) player.move(direction);
    }

}
