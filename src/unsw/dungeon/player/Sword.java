package unsw.dungeon.player;
import unsw.dungeon.*;
import unsw.dungeon.menu.*;
import unsw.dungeon.enemy.*;

import java.util.Iterator;
import unsw.dungeon.player.*;

public class Sword extends PlayerBaseDecorator implements Observer {
    private int hitsLeft;
    // private BooleanProperty isPickup = new SimpleBooleanProperty(false);
    
    public Sword(int x, int y) {
        super(x, y);
        setName("Sword");
        hitsLeft = 5;
    }
    @Override
    public void interactionWithPlayer(Player player, String direction, Entity sword, Iterator<Entity> entities) {
        if (player.countGivenCollection("Sword") == 0) {
            player.collect(sword);
            player.setHasSword(true);
            PopupWindow popup = new PopupWindow("Sword picked up");
            popup.show();
            player.attach((Observer)(Sword)sword);
        }
        player.move(direction);
    }

    public void reduceHitsLeft(Sword s, Player player) {
        s.hitsLeft--;
        if (hitsLeft <= 0) {
            player.removeCollection("Sword");
            player.setHasSword(false);
        }
    }

    public int getHitsLeft() {
        return hitsLeft;
    }

    public void setHitsLeft(int hitsLeft) {
        this.hitsLeft = hitsLeft;
    }

    @Override
    public void update(Subject obj) {
        if (!(obj instanceof Player)) return;
        Player p = (Player) obj;
        for (Entity e : p.getEntitiesFromDungeon()) {
            if (e instanceof Enemy && p.getDungeon().isOnSamePostion(p, e)) {
                Sword s = (Sword)p.getGivenCollection("Sword");
                if ( s != null) {
                    s.reduceHitsLeft(s, p);
                }
            }
        }
    }
    
}