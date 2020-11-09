package unsw.dungeon;

import javafx.animation.AnimationTimer;
import unsw.dungeon.enemy.EnemyBase;
import unsw.dungeon.player.*;

public class BackendTask implements BackgroundTask {
    private Player player;
    private Skill1 iceball;
    private AnimationTimer iceballTimer;
    private Character toBeMove;
    private int direction;
    @Override
    public void start(String which) {
        which = which.toLowerCase();
        switch (which) {
            case "walldisappear":
                player.setMoveThroughWall(true);;
                break;
            case "enemysheep":
                player.setEnemySheep(true);
                player.getDungeon().setGameStop(true);
                break;
            case "iceball":
                
                break;
            case "moveinplayerdirection":
                
                toBeMove.moveGivenDirection(direction);
                break;
            case "fireball":
                
                toBeMove.moveBoss("LEFT", player);;
                break;
            case "lightning":
                
                toBeMove.moveBoss("RIGHT", player);;
                break;
            case "alpaca":
                
                toBeMove.moveBoss("UP", player);;
                break;
            case "wizard":
                
                toBeMove.moveBoss("DOWN", player);;
                break;
            default:
                break;
        }

    }

    @Override
    public void stop(String which) {
        which = which.toLowerCase();
        switch (which) {
            case "walldisappear":
                player.setMoveThroughWall(false);
                player.setWallVisable(true);
                break;
            case "enemysheep":
                player.setEnemySheep(false);
                player.getDungeon().setGameStop(false);
                break;
            case "iceball":
                if (iceballTimer != null) {
                    iceballTimer.stop();;
                    iceballTimer = null;
                    iceball.setMoving(false);
                }
                break;
            case "moveinplayerdirection":
                toBeMove.getNode().setVisible(false);
                toBeMove.setIsMoving(false);
                toBeMove.setX(0);
                toBeMove.setY(0);
                break;
            case "fireball":
                
                toBeMove.getNode().setVisible(false);
                toBeMove.setIsMoving(false);
                toBeMove.setX(0);
                toBeMove.setY(0);
                break;
            case "lightning":
                
                toBeMove.getNode().setVisible(false);
                toBeMove.setIsMoving(false);
                toBeMove.setX(0);
                toBeMove.setY(0);
                break;
            case "alpaca":
                
                toBeMove.getNode().setVisible(false);
                toBeMove.setIsMoving(false);
                toBeMove.setX(0);
                toBeMove.setY(0);
                break;
            case "wizard":
                
                toBeMove.getNode().setVisible(false);
                toBeMove.setIsMoving(false);
                toBeMove.setX(0);
                toBeMove.setY(0);
                break;
            default:
                break;
        }

    }
    public void set_player_task(Player player) {
        this.player = player;
    }
    public void set_iceball_task(Player player, Skill1 iceball, AnimationTimer iceballTimer) {
        this.player = player;
        this.iceball = iceball;
        this.iceballTimer = iceballTimer;
    }
    public void set_move_task(Character e, Player player) {
        toBeMove = e;
        this.player = player;
        direction = player.getDirections().get();
    }
}