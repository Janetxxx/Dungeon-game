package test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.json.JSONArray;
import org.json.JSONObject;
import unsw.dungeon.goal.Goal;
import unsw.dungeon.*;
import unsw.dungeon.entity.*;
import unsw.dungeon.enemy.*;
import unsw.dungeon.player.*;

public class GoalTest {
    public Dungeon dungeon = null;
	public Player player = null;
    public Goal goal = null;
    public int height = 30, width = 30;
    
    @Test
    public void testEnemyGoal() {
        JSONObject goal = new JSONObject();
        goal.put("goal", "enemies");
        dungeon = new Dungeon(width, height, goal);
        player = new Player(dungeon, 2, 2);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        Enemy enemy = new Enemy(10, 10);
        dungeon.addEntity(enemy);
        player.moveDown();
        enemy.die();
        player.moveDown();
        assertEquals(true, dungeon.countGivenEntity("Enemy") == 0);
        assertEquals(true, dungeon.getGoal().getIsGoalCompleted());
        
    }

    @Test
    public void testTreasureGoal() {
        JSONObject goal = new JSONObject();
        goal.put("goal", "treasure");
        dungeon = new Dungeon(width, height, goal);
        player = new Player(dungeon, 2, 2);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        Treasure t1 = new Treasure(3, 1);
        dungeon.addEntity(t1);
        player.moveDown();
        player.collect(t1);
        player.moveDown();
        assertEquals(true, dungeon.getGoal().getIsGoalCompleted());
        
    }
    @Test
    public void testSwitcheGoal() {
        JSONObject goal = new JSONObject();
        goal.put("goal", "boulders");
        dungeon = new Dungeon(width, height, goal);
        player = new Player(dungeon, 8, 8);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        Boulder boulder1 = new Boulder(8, 9, dungeon);
        dungeon.addEntity(boulder1);
        Switch switch1 = new Switch(8, 10, dungeon);
        dungeon.addEntity(switch1);
        player.moveDown();
        player.moveDown();
        assertEquals(true, dungeon.getGoal().getIsGoalCompleted());
        
    }
    @Test
    public void testAndGoal() {
        JSONArray subgoals = new JSONArray();
        JSONObject logic = new JSONObject();
        JSONObject goal = new JSONObject();
        goal.put("goal", "boulders");
        goal.put("goal", "treasure");
        goal.put("goal", "treasure");
        logic.put("goal", "AND");
        subgoals.put(goal);
        logic.put("subgoals", subgoals);
        dungeon = new Dungeon(width, height, logic);
        player = new Player(dungeon, 8, 8);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        Treasure t1 = new Treasure(8, 9);
        dungeon.addEntity(t1);
        Boulder boulder1 = new Boulder(8, 9, dungeon);
        dungeon.addEntity(boulder1);
        Switch switch1 = new Switch(8, 10, dungeon);

        dungeon.addEntity(switch1);
        player.moveDown();
        player.moveDown();

        assertEquals(true, dungeon.getGoal().getIsGoalCompleted());
        
    }

    @Test
    public void testOrGoal() {
        JSONArray subgoals = new JSONArray();
        JSONObject logic = new JSONObject();
        JSONObject goal = new JSONObject();
        goal.put("goal", "boulders");
        goal.put("goal", "treasure");
        goal.put("goal", "treasure");
        logic.put("goal", "OR");
        subgoals.put(goal);
        logic.put("subgoals", subgoals);
        dungeon = new Dungeon(width, height, logic);
        player = new Player(dungeon, 8, 8);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        Treasure t1 = new Treasure(8, 9);
        dungeon.addEntity(t1);
        Boulder boulder1 = new Boulder(8, 9, dungeon);
        dungeon.addEntity(boulder1);
        Switch switch1 = new Switch(8, 10, dungeon);

        dungeon.addEntity(switch1);
        player.moveDown();
        player.moveDown();

        assertEquals(true, dungeon.getGoal().getIsGoalCompleted());
        
    }
}