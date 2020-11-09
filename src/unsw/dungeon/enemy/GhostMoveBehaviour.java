package unsw.dungeon.enemy;

import java.util.Iterator;
import unsw.dungeon.entity.*;
import unsw.dungeon.*;
import unsw.dungeon.player.*;

public class GhostMoveBehaviour extends EnemyBehaviour {

     /**
     * if meets wall in next move, then goto next possible move that has the least distance with player
     * @param x
     * @param y
     * @param direction
     * @param ghost
     * @param player
     * @return
     */
    @Override
    public boolean encounterObstacle(int x, int y, int direction, EnemyBase ghost, Player player) {
        Iterator<Entity> iter = player.getEntitiesFromDungeon().iterator();
        Ghost g = (Ghost) ghost;
        //System.out.println("ghost position : x: "+g.getX()+" y: "+g.getY());
        while (iter.hasNext()) {
            Entity e = iter.next();
            
            if (e != null && e.getX() == x && e.getY() == y) {
                if (e instanceof Wall && (e.getX() == 0 || e.getY() == 0)){
                    
                    return true;  
                    //System.out.println("in the same position with wall");
                    //e.getNode().setVisible(false); // set wall to not visible 
                    //g.getVisible();
                    //System.out.println(g.getVisible());
                    //e.getNode().setVisible(true);  
                    
                } else {
                    if (e instanceof Wall){
                        //System.out.println("on the same position with ghost");
                        if (e.getNode().isVisible()) {
                            e.getNode().setVisible(false);
                        } else if (e.getNode().isVisible() == false){
                            e.getNode().setVisible(true);
                        }
                            
                    }
                }
                
                //e.getNode().setVisible(true);
                /*
                if (g.getX() == e.getX() && g.getY() == e.getY()){ // on the same position
                    System.out.println("on the same position with ghost");
                    e.getNode().setVisible(false);
                }
                e.getNode().setVisible(true);*/
            } 
        }
        normalMove(ghost, direction, x, y);
        attack(player, ghost);
        
        return false;
    }




}