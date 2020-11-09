package unsw.dungeon.player;
import unsw.dungeon.*;

import java.util.Iterator;


public interface PlayerBehaviour {
    
    /**
     * items agrument only used for e.g key not for e.g door
     * @param player
     * @param direction
     * @param items
     * @param entities
     */
    public void behaviour(PlayerBase player, String direction, Entity items, Iterator<Entity> entities); 
}