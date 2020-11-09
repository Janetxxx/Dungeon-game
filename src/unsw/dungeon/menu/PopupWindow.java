package unsw.dungeon.menu;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PopupWindow {
    private String text;
    public PopupWindow (String text){
        //show the stage
        this.text = text;
    }

    public void start(Stage primaryStage) throws Exception{
    	primaryStage.setTitle("Warning");
    	//set fxml
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupWindow.fxml"));
    	PopupController controller = new PopupController(text);
    	loader.setController(controller);
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("unsw/dungeon/labelColor.css");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        AnimationTimer timer = new AnimationTimer() { 

            private long lastToggle;

            @Override
            public void handle(long now) {
                
                if (lastToggle == 0L) {
                    lastToggle = now;
                    primaryStage.show();   
                } 
                else {
                    long diff = now - lastToggle;
                    if (diff >= 1_000_000_000L) { // 500,000,000ns == 500ms, execute every 1s, here
                        primaryStage.close();  
                        lastToggle = now;   
                    } 
                }
            }
        };
        timer.start();
        
    }  

    public void show() {
    	Stage stage = new Stage();
    	
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

}