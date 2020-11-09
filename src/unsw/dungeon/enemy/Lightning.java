package unsw.dungeon.enemy;

import unsw.dungeon.*;
import unsw.dungeon.Character;
import unsw.dungeon.player.*;

import java.util.Iterator;

import javafx.animation.AnimationTimer;

public class Lightning extends Character {
    
    public Lightning(int x, int y) {
        super(x, y);
        setName("Lightning");
    }

    @Override
    public void die() {

    }

    @Override
	public void interactionWithPlayer(Player player, String direction, Entity items, Iterator<Entity> entites) {
        player.move(direction);
        player.changeHealth(-1);
	}
    public void backgroundRun(Player player, Boss b) {
        BackendTask t = new BackendTask();
        t.set_move_task(this, player);
        SideTimer timer = new SideTimer();
        this.getNode().setVisible(true);
        this.setIsMoving(true);
        this.setX(b.getX());
        this.setY(b.getY());

        timer.makeCycleTimer(500_000_000L, 5, t, "lightning");
    }
}