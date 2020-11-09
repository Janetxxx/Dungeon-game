package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import unsw.dungeon.enemy.*;
import unsw.dungeon.entity.*;
import unsw.dungeon.player.*;
/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        // add goal-condition
        JSONObject jsonGoal = json.getJSONObject("goal-condition");
        
        Dungeon dungeon = new Dungeon(width, height, jsonGoal);
        
        JSONArray jsonEntities = json.getJSONArray("entities");
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        return dungeon;
    }
 
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
            
        // TODO Handle other possible entities
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            break;
        
        case "enemy":
        Enemy enemy = new Enemy(x, y);
            onLoad(enemy);
            entity = enemy;
            break;

        case "sword":
        Sword sword = new Sword(x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "invincibility":
        InvincibilityPotion invincibilityPotion = new InvincibilityPotion(x, y);
            onLoad(invincibilityPotion);
            entity = invincibilityPotion;
            break;
        case "key":
            int id_key = json.getInt("id");
            Key key = new Key(x, y, id_key);
            onLoad(key);
            entity = key;
            break;
        case "door":
            int id_door = json.getInt("id");
            Door door = new Door(x, y, id_door);
            onLoad(door);
            entity = door;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "switch":
            Switch floorSwitch = new Switch(x, y, dungeon);
            onLoad(floorSwitch);
            entity = floorSwitch;
            break;
        case "boulder":
            Boulder boulder = new Boulder(x, y, dungeon);
            onLoad(boulder);
            entity = boulder;
            break;
        case "portal":
            int id_portal = json.getInt("id");
            String portal_name = json.getString("portal_name");
            Portal portal = new Portal(x, y, id_portal, dungeon, portal_name);
            onLoad(portal);
            entity = portal;
            break;
        case "alpaca":
            Alpaca alpaca = new Alpaca(x, y);
            onLoad(alpaca);
            entity = alpaca;
            break;
        case "wizard":
            Wizard wizard = new Wizard(x, y);
            onLoad(wizard);
            entity = wizard;
            break;
        case "fireball":
            Fireball fireball = new Fireball(x, y);
            onLoad(fireball);
            entity = fireball;
            break;
        case "mushroom":
            Mushroom mushroom = new Mushroom(x, y);
            onLoad(mushroom);
            entity = mushroom;
            break;
        case "sunHealth":
            SunHealth sunHealth = new SunHealth(x, y);
            onLoad(sunHealth);
            entity = sunHealth;
            break;
        case "knight1":
            Knight1 knight1 = new Knight1(x, y);
            onLoad(knight1);
            entity = knight1;
            break;
        case "knight2":
            Knight2 knight2 = new Knight2(x, y);
            onLoad(knight2);
            entity = knight2;
            break;
        case "knight3":
            Knight3 knight3 = new Knight3(x, y);
            onLoad(knight3);
            entity = knight3;
            break;
        case "knight4":
            Knight4 knight4 = new Knight4(x, y);
            onLoad(knight4);
            entity = knight4;
            break;
        case "skill1":
            Skill1 skill1 = new Skill1(x, y);
            onLoad(skill1);
            entity = skill1;
            break;
        case "lightning":
            Lightning lightning = new Lightning(x, y);
            onLoad(lightning);
            entity = lightning;
            break;
        case "ghost":
            Ghost ghost = new Ghost(x, y);
            onLoad(ghost);
            entity = ghost;
            break;
        case "bow":
            Bow bow = new Bow(x, y);
            onLoad(bow);
            entity = bow;
            break;
        case "wind":
            Wind wind = new Wind(x, y);
            onLoad(wind);
            entity = wind;
            break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(InvincibilityPotion invincibilityPotion);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Switch floorSwitch);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(Alpaca alpaca);
    public abstract void onLoad(Wizard wizard);
    public abstract void onLoad(Mushroom mushroom);
    public abstract void onLoad(Fireball fireball);
    public abstract void onLoad(SunHealth sunHealth);
    public abstract void onLoad(Lightning lightning);
    public abstract void onLoad(Knight1 knight1);
    public abstract void onLoad(Knight2 knight2);
    public abstract void onLoad(Knight3 knight3);
    public abstract void onLoad(Knight4 knight4);
    public abstract void onLoad(Skill1 skill1);
    public abstract void onLoad(Ghost ghost);
    public abstract void onLoad(Bow bow);
    public abstract void onLoad(Wind wind);
    
}

