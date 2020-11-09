package unsw.dungeon.entity;

import java.util.Iterator;

import unsw.dungeon.Entity;
import unsw.dungeon.player.*;
import unsw.dungeon.menu.*;
/**
 * Treasure
 */
public class Treasure extends Entity {

    public Treasure(int x, int y) {
        super(x, y);
        setName("Treasure");
    }

    @Override
    public void interactionWithPlayer(Player player, String direction, Entity treasure1, Iterator<Entity> entities) {
        
        player.move(direction);
        player.collect(treasure1);
        PopupWindow popup = new PopupWindow("Treasure picked up");
        popup.show();
        player.notifyObservers();
    }

}