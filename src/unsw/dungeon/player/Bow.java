package unsw.dungeon.player;
import unsw.dungeon.menu.*;

import java.util.Iterator;

import unsw.dungeon.*;

public class Bow extends PlayerBaseDecorator implements Observer {
    private Wind wind;
    public Bow(int x, int y) {
        super(x, y);
        setName("Bow");
        
    }
    @Override
    public void interactionWithPlayer(Player player, String direction, Entity bow, Iterator<Entity> entities) {
        if (player.countGivenCollection("Bow") == 0) {
            player.collect(bow);
            PopupWindow popup = new PopupWindow("Bow picked up, press S to use");
            popup.show();

            setDungeon(player.getDungeon());
            setPlayer(player);
            player.attach((Observer)bow);
            wind = (Wind)getPlayer().getDungeon().getGivenEntity("Wind");
            wind.setDungeon(getDungeon());
            wind.setPlayer(getPlayer());
        }
        player.move(direction);
    }
    @Override
    public void attack() {
        
        BackendTask t = new BackendTask();
        t.set_move_task(wind, getPlayer());
        SideTimer timer = new SideTimer();
        wind.getNode().setVisible(true);
        wind.setIsMoving(true);
        wind.setX(getPlayer().getX());
        wind.setY(getPlayer().getY());

        timer.makeCycleTimer(1_000_000_000L, 5, t, "moveinplayerdirection");
    }
    @Override
    public void update(Subject obj) {
        if (!(obj instanceof Player)) return;
        Player p = (Player) obj;
        
    }

    
}