package unsw.dungeon.enemy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.AnimationTimer;
import unsw.dungeon.Entity;
import unsw.dungeon.Subject;
import unsw.dungeon.player.*;

/**
 * only knight one is updated with player
 */
public class Knight1 extends Boss {

    public Knight1(int x, int y) {
        super(x, y);
        
        setIfRunAway(false);
        setName("Knight1");
    }
    
    @Override
    public void update(Subject obj) {
        if (!this.isInDungeon().get()) return; // enmey die
        
        if (obj instanceof Player) {
            setPlayer((Player) obj); // update player position
            dungeon = getPlayer().getDungeon();
            setWholeOthers();
            updateWhole(); // update all
            setEnemyBehaviour(new BossMove(findWhole())); // initially, enemy move towards player
            setFireball();
            
        }
        if (getTimer() == null) {
            
            backgroundRun();   // only run enemy once, then it continues 
        }
            

        // do it only once 
        if (getPlayer().hasCollection("InvincibilityPotion") && !isIfRunAway()) {
            
            setIfRunAway(true);
            backgroundRun(); // move towards to away
        }

        if (!getPlayer().hasCollection("InvincibilityPotion") && isIfRunAway()) {
            setIfRunAway(false);
            backgroundRun(); // move away refresh when double collect
        }
        
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

    public void backgroundRun() {
        
        Knight1 en = this;
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
    private List<EnemyBase> findWhole() {
        List<EnemyBase> tmp = new ArrayList<>();
        tmp.add((EnemyBase)getPlayer().getDungeon().getGivenEntity("Knight2"));
        tmp.add((EnemyBase)getPlayer().getDungeon().getGivenEntity("Knight3"));
        tmp.add((EnemyBase)getPlayer().getDungeon().getGivenEntity("Knight4"));
        
        return tmp;
    }
    private void setFireball() {
        
        Fireball f = (Fireball)getPlayer().getDungeon().getGivenEntity("Fireball");
        if (!f.getIsMoving().get()) {
            f.backgroundRun(getPlayer(), this);
        f.setDungeon(getDungeon());
        }
        

        Lightning l = (Lightning)getPlayer().getDungeon().getGivenEntity("Lightning");
        if (!l.getIsMoving().get()) {
            
            l.backgroundRun(getPlayer(), this);
            l.setDungeon(getDungeon());
        }
        Alpaca a = (Alpaca)getPlayer().getDungeon().getGivenEntity("Alpaca");
        if (!a.getIsMoving().get()) {
            
            a.backgroundRun(getPlayer(), this);
            a.setDungeon(getDungeon());
        }
        Wizard w = (Wizard)getPlayer().getDungeon().getGivenEntity("Wizard");
        if (!w.getIsMoving().get()) {
            
            w.backgroundRun(getPlayer(), this);
            w.setDungeon(getDungeon());
        }
    }
}