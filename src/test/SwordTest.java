package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.enemy.*;
import unsw.dungeon.player.*;

import static org.junit.Assert.*;

public class SwordTest extends TestFixture {
    Enemy enemy = new Enemy(12, 12);
    @BeforeEach
    private void setup1() {
        
        dungeon.addEntity(enemy);
        
    }
    @Test
    public void testCollecteNone() {
        
        dungeon.addEntity(new Sword(2, 1));
        assertEquals(0, player.getCollections().size());
    }
    @Test
    public void testCollectesome() {
        
        dungeon.addEntity(new Sword(2, 1));
        
        player.x().set(2);
        player.y().set(2);
        player.moveUp();
        assertEquals(1, player.getCollections().size());
    }
    @Test
    public void testCCollectMany() {
        Sword t1 = new Sword(3, 1);
        Sword t2 = new Sword(4, 1);
        Sword t3 = new Sword(5, 1);
        Sword t4 = new Sword(6, 1);
        Sword t5 = new Sword(7, 1);
        Sword t6 = new Sword(8, 1);

        dungeon.addEntity(t1);
        dungeon.addEntity(t2);
        dungeon.addEntity(t3);
        dungeon.addEntity(t4);
        dungeon.addEntity(t5);
        dungeon.addEntity(t6);
        player.collect(t1);
        player.collect(t2);
        player.collect(t3);
        player.collect(t4);
        player.collect(t5);
        player.collect(t6);
    

        assertEquals(6, player.getCollections().size());

    }
    /**
     * each hit reduce by one, only one at a time
     */
    @Test
    public void testHitsReducedAndRepeatedSword() {
        Sword s1 = new Sword(2, 1);
        player.collect(s1);
        assertEquals(5, s1.getHitsLeft());
        player.moveDown();
        enemy.x().set(2);
        enemy.y().set(3);
        player.x().set(2);
        player.y().set(2);
        player.moveDown();
        Sword s2 = new Sword(2, 1);
        player.collect(s2);
        assertEquals(4, s1.getHitsLeft());
    }
    /**
     * after sword has no hit, then removed 
     */
    @Test
    public void testSwordRemoved() {
        Sword s1 = new Sword(2, 1);
        Enemy e1 = new Enemy(10, 10);
        Enemy e2 = new Enemy(10, 11);
        Enemy e3 = new Enemy(10, 12);
        Enemy e4 = new Enemy(10, 13);
        Enemy e5 = new Enemy(10, 14);
        dungeon.addEntity(e1);
        dungeon.addEntity(e2);
        dungeon.addEntity(e3);
        dungeon.addEntity(e4);
        dungeon.addEntity(e5);
        player.collect(s1);
        player.moveDown();
        player.x().set(10);
        player.y().set(9);
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveLeft();
        assertEquals(0, s1.getHitsLeft());
        // /assertEquals(0, player.getCollections().size());
    }

 
}