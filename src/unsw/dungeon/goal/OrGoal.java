package unsw.dungeon.goal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Subject;

import java.util.ArrayList;
import java.util.List;

public class OrGoal extends Goal {
    private List<Goal> subgoals = new ArrayList<>();
    
    public OrGoal(Dungeon dungeon) {
        super(dungeon);
    }
    
    @Override
    public void addSubgoal(Goal goal) {
        if (subgoals.contains(goal)) return;
        subgoals.add(goal);
    }
    @Override
    public void update(Subject obj) {
        //System.out.println("Reach Orgoal");
        for (Goal g : subgoals) {
            g.update(obj);
        }
        setIsGoalCompleted(ifOrGoalCompleted());
    }
    private boolean ifOrGoalCompleted() {
        for (Goal g : subgoals) {
            if (g.getIsGoalCompleted()) 
                return true;
        }
        return false;
    }
    
}