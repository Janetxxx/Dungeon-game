package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.entity.*;
import unsw.dungeon.Dungeon;
import unsw.dungeon.player.*;
import org.json.JSONObject;

public class SwitchTest {

    @Test
    public void testTriggerSwitch(){
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
        // create switch
        Switch switch1 = new Switch(8, 10, dungeon);
        dungeon.addEntity(switch1);
        // player try to push boulder down 
        // boulder can be pushed and trigger the switch
        player.moveDown();
        assertTrue(boulder1.getX() == 8);
        assertTrue(boulder1.getY() == 10);
        assertTrue(switch1.getTriggered() == true);

    }

    @Test
    public void testUnTriggerSwitch(){
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
        // create switch
        Switch switch1 = new Switch(8, 10, dungeon);
        dungeon.addEntity(switch1);
        // player try to push boulder down 
        // boulder can be pushed and trigger the switch
        player.moveDown();
        assertTrue(boulder1.getX() == 8);
        assertTrue(boulder1.getY() == 10);
        assertTrue(switch1.getTriggered() == true);
        // player continue push boulder down
        // untrigger the switch
        player.moveDown();
        assertTrue(boulder1.getX() == 8);
        assertTrue(boulder1.getY() == 11);
        assertTrue(switch1.getTriggered() == false);
           
    }
    
}