package unsw.dungeon.enemy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import unsw.dungeon.Entity;
import unsw.dungeon.menu.PopupWindow;
import unsw.dungeon.player.Player;

public class Boss extends EnemyBase {
    List<EnemyBase> whole = new ArrayList<>();

    public Boss(int x, int y) {
        super(x, y);
    }
    public void checkDie() {
        PopupWindow popup = new PopupWindow("Boss's Health Left: " + getHealth().get());
        popup.show();
        if (getHealth().get() <= 0) {
            
            die();
        }
    }
    /**
     * all disappear
     */
    @Override
    public void die() {
        if (this.getWhole() == null) return;
        for (EnemyBase b : whole) {
            if (b == null) continue;
            if (b.getTimer() != null) getTimer().stop();
            b.getPlayer().notifyObservers();
            b.setDisappear();
        }
        
    }
    /**
     * all part change health
     */
    @Override
    public void changeHealth(int i) {
        
        for (EnemyBase e : whole) {
            
            e.getHealth().set(getHealth().get() + i);
        }
        
    }
    public void updateWhole() {
        for (EnemyBase e : whole) {
            e.setPlayer(getPlayer());
            e.setDungeon(getDungeon());
        }
    }
    /**
     * connect parts to one
     */
    public void setWholeOthers() {
        List<EnemyBase> tmp = new ArrayList<>();
        tmp.add((EnemyBase)getPlayer().getDungeon().getGivenEntity("Knight1"));
        tmp.add((EnemyBase)getPlayer().getDungeon().getGivenEntity("Knight2"));
        tmp.add((EnemyBase)getPlayer().getDungeon().getGivenEntity("Knight3"));
        tmp.add((EnemyBase)getPlayer().getDungeon().getGivenEntity("Knight4"));
        for (EnemyBase e : tmp) {
            Boss b = (Boss) e;
            b.setWhole(tmp);
        }
    }
    
    @Override
    public void backgroundRun() {}

    @Override
    public void interactionWithPlayer(Player player, String direction, Entity items, Iterator<Entity> entites) {}

    public List<EnemyBase> getWhole() {
        return whole;
    }

    public void setWhole(List<EnemyBase> whole) {
        this.whole = whole;
    }
    
        
}