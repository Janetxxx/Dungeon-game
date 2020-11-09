package unsw.dungeon.player;
import unsw.dungeon.*;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Node;
import unsw.dungeon.entity.*;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends PlayerBase {
    private BooleanProperty moveThroughWall = new SimpleBooleanProperty(false);
    private BooleanProperty enemySheep = new SimpleBooleanProperty(false);
    // private AnimationTimer timer = null;
    private AnimationTimer iceballTimer = null;
    // private AnimationTimer sideTimer = null;
    private int iceBallSpeed = 0;
    private Skill1 iceball = null;
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.keys = new ArrayList<Key>();
        setDungeon(dungeon);
    }
    
    /**
     * explanation:
     *          since each entity has implement the playerbehaviour class, can treat them the same 
        use iterator to avoid ConcurrentModificationExceptionw 
        while removing elements from `ArrayList` while iterating it
     * @param x
     * @param y
     * @return
     */
    
    public void encounterObstacle(int x, int y, String direction) {
        if (iceball == null) {
            iceball = (Skill1)getDungeon().getGivenEntity("Skill1");
            iceball.setDungeon(dungeon);
        }

        //dungeon.debug();
        addPlayerListener();
        notifyObservers(); // notify enemy player's position
        boolean meetEntity = false;
        //
        Iterator<Entity> iter = getEntitiesFromDungeon().iterator();

        while (iter.hasNext()) {
            Entity e = iter.next();

            if (e != null && e.getX() == x && e.getY() == y) { // for some reason, has to check null, prob there are some other stuff in the entites that is null
                    e.interactionWithPlayer(this, direction, e, iter); // here method should be overload by its subclass
                    meetEntity = true; // right now, as long as items is not picked up / update class, then it cannot move through
            }
        }
        removeLeftEntity();
        notifyObservers(); // update goal
        //if (this.getDungeon().getGoal().getIsGoalCompleted()) this.getDungeon().enterBossMode(); // boss mode
        if (!meetEntity) {
            move(direction); // normal movement without encounter any entity
            
        }
    }
    public void setWallVisable(boolean bool) {
        for (Entity e : getDungeon().getEntities()) {
            if (getDungeon().isEdgeWall(e)) {
                continue;
            } 
            if (e instanceof Wall) {
                e.getNode().setVisible(bool);
                
            }
        }
    }
    @Override
    public void skill2() {
        setWallVisable(false);
        
        BackendTask bt = new BackendTask();
        bt.set_player_task(this);
        SideTimer st = new SideTimer();
        st.makeSideTimer(5_000_000_000L, bt, "walldisappear");
    }
    @Override
    public void skill() {
        if (iceball == null) return;
        iceball.getNode().setVisible(true);
        iceball.setMoving(true);
        backgroundRun(this);
        BackendTask bt = new BackendTask();
        bt.set_player_task(this);
        SideTimer st = new SideTimer();
        st.makeSideTimer(5_000_000_000L, bt, "enemysheep");

    }
    
    private int pickSpeed(int direction) {
        if (direction == 2 || direction == 4) { // down / right
            return 1;
        } else {
            return -1;
        }
    }
    /**
     * move towards one direction
     * @param e
     * @param direction
     */
    public void setIceBallDirection(PlayerBase e, int direction) {
        switch (direction) {
            case 1:
                e.move("UP");
                break;
            case 2:
                e.move("DOWN");
                break;
            case 3:
                e.move("LEFT");
                break;
            case 4:
                e.move("RIGHT");
                break;
            default:
                break;
        }
    }
    private void sideTimer() {
       
        BackendTask bt = new BackendTask();
        bt.set_iceball_task(this, iceball, iceballTimer);
        SideTimer st = new SideTimer();
        st.makeSideTimer(4_000_000_000L, bt, "iceball");
    }

    public Skill1 getIceball() {
        return iceball;
    }

    public void setIceball(Skill1 iceball) {
        this.iceball = iceball;
    }
    private void backgroundRun(Player player) {
        Node skill = iceball.getNode();
        final int direction = player.getDirections().get();

        final double skillSpeed = 100 * pickSpeed(direction) ; // pixels per second
        final double minX = 0 ;
        final double maxX = 800 ; // whatever the max value should be.. can use a property and bind to scene width if needed...
        final DoubleProperty rectangleVelocity = new SimpleDoubleProperty();
        final LongProperty lastUpdateTime = new SimpleLongProperty();
        
        iceballTimer = new AnimationTimer() {
        
            @Override
            public void handle(long timestamp) {
                
                if (lastUpdateTime.get() > 0) {
                    final double elapsedSeconds = (timestamp - lastUpdateTime.get()) / 1_000_000_000.0 ;
                    final double deltaX = elapsedSeconds * rectangleVelocity.get();
                    final double oldX;
                    final double newX;
                    
                    //
                    if (direction == 3 || direction == 4) {
                        oldX = skill.getTranslateX();
                    } else {
                        oldX = skill.getTranslateY();
                    }

                    if (skillSpeed > 0) {
                        newX = Math.max(minX, Math.min(maxX, oldX + deltaX));
                    } else {
                        newX = Math.min(minX, Math.min(maxX, oldX + deltaX));
                    }
                    
                    if (direction == 3 || direction == 4) {
                        skill.setTranslateX(newX);
                    } else {
                        skill.setTranslateY(newX);
                    }
                    
                    iceBallSpeed++;
                    if (iceBallSpeed == 18) {
                        setIceBallDirection(iceball, direction);
                    }
                    //
                }
                lastUpdateTime.set(timestamp);
            }
        };
        sideTimer();
        iceballTimer.start();
        rectangleVelocity.set(skillSpeed);
    }
    
    // ------------------------------------------------------------------------------
    //                          below is getter and setter
    // -------------------------------------------------------------------------------
    public BooleanProperty getMoveThroughWall() {
        return moveThroughWall;
    }

    public void setMoveThroughWall(boolean bool) {
        moveThroughWall.set(bool);
    }

    public BooleanProperty getEnemySheep() {
        return enemySheep;
    }

    public void setEnemySheep(Boolean bool) {
        enemySheep.set(bool);
    }
}