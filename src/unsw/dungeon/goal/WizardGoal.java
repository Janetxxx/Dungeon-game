package unsw.dungeon.goal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Subject;

public class WizardGoal extends Goal {

    public WizardGoal(Dungeon dungeon) {
        super(dungeon);
    }
    @Override
    public void update(Subject obj) {
        
        if (getDungeon().countGivenEntity("Wizard") == 0 && !getIsGoalCompleted())
            System.out.println("complete Wizard goal");
        setIsGoalCompleted(getDungeon().countGivenEntity("Wizard") == 0);
    }
}