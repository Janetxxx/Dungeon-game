package unsw.dungeon.enemy;

import java.util.Iterator;

import javafx.animation.AnimationTimer;
import unsw.dungeon.*;
import unsw.dungeon.player.*;
public class Mushroom extends EnemyBase {

    public Mushroom(int x, int y) {
        super(x, y);
        setName("Mushroom");
        setEnemyBehaviour(new EnemyJumpBehaviour());; // initially, enemy move towards player
        setIfRunAway(false);
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
        
        Mushroom en = this;
        if (!en.isInDungeon().get()) return;
        if (getTimer() != null) getTimer().stop();
        setRunAwayCyclecount(0); // the variable in the timer has to be final, so I can only put the count here
        setTimer(
            new AnimationTimer() {

                private long lastToggle;

                @Override
                public void handle(long now) {
                    
                    if (lastToggle == 0L) {
                        lastToggle = now;
                        en.getEnemyBehaviour().behaviour(en, getPlayer());
                    } 
                    else {
                        long diff = now - lastToggle;
                        if (diff >= 1_000_000_000L) { // 500,000,000ns == 500ms, execute every 1s, here
                            if (getRunAwayCyclecount() == 4 && isIfRunAway()) {
                                setIfRunAway(false);
                            }
                            en.getEnemyBehaviour().behaviour(en, getPlayer());
                            lastToggle = now;
                            setRunAwayCyclecount(getRunAwayCyclecount() + 1);
                        } 
                    }
                }
            });
        getTimer().start();
    }    
}