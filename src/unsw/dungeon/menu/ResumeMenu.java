package unsw.dungeon.menu;

import javafx.scene.layout.VBox;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

public class ResumeMenu extends CreateNewMenu {
    public void newSceneConnectToStage(DungeonApplication dungeonApplication, Scene e) {
        dungeonApplication.resumeMenu= e;
    }
    @Override
    public boolean isMakeGameStop() {
        return true;
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

        ResumeMenu resumeMenu = new ResumeMenu(dungeonApplication, dungeon); // here past in object instead of value
        resumeMenu.setVisible(true); // set as false if used as resume menu

        root.getChildren().addAll(imgView, resumeMenu);
        return root;
    }
    public ResumeMenu() {}

    private ResumeMenu(DungeonApplication d, Dungeon dungeon) {
        
        VBox menu0 = new VBox(10);
        VBox menu1 = new VBox(10); // distance between two elements to 10

        menu0.setTranslateX(100); // sub menu
        menu0.setTranslateY(200);

        menu1.setTranslateX(100);
        menu1.setTranslateY(200);

        final int offset = 400;

        menu1.setTranslateX(offset);

        MenuButton btnResume = new MenuButton("RESUME");
        btnResume.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(evt -> setVisible(false));
            ft.play();
            dungeon.setGameStop(false);
            d.window.setScene(d.currentMenu); 
        });

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
            
            try {
                d.start(d.window);
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        });

        MenuButton btnLevel = new MenuButton("RETURN LEVEL MENU");
        btnLevel.setOnMouseClicked(event -> {
            MapSelectMenu mapMenu = new MapSelectMenu();
            mapMenu.createNewMenu(d, null);
            d.window.setScene(d.mapSelectMenu);
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

        MenuButton btnHelp = new MenuButton("HELP?");
        btnHelp.setOnMouseClicked(event -> {
            d.prevMenu = d.resumeMenu;
            d.window.setScene(d.helpMenu); // transit to next scene 
        });

        menu0.getChildren().addAll(btnResume, btnOptions, btnHelp, btnLevel, btnExit);
        menu1.getChildren().addAll(btnBack, btnSound, btnVideo);

        Rectangle bg = new Rectangle(800, 600); // extra layer to make bg img darker
        bg.setFill(Color.GREY);
        bg.setOpacity(0.4);

        getChildren().addAll(bg, menu0);
    }
}
/**
     * for adding pause functinoality during the game
     * @param scene
     * @return
     */
    //  private Scene addOnScene(Scene scene) {
    //     scene.setOnKeyPressed(event -> {
    //         if (event.getCode() == KeyCode.ESCAPE) {
    //             if (!gameMenu.isVisible()) {
    //                 FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
    //                 ft.setFromValue(0);
    //                 ft.setToValue(1);

    //                 gameMenu.setVisible(true);
    //                 ft.play();
    //             }
    //             else {
    //                 FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
    //                 ft.setFromValue(1);
    //                 ft.setToValue(0);
    //                 ft.setOnFinished(evt -> gameMenu.setVisible(false));
    //                 ft.play();
    //             }
    //         }
    //     });
    // }