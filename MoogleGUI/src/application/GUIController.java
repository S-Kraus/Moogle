package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
 
public class GUIController {
	
	ObservableList<String> choiceboxList = FXCollections.observableArrayList("Volltextsuche", "Personensuche", "Organisationssuche");
	
	@FXML
	ChoiceBox<String> choiceBox;
	
//	@FXML
//	TextField suchtextfeld;
//	
//	@FXML
//	DatePicker zeitraumvon;
//	
//	@FXML
//	DatePicker zeitraumbis;
	
	@FXML 
	public void initialize(){
		choiceBox.getItems().remove(choiceBox.getItems());
		choiceBox.getItems().addAll(choiceboxList);
		choiceBox.getSelectionModel().select(0);
	}
		

//	@FXML
//	protected void buttonPressed(){
//		String text = textArea.getText();
//		label.setText(text);
//		textArea.clear();
//	}
}
