package unsw.dungeon.menu;

import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonApplication;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WinMenu extends CreateNewMenu {
    
    public void newSceneConnectToStage(DungeonApplication dungeonApplication, Scene e) {
        dungeonApplication.winMenu = e;
    }

    @Override
    public Pane setMenu(DungeonApplication dungeonApplication, Dungeon dungeon) throws IOException {
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        InputStream is = Files.newInputStream(Paths.get("src/unsw/dungeon/youwin.jpg"));  // relative to the src folder
        Image img = new Image(is);
        is.close();

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(800);
        imgView.setFitHeight(600);

        WinMenu winMenu = new WinMenu(dungeonApplication, dungeon); // here past in object instead of value
        winMenu.setVisible(true); // set as false if used as resume menu

        root.getChildren().addAll(imgView, winMenu);
        return root;
    }
    public WinMenu() {}

    private WinMenu(DungeonApplication d, Dungeon dungeon) {
        
        VBox menu0 = new VBox(10);
        VBox menu1 = new VBox(10); // distance between two elements to 10

        menu0.setTranslateX(100); // sub menu
        menu0.setTranslateY(200);

        menu1.setTranslateX(100);
        menu1.setTranslateY(200);

        final int offset = 400;

        menu1.setTranslateX(offset);

        MenuButton btnExit = new MenuButton("RETURN MAIN MENU");
        btnExit.setOnMouseClicked(event -> {
            // d.window.setScene(d.scene1); // transit to first scene 
            try {
                d.start(d.window);
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        });

        menu0.getChildren().addAll(btnExit); // resumebtn

        getChildren().addAll(menu0);
    }
}
