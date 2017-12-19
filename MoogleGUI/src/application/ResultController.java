package application;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ResultController {

	public void setText(String text) {
		this.suchTextfeld.setText(text);
	}

	ObservableList<String> choiceboxList = FXCollections.observableArrayList("Volltextsuche", "Personensuche",
			"Organisationssuche");

	@FXML
	VBox resultvbox;
	
	@FXML
	TextField suchTextfeld;

	@FXML
	ImageView picture;

	@FXML
	ChoiceBox<String> choiceBoxResult;
	
	@FXML
	Button suchanfrage;
	
	@FXML
	protected void buttonPressed() throws IOException, ParseException {
		
		TrefferAusgabe neuerEintrag = new TrefferAusgabe("Quatsch mit Rene", "12.12.1212", "http://www.linkus.de");
		resultvbox.getChildren().add(neuerEintrag);
	}

	@FXML
	public void initialize() {
		choiceBoxResult.getItems().remove(choiceBoxResult.getItems());
		choiceBoxResult.getItems().addAll(choiceboxList);
		choiceBoxResult.getSelectionModel().select(0);

		System.out.println("test1");

		picture.setOnMouseClicked((event) -> {
			try {
				MouseEvent();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	private void MouseEvent() {
		Main search = new Main();
		search.showSearchLayout();

	}
}
