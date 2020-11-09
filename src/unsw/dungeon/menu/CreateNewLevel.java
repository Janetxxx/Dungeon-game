package unsw.dungeon.menu;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import unsw.dungeon.DungeonApplication;
import unsw.dungeon.DungeonController;
import unsw.dungeon.DungeonControllerLoader;

/**
 * initialise all maps
 */
public class CreateNewLevel {

    public CreateNewLevel(DungeonApplication dungeonApplication) throws IOException {
        String maps[] = {"maze.json", "bouldergame.json", "combo.json", "boss.json"};
        for (int i = 0; i < maps.length; i++) {
            createLevels(dungeonApplication, maps[i]);
        }   
    }
   
    private void createLevels(DungeonApplication dungeonApplication, String mapName) throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(mapName);

        DungeonController controller = dungeonLoader.loadController();
        //
        controller.setDungeonApplication(dungeonApplication);
        //
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        switch (mapName) {
            case "combo.json":
                dungeonApplication.gameScene = new Scene(root);        
                break;
            case "bouldergame.json":
                dungeonApplication.bouldersScene = new Scene(root);        
                break;
            case "maze.json":
                dungeonApplication.mazeScene= new Scene(root);        
                break;
            case "boss.json":
                dungeonApplication.bossScene = new Scene(root);        
                break;
            default:
                break;
        }
        
        root.requestFocus();
    }
}
   