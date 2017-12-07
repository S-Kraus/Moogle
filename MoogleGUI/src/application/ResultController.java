package application;

import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ResultController {

	public void setText(String text) {
		this.suchTextfeld.setText(text);
	}

	ObservableList<String> choiceboxList = FXCollections.observableArrayList("Volltextsuche", "Personensuche",
			"Organisationssuche");

	@FXML
	TextField suchTextfeld;

	@FXML
	ImageView picture;

	@FXML
	ChoiceBox<String> choiceBoxResult;

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
