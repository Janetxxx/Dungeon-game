package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.player.*;
import unsw.dungeon.entity.*;
import org.json.JSONObject;

public class PortalTest {

    @Test
    public void testCanTeleport(){
        // create dungeon of size 18 x 16 with exit goal
        JSONObject goal = new JSONObject();
        goal.put("goal", "exit");
        Dungeon dungeon = new Dungeon(18, 16, goal);
        // create player in position x = 10, y = 10
        Player player = new Player(dungeon, 10, 10);
        dungeon.setPlayer(player);
        // create portal with name "portal_1", and id "1"
        Portal portal1 = new Portal(9, 10, 1, dungeon, "portal_1");
        dungeon.addEntity(portal1);
        // create portal with name "portal_2", and id "1"
        Portal portal2 = new Portal(6, 6, 1, dungeon, "portal_2");
        dungeon.addEntity(portal2);
        // player move left
        player.moveLeft();
        // player one will encounter "portal_1" 
        // player will be teleported to "portal_2"
        // becanse "portal_1" and "portal_2" have the same id "1" 
        assertTrue(player.getX() == 6);
        assertTrue(player.getY() == 6);
     
    }

    @Test
    public void testCanNotTeleport(){
        // create dungeon of size 18 x 16 with exit goal
        JSONObject goal = new JSONObject();
        goal.put("goal", "exit");
        Dungeon dungeon = new Dungeon(18, 16, goal);
        // create player in position x = 10, y = 10
        Player player = new Player(dungeon, 10, 10);
        dungeon.setPlayer(player);
        // create portal with name "portal_1", and id "1"
        Portal portal1 = new Portal(9, 10, 1, dungeon, "portal_1");
        dungeon.addEntity(portal1);
        // create portal with name "portal_2", and id "2"
        Portal portal2 = new Portal(6, 6, 2, dungeon, "portal_2");
        dungeon.addEntity(portal2);
        // player move left
        player.moveLeft();
        // player one will encounter "portal_1" 
        // but can not be teleported to "portal_2"
        // becanse "portal_1" and "portal_2" have different id  
        // player still in original position 
        assertTrue(player.getX() == 10);
        assertTrue(player.getY() == 10);
     
    }

}