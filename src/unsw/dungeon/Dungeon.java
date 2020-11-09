/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import unsw.dungeon.goal.Goal;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.entity.*;
import unsw.dungeon.menu.*;
import unsw.dungeon.enemy.*;
import unsw.dungeon.player.*;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player; // put it outside instead of in entities maybe because it is the star role
    private IntegerProperty triggeredSwitchCount;
    private Goal goal; 
    private BooleanProperty isGoalSuccess = new SimpleBooleanProperty(false);
    private BooleanProperty isGameOver = new SimpleBooleanProperty(false);

    // goalLogic e.f AND / OR
    public Dungeon(int width, int height,JSONObject goalObject) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        // this.goals = new ArrayList<Objective>();
        // this.goalDefine = goalDefine;
        this.triggeredSwitchCount = new SimpleIntegerProperty(0);

        goal = new Goal(goalObject, this);
        goal = goal.getGoal(); // the goal inside the goal class
        
    }
    
    public Entity getGivenEntity(String name) {
        for (Entity e : entities) {
            if (e.getName().equalsIgnoreCase(name)) return e;
        }
        return null; // shouldnt get here
    }

    public boolean isEdgeWall(Entity e) {
        if (e instanceof Wall && (e.getX() == 0 || e.getY() == 0 || e.getX() == getWidth() - 1
            || e.getY() == this.getHeight() - 1)) {
                return true;    
            }
            
        return false;
    }
    // ------------------------------------------------------------------------------
    //                          below is getter and setter
    // -------------------------------------------------------------------------------

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    public void removeOneEntity(Entity e) {
        entities.remove(e);
    }
    public Goal getGoal() {
        return goal;
    }
    // check wether two entities on the same square
    public boolean isOnSamePostion(Entity e1, Entity e2){
        if((e1.getX() == e2.getX()) && (e1.getY() == e2.getY())){
            return true;
        }
        return false;
    }


    //------------------------Goal-----------------------


    //--------------check whether boulder can move------------

    // check the spot adjacent to the boulder to see if it can move
    // x and y is the position the boulder wants to move to
    // return true if the boulder can move
    public boolean checkSpaceBehindBoulder(int x, int y){
        Entity e = getEntity(x, y);
        if (x < 0 || x >= getWidth()) return false;
        if (y < 0 || y >= getHeight()) return false;
        if (e == null || e instanceof Switch){
            return true;
        }
        return false;
    }

    // get entity on a given position
    public Entity getEntity (int x, int y){
        Entity entity = null;
        for(Entity e: entities){
            if(e.getX() == x && e.getY() == y && !(e instanceof Player)){
                entity = e;
            }
        }
        return entity;
    }

    // getter and setter of count the number of switch in dungeon
	public IntegerProperty getTriggeredSwitchCount() {
		return triggeredSwitchCount;
	}
	
	public void setTriggeredSwitchCount(int value) {
		this.triggeredSwitchCount.set(value);;
	}
    // public void debug() {
    //     // debugg, assume start with and logic
    //     if (goal instanceof AndGoal) {
    //         AndGoal g = (AndGoal) goal;
    //         List<Goal> tmp = g.getSubGoals();
    //         int i = 0;
    //         for (Goal gg : tmp) {
    //             i++;
    //         }
    //         System.out.println(i);
    //     //
    //     }
        
    // }
    //
    public int countGivenEntity(String name) {
        int count = 0;
        for (Entity e : entities) {
            if (e != null && e.getName().equals(name)) count++;
        }
        return count;
    }
    public int countEnemies() {
        int count = 0;
        for (Entity e : entities) {
            if (e != null && e instanceof EnemyBase) count++;
        }
        return count;
    }
    /**
     * if completed, pop up win sign
     */
    public void checkGoalCompleted() {
        if (goal.getIsGoalCompleted()) {
            for (Entity e : getEntities()) {
                if (e != null) {
                    e.setDisappear();  
                }
            }
            isGoalSuccess.set(true);
        }

    }
    public void gameOver() {
        for (Entity e : getEntities()) {
            if (e != null) {
                e.setDisappear();  
            }
        }
        
        setGameStop(true);
        // System.out.println("The game is finished, u lose the game, game is finished now");
        PopupWindow popup = new PopupWindow("The game is finished, u lose the game, game is finished now");
        popup.show();
        isGameOver.set(true);
        
        //System.exit(0);
    }
    /**
     * since currently onlu enemy move, so set enemy stops
     */
    public void setGameStop(boolean bool) {
        for (Entity e : entities) {
            if (e instanceof EnemyBase) {
                EnemyBase en = (EnemyBase) e;
                if (en.getTimer() != null) {
                    if (bool)
                        en.getTimer().stop();
                    else 
                    en.getTimer().start();;
                }
                    
            }
        }
        
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
    public Entity getEntityGivenPosition(int x, int y) {
        for (Entity e : entities) {
            if (e != null && e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        //System.err.println("No such entity given position");
        return null;
    }

    public BooleanProperty getIsGoalSuccess() {
        return isGoalSuccess;
    }

    public BooleanProperty getIsGameOver() {
        return isGameOver;
    }

}
