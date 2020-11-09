package unsw.dungeon.enemy;

import unsw.dungeon.*;
import unsw.dungeon.Character;
import unsw.dungeon.player.*;

import java.util.Iterator;

import javafx.animation.AnimationTimer;

public class Wizard extends Character {
    
    public Wizard(int x, int y) {
        super(x, y);
        setName("Wizard");
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

        timer.makeCycleTimer(500_000_000L, 5, t, "wizard");
    }
}




// package unsw.dungeon.enemy;
// import unsw.dungeon.*;
// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.List;
// import javafx.animation.AnimationTimer;
// import javafx.beans.property.DoubleProperty;
// import javafx.beans.property.LongProperty;
// import javafx.beans.property.SimpleDoubleProperty;
// import javafx.beans.property.SimpleLongProperty;
// import javafx.scene.Node;
// import unsw.dungeon.player.*;

// /**
//  * fireball will starts from the same position as wizard
//  */
// public class Wizard extends EnemyBase {
//     List<Fireball> fireballs = new ArrayList<>();
    
//     private int speedCount = 0;
//     public Wizard(int x, int y) {
//         super(x, y);
//         setName("Wizard");
//         setEnemyBehaviour(new EnemyMoveBehaviour());; // initially, enemy move towards player
//         setIfRunAway(false);
//     }
//     @Override
//     public void interactionWithPlayer(Player player, String direction, Entity enemy, Iterator<Entity> entities) {
//         player.notifyObservers();
//         if (isDiedContion()) {
//             player.move(direction);
//             EnemyBase e = (EnemyBase) enemy;
//             e.die();
//         } 
//         else {
//             player.die();
//         }  
//         player.move(direction);
//     }
    
//     @Override
//     public void backgroundRun() {
//         for (Entity e : getPlayer().getEntitiesFromDungeon()) {
//             if (e != null && e instanceof Fireball && e.getX() == x().get() && e.getY() == y().get()) {
//                 Fireball f = (Fireball) e;
//                 fireballs.add(f);
//             }
//         }
//         for (Fireball f : fireballs) {
//             // f.getNode().setVisible(true); // make fireball shown near wizard
//             //makefireball(f, "UP");
//         }
        
//     }
//     // /**
//     //  * @see https://stackoverflow.com/questions/21331519/how-to-get-smooth-animation-with-keypress-event-in-javafx
//     //  * @param fireball
//     //  */
//     // public void makefireball(Fireball f, String direction) {
//     //     Node fireball = f.getNode();
//     //     final double fireballSpeed = 100 * pickSpeed(direction); // pixels per second
//     //     final double minX = 0 ;
//     //     final double maxX = 800 ; // whatever the max value should be.. can use a property and bind to scene width if needed...
//     //     final DoubleProperty velocity = new SimpleDoubleProperty();
//     //     final LongProperty lastUpdateTime = new SimpleLongProperty();
        
//     //     speedCount = 0;
//     //     setTimer( new AnimationTimer() {
//     //         @Override
//     //         public void handle(long timestamp) {
//     //             if (lastUpdateTime.get() > 0) {
//     //                 final double elapsedSeconds = (timestamp - lastUpdateTime.get()) / 1_000_000_000.0 ;
//     //                 final double deltaX = elapsedSeconds * velocity.get();

//     //                 final double oldX = pickDirection(direction, fireball);
                   
//     //                 final double newX = Math.max(minX, Math.min(maxX, oldX + deltaX));
                    
//     //                 if (direction.equals("LEFT") || direction.equals("RIGHT")) {
//     //                     fireball.setTranslateX(newX);
//     //                 }
//     //                 else {
//     //                     fireball.setTranslateY(newX);
//     //                 }
                        
                    
//     //                 //
//     //                 speedCount++;
//     //                 if (speedCount == 18) {
//     //                     setFireballDirection(f, direction);
//     //                     speedCount = 0;
//     //                 }
                        
