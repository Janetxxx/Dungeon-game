package test;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import unsw.dungeon.Dungeon;
import unsw.dungeon.player.*;
import unsw.dungeon.goal.Goal;

/**
 * make tests for each entity since it satisfied open and close principle 
 */
public class TestFixture {
    public Dungeon dungeon = null;
	public Player player = null;
    public Goal goal = null;
    public int height = 30, width = 30;
    
    @BeforeEach
    public void setup() {

        JSONObject goal = new JSONObject();
        goal.put("goal", "exit");
        dungeon = new Dungeon(width, height, goal);
        player = new Player(dungeon, 2, 2);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
    }
}
