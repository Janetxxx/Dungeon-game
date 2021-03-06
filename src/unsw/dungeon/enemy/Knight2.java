package unsw.dungeon.enemy;

import java.util.Iterator;

import unsw.dungeon.Entity;
import unsw.dungeon.player.*;

public class Knight2 extends Boss {

    public Knight2(int x, int y) {
        super(x, y);
        setEnemyBehaviour(new EnemyMoveBehaviour());; // initially, enemy move towards player
        setIfRunAway(false);
        setName("Knight2");
    }

    @Override
    public void interactionWithPlayer(Player player, String direction, Entity enemy, Iterator<Entity> entities) {
        player.notifyObservers();
        if (isDiedContion()) {
            player.move(direction);
            EnemyBase e = (EnemyBase) enemy;
            e.die();
        } 
        else {
            player.changeHealth(-1);
        }  
        player.move(direction);
    }

    @Override
    public void backgroundRun() {
        

    }

    
}