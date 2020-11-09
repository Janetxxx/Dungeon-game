package test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.*;
import unsw.dungeon.player.*;

import org.json.JSONObject;


public class DoorTest {
    @Test
    public void testWithoutKey(){
        // create dungeon of size 18 x 16 with exit goal
        JSONObject goal = new JSONObject();
        goal.put("goal", "boulder");
        Dungeon dungeon = new Dungeon(18, 16, goal);
        // create player in position x = 10, y = 10
        Player player = new Player(dungeon, 10, 10);
        dungeon.setPlayer(player);
        // create door with id 1
        Door door = new Door(13, 10, 1);
        dungeon.addEntity(door);
        player.moveRight();
        player.moveRight();
        assertTrue(player.getX() == 12);
        assertTrue(player.getY() == 10);
        // player can not move to right 
        // door block player's way
        player.moveRight();
        assertTrue(player.getX() == 12);
        assertTrue(player.getY() == 10);
        // door still be closed
        assertTrue(door.isOpen().get() == false);
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

    @Test
    public void testKeyCanNotOpenDoor(){
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
        // create door with id 2
        Door door = new Door(13, 10, 2);
        dungeon.addEntity(door);
        player.moveRight();
        // key can not open door
        // key's id = 1, door's id = 2
        player.unlockDoor(door);
        assertTrue(door.isOpen().get() == false);
    }


}