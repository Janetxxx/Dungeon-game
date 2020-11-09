package test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.*;
import unsw.dungeon.player.*;
import org.json.JSONObject;

public class ExitTest {
    @Test
    public void testExitGoalCompleted(){
        // create dungeon of size 18 x 16 with exit goal
        JSONObject goal = new JSONObject();
        goal.put("goal", "exit");
        Dungeon dungeon = new Dungeon(18, 16, goal);
        // create player in position x = 10, y = 10
        Player player = new Player(dungeon, 10, 10);
        dungeon.setPlayer(player);
        // create exit
        Exit exit = new Exit(9, 10);
        dungeon.addEntity(exit);
        // player move left want to get into exit
        player.moveLeft();
        assert(dungeon.getGoal().getIsGoalCompleted() == true);
    }

    @Test
    public void testExitGoalNotCompleted(){
        // create dungeon of size 18 x 16 with exit goal
        JSONObject goal = new JSONObject();
        goal.put("goal", "exit");
        Dungeon dungeon = new Dungeon(18, 16, goal);
        // create player in position x = 10, y = 10
        Player player = new Player(dungeon, 10, 10);
        dungeon.setPlayer(player);
        // create exit
        Exit exit = new Exit(6, 10);
        dungeon.addEntity(exit);
        // player move left want to get into exit
        // but did not reach the exit 
        player.moveLeft();
        assert(dungeon.getGoal().getIsGoalCompleted() == false);
    }


}