package unsw.dungeon.menu;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonApplication;

/**
 * template
 * 
 * example as below:
 * ResumeMenu menu = new ResumeMenu(); // here past in object instead of value
   menu.createNewMenu(dungeonApplication, dungeon);
 */
public abstract class CreateNewMenu extends Parent {
    
    /**
     * 
     * @param dungeonApplication
     * @param dungeon - used when needed (e.g gamestop, stop the dungeon), otherwise, set null
     */
    public void createNewMenu(DungeonApplication dungeonApplication, Dungeon dungeon) {
        
        Pane layout = null;
        try {
            layout = this.setMenu(dungeonApplication, dungeon);
        } catch (IOException e1) {
            
            e1.printStackTrace();
        }
        Scene e = new Scene(layout);
        newSceneConnectToStage(dungeonApplication, e);
        dungeonApplication.window.setScene(e);

        if (isMakeGameStop() && dungeon != null) dungeon.setGameStop(true);
    }
    public abstract Pane setMenu(DungeonApplication dungeonApplication, Dungeon dungeon) throws IOException; // not used when create newMenu, reserved for createNewMenu's implementation
    // hook
    public boolean isMakeGameStop() {
        return false;
    }
    public abstract void newSceneConnectToStage(DungeonApplication dungeonApplication, Scene e);
}