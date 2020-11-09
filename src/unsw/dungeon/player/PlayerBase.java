package unsw.dungeon.player;

import unsw.dungeon.menu.*;
import unsw.dungeon.enemy.*;
import unsw.dungeon.*;
import unsw.dungeon.Character;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.entity.*;
/**
 * different player behaviours differently to differnt entities
 */
public abstract class PlayerBase extends Character implements Subject {
    public List<Entity> collections = new ArrayList<Entity>(); // items being collect put here
    public List<Observer> listObservers = new ArrayList<Observer>();
    public ArrayList<Key> keys;
    public BooleanProperty hasPotion = new SimpleBooleanProperty(false);
    public BooleanProperty hasSword = new SimpleBooleanProperty(false);
    public IntegerProperty directions = new SimpleIntegerProperty(2);
    public BooleanProperty isHurted = new SimpleBooleanProperty(false);
    public PlayerBaseDecorator itemOnHand;
    
    public PlayerBase(int x, int y) {
        super(x, y);
    }
    public void skill(){};
    public void skill2(){};
    @Override
    public void die() {
        this.setDisappear();
        dungeon.gameOver();
        notifyObservers();
    }
    @Override
    public void notifyObservers() {
        
        for (Observer ob : listObservers) {
            if (ob == null) continue;
            if (ob instanceof Knight2 || ob instanceof Knight3 || ob instanceof Knight4) continue;
            ob.update(this);
        }
        
    }
    @Override
    public void attack() {
        if (itemOnHand != null)
            itemOnHand.attack();
    }
    public abstract void encounterObstacle(int x, int y, String direction);
    /**
     * direction
     */
    public void moveUp() {
        if (getY() > 0) {
            
            encounterObstacle(getX(), getY() - 1, "UP");
        } 
    }

    public void moveDown() {
        
        if (getY() < dungeon.getHeight() - 1) {
            
            encounterObstacle(getX(), getY() + 1, "DOWN");       
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            
            encounterObstacle(getX() - 1, getY(), "LEFT");
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            
            encounterObstacle(getX() + 1, getY(), "RIGHT");
        }
    }
    
    //
    public void removeLeftEntity() {
        Iterator<Entity> iter = getEntitiesFromDungeon().iterator();
        while(iter.hasNext()) {
            Entity e = iter.next();
            if (e == null) continue;
            if (!e.isInDungeon().get()) {
                iter.remove();
            }
        }

    }
    public List<Entity> getEntitiesFromDungeon() {
        return dungeon.getEntities();
    }
    @Override
    public void interactionWithPlayer(Player player, String direction, Entity items, Iterator<Entity> entites) {}

    ///////////////////////////////////////// getter and setter /////////////////////////////////////////////

    public BooleanProperty getHasPotion() {
        return hasPotion;
    }

    public void setHasPotion(boolean bool) {
        hasPotion.set(bool);
    }

    public BooleanProperty getHasSword() {
        return hasSword;
    }

    public void setHasSword(boolean bool) {
        hasSword.set(bool);
    }

    public IntegerProperty getDirections() {
        return directions;
    }

    public void setDirections(IntegerProperty directions) {
        this.directions = directions;
    }
    public int countGivenCollection(String name) {
        int count = 0;
        for (Entity c : collections) {
            if (c.getName().equals(name)) count++;
        }
        return count;
    }
    public Entity getGivenCollection(String name) {
        for (Entity e : collections) {
            if (e.getName().equals(name)) 
                return e;
        }
        //System.err.println("No given collecction found");
        return null;
    }

    //--------------------------check collection-------------------------

    public void removeCollection(String name) {
        if (getCollection(name) == null) return; //System.err.println("No such entity");
        collections.remove(getCollection(name)); 

    }

    public boolean hasCollection(String name) {
        
        if (getCollection(name) == null) return false;
        return true;
    }
    private Entity getCollection(String name) {
        Iterator<Entity> iter = collections.iterator();
        while (iter.hasNext()) {
            Entity e = iter.next();
            if (e.getName().equals(name)) return e; // TODO: if id added, then id has the matched for duplicate items
        }
        return null;
    }

    //-----------------------------key and door---------------------------------- 
    public ArrayList<Key> getKeys() {
		return keys;
	}

    public void addKey(Key key) {
		this.keys.add(key);
    }
    
    public void removeKey(){
        this.keys.clear();
    }

    public Key getKeyInHand() { 
        if (keys.size() == 0) return null;
        return keys.get(0); // player can carry only one key at a time
    }

    // For a given door ID that the player is trying to walk through
    // check whether door and key id match or not 
    public boolean hasKey(int doorID){
        if (getKeyInHand() == null) return false;
        if (getKeyInHand().getId() == doorID) {
            return true;
        }
        return false;
    }

    public void dropKey(){
        if (this.getKeyInHand() == null){
            PopupWindow popup = new PopupWindow("Can not drop key, no key in hand");
            popup.show();
        }
        else{ //drop key
            int keyX = this.getX();
            int keyY = this.getY();
            Key key = this.getKeyInHand();
            key.x().set(keyX);
            key.y().set(keyY);
            key.addVisible();  
            PopupWindow popup = new PopupWindow("drop key with key ID: " + key.getId());
            popup.show();
            keys.clear();
        }
    }

    //door
    // open door
    public boolean unlockDoor (Door d) {
        if(hasKey(d.getDoorID()) == true){
            d.setCanGoThrough(true);
            return true;
        }
        return false;
    }    
    public List<Entity> getCollections() {
		return collections;
	}

	public void addCollections(Entity e) {
		collections.add(e);
	}

    public Dungeon getDungeon() {
        return dungeon;
    }
    
    public void setGoalCompleteForDungeon() {
        // dungeon.setGoalComplete();
    }
    @Override
    public void attach(Observer o) {
        if (o == null) {
            System.err.println("not such given entity");
            return;
        }   
        listObservers.add(o);
        //if(!listObservers.contains(o)) 
    }
    @Override
    public void detach(Observer o) {
        listObservers.remove(o);
    }
    
    /**
     * player collect given entity
     * @param e
     */
    public void collect(Entity e) {
        PlayeCollectBehaviour collect = new PlayeCollectBehaviour();
        collect.behaviour(this, "COLLECT", e, null);
        if (e instanceof PlayerBaseDecorator)
            itemOnHand = (PlayerBaseDecorator) e;
    }
    /**
     * add listerner to player - observer pattern
     * 
     * Goal
     * Enemy
     */
    public void addPlayerListener() {
        for (Entity e : dungeon.getEntities()) {
            if (e instanceof EnemyBase || e instanceof Skill1) // all types of enemy
                attach((Observer) e);
        }
        attach((Observer) dungeon.getGoal());
    }
    
    public void removeOneEntity(Entity e) {
        if (e == null) return;
        dungeon.removeOneEntity(e);
    }

    public List<Observer> getListObservers() {
        return listObservers;
    }

    public BooleanProperty getIsHurted() {
        return isHurted;
    }

    public void setIsHurted(boolean bool) {
        isHurted.set(bool);
    }

}