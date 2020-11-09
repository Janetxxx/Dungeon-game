package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.File;
import unsw.dungeon.enemy.*;
import unsw.dungeon.entity.*;
import unsw.dungeon.player.*;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;
    
    //Images
    private Image wallImage;
    private Image treasureImage;
    private Image enemyImage;
    private Image swordImage;
    private Image invincibilityPotionImage;
    // TODO: above to be added and design
    private Image keyImage;
    private Image closedDoorImage;
    private Image openDoorImage;
    private Image exitImage;
    private Image boulderImage;
    private Image floorSwitchImage;
    private Image portalImage;
    private Image alpacaImage;
    private Image wizardImage;
    private Image mushroomImage;
    private Image fireballImage;
    private Image potionStatusImage;
    private Image swordStatusImage;
    private Image disappearEffectImage;
    private Image sunHealthImage;
    private Image lightningImage;
    private Image knight1Image;
    private Image knight2Image;
    private Image knight3Image;
    private Image knight4Image;
    private Image skill1Image;
    private Image ghostImage;
    private Image bowImage;
    private Image windImage;
    
    private Image playerUpImage;
    private Image playerDownImage;
    private Image playerLeftImage;
    private Image playerRightImage;

    /**
     * where image is linked
     */
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        enemyImage = new Image((new File("images/alpacaRide0.png")).toURI().toString()); //  deep_elf_master_archer // 
        swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        invincibilityPotionImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString()); 
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        floorSwitchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        closedDoorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        openDoorImage = new Image((new File("images/open_door.png")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString()); 
        alpacaImage = new Image((new File("images/alpaca.gif")).toURI().toString()); 
        wizardImage = new Image((new File("images/gnome.png")).toURI().toString()); 
        mushroomImage = new Image((new File("images/mushroom.gif")).toURI().toString()); 
        fireballImage = new Image((new File("images/iceball.gif")).toURI().toString()); 
        potionStatusImage = new Image((new File("images/potionstatus.gif")).toURI().toString()); 
        swordStatusImage = new Image((new File("images/swordstatus.gif")).toURI().toString()); 
        disappearEffectImage = new Image((new File("images/disappearEffect.gif")).toURI().toString()); 
        sunHealthImage = new Image((new File("images/sunHealth.gif")).toURI().toString()); 
        lightningImage = new Image((new File("images/lightning.gif")).toURI().toString()); 
        skill1Image = new Image((new File("images/iceball.gif")).toURI().toString()); 
        ghostImage = new Image((new File("images/ghost.png")).toURI().toString());
        bowImage =  new Image((new File("images/bow.png")).toURI().toString());
        windImage =  new Image((new File("images/wind.gif")).toURI().toString());

        playerUpImage = new Image((new File("images/playerUp.gif")).toURI().toString()); 
        playerDownImage = new Image((new File("images/playerDown.gif")).toURI().toString()); 
        playerLeftImage = new Image((new File("images/playerLeft.gif")).toURI().toString()); 
        playerRightImage = new Image((new File("images/playerRight.gif")).toURI().toString());
        

        knight1Image = new Image((new File("images/knight_1.gif")).toURI().toString()); 
        knight2Image = new Image((new File("images/knight_2.gif")).toURI().toString()); 
        knight3Image = new Image((new File("images/knight_3.gif")).toURI().toString()); 
        knight4Image = new Image((new File("images/knight_4.gif")).toURI().toString()); 
        
    }

    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerDownImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    @Override 
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    @Override 
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }
    @Override 
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }
    @Override 
    public void onLoad(InvincibilityPotion invincibilityPotion) {
        ImageView view = new ImageView(invincibilityPotionImage);
        addEntity(invincibilityPotion, view);
    }

    @Override 
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }

    @Override 
    public void onLoad(Door door) {
        ImageView view = new ImageView(closedDoorImage);
        addEntity(door, view);
    }

    @Override 
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }

    @Override 
    public void onLoad(Switch floorSwitch) {
        ImageView view = new ImageView(floorSwitchImage);
        addEntity(floorSwitch, view);
    }
    
    @Override 
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }
    
    @Override 
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }

    @Override 
    public void onLoad(Alpaca alpaca) {
        ImageView view = new ImageView(alpacaImage);
        addEntity(alpaca, view);
    }
    @Override 
    public void onLoad(Wizard wizard) {
        ImageView view = new ImageView(wizardImage);
        addEntity(wizard, view);
    }
    @Override 
    public void onLoad(Mushroom mushroom) {
        ImageView view = new ImageView(mushroomImage);
        addEntity(mushroom, view);
    }
    @Override 
    public void onLoad(Fireball fireball) {
        ImageView view = new ImageView(fireballImage);
        addEntity(fireball, view);
    }
    @Override 
    public void onLoad(SunHealth sunHealth) {
        ImageView view = new ImageView(sunHealthImage);
        addEntity(sunHealth, view);
    }
    @Override 
    public void onLoad(Lightning lightning) {
        ImageView view = new ImageView(lightningImage);
        addEntity(lightning, view);
    }
    @Override 
    public void onLoad(Knight1 knight1) {
        ImageView view = new ImageView(knight1Image);
        addEntity(knight1, view);
    }
    @Override 
    public void onLoad(Knight2 knight2) {
        ImageView view = new ImageView(knight2Image);
        addEntity(knight2, view);
    }
    @Override 
    public void onLoad(Knight3 knight3) {
        ImageView view = new ImageView(knight3Image);
        addEntity(knight3, view);
    }
    @Override 
    public void onLoad(Knight4 knight4) {
        ImageView view = new ImageView(knight4Image);
        addEntity(knight4, view);
    }
    @Override 
    public void onLoad(Skill1 skill1) {
        ImageView view = new ImageView(skill1Image);
        view.setVisible(false);
        addEntity(skill1, view);
    }
    @Override 
    public void onLoad(Ghost ghost) {
        ImageView view = new ImageView(ghostImage);
        addEntity(ghost, view);
    }
    @Override 
    public void onLoad(Bow bow) {
        ImageView view = new ImageView(bowImage);
        addEntity(bow, view);
    }
    @Override 
    public void onLoad(Wind wind) {
        ImageView view = new ImageView(windImage);
        view.setVisible(false);
        addEntity(wind, view);
    }
    // TODO: to be add more and later add design for above
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entity.setNode(view);
        entities.add(view);
    }

    

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        
        entity.isInDungeon().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, 
                Boolean oldValue, Boolean newValue) {
                if (entity instanceof EnemyBase) {
                    setDisappearEffect(node, entity);
                }
                else {
                    node.setVisible(false);
                    entity.x().set(0);
                    entity.y().set(0);
                }
                    
            }
        });
        
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        // door
        if (entity instanceof Door) {
        	((Door) entity).isOpen().addListener(new ChangeListener<Boolean>() {
				@Override
                public void changed(ObservableValue<? extends Boolean> observable, 
                    Boolean oldValue, Boolean newValue) {
					((ImageView) node).setImage(openDoorImage);
				}
			});
        }
        if (entity instanceof Key) {
        	((Key) entity).getVisible().addListener(new ChangeListener<Boolean>() {
				@Override
                public void changed(ObservableValue<? extends Boolean> observable, 
                    Boolean oldValue, Boolean newValue) {
                    node.setVisible(newValue);
                    ((ImageView) node).setImage(keyImage);
				}
			});
        }
        // player
        if (entity instanceof Player) {
            Player player = (Player) entity;
        	player.getHasPotion().addListener(new ChangeListener<Boolean>() {
				@Override
                public void changed(ObservableValue<? extends Boolean> observable, 
                    Boolean oldValue, Boolean newValue) {
                        if (newValue == true)
                            ((ImageView) node).setImage(potionStatusImage);
                        else 
                            ((ImageView) node).setImage(playerDownImage);
				}
            });
            player.getHasSword().addListener(new ChangeListener<Boolean>() {
				@Override
                public void changed(ObservableValue<? extends Boolean> observable, 
                    Boolean oldValue, Boolean newValue) {
                        if (newValue == true)
                            ((ImageView) node).setImage(swordStatusImage);
                        else 
                            ((ImageView) node).setImage(playerDownImage);
				}
            });
            
            player.getDirections().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                        Number oldValue, Number newValue) {
                    if (player.getHasSword().get() == true || player.getHasPotion().get() == true) return;
                    switch (newValue.intValue()) {
                        case 1:
                            ((ImageView) node).setImage(playerUpImage);
                            break;
                        case 2:
                            ((ImageView) node).setImage(playerDownImage);
                            break;
                        case 3:
                            ((ImageView) node).setImage(playerLeftImage);
                            break;
                        case 4:
                            ((ImageView) node).setImage(playerRightImage);
                            break;
                        default:
                            break;
                    }
                   
                }
            });
        }
        
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }
    // FIXME: make timer stops after 1 s
    private void setDisappearEffect(Node n, Entity entity) {
        
        AnimationTimer timer = new AnimationTimer() {

            private long lastToggle;

            @Override
            public void handle(long now) {
                
                if (lastToggle == 0L) {
                    lastToggle = now;
                    ((ImageView) n).setImage(disappearEffectImage);
                } 
                else {
                    long diff = now - lastToggle;
                    if (diff >= 1_000_000_000L) { // 500,000,000ns == 500ms, execute every 1s, here
                        n.setVisible(false);
                        entity.x().set(0);
                        entity.y().set(0);
                        lastToggle = now;   
                    } 
                }
            }
        };
        timer.start();
    }

}

