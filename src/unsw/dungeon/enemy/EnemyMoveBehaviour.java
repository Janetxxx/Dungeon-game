package unsw.dungeon.enemy;
import unsw.dungeon.*;
import java.util.Iterator;
import unsw.dungeon.player.*;

/**
 * if there exists different enemy which behaviours differently, then use instancof the make different ones
 */
public class EnemyMoveBehaviour extends EnemyBehaviour {
    
    /**
     * if meets wall in next move, then goto next possible move that has the least distance with player
     * @param x
     * @param y
     * @param direction
     * @param enemy
     * @param player
     * @return
     */
    public boolean encounterObstacle(int x, int y, int direction, EnemyBase enemy, Player player) {

        Iterator<Entity> iter = player.getEntitiesFromDungeon().iterator();

        while (iter.hasNext()) {
            Entity e = iter.next();
            
            if (e != null && e.getX() == x && e.getY() == y) {
                if (!e.canMoveThrough(e)) 
                    return true;    
            } 
        }
        normalMove(enemy, direction, x, y);
        attack(player, enemy);
        
        // if (x == prevX && y == prevY && !(countObstacleAround(x, y, player) == 3)) return true; // path choosing
        //setPrevPosition(enemy.getX(), enemy.getY());
        return false;
    }


    //private int prevX, prevY;

    // private void setPrevPosition(int x, int y) {
    //     prevX = x;
    //     prevY = y;
    // }
    // private int countObstacleAround(int x, int y, Player player) {
    //     int count = 0;
    //     if (isObstaclesAround(x + 1, y, player)) count++;
    //     if (isObstaclesAround(x - 1, y, player)) count++;
    //     if (isObstaclesAround(x, y - 1, player)) count++;
    //     if (isObstaclesAround(x, y + 1, player)) count++;
    //     return count;
    // }
    // private boolean isObstaclesAround(int x, int y, Player player) {
        
    //     for (Entity e : player.getEntitiesFromDungeon()) {
    //         if (e != null && e.getX() == x && e.getY() == y && !e.canMoveThrough(e)) 
    //             return true;
    //     }
    //     return false;
    // }
}