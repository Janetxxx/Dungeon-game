package unsw.dungeon.goal;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Observer;
import unsw.dungeon.Subject;

public class Goal implements Observer {

    private Goal goal; // reversed for create a goal, not used for others
    private boolean isGoalCompleted;
    private Dungeon dungeon;

    public Goal(Dungeon dungeon) {
        isGoalCompleted = false;
        this.dungeon = dungeon;
    }
    public Goal(String goalName) {
        // add basic goal here
    }
    public Goal(JSONObject goalObject, Dungeon dungeon) {
        goal = setGoals(goalObject, dungeon);
    }
    
    /**
     * FIXME: if more goals added, modified here
     * @param subGoals
     */
    private Goal setGoals(JSONObject goalObject, Dungeon dungeon) {
         
        String goalType = goalObject.getString("goal");
		Goal goal = new Goal(dungeon); 
        boolean isSubGoals = false;
        switch (goalType) {
        
        case "AND":
            goal = new AndGoal(dungeon);
            isSubGoals = true;
            break;
		case "OR":
            goal = new OrGoal(dungeon);
            isSubGoals = true;
			break;
		case "exit":
			goal = new ExitGoal(dungeon);
			break;
		case "enemies":
			goal = new EnemyGoal(dungeon);
			break;
		case "boulders":
			goal = new BoulderGoal(dungeon);
			break;
		case "treasure":
			goal = new TreasureGoal(dungeon);
			break;
		case "wizard":
			goal = new WizardGoal(dungeon);
            break; 
        // FIXME:  add more goals here
		}

		if (isSubGoals) {
			JSONArray subGoalsArray = goalObject.getJSONArray("subgoals");
			for (int i = 0; i < subGoalsArray.length(); i++) {
				Goal subgoal = setGoals(subGoalsArray.getJSONObject(i), dungeon);
				goal.addSubgoal(subgoal);
			}
		}

		return goal;
    }

    public void addSubgoal(Goal subGoal) {
        System.err.println("Error: addSubgoal method in Goal class is not all overloaded by AndGoal / OrGoal");
    }
    public void setIsGoalCompleted(boolean bool) {
        isGoalCompleted = bool;
        getDungeon().checkGoalCompleted(); // check if finish the game
    }

    public boolean getIsGoalCompleted() {
        return isGoalCompleted;
    }
    @Override
    public void update(Subject obj) {
        System.err.println("Error: Update method in Goal class is not all overloaded by subgoals");
        
    }
    public Dungeon getDungeon() {
        return dungeon;
    }
    public Goal getGoal() {
        return goal;
    }
}
