package unsw.dungeon.enemy;

import java.util.Iterator;

import javafx.animation.AnimationTimer;
import unsw.dungeon.Entity;
import unsw.dungeon.player.*;

public class Knight3 extends Boss {

    public Knight3(int x, int y) {
        super(x, y);
        setEnemyBehaviour(new EnemyMoveBehaviour());; // initially, enemy move towards player
        setIfRunAway(false);
        setName("Knight3");
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