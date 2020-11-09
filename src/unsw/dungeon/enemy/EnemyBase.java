package unsw.dungeon.enemy;

import unsw.dungeon.*;
import unsw.dungeon.Character;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.player.*;

/**
 * all enemy are observer
 */
public abstract class EnemyBase extends Character implements Observer {
    private Player player;
    private EnemyBehaviour enemyBehaviour; // strategy pattern, if more behavirous is added
    private boolean ifRunAway;
    private int runAwayCyclecount = 0; 
    private AnimationTimer timer = null;
    private BooleanProperty visible;
    
    public EnemyBase(int x, int y) {
        super(x, y);
        this.visible = new SimpleBooleanProperty(true);
    }

    abstract public void backgroundRun();

    public boolean isDiedContion() {
        if (player == null) {
            return false;
        }
        if (this instanceof Boss) {
            if (player.hasCollection("Sword") || player.hasCollection("InvincibilityPotion")) {
                Boss b = (Boss) this;
                b.changeHealth(-1);
                if (b.getHealth().get() <= 0) return true;
            }
            return false;
        }
        return player.hasCollection("Sword") || player.hasCollection("InvincibilityPotion");
    }

    /**
     * either player meets enemy or enemy meets player
     */
    @Override
    public void die() {
        if (timer != null) timer.stop();
        getPlayer().notifyObservers();
        //getPlayer().detach(this); // enemy will not update
        this.setDisappear();
    }

    public void update(Subject obj) {
        if (!this.isInDungeon().get()) return; // enmey die
        if (obj instanceof Player) {
            setPlayer((Player) obj); // update player position
            dungeon = player.getDungeon();
        }
        if (getTimer() == null) 
            backgroundRun();   // only run enemy once, then it continues 

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
    // 
    ////////////////////////////// getter and setter ///////////////////////////////////////////
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public EnemyBehaviour getEnemyBehaviour() {
        return enemyBehaviour;
    }

    public void setEnemyBehaviour(EnemyBehaviour enemyBehaviour) {
        this.enemyBehaviour = enemyBehaviour;
    }

    public boolean isIfRunAway() {
        return ifRunAway;
    }

    public void setIfRunAway(boolean ifRunAway) {
        this.ifRunAway = ifRunAway;
    }

    public AnimationTimer getTimer() {
        return timer;
    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    public int getRunAwayCyclecount() {
        return runAwayCyclecount;
    }

    public void setRunAwayCyclecount(int runAwayCyclecount) {
        this.runAwayCyclecount = runAwayCyclecount;
    }

    public void addVisible() {
        visible.setValue(true);
    }

    public BooleanProperty getVisible() {
        return visible;
    }

}