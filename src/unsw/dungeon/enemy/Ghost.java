package unsw.dungeon.enemy;

import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.*;
import unsw.dungeon.player.*;

public class Ghost extends EnemyBase {

    private BooleanProperty visible;
    
    public Ghost(int x, int y) {
        super(x, y);
        setName("Ghost");
        setEnemyBehaviour(new GhostMoveBehaviour());// initially, ghost move towards player
        setIfRunAway(false);
        this.visible = new SimpleBooleanProperty(true);
    }

    @Override
    public void backgroundRun() {
        // ghost run in the game
        Ghost ghost = this;
        if (!ghost.isInDungeon().get()) return;
        if (getTimer() != null) getTimer().stop();
        setRunAwayCyclecount(0); // the variable in the timer has to be final, so I can only put the count here
        setTimer(
            new AnimationTimer() {

                private long lastToggle;

                @Override
                public void handle(long now) {
                    
                    if (lastToggle == 0L) {
                        lastToggle = now;
                        ghost.getEnemyBehaviour().behaviour(ghost, getPlayer());
                    } 
                    else {
                        long diff = now - lastToggle;
                        if (diff >= 1_000_000_000L) { // 500,000,000ns == 500ms, execute every 1s, here
                            if (getRunAwayCyclecount() == 4 && isIfRunAway()) {
                                setIfRunAway(false);
                                getPlayer().removeCollection("InvincibilityPotion"); // remove potions after the effective time
                                getPlayer().setHasPotion(false);
                            }
                            ghost.getEnemyBehaviour().behaviour(ghost, getPlayer());
                            lastToggle = now;
                            setRunAwayCyclecount(getRunAwayCyclecount() + 1);
                        } 
                    }
                }
            });
        getTimer().start();


    }

    @Override
    public void interactionWithPlayer(Player player, String direction, Entity ghost, Iterator<Entity> entites) {
        // when player encounter ghost
        player.notifyObservers();
        if (isDiedContion()) { // player can kill ghost in the same way as kill enemy
            player.move(direction);
            EnemyBase e = (EnemyBase) ghost;
            e.die();
        } 
        else { //if player encounter ghost, player health point -1
            player.changeHealth(-1);
        }  
        player.move(direction);
    }
    
    public void addVisible() {
        visible.setValue(true);
    }

    public BooleanProperty getVisible() {
        return visible;
    }



}