package unsw.dungeon.player;

import java.util.Iterator;
import unsw.dungeon.*;

public class Skill1 extends PlayerBaseDecorator implements Observer {
    private boolean isMoving = false;
    public Skill1(int x, int y) {
        super(x, y);
        setName("Skill1");
    }

    @Override
    public void interactionWithPlayer(Player player, String direction, Entity items, Iterator<Entity> entites) {
        

    }

    @Override
    public void update(Subject obj) {
        if (obj instanceof Player) {
            Player p = (Player) obj;
            this.x().set(p.getX());
            this.y().set(p.getY());
        }

    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

}