package unsw.dungeon.enemy;
import unsw.dungeon.*;
import java.util.Arrays;
import java.util.Collections;
import unsw.dungeon.player.*;

/**
 * how enemy reacts to the player state e.g hold sword all enemy's type of
 * behaviour will be group here
 * 0 - left, 1 - right, 2 - up, 3 down
 */
// template pattern
public abstract class EnemyBehaviour {
    public boolean runAway;

    public abstract boolean encounterObstacle(int x, int y, int direction, EnemyBase enemy, Player player);

    public void behaviour(Entity enemy, Player player) {
        runAway = false;
        EnemyBase e = null;
        if (enemy instanceof EnemyBase) {
            e = (EnemyBase) enemy;
            runAway = e.isIfRunAway();
        }
        if (e == null) System.err.println("Error in enemymovebehaviour.java");
        // cast the enemy, if more enemies are added, modified here

        Double[] directions = findDirection(distances(player, e));

        for (int i = 0; i < directions.length; i++) {
            
            // 
            if (directions[i] == 0) {
                if (!encounterObstacle(e.getX() - 1, e.getY(), 0, e, player)) 
                    break;
                
            }
            else if (directions[i] == 1) {
                if(!encounterObstacle(enemy.getX() + 1, e.getY(), 1, e, player))
                    break;
                
            }
            else if (directions[i] == 2) {
                if(!encounterObstacle(enemy.getX(), e.getY() - 1, 2, e, player))
                    break;
            } 
            else if (directions[i] == 3) {
                if(!encounterObstacle(enemy.getX(), e.getY() + 1, 3, e, player))
                    break;
            }
        }
        
        
    }
    public Double[] distances(Player player, EnemyBase enemy) {
        int x1 = enemy.getX();
        int x2 = player.getX();
        int y1 = enemy.getY();
        int y2 = player.getY();

        Double disUp = calculatetDis(x1 - 1, x2, y1, y2);
        Double disDown = calculatetDis(x1 + 1, x2, y1, y2);
        Double disLeft = calculatetDis(x1, x2, y1 - 1, y2);
        Double disRight = calculatetDis(x1, x2, y1 + 1, y2);
        Double[] tmp = {disUp, disDown, disLeft, disRight};

        return tmp;
    }
    /**
     * helper function: distance between two points
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return
     */
    public Double calculatetDis(int x1, int x2, int y1, int y2) {
        return (Double)Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
    }
    /**
     * store the index of which represents the direction in order of ascending distance to player
     * @param tmp
     * @return
     */
    public Double[] findDirection(Double[] tmp) {
        Double[] sorted = tmp.clone();
        if (!runAway) {
            Arrays.sort(sorted);
        } else {
            Arrays.sort(sorted, Collections.reverseOrder());
        }
        
        Double[] result = sorted.clone(); // create new array
        for (int i = 0; i < sorted.length; i++) {
            for (int k = 0; k < tmp.length;k++) {
                if (sorted[i] == tmp[k]) {
                    result[i] = (double)k; 
                }
            }
        }
        return result;
    }
    
    /**
     * enemy move into player:
     *                          1. has sword
     * @param player
     * @param x
     * @param y
     */
    public void attack(Player player, EnemyBase enemy) { 
        
        if (player.getX() == enemy.getX() && player.getY() == enemy.getY()) {
            if (enemy.isDiedContion()) {
                enemy.die();
            }
            else {
                player.changeHealth(-1);
            }
        }
    }
    /**
     * move behaviour without meeting any obstacle
     * @param enemy
     * @param direction
     */
    public void normalMove(Entity enemy, int direction, int x, int y) {
        if (x < 0 || y < 0) return;
        enemy.x().set(x);
        enemy.y().set(y);
    }
}