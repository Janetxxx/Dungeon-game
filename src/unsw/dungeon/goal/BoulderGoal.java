package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Subject;
import unsw.dungeon.entity.*;

public class BoulderGoal extends Goal {
    
    public BoulderGoal(Dungeon dungeon) {
        super(dungeon);
    }
    
    @Override
    public void update(Subject obj) {
        
        if (checkBoulderGoal() && !getIsGoalCompleted())
            System.out.println("complete boulder goal");
        setIsGoalCompleted(checkBoulderGoal());
            
    }

    private boolean checkBoulderGoal(){
        Dungeon dungeon = getDungeon();
        int totalNumOfSwitches = 0;
        for (Entity en : dungeon.getEntities()){
            if(en instanceof Switch){
                totalNumOfSwitches++;
            }

        }
        int triggeredSwitches = 0;
        for (Entity e : dungeon.getEntities()){
            if(e instanceof Switch){
                if(((Switch) e).getTriggered() == true){
                    triggeredSwitches++;
                }
            }
        }
        if(triggeredSwitches == totalNumOfSwitches){
            return true;
        }

        return false;
    }

    /*
    private boolean checkBoulderGoal() {
        Dungeon dungeon = getDungeon();
        for (Entity e : dungeon.getEntities()) {
            for (Entity e2 : dungeon.getEntities())
                if (e instanceof Switch && e2 instanceof Boulder) {
                    if (dungeon.isOnSamePostion(e, e2) == true) {
                        return true;
                    }
                }
        }
        return false;
    }*/
}
