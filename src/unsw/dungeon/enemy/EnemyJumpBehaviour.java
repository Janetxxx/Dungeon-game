package unsw.dungeon.enemy;
import unsw.dungeon.*;
import java.util.Iterator;
import unsw.dungeon.player.*;

public class EnemyJumpBehaviour extends EnemyBehaviour {

    @Override
    public boolean encounterObstacle(int x, int y, int direction, EnemyBase enemy, Player player) {
        Iterator<Entity> iter = player.getEntitiesFromDungeon().iterator();
        while (iter.hasNext()) {
            Entity e = iter.next();
            
            if (e != null && e.getX() == x && e.getY() == y) {
                if (!e.canMoveThrough(e) && !isMoreObstacle(player, direction, x, y)) {
                    jump(enemy, player, direction, x, y);
                    return false; // always return false since then can exit enemybehaviour
                }
                if (!e.canMoveThrough(e)) return false;
                break;
            } 
        }
        normalMove(enemy, direction, x, y);
        attack(player, enemy);
        return false;
    }
    private void jump(EnemyBase enemy, Player player, int direction, int x, int y) {
        // if (x == 1 || x == player.getDungeon().getWidth() - 2 || y == 1 || y == player.getDungeon().getHeight() - 2)
        //     return;
        switch (direction) {
            case 0:
                if (x - 1 <= 0) return;
                enemy.x().set(x - 1);
                attack(player, enemy);
                break;
            case 1:
                if (x+1 == player.getDungeon().getWidth()) return;
                enemy.x().set(x + 1);
                attack(player, enemy);
                break;
            case 2:
                if (y-1 <= 0) return;
                enemy.y().set(y - 1);
                attack(player, enemy);
                break;
            case 3:
            if (y+1 >= player.getDungeon().getHeight()) return;
                enemy.y().set(y + 1);
                attack(player, enemy);
                break;
            default:
                break;
        }
    }
    private boolean isMoreObstacle(Player player, int direction, int x, int y) {
        
        Entity e = null;
        switch (direction) {
            case 0:
                e = player.getDungeon().getEntityGivenPosition(x - 1, y);
                
                break;
            case 1:
                e = player.getDungeon().getEntityGivenPosition(x + 1, y);
                
                break;
            case 2:
                e = player.getDungeon().getEntityGivenPosition(x, y-1);
                
                break;
            case 3:
                e = player.getDungeon().getEntityGivenPosition(x, y+1);
                
                //if (x + 1 == player.getDungeon().getWidth()) return true; // health bar
                break;
            default:
                System.err.println("Erro");
                break;
        }
        if (e == null) return false;
        if (!e.canMoveThrough(e)) {
            return true;
        }
        return false;
    }
}