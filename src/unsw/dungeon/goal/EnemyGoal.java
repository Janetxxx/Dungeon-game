package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Subject;

public class EnemyGoal extends Goal {

    public EnemyGoal(Dungeon dungeon) {
        super(dungeon);
    }
    @Override
    public void update(Subject obj) {
        
        if (getDungeon().countEnemies() == 0 && !getIsGoalCompleted())
            System.out.println("complete Enemy goal");
        setIsGoalCompleted(getDungeon().countEnemies() == 0);
    }
}