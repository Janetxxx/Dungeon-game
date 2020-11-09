package unsw.dungeon.menu;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
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

/**
 * * @see https://www.youtube.com/watch?v=aOcow70vqb4
 * * @see https://stackoverflow.com/questions/24978278/fade-in-fade-out-a-screen-in-javafx
 */
public class GameMenu extends CreateNewMenu {
    
    public void newSceneConnectToStage(DungeonApplication dungeonApplication, Scene e) {
        dungeonApplication.gameMenu = e;
        dungeonApplication.prevMenu = dungeonApplication.gameMenu;
    }
    
    public Pane setMenu(DungeonApplication dungeonApplication, Dungeon dungeon) throws IOException {
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        InputStream is = Files.newInputStream(Paths.get("src/unsw/dungeon/mainmenu.png"));  // relative to the src folder
        Image img = new Image(is);
        is.close();

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(800);
        imgView.setFitHeight(600);

        GameMenu gameMenu = new GameMenu(dungeonApplication); // here past in object instead of value
        gameMenu.setVisible(true); // set as false if used as resume menu

        root.getChildren().addAll(imgView, gameMenu);
        return root;
    }
    public GameMenu() {}

    private GameMenu(DungeonApplication d) {
        
        VBox menu0 = new VBox(10);
        VBox menu1 = new VBox(10); // distance between two elements to 10

        menu0.setTranslateX(100); // sub menu
        menu0.setTranslateY(200);

        menu1.setTranslateX(100);
        menu1.setTranslateY(200);

        final int offset = 400;

        menu1.setTranslateX(offset);
        
        // fade effect - when initialise
        FadeTransition ft = new FadeTransition(Duration.millis(4000), this);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        MapSelectMenu mapMenu = new MapSelectMenu();
        mapMenu.createNewMenu(d, null);
        

        MenuButton btnResume = new MenuButton("PLAY");
        btnResume.setOnMouseClicked(event -> {
            // fade effect - when come back
            FadeTransition ftt = new FadeTransition(Duration.millis(3000), this);
            ftt.setFromValue(0.0);
            ftt.setToValue(1.0);
            ftt.play();
            
            d.window.setScene(d.mapSelectMenu); // transit to next scene 
           
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

        MenuButton btnExit = new MenuButton("EXIT");
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
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

        HelpMenu helpMenu = new HelpMenu();
        helpMenu.createNewMenu(d, null);
        MenuButton btnHelp = new MenuButton("HELP?");
        btnHelp.setOnMouseClicked(event -> {
           
            d.window.setScene(d.helpMenu); // transit to next scene 
        });
        

        menu0.getChildren().addAll(btnResume, btnOptions, btnHelp, btnExit);
        menu1.getChildren().addAll(btnBack, btnSound, btnVideo);

        // Rectangle bg = new Rectangle(800, 600);
        // bg.setFill(Color.GREY);
        // bg.setOpacity(0);

        getChildren().addAll(menu0);
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