//     //                 if (f.isInWall()) {
//     //                     velocity.set(-fireballSpeed);
//     //                     f.setForward(false);
//     //                 }
//     //                 meetPlayer(f, velocity, fireballSpeed,direction);
//     //                 //
//     //             }
//     //             lastUpdateTime.set(timestamp);
//     //         }
//     //     });
//     //     velocity.set(fireballSpeed);
//     //     getTimer().start();
//     // }
//     // private int pickSpeed(String direction) {
//     //     if (direction.equals("RIGHT") || direction.equals("DOWN")) {
//     //         return 1;
//     //     } else {
//     //         return -1;
//     //     }
//     // }
//     // private double pickDirection(String direction, Node fireball) {
//     //     if (direction.equals("LEFT") || direction.equals("RIGHT")) {
//     //         return fireball.getTranslateX();
//     //     } else {
//     //         return fireball.getTranslateY();
//     //     }
//     // }
    
//     // private void meetPlayer(Fireball fireball, DoubleProperty velocity, double fireballSpeed, String direction) {
        
//     //     switch (direction) {
//     //         case "RIGHT":
//     //             if (getPlayer().getX() == fireball.getX() && getPlayer().getY() == fireball.getY()) {

//     //                 playerMoveAway(fireball, direction);
//     //                 getPlayer().changeHealth(-1);
                    
//     //             }
//     //             if (getX() == fireball.getX() - 1 && getY() == fireball.getY() && !fireball.isForward()) {
//     //                 getTimer().stop();
//     //                 fireball.setForward(true);
//     //                 makefireball(fireball, direction);
//     //             }
//     //             break;
//     //         case "LEFT":
//     //             if (getPlayer().getX() == fireball.getX() && getPlayer().getY() == fireball.getY()) {

//     //                 playerMoveAway(fireball, direction);
//     //                 getPlayer().changeHealth(-1);
                    
//     //             }
//     //             if (getX() == fireball.getX() + 1 && getY() == fireball.getY() && !fireball.isForward()) {
//     //                 getTimer().stop();
//     //                 fireball.setForward(true);
//     //                 makefireball(fireball, direction);
//     //             }
//     //             break;
//     //         case "DOWN":
//     //             if (getPlayer().getX() == fireball.getX() && getPlayer().getY() == fireball.getY()) {

//     //                 playerMoveAway(fireball, direction);
//     //                 getPlayer().changeHealth(-1);
                    
//     //             }
//     //             if (getX() == fireball.getX() && getY() == fireball.getY() - 1 && !fireball.isForward()) {
//     //                 getTimer().stop();
//     //                 fireball.setForward(true);
//     //                 makefireball(fireball, direction);
//     //             }
//     //             break;
//     //         case "UP":
//     //             if (getPlayer().getX() == fireball.getX() && getPlayer().getY() == fireball.getY()) {

//     //                 playerMoveAway(fireball, direction);
//     //                 getPlayer().changeHealth(-1);
                    
//     //             }
//     //             if (getX() == fireball.getX() && getY() == fireball.getY() + 1 && !fireball.isForward()) {
//     //                 getTimer().stop();
//     //                 fireball.setForward(true);
//     //                 makefireball(fireball, direction);
//     //             }
//     //             break;
//     //         default:
//     //             break;
//     //     }
        
//     // }
//     // private void setFireballDirection(Fireball f, String direction) {
//     //     int tmp = pickSpeed(direction);
//     //     if (direction.equals("RIGHT") || direction.equals("LEFT")) {
            
//     //         if (f.isForward()) {
//     //             f.addX(tmp);
//     //         }
//     //         else {
//     //             f.addX(tmp);
//     //         }
//     //     }
//     //     else {
//     //         if (f.isForward()) {
//     //             f.addY(tmp);
//     //         }
//     //         else {
//     //             f.addY(tmp);
//     //         }
//     //     }
        
//     // }
//     // private void playerMoveAway(Fireball f, String direction) {
//     //     if (direction.equals("LEFT") || direction.equals("RIGHT")) {
//     //         if (f.isForward()) {
//     //             getPlayer().addX(-1);
//     //         }
//     //         else {
//     //             getPlayer().addX(1);
//     //         }
//     //     } else {

//     //     }
        
        
//     // }
    
// }