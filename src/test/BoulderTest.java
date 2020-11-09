package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import unsw.dungeon.entity.*;
import unsw.dungeon.Dungeon;
import unsw.dungeon.player.*;

import org.json.JSONObject;

public class BoulderTest {
    
    @Test
    public void testPushBoulder(){
        // create dungeon of size 18 x 16 with exit goal
        JSONObject goal = new JSONObject();
        goal.put("goal", "boulder");
        Dungeon dungeon = new Dungeon(18, 16, goal);
        // create player in position x = 8, y = 8
        Player player = new Player(dungeon, 8, 8);
        dungeon.setPlayer(player);
        // create boulder
        Boulder boulder1 = new Boulder(8, 9, dungeon);
		dungeon.addEntity(boulder1);
        // player try to push bouler down 
        // boulder can be pushed
        boulder1.moveBoulder(player, "DOWN");
        assertTrue(boulder1.getX() == 8);
        assertTrue(boulder1.getY() == 10);
        
    }

    @Test
    public void testCanNotPushBoulder(){
        // create dungeon of size 18 x 16 with exit goal
        JSONObject goal = new JSONObject();
        goal.put("goal", "boulder");
        Dungeon dungeon = new Dungeon(18, 16, goal);
        // create player in position x = 10, y = 10
        Player player = new Player(dungeon, 10, 10);
        dungeon.setPlayer(player);
        // create boulder
        Boulder boulder1 = new Boulder(8, 10, dungeon);
        dungeon.addEntity(boulder1);
        // create wall which behinds the boulder
        // so that player can not push
        Wall wall1 = new Wall(7, 10);
        dungeon.addEntity(wall1);
        // player move left
        player.moveLeft();
        // player try to push bouler down 
        // boulder can not be pushed
        boulder1.moveBoulder(player, "LEFT");
        assertTrue(boulder1.getX() == 8);
        assertTrue(boulder1.getY() == 10);
     
    }

}