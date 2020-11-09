package test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import unsw.dungeon.enemy.*;
import unsw.dungeon.player.*;
public class InvincibilityPotionTest extends TestFixture {
    @Test
    public void testRunAway() {
        Enemy enemy = new Enemy(10, 10);
        dungeon.addEntity(enemy);
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
    }
    @Test
    public void testdoublepotion() {
        Enemy enemy = new Enemy(10, 10);
        InvincibilityPotion t1 = new InvincibilityPotion(3, 1);
        dungeon.addEntity(t1);
        dungeon.addEntity(enemy);
        player.moveDown();
        player.x().set(2);
        player.y().set(1);
        enemy.x().set(8);
        enemy.y().set(3);
        int dis = enemy.getX() - player.getX();
        player.moveRight();
        player.moveRight();
        int after = enemy.getX() - player.getX();
        assertEquals(true, after < dis);
        InvincibilityPotion t2 = new InvincibilityPotion(5, 1);
        dungeon.addEntity(t2);
        player.moveRight();
        player.moveRight();
        assertEquals(1, player.getCollections().size());
        
        
    }
    @Test
    public void testCCollectMany() {
        InvincibilityPotion t1 = new InvincibilityPotion(3, 1);
        InvincibilityPotion t2 = new InvincibilityPotion(4, 1);
        InvincibilityPotion t3 = new InvincibilityPotion(5, 1);
        InvincibilityPotion t4 = new InvincibilityPotion(6, 1);
        InvincibilityPotion t5 = new InvincibilityPotion(7, 1);
        InvincibilityPotion t6 = new InvincibilityPotion(8, 1);

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

}