package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import unsw.dungeon.*;
import unsw.dungeon.entity.*;
import unsw.dungeon.player.*;
import unsw.dungeon.enemy.*;

public class EnemyTest extends TestFixture{
    Enemy enemy = new Enemy(10, 10);
    @BeforeEach
    private void setup1() {
        
        dungeon.addEntity(enemy);
        player.moveDown();
    }
    /**
     * distance closer
     */
    @Test
    public void testMoveCloser()  {
        player.x().set(3);
        player.y().set(3);
        enemy.x().set(8);
        enemy.y().set(3);
        int dis = enemy.getX() - player.getX();
        for (int count = 0; count < 4; count++) {
            int prevdis = dis;
            enemy.getState().behaviour(enemy, player);
            dis = enemy.getX() - player.getX();
            assertEquals(true, prevdis > dis);
        
        }
        enemy.backgroundRun();
        player.moveDown();
        player.moveLeft();
        player.moveRight();
        player.moveUp();
        enemy.getTimer().stop();
        int newdis = enemy.getX() - player.getX();
        assertEquals(true, dis == newdis);
    }
    @Test
    public void testDie() {
        
        enemy.die();
        assertEquals(false, enemy.isInDungeon().get());
        
    }
    @Test
    public void enemyencouterplayer() {
        player.x().set(3);
        player.y().set(3);
        enemy.x().set(4);
        enemy.y().set(3);
        enemy.getState().behaviour(enemy, player);
        assertEquals(false, player.isInDungeon().get());
        
    }
    @Test
    public void enemyencouterplayer2() {
        player.x().set(3);
        player.y().set(3);
        enemy.x().set(4);
        enemy.y().set(3);
        Sword s1 = new Sword(2, 1);
        player.collect(s1);
        enemy.getState().behaviour(enemy, player);
        assertEquals(true, player.isInDungeon().get());
        
    }
    @Test
    public void playerencouterenemy() {
        player.x().set(3);
        player.y().set(3);
        enemy.x().set(2);
        enemy.y().set(3);
        player.moveLeft();
        assertEquals(false, player.isInDungeon().get());
        
    }
    @Test
    public void enemywithwallandDoor() {
        Wall w = new Wall(3, 4);
        dungeon.addEntity(w);
        player.x().set(3);
        player.y().set(3);
        enemy.x().set(3);
        enemy.y().set(5);
        player.moveLeft();
        enemy.getState().behaviour(enemy, player);
        player.moveRight();
        enemy.getState().behaviour(enemy, player);
        assertEquals(true, player.isInDungeon().get());
        Door door = new Door(4,4,1);
        dungeon.addEntity(door);
        player.moveLeft();
        enemy.getState().behaviour(enemy, player);
        player.moveDown();
        enemy.getState().behaviour(enemy, player);
        assertEquals(false, player.isInDungeon().get());
    }

}