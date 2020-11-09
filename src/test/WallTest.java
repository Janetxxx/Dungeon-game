package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.player.*;
import unsw.dungeon.entity.*;
import org.json.JSONObject;

public class WallTest {

    @Test
    public void testMovement(){
        // create dungeon of size 18 x 16 with exit goal
        JSONObject goal = new JSONObject();
        goal.put("goal", "exit");
        Dungeon dungeon = new Dungeon(18, 16, goal);
        // create player in position x = 6, y = 6
        Player player = new Player(dungeon, 6, 6);
        dungeon.setPlayer(player);
        // create wall 
        Wall wall1 = new Wall(6, 5);
		dungeon.addEntity(wall1);
		Wall wall2 = new Wall(5, 6);
		dungeon.addEntity(wall2);
		Wall wall3 = new Wall(7, 6);
        dungeon.addEntity(wall3);
        // player try to move up, left, right but still be there
        // wall block its way
        player.moveUp();
        assertTrue(player.getX() == 6);
        assertTrue(player.getY() == 6);
        player.moveLeft();
        assertTrue(player.getX() == 6);
        assertTrue(player.getY() == 6);
        player.moveRight();
        assertTrue(player.getX() == 6);
        assertTrue(player.getY() == 6);
        
    }
    
}