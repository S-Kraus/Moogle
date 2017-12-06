package application;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
 
public class ResultController  {
	
	
	public void setText(String text){
		this.suchTextfeld.setText(text);
	}
	
	ObservableList<String> choiceboxList = FXCollections.observableArrayList("Volltextsuche", "Personensuche", "Organisationssuche");
	
	@FXML
	TextField suchTextfeld;
	
	@FXML
	ChoiceBox<String> choiceBoxResult;
	

	@FXML 
	public void initialize(){
		choiceBoxResult.getItems().remove(choiceBoxResult.getItems());
		choiceBoxResult.getItems().addAll(choiceboxList);
		choiceBoxResult.getSelectionModel().select(0);
		
		System.out.println("test1");
	}

}

