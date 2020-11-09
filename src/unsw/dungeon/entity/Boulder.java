package unsw.dungeon.entity;

import java.util.Iterator;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.player.*;
import unsw.dungeon.menu.*;

public class Boulder extends Entity {

	private Dungeon dungeon;

	public Boulder(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.dungeon = dungeon;   
	}

	public void moveBoulder(Player player, String direction){
		if(direction.equals("UP")){
			pushUp();
		}
		else if(direction.equals("DOWN")){
			pushDown();
		}
		else if(direction.equals("LEFT")){
			pushLeft();
		}
		else if (direction.equals("RIGHT")) {
			pushRight();
		}
		
	}


	private void pushUp() {
		int currX = getX();
		int currY = getY();
		int nextX = currX;
		int nextY = currY-1;
		if (this.dungeon.checkSpaceBehindBoulder(nextX, nextY) == true){
			checkSwitch(currX, nextY);
			setY(nextY);
		}
		
	}

	private void pushDown() {
		int currX = getX();
		int currY = getY();
		int nextX = currX;
		int nextY = currY+1;
		if (this.dungeon.checkSpaceBehindBoulder(nextX, nextY) == true){
			checkSwitch(currX, nextY);
			setY(nextY);
		}

	}

	private void pushLeft() {
		int currX = getX();
		int currY = getY();
		int nextX = currX-1;
		int nextY = currY;
		if(this.dungeon.checkSpaceBehindBoulder(nextX, nextY) == true){
			checkSwitch(nextX, currY);
			setX(nextX);
		}

	}

	private void pushRight() {
		int currX = getX();
		int currY = getY();
		int nextX = currX+1;
		int nextY = currY;
		if(this.dungeon.checkSpaceBehindBoulder(nextX, nextY) == true){
			checkSwitch(nextX, currY);
			setX(nextX);
		}

	}
	
	// if entity is floor switch, push boulder on it will trigger that
	// if not floor switch, return false
	private boolean checkSwitch(int x, int y) {
		Entity e = dungeon.getEntity(x, y);
		if (e instanceof Switch){
			triggerSwitch((Switch) e);
		}
		return false;
	}
	
	private void triggerSwitch(Switch floorswitch) {
		floorswitch.setTrigger(true);
		System.out.println("trigger the switch.");
		PopupWindow popup = new PopupWindow("Trigger the switch");
        popup.show();
		floorswitch.increaseTriggerCount();
	}

	// private void untriggerFloorSwitch(Switch floorswitch) {
	// 	floorswitch.setTrigger(false);
	// 	floorswitch.decreaseTriggerCount();
	// }
	
	@Override
    public void interactionWithPlayer(Player player, String direction, Entity boulder, Iterator<Entity> entities) {
		this.moveBoulder(player, direction);
	}

}
