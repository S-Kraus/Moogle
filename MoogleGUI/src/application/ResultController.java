package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
 
public class ResultController  {
	
	ObservableList<String> choiceboxList = FXCollections.observableArrayList("Volltextsuche", "Personensuche", "Organisationssuche");
	
	@FXML
	ChoiceBox<String> choiceBoxResult;
	

	@FXML 
	public void initialize(){
		choiceBoxResult.getItems().remove(choiceBoxResult.getItems());
		choiceBoxResult.getItems().addAll(choiceboxList);
		choiceBoxResult.getSelectionModel().select(0);
	}

}

