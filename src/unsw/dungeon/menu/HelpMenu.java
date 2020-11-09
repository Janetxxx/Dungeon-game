package unsw.dungeon.menu;

import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonApplication;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelpMenu extends CreateNewMenu {
    public void newSceneConnectToStage(DungeonApplication dungeonApplication, Scene e) {
        dungeonApplication.helpMenu= e;
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

        HelpMenu helpMenu = new HelpMenu(dungeonApplication, dungeon); // here past in object instead of value
        helpMenu.setVisible(true); // set as false if used as resume menu

        root.getChildren().addAll(imgView, helpMenu);
        return root;
    }
    public HelpMenu() {}

    private HelpMenu(DungeonApplication d, Dungeon dungeon) {
        
        VBox menu0 = new VBox(5);

        menu0.setTranslateX(100); // sub menu
        menu0.setTranslateY(50);

        MenuButton btnResume = new MenuButton("BACK");
        btnResume.setOnMouseClicked(event -> {
            
            d.window.setScene(d.prevMenu); 
        });
        Text Treasure = new Text("Treasure - can be collected by the player");
        Treasure.setFont(Treasure.getFont().font(14));
        Treasure.setFill(Color.WHITE);
        Text Door = new Text("Door - player holds the matching key, can open the door by moving through it");
        Door.setFont(Door.getFont().font(14));
        Door.setFill(Color.WHITE);
        Text Key = new Text("Key - player can carry only one key at a time, can drop the key when player dont want");
        Key.setFont(Key.getFont().font(14));
        Key.setFill(Color.WHITE);
        Text Boulder = new Text("Boulder - acts like a wall in most cases, player can push one boulder at a time");
        Boulder.setFont(Boulder.getFont().font(14));
        Boulder.setFill(Color.WHITE);
        Text Switch = new Text("Switch - pushing a boulder on the floor switch triggers it, vice versa");
        Switch.setFont(Switch.getFont().font(14));
        Switch.setFill(Color.WHITE);
        Text Portal = new Text("Portal - teleports entities to a corresponding portal");
        Portal.setFont(Portal.getFont().font(14));
        Portal.setFill(Color.WHITE);
        Text Ghost = new Text("Ghost - can move through solid entity and kill player");
        Ghost.setFont(Ghost.getFont().font(14));
        Ghost.setFill(Color.WHITE);
        Text Mushroom = new Text("Mushroom - can jump towards player and eat player");
        Mushroom.setFont(Mushroom.getFont().font(14));
        Mushroom.setFill(Color.WHITE);

        Text sword = new Text("Sword - can only be picked once, can kill enemy instantly");
        sword.setFont(sword.getFont().font(14));
        sword.setFill(Color.WHITE);
        Text potion = new Text("Potion - when hold enemy in contact die, but enemy will run away");
        potion.setFont(potion.getFont().font(14));
        potion.setFill(Color.WHITE);
        Text sun = new Text("Sun - can heal one health");
        sun.setFont(sun.getFont().font(14));
        sun.setFill(Color.WHITE);
        Text bow = new Text("bow - can shoot tornado");
        bow.setFont(bow.getFont().font(14));
        bow.setFill(Color.WHITE);

        Text attack = new Text("attack - press S key");
        attack.setFont(attack.getFont().font(14));
        attack.setFill(Color.WHITE);
        Text empty = new Text("");
        empty.setFont(empty.getFont().font(14));
        empty.setFill(Color.WHITE);

        menu0.getChildren().addAll(sword, sun, Key, Ghost, Mushroom, potion, Treasure, Door, Boulder, Switch, Portal, bow, attack, empty, btnResume);
        
        getChildren().addAll(menu0);
    }
}
