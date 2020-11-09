package unsw.dungeon;

import java.util.Iterator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import unsw.dungeon.entity.*;
import unsw.dungeon.player.*;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private String name;
    private BooleanProperty isInDungeon;
    private Node node;
    /**
     * Create an entity positioned in square (x,y)
     * 
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        name = "";
        this.isInDungeon = new SimpleBooleanProperty(true);
        
    }
    /**
     * 1. Each entity class has associated hehaviour reacted with player entity
     * 2. group these behaviours by making PlayerBehaviour classes
     * 3. then the overriden method in entity class wil utilise these PlayerBehaviour class
     * 4. this achieve open and close since if PlayerBehaviour needs to be changed, then
     *    only need to open the PlayerBehaviour classes
     */
    public abstract void interactionWithPlayer(Player player, String direction, Entity items, Iterator<Entity> entites);
    /**
     * 1 - up, 2 - down, 3 - left, 4 - right
     * @param direction
     */
    
    // ------------------------------------------------------------------------------
    // below is getter and setter
    // ------------------------------------------------------------------------------
    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if (obj == null) return false;
    //     if (!(obj instanceof Entity)) return false; // for this purpose, it uses instanceof

    //     Entity other = (Entity) obj;
    //     return name.equals(other.getName());  
    // }

    public void setX(int x) {
    	x().set(x);
	}
    
	public void setY(int y) {
		y().set(y);
	}

    public BooleanProperty isInDungeon() {
        return isInDungeon;
    }
    
    public void setIsInDungeon(boolean bool) {
    	isInDungeon().set(bool);
    }

    public void setNode(Node node) {
        this.node = node;
    }
    public Node getNode() {
        return node;
    }
    public void setDisappear() {
        // if (this.getNode() != null) this.getNode().setVisible(false);
        this.isInDungeon().set(false);
        // this.x().set(0);
        // this.y().set(0);
        
    }
    public boolean canMoveThrough(Entity e) {
        if (e == null) return false;
        if (e instanceof Wall) return false;
        if (e instanceof Door) {
            Door d = (Door) e;
            return d.isOpen().get();
        }
        return true;
    }
    public boolean isOnSamePosition(Entity e) {
        return this.getX() == e.getX() && this.getY() == e.getY();
    }
    public void addX(int x) {
        this.x().set(x().get()+ x);
    }
    public void addY(int y) {
        this.y().set(y().get()+ y);
    }
}
