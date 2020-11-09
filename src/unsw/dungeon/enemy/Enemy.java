package unsw.dungeon.enemy;
import unsw.dungeon.*;
import java.util.Iterator;
import unsw.dungeon.player.*;

import javafx.animation.AnimationTimer;

/**
 * later on, if new moveable entity is added, then add a new class called move entity behaviour
 */
public class Enemy extends EnemyBase {

    public Enemy(int x, int y) {
        super(x, y);
        setName("Enemy");
        setEnemyBehaviour(new EnemyMoveBehaviour());; // initially, enemy move towards player
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
    /** enemy movement run as another thread
     * enemy moves every 1 sec
     * @see https://stackoverflow.com/questions/9966136/javafx-periodic-background-task
     */
    public void backgroundRun() {
        
        Enemy en = this;
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
                                getPlayer().removeCollection("InvincibilityPotion"); // remove potions after the effective time
                                getPlayer().setHasPotion(false);
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
    
    ////////////////////////////////// getter and setter ////////////////////////////////////////////////////

    public EnemyBehaviour getState() {
        return getEnemyBehaviour();
    }

    public void setState(EnemyBehaviour state) {
        setEnemyBehaviour(state);
    }

} 