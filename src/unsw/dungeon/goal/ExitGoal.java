package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.entity.*;
import unsw.dungeon.Subject;

public class ExitGoal extends Goal {
    
    public ExitGoal(Dungeon dungeon) {
        super(dungeon);
    }
    
    @Override
    public void update(Subject obj) {
        
        // if (isExitGoalCompleted())
            //System.out.println("complete Exit goal");
        setIsGoalCompleted(isExitGoalCompleted());
    }
    private boolean isExitGoalCompleted() {
        Dungeon dungeon = getDungeon();
        for (Entity e : dungeon.getEntities()) {
            if (e instanceof Exit && dungeon.isOnSamePostion(e, dungeon.getPlayer())) {
                return true;
            }
        }
        return false;
    }
}