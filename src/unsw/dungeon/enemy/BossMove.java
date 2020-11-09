package unsw.dungeon.enemy;

import java.util.Iterator;
import java.util.List;

import unsw.dungeon.Entity;
import unsw.dungeon.player.Player;

public class BossMove extends EnemyBehaviour {
    private List<EnemyBase> whole;
    
    public BossMove(List<EnemyBase> whole) {
        this.whole = whole;
    }
    @Override
    public boolean encounterObstacle(int x, int y, int direction, EnemyBase enemy, Player player) {

        Iterator<Entity> iter = player.getEntitiesFromDungeon().iterator();

        while (iter.hasNext()) {
            Entity e = iter.next();
            
            if (e != null && e.getX() == x && e.getY() == y) {
                if (!e.canMoveThrough(e)) 
                    return true;    
            } 
        }
        
        // move as a whole
        
        for (EnemyBase e : whole) {
            if (direction == 0) {
                normalMove(e, direction, e.getX() - 1, e.getY()); 
            } else if (direction == 1) {
                normalMove(e, direction, e.getX() + 1, e.getY()); 
            } else if (direction == 2) {
                normalMove(e, direction, e.getX(), e.getY() - 1); 
            } else {
                normalMove(e, direction, e.getX(), e.getY() + 1); 
            }
            
            normalMove(enemy, direction, x, y);
            attack(player, e);
            
        }
        attack(player, enemy);
        
        // if (x == prevX && y == prevY && !(countObstacleAround(x, y, player) == 3)) return true; // path choosing
        //setPrevPosition(enemy.getX(), enemy.getY());
        return false;
    }
    
}