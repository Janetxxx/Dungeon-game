package unsw.dungeon.entity;

import java.util.Iterator;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.player.*;
import unsw.dungeon.menu.*;

public class Switch extends Entity {

    private boolean triggered;
    //private int triggerCount = 0;
    private Dungeon dungeon;



    public Switch(int x, int y, Dungeon dungeon) {
        super(x, y);
        this.triggered = false;
        this.dungeon = dungeon; 
    }

    public boolean getTriggered() {
      return this.triggered;
    }
    
    // change switch status once push boulder on it, trigger the switch
	  public void setTrigger(boolean bool) {
      this.triggered = bool;
      //System.out.println(triggered);
    }


    public void increaseTriggerCount() {
		int count = dungeon.getTriggeredSwitchCount().get() +1;
		dungeon.setTriggeredSwitchCount(count);
    }
    
    public void decreaseTriggerCount() {
		int count = dungeon.getTriggeredSwitchCount().get() -1;
		dungeon.setTriggeredSwitchCount(count);
	}

  @Override
    public void interactionWithPlayer(Player player, String direction, Entity floorswitch, Iterator<Entity> entities) {
      player.move(direction);
      // when move boulder away, untrigger the switch
      if(this.getTriggered()){
        for(Entity entity : this.dungeon.getEntities()){
          if(entity instanceof Boulder){
            if(!dungeon.isOnSamePostion(this, entity)){
              this.setTrigger(false);
              System.out.println("Untrigger the switch.");
              PopupWindow popup = new PopupWindow("Untrigger the switch");
              popup.show();
            }
          }
        }
      } 
    }

}
