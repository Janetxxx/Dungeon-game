package unsw.dungeon.player;

import unsw.dungeon.*;
import unsw.dungeon.player.*;


public class Wind extends PlayerBaseDecorator {
    
    public Wind(int x, int y) {
        super(x, y);
        setName("Wind");
    }
    @Override
    public void moveGivenDirection(int direction) {
        switch (direction) {
            case 1:
                if (getY() > 0) {
                    y().set(getY() - 1);
                    meetEnemy();
                }
                    
                break;
            case 2:
                if (getY() < getDungeon().getHeight() - 1) {
                    y().set(getY() + 1);
                    meetEnemy();
                }
                    
                break;
            case 3:
                if (getX() > 0) {
                    x().set(getX() - 1);
                    meetEnemy(); 
                }
                    
                break;
            case 4:
                if (getX() < getDungeon().getWidth() - 1) {
                    x().set(getX() + 1);
                    meetEnemy(); 
                }
                    
                break;
            default:
                break;
        }
    }
}