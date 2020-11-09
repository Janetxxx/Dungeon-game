package unsw.dungeon.entity;

import java.util.Iterator;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.player.*;

public class Portal extends Entity {

    private int id_portal;
    private Dungeon dungeon;
    private String portal_name;

    public Portal(int x, int y, int id_portal, Dungeon dungeon, String portal_name) {
        super(x, y);
        this.id_portal = id_portal;
        this.dungeon = dungeon;
        this.portal_name = portal_name;
    }

	public int getId_portal() {
        return id_portal;
    }

    public String getName_portal() {
        return portal_name;
    }

    // teleport entity
    // para entity is the given entity what we want to teleport
    public void Teleports(Entity entity){
        for(Entity e : dungeon.getEntities()){ 
            if(e instanceof Portal){ 
                if(((Portal) e).getId_portal() == this.getId_portal()
                    && ((Portal) e).getName_portal() != this.getName_portal()){
                    //e is new position
                    int newX = e.getX();
                    int newY = e.getY();
                    entity.setX(newX);
                    entity.setY(newY);
                }
            }
        }
    }

    @Override
    public void interactionWithPlayer(Player player, String direction, Entity portal, Iterator<Entity> entities) {
        System.out.println("Portal id: " + this.getId_portal() + " name: " + this.getName_portal());
        this.Teleports(player);
    }
    

    
}