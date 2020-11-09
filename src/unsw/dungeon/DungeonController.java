package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import unsw.dungeon.enemy.*;
import unsw.dungeon.menu.*;
import unsw.dungeon.player.*;

import java.io.File;
import java.io.IOException;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    List<ImageView> hearts = new ArrayList<>();
    private AnimationTimer timer;
    private Integer countdown = 4;
    private Text text = new Text("");
    private int moveCounter = 0;
    public DungeonApplication dungeonApplication;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);

    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/wall0.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        int heartcount = 0;
        boolean isTracked = false;

        for (int x = 0; x < dungeon.getWidth() + 1; x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                if (x == dungeon.getWidth()) {
                    ImageView view = null;
                    addHealthBar(x, y, view, heartcount);
                    if (!isTracked) {
                        trackMapLayout(view, "none");
                        isTracked = true;
                    }
                    heartcount++;
                    continue;
                }
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
            squares.setHalignment(entity, HPos.CENTER);
            squares.setValignment(entity, VPos.CENTER);
        }

        fixBigEntityPostion();
    }

    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException {
        moveCounter++;

        switch (event.getCode()) {
            case UP:
                player.directions.set(1);
                if (moveCounter != 3)
                    return;
                moveCounter = 0;
                player.moveUp();
                break;
            case DOWN:
                player.directions.set(2);
                if (moveCounter != 3)
                    return;
                moveCounter = 0;
                player.moveDown();
                break;
            case LEFT:
                player.directions.set(3);
                if (moveCounter != 3)
                    return;
                moveCounter = 0;
                player.moveLeft();
                break;
            case RIGHT:
                player.directions.set(4);
                if (moveCounter != 3)
                    return;
                moveCounter = 0;
                player.moveRight();
                break;
            case D:
                player.dropKey();
                break;
            case A:
                if (!player.getMoveThroughWall().get()) {
                    player.skill2();
                }
                
                break;
            case W :
                if (player.getIceball() != null && !player.getIceball().isMoving()) {
                    if (!player.getEnemySheep().get())
                        player.skill(); // FIXEME - add new key
                }
                break;
            case ESCAPE:

                ResumeMenu menu = new ResumeMenu(); // here past in object instead of value
                menu.createNewMenu(dungeonApplication, dungeon);

                break;
            case S:
                player.attack();
                break;
            default:
                break;
        }
    }

    @FXML
    public void handleKeyRelease(KeyEvent event) throws IOException {
        // moveCounter
        moveCounter = 0;
        switch (event.getCode()) {
            case UP:

                player.moveUp();
                break;
            case DOWN:

                player.moveDown();
                break;
            case LEFT:

                player.moveLeft();
                break;
            case RIGHT:

                player.moveRight();
                break;
            default:
                break;
        }
    }

    /**
     * health bar track skill bar track lose menu use name to identify whether the
     * view variable is used
     * 
     * @param view
     */
    private void trackMapLayout(ImageView view, String name) {
        
        if (name.equalsIgnoreCase("fireskill")) {
            player.getMoveThroughWall().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    if (newValue) {
                        view.setVisible(false);
                        countdownLabel(dungeon.getWidth(), 6);
                        timer.start();
                    } else {
                        view.setVisible(true);
                        timer.stop();
                        createLabel(-1, dungeon.getWidth(), 6);
                        countdown = 4;
                    }

                }
            });
        }
        
        if (name.equalsIgnoreCase("coolskill")) {
            player.getEnemySheep().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    if (newValue) {
                        view.setVisible(false);
                        countdownLabel2(dungeon.getWidth(), 7);
                        timer2.start();
                    } else {
                        view.setVisible(true);
                        timer2.stop();
                        createLabel2(-1, dungeon.getWidth(), 7);
                        countdown2 = 4;
                    }

                }
            });
        }
        // below not using view
        if (!name.equalsIgnoreCase("none"))
            return;
        player.getHealth().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 0)
                    return;
                if (newValue.intValue() > oldValue.intValue()) {
                    changeOneHeart(false);

                } else if (newValue.intValue() < oldValue.intValue()) {
                    changeOneHeart(true);

                }
            }
        });
        player.getIsHurted().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == true) {
                    setHurtEffect(player);
                }
            }
        });
        // dungeon - enter boss mode
        dungeon.getIsGoalSuccess().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                
                PopupWindow popup = new PopupWindow("The game is finished, u win the game, game is finished now");
                popup.show();
                MenuTask menuTask = new MenuTask();
                menuTask.set_losemenu_task(dungeonApplication, dungeon);
                SideTimer t = new SideTimer();
                t.makeSideTimer(2_000_000_000L, menuTask, "winmenu");
            }
        });
        // dungeon - losemeu
        dungeon.getIsGameOver().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {
                    
                    MenuTask menuTask = new MenuTask();
                    menuTask.set_losemenu_task(dungeonApplication, dungeon);
                    SideTimer t = new SideTimer();
                    t.makeSideTimer(2_000_000_000L, menuTask, "losemenu");
                }  
            }
        });
    }
    // true - change full heart to empty
    // false - change empty heart to true
    // precondition: assume at least one heart
    private void changeOneHeart(boolean bool) {
        ImageView prev = null;
        for (ImageView i : hearts) {
            
            if (bool) {
                if (!i.isVisible()) {
                    prev.setVisible(false); // when convert full to empty, starts from end
                    return;
                }
                    
            }
            else {
                if (!i.isVisible()) {
                    i.setVisible(true);
                    return;
                }  
            }
            prev = i;
        }
    }

    public void setDungeonApplication(DungeonApplication dungeonApplication) {
        this.dungeonApplication = dungeonApplication;
    }
    /**
     * @see https://stackoverflow.com/questions/25852959/center-align-rows-of-a-gridpane-in-javafx - positioning
     * @param x
     * @param y
     */
    
    public void countdownLabel( int x, int y) {
        timer = new AnimationTimer() {

            private long lastToggle = 0;

            @Override
            public void handle(long now) {
                
                if (lastToggle == 0L) {
                    lastToggle = now;
                    
                } 
                else {
                    long diff = now - lastToggle;
                    if (diff >= 1_000_000_000L) { // 500,000,000ns == 500ms, execute every 1s, here
                        createLabel(countdown, x, y);
                        countdown--;
                        lastToggle = now;   
                    } 
                }
            }
        };
    }
    /**
     * if -1 - a, for skill button
     * @param c
     * @param x
     * @param y
     * @see https://www.tutorialspoint.com/javafx/javafx_text.htm#:~:text=You%20can%20change%20the%20font,Font%20of%20the%20package%20javafx.
     */
    private void createLabel(int c, int x, int y) {
        
        String str = String.valueOf(c);
        if (c == -1) {
            str = "a";
        }
        text.setVisible(false);
        text = new Text(str);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text.setFill(Color.WHITE);
        
        squares.add(text, x, y);
        squares.setHalignment(text, HPos.CENTER);
        squares.setValignment(text, VPos.CENTER);
    }
    
    private void fixBigEntityPostion() {
        for (Entity e : dungeon.getEntities()) {
            if (e instanceof Knight1 || e instanceof Knight3) {
                squares.setHalignment((ImageView) e.getNode(), HPos.RIGHT);
            } else if (e instanceof Knight2 || e instanceof Knight4) {
                squares.setHalignment((ImageView) e.getNode(), HPos.LEFT);
            }
        }
    }
    // add RHS Bar
    private void addHealthBar(int x, int y, ImageView view, int heartcount) {
        Image heart = new Image((new File("images/heart.png")).toURI().toString());
        Image emptyHeart = new Image((new File("images/emptyheart.png")).toURI().toString());
        Image black = new Image((new File("images/blackbackGround.png")).toURI().toString());
        Image fireSkill = new Image((new File("images/fireSkill.png")).toURI().toString());
        Image cool = new Image((new File("images/cool.png")).toURI().toString());

        view = new ImageView(emptyHeart); // buttom layer
        if (heartcount >= 5) view = new ImageView(black);
        squares.add(view, x, y);
        if (heartcount == 6) {
            ImageView view2 = new ImageView(fireSkill);
            squares.add(view2, x, y);
            createLabel(-1, dungeon.getWidth(), 6);
            trackMapLayout(view2, "fireskill");
        }
        if (heartcount == 7) {
            ImageView view2 = new ImageView(cool);
            squares.add(view2, x, y);
            createLabel2(-1, dungeon.getWidth(), 7);
            trackMapLayout(view2, "coolskill");
        }
        if (heartcount == 9) { // button
            Button b = new Button("BAG");
            setBag(b);
            squares.add(b, x, y);
        }
        if (heartcount == 10) { // button
            Button b = new Button("GOAL");
            setGoal(b);
            squares.add(b, x, y);
        }
        view = new ImageView(heart);

        if (heartcount >= 3) {
            view.setVisible(false); // to be converted to true
        }
        
        squares.add(view, x, y);
        hearts.add(view);
    }
    private void setHurtEffect(Entity entity) {
        
        ImageView playerHurtedImage = new ImageView(new Image((new File("images/hurt.gif")).toURI().toString())); 
        AnimationTimer timer = new AnimationTimer() {

            private long lastToggle;

            @Override
            public void handle(long now) {
                
                if (lastToggle == 0L) {
                    lastToggle = now;
                    squares.add(playerHurtedImage, entity.getX(), entity.getY());
                } 
                else {
                    long diff = now - lastToggle;
                    if (diff >= 1_000_000_000L) { // 500,000,000ns == 500ms, execute every 1s, here
                        playerHurtedImage.setVisible(false);
                        player.setIsHurted(false);
                        lastToggle = now;   
                    } 
                }
            }
        };
        timer.start();
    }
    private void setBag(Button b) {
        b.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 8));
        b.setOnMouseClicked(event -> {
            String output = "";
            for (Entity e : player.getCollections()) {
                if (e == null) continue;
                output += e.getName() + " ";
            }
            if (player.getKeyInHand() != null) {
                if (!output.contains("Key"))
                    output += player.getKeyInHand().getName() + " ";
            }
            if (output.equals("")) {
                output = "Nothing in the bag yet";
            }
            PopupWindow popup = new PopupWindow(output);
            popup.show();
        });
    }
    private void setGoal(Button b) {
        b.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 8));
        b.setOnMouseClicked(event -> {
            
            PopupWindow popup = new PopupWindow(dungeonApplication.currentGame);
            popup.show();
        });
    }
    private AnimationTimer timer2;
    private Integer countdown2 = 4;
    private Text text2 = new Text("");
    private void createLabel2(int c, int x, int y) {
        
        String str = String.valueOf(c);
        if (c == -1) {
            str = "w";
        }
        text2.setVisible(false);
        text2 = new Text(str);
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text2.setFill(Color.WHITE);
        
        squares.add(text2, x, y);
        squares.setHalignment(text2, HPos.CENTER);
        squares.setValignment(text2, VPos.CENTER);
    }
    private void countdownLabel2( int x, int y) {
        timer2 = new AnimationTimer() {

            private long lastToggle = 0;

            @Override
            public void handle(long now) {
                
                if (lastToggle == 0L) {
                    lastToggle = now;
                    
                } 
                else {
                    long diff = now - lastToggle;
                    if (diff >= 1_000_000_000L) { // 500,000,000ns == 500ms, execute every 1s, here
                        createLabel2(countdown2, x, y);
                        countdown2--;
                        lastToggle = now;   
                    } 
                }
            }
        };
    }
}


