package unsw.dungeon.goal;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Subject;

public class AndGoal extends Goal {
    private List<Goal> subgoals = new ArrayList<>();

    public AndGoal(Dungeon dungeon) {
        super(dungeon);
    }

    @Override
    public void addSubgoal(Goal goal) {
        if (subgoals.contains(goal)) return;
        subgoals.add(goal);
    }
    public List<Goal> getSubGoals() {
        return subgoals;
    }
    @Override
    public void update(Subject obj) {
        //System.out.println("Reach AndGoal");
        for (Goal g : subgoals) {
            g.update(obj);
        }
        setIsGoalCompleted(ifAndGoalCompleted());
    }
    private boolean ifAndGoalCompleted() {
        for (Goal g : subgoals) {
            if (!g.getIsGoalCompleted()) 
                return false;
        }
        return true;
    }
    
}