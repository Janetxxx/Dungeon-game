package unsw.dungeon.menu;

import javafx.scene.layout.VBox;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.util.Duration;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonApplication;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class LoseMenu extends CreateNewMenu {
    
    public void newSceneConnectToStage(DungeonApplication dungeonApplication, Scene e) {
        dungeonApplication.loseMenu = e;
    }

    @Override
    public Pane setMenu(DungeonApplication dungeonApplication, Dungeon dungeon) throws IOException {
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        InputStream is = Files.newInputStream(Paths.get("src/unsw/dungeon/youlose.jpg"));  // relative to the src folder
        Image img = new Image(is);
        is.close();

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(800);
        imgView.setFitHeight(600);

        LoseMenu loseMenu = new LoseMenu(dungeonApplication, dungeon); // here past in object instead of value
        loseMenu.setVisible(true); // set as false if used as resume menu

        root.getChildren().addAll(imgView, loseMenu);
        return root;
    }
    public LoseMenu() {}

    private LoseMenu(DungeonApplication d, Dungeon dungeon) {
        
        VBox menu0 = new VBox(10);
        VBox menu1 = new VBox(10); // distance between two elements to 10

        menu0.setTranslateX(100); // sub menu
        menu0.setTranslateY(200);

        menu1.setTranslateX(100);
        menu1.setTranslateY(200);

        final int offset = 400;

        menu1.setTranslateX(offset);

        MenuButton btnOptions = new MenuButton("OPTIONS");
        btnOptions.setOnMouseClicked(event -> {
            getChildren().add(menu1);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt.setToX(menu0.getTranslateX() - offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu0);
            });
        });

        MenuButton btnExit = new MenuButton("RETURN MAIN MENU");
        btnExit.setOnMouseClicked(event -> {
            // d.window.setScene(d.scene1); // transit to first scene 
            try {
                d.start(d.window);
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        });

        MenuButton btnBack = new MenuButton("BACK");
        btnBack.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt.setToX(menu1.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu1.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu1);
            });
        });

        MenuButton btnSound = new MenuButton("SOUND");
        MenuButton btnVideo = new MenuButton("VIDEO");

        
        menu0.getChildren().addAll(btnOptions, btnExit); // resumebtn
        menu1.getChildren().addAll(btnBack, btnSound, btnVideo);

        getChildren().addAll(menu0);
    }
}
