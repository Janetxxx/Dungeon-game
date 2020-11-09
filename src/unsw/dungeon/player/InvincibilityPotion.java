package unsw.dungeon.player;
import unsw.dungeon.menu.*;
import java.util.Iterator;
import unsw.dungeon.*;

public class InvincibilityPotion extends PlayerBaseDecorator {
    
    public InvincibilityPotion(int x, int y) {
        super(x, y);
        setName("InvincibilityPotion");
    }
    /**
     * once pick up, then "notify" the enemy to run away
     */
    @Override
    public void interactionWithPlayer(Player player, String direction, Entity potion, Iterator<Entity> entities) {
        
        player.move(direction);
        if (player.hasCollection("InvincibilityPotion")) {
            player.removeCollection("InvincibilityPotion"); // refresh
            player.notifyObservers();
        }
        player.collect(potion);
        player.setHasPotion(true);
        PopupWindow popup = new PopupWindow("InvincibilityPotion picked up");
        popup.show();
        player.notifyObservers();
    }
}