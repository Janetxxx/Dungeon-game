package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.entity.*;
import unsw.dungeon.enemy.*;
import unsw.dungeon.player.*;

/**
 * template pattern
 */
public abstract class Character extends Entity {
    public Dungeon dungeon; // enemy use dungeon from the player class
    private IntegerProperty health;
    private BooleanProperty isMoving = new SimpleBooleanProperty(false);
    
    public abstract void die();
    
    public Character(int x, int y) {
        super(x, y);
        if (this instanceof Boss)
            health = new SimpleIntegerProperty(5);
        else if (this instanceof EnemyBase) {
            health = new SimpleIntegerProperty(1);
        }
        else if (this instanceof Player)
            health = new SimpleIntegerProperty(3);
        
    }
    public void attack() {};
    public void move(String direction) {
        direction = direction.toUpperCase();
        switch(direction) {
            case "UP":
                if (getY() > 0) 
                    y().set(getY() - 1);
                break;
            case "DOWN":
                if (getY() < getDungeon().getHeight() - 1) 
                    y().set(getY() + 1);
                break;
            case "LEFT":
                if (getX() > 0) 
                    x().set(getX() - 1);
                break;
            case "RIGHT":
                if (getX() < getDungeon().getWidth() - 1) 
                    x().set(getX() + 1);
                break;
        }
    }
    public void moveGivenDirection(int direction) {
        switch (direction) {
            case 1:
                if (getY() > 0) 
                    y().set(getY() - 1);
                break;
            case 2:
                if (getY() < getDungeon().getHeight() - 1) 
                    y().set(getY() + 1);
                break;
            case 3:
                if (getX() > 0) 
                    x().set(getX() - 1);
                break;
            case 4:
                if (getX() < getDungeon().getWidth() - 1) 
                    x().set(getX() + 1);
                break;
            default:
                break;
        }
    }
    public void changeHealth(int i) {
        // if (i == 0) return;
        if (this instanceof Player) {
            if (i < 0 && -i >= health.get()) { // player die
                Player p = (Player) this;
                health.set(health.get() + i);
                p.getDungeon().gameOver();
                return;
            }
            if (i > 0 && health.get() > 5) { // stop over healing
                return;
            }
            PlayerBase p = (PlayerBase) this;
            p.isHurted.set(true);
        }
        

        health.set(health.get() + i);
    }
    public boolean isInWall() {
        
        for (Entity e : this.dungeon.getEntities()) {
            if (e != null && e instanceof Wall && this.isOnSamePosition(e)) {
                return true;
            }
        }
        return false;
    }
    public void moveBoss(String direction, Player player) {
        direction = direction.toUpperCase();
        switch(direction) {
            case "UP":
                if (getY() > 0)  {
                    y().set(getY() - 1);
                }
                    
                break;
            case "DOWN":
                if (getY() < getDungeon().getHeight() - 1) {
                    y().set(getY() + 1);
                }
                    
                break;
            case "LEFT":
                if (getX() > 0) {
                    x().set(getX() - 1);
                }
                    
                break;
            case "RIGHT":
                if (getX() < getDungeon().getWidth() - 1) {
                    x().set(getX() + 1);
                }
                    
                break;
        }
        meetPlayerCheck(this, player);
    }
    private void meetPlayerCheck(Entity e, Player player) {
        if (e.isOnSamePosition(player)) {
            player.changeHealth(-1);
        }
    }
    ///////////////////////////////////////////////////// getter and setter /////////////////////////////////////////
    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }


    public IntegerProperty getHealth() {
        return health;
    }

    public BooleanProperty getIsMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean bool) {
        isMoving.set(bool);
    }

    
}