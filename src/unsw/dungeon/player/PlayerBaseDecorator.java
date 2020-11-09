package unsw.dungeon.player;
import unsw.dungeon.enemy.*;
import unsw.dungeon.*;

public class PlayerBaseDecorator extends PlayerBase {
    private Player player;
    
    public PlayerBaseDecorator(int x, int y) {
        super(x, y);
    }

    @Override
    public void encounterObstacle(int x, int y, String direction) {}

    @Override
    public void skill() {};

    public void attack(){}
    
    public void meetEnemy() {
        for (Entity e : player.getEntitiesFromDungeon()) {
            if (e != null && this.isOnSamePosition(e) && e instanceof Boss) {
                Boss en = (Boss) e;
                en.changeHealth(-1);
                en.checkDie();
            }
            else if (e != null && this.isOnSamePosition(e) && e instanceof EnemyBase) {
                EnemyBase en = (EnemyBase) e;
                en.die();
            }
        }
    }
    ///////////////////////////////////////////////
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }; // e.g bow attach

}