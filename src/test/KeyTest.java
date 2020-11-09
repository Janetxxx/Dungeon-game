package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.*;
import unsw.dungeon.player.*;
import org.json.JSONObject;

public class KeyTest {

    @Test
    public void testKeepOneKey(){
        // create dungeon of size 18 x 16 with exit goal
        JSONObject goal = new JSONObject();
        goal.put("goal", "boulder");
        Dungeon dungeon = new Dungeon(18, 16, goal);
        // create player in position x = 10, y = 10
        Player player = new Player(dungeon, 10, 10);
        dungeon.setPlayer(player);
        // create key with id 1
        Key key1 = new Key(11, 10, 1);
        dungeon.addEntity(key1);
        // create key2 with id 2
        Key key2 = new Key(13, 10, 2);
        dungeon.addEntity(key2);
        // player move right to collect key
        player.moveRight();
        assertTrue(player.getX() == 11);
        assertTrue(player.getY() == 10);
        // key in player hand with id = 1
        // player collect key1
        assertTrue(player.getKeyInHand().getId() == 1);
        // player can not collect key2
        // and can not move right due to key2 block player's way
        player.moveRight();
        assertTrue(player.getX() == 12);
        assertTrue(player.getY() == 10);
        assertTrue(player.getKeyInHand().getId() != 2);
    }

    
    @Test
    public void testKeyCanOpenDoor(){
        // create dungeon of size 18 x 16 with exit goal
        JSONObject goal = new JSONObject();
        goal.put("goal", "boulder");
        Dungeon dungeon = new Dungeon(18, 16, goal);
        // create player in position x = 10, y = 10
        Player player = new Player(dungeon, 10, 10);
        dungeon.setPlayer(player);
        // create key with id 1
        Key key = new Key(11, 10, 1);
        dungeon.addEntity(key);
        player.moveRight();
        assertTrue(player.getX() == 11);
        assertTrue(player.getY() == 10);
        player.moveRight();
        assertTrue(player.getX() == 12);
        assertTrue(player.getY() == 10);
        // key in player hand with id = 1
        assertTrue(player.getKeyInHand().getId() == 1);
        // create door with id 1
        Door door = new Door(13, 10, 1);
        dungeon.addEntity(door);
        player.moveRight();
        // use key to unlock door which has same id 1
        player.unlockDoor(door);
        assertTrue(door.isOpen().get() == true);
    }

    
}