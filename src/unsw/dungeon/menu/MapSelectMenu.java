package unsw.dungeon.menu;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonApplication;
import unsw.dungeon.MenuTask;
import unsw.dungeon.SideTimer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * * create new scene to select maps to starts
 */
public class MapSelectMenu extends CreateNewMenu {
    
    public void newSceneConnectToStage(DungeonApplication dungeonApplication, Scene e) {
        dungeonApplication.mapSelectMenu = e;
    }

    public Pane setMenu(DungeonApplication dungeonApplication, Dungeon dungeon) throws IOException {
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        InputStream is = Files.newInputStream(Paths.get("src/unsw/dungeon/mapselect.png"));  // relative to the src folder
        Image img = new Image(is);
        is.close();

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(800);
        imgView.setFitHeight(600);

        MapSelectMenu mapMenu = new MapSelectMenu(dungeonApplication); // here past in object instead of value
        mapMenu.setVisible(true); // set as false if used as resume menu

        root.getChildren().addAll(imgView, mapMenu);
        return root;
    }
    public MapSelectMenu() {}

    private MapSelectMenu(DungeonApplication d) {
        
        VBox menu0 = new VBox(10);
        VBox menu1 = new VBox(10); // distance between two elements to 10

        menu0.setTranslateX(100); // sub menu
        menu0.setTranslateY(200);

        menu1.setTranslateX(100);
        menu1.setTranslateY(200);

        final int offset = 400;

        menu1.setTranslateX(offset);
        
        // fade effect - when initialise
        FadeTransition ft = new FadeTransition(Duration.millis(3000), this);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        MenuButton btnResume = new MenuButton("ENEMY LEVEL");
        btnResume.setOnMouseClicked(event -> {
            
            
            d.currentMenu = d.gameScene;
            d.window.setScene(d.gameScene); // transit to next scene 
            MenuTask menuTask = new MenuTask();
            SideTimer t = new SideTimer();
            t.makeSideTimer(5_000_000_000L, menuTask, "showenemygoal");
            d.currentGame = "Goal: Kill all the enemy and go to exit";
        });
        
        MenuButton btnMaze = new MenuButton("MAZE LEVEL");
        btnMaze.setOnMouseClicked(event -> {
            d.currentMenu = d.mazeScene;
            d.window.setScene(d.mazeScene); // transit to next scene 
            MenuTask menuTask = new MenuTask();
            SideTimer t = new SideTimer();
            t.makeSideTimer(5_000_000_000L, menuTask, "showmazegoal");
            d.currentGame = "Goal: go to exit";
        });

        MenuButton btnBoulders = new MenuButton("BOULDERS LEVEL");
        btnBoulders.setOnMouseClicked(event -> {
            d.currentMenu = d.bouldersScene;
            d.window.setScene(d.bouldersScene); // transit to next scene 
            MenuTask menuTask = new MenuTask();
            SideTimer t = new SideTimer();
            t.makeSideTimer(5_000_000_000L, menuTask, "showbouldersgoal");
            d.currentGame = "Goal: push all boulders to switch";
        });

        MenuButton btnBoss = new MenuButton("BOSS LEVEL");
        btnBoss.setOnMouseClicked(event -> {
            d.currentMenu = d.bossScene;
            d.window.setScene(d.bossScene); // transit to next scene 
            MenuTask menuTask = new MenuTask();
            SideTimer t = new SideTimer();
            t.makeSideTimer(5_000_000_000L, menuTask, "showbossgoal");
            d.currentGame = "Goal: kill the boss";
        });

        MenuButton btnBack = new MenuButton("BACK");
        btnBack.setOnMouseClicked(event -> {
            // fade effect - when come back
            FadeTransition ftt = new FadeTransition(Duration.millis(5000), this);
            ftt.setFromValue(0.0);
            ftt.setToValue(1.0);
            ftt.play();
            d.window.setScene(d.gameMenu);
        });

        menu0.getChildren().addAll(btnResume, btnMaze, btnBoulders, btnBoss, btnBack);

        getChildren().addAll(menu0);
    }
}
