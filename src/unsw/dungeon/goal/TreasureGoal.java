package unsw.dungeon.goal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Subject;

public class TreasureGoal extends Goal {
    
    public TreasureGoal(Dungeon dungeon) {
        super(dungeon);
    }
    
    @Override
    public void update(Subject obj) {
        
        if (getDungeon().countGivenEntity("Treasure") == 0 && !getIsGoalCompleted())
            System.out.println("complete treasure goal");
        setIsGoalCompleted(getDungeon().countGivenEntity("Treasure") == 0);
    }
}