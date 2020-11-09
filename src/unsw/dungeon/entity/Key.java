package unsw.dungeon.entity;

import java.util.Iterator;

import unsw.dungeon.*;
import unsw.dungeon.Entity;
import unsw.dungeon.player.*;
import unsw.dungeon.menu.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Key extends Entity {


    private int id_key;
    private BooleanProperty visible;

    public Key(int x, int y, int id_key) {
        super(x, y);
        this.id_key = id_key;
        this.visible = new SimpleBooleanProperty(true);
        setName("Key");
    }

    public int getId() {
		return this.id_key;
	}

    @Override
    public void interactionWithPlayer(Player player, String direction, Entity key, Iterator<Entity> entities) {
        
        player.move(direction);
        player.addKey(this);
        System.out.println("Key id: " + player.getKeyInHand().getId());
        if(player.getKeys().size() < 2){
            player.collect(key);
            visible.setValue(false);
            PopupWindow popup = new PopupWindow("Key picked up with ID: " + this.getId());
            popup.show();
        }
    }

    public void addVisible() {
        visible.setValue(true);
    }

    public BooleanProperty getVisible() {
        return visible;
    }
    
}