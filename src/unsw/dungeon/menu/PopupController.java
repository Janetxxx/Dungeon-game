package unsw.dungeon.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PopupController {
	private String text;
	public PopupController(String text) {
		this.text = text;
	}
	@FXML
	private AnchorPane anchorpane;
	@FXML
    private Button CloseButton;
	@FXML
	private Label textfield;
    @FXML
    void CloseButtonAction(ActionEvent event) {
    	Stage stage  = (Stage) CloseButton.getScene().getWindow();
    	stage.close();
    	//close the window which is displayed now
    }
    @FXML
    private void initialize() {
		anchorpane.getStyleClass().add("pane");
    	textfield.setText(text);
		textfield.setWrapText(true);	
		textfield.getStyleClass().add("label");
    	//set the text 
    }

}

