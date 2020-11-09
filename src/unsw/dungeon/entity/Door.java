package unsw.dungeon.entity;

import java.util.Iterator;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.player.*;
import unsw.dungeon.menu.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
       
    private int id_door;
    private BooleanProperty open;


    public Door(int x, int y, int id_door) {
        super(x, y);
        this.id_door = id_door;
        this.open = new SimpleBooleanProperty(false); //initial is closed door

    }
    

    public int getDoorID() {
		return id_door;
	}

    

    public BooleanProperty isOpen() {
        return open;
    }


    public void setCanGoThrough(boolean canGoThrough) {
		open.setValue(canGoThrough);
    }


    @Override
    public void interactionWithPlayer(Player player, String direction, Entity door, Iterator<Entity> entities) {
        if (player.unlockDoor(this)) {
            System.out.println("Can open the door!");
            PopupWindow popup = new PopupWindow("Can open the door");
            popup.show();
            player.move(direction);
            player.removeKey(); //when key used, key will be removed from player's hand
        }
        else{
            PopupWindow popup = new PopupWindow("Can not go through the door, door ID is: " +this.getDoorID());
            popup.show();
        }

        // once door unlocked, player can go through anytime without key
        if (this.open.get() == true){
            player.move(direction);
        }

        System.out.println("Door id: " + this.getDoorID());
    }

}