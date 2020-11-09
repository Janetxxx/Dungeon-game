package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.menu.*;

/**
 * @see thenewBoston JavaFX java GUI Tutorial
 */
public class DungeonApplication extends Application {
    public Stage window;
    public Scene gameScene; 
    public Scene mazeScene;
    public Scene bouldersScene;
    public Scene bossScene;
    public Scene gameMenu; // main menu
    public Scene mapSelectMenu;
    public Scene resumeMenu;
    public Scene winMenu;
    public Scene loseMenu;
    public Scene helpMenu;
    public Scene currentMenu; // current game scene
    public Scene prevMenu; // current game scene, now used for help menu only
    public String currentGame;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        GameMenu tmp = new GameMenu(); // set main menu
        tmp.createNewMenu(this, null);

        primaryStage.setTitle("Dungeon");
        new CreateNewLevel(this); // create different maps
        
        window.setTitle("LOL");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
