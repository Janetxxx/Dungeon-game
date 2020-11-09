package test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import unsw.dungeon.entity.*;

public class TreasureTest extends TestFixture {
    @Test
    public void testCollecteNone() {
        
        dungeon.addEntity(new Treasure(2, 1));
        assertEquals(0, player.getCollections().size());
    }
    @Test
    public void testCCollectMany() {
        Treasure t1 = new Treasure(3, 1);
        Treasure t2 = new Treasure(3, 1);
        Treasure t3 = new Treasure(3, 1);
        Treasure t4 = new Treasure(3, 1);
        Treasure t5 = new Treasure(3, 1);
        Treasure t6 = new Treasure(3, 1);

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