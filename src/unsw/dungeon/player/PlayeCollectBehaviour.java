package unsw.dungeon.player;
import unsw.dungeon.*;

import java.util.Iterator;

public class PlayeCollectBehaviour implements PlayerBehaviour {
    @Override
    public void behaviour(PlayerBase player, String direction, Entity collectable, Iterator<Entity> entites) {
        player.addCollections(collectable);
        collectable.setDisappear();
        // collectable.setNode(null);       
    }   
}