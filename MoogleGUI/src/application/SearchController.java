package application;

import java.io.IOException;
import org.apache.lucene.queryparser.classic.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SearchController {

	// Befüllung der Auswahlliste für die Suchart
	ObservableList<String> choiceboxList = FXCollections.observableArrayList("Volltextsuche", "Personensuche",
			"Organisationssuche");

	@FXML
	MenuItem mbnew;

	@FXML
	MenuItem mbexit;

	@FXML
	MenuItem mbclear;

	@FXML
	MenuItem mbhelp;

	@FXML
	MenuItem mbabout;

	@FXML
	TextField suchtextfeld;

	@FXML
	Button searchbutton;

	@FXML
	ChoiceBox<String> choiceBox;

	@FXML
	DatePicker datefrom;

	@FXML
	DatePicker dateto;

	@FXML
	ImageView image;

	@FXML
	CheckBox cbfourplayers;

	@FXML
	CheckBox cbgamestar;

	@FXML
	CheckBox cbchip;

	@FXML
	CheckBox cbgamepro;

	@FXML
	CheckBox cbgiga;

	@FXML
	CheckBox cbgolem;

	@FXML
	CheckBox cbign;

	// JavaFX Elemente initialisieren
	@FXML
	public void initialize() {
		// choiceBox.getItems().remove(choiceBox.getItems());
		choiceBox.getItems().clear();
		choiceBox.getItems().addAll(choiceboxList);
		choiceBox.getSelectionModel().select(0);

		suchtextfeld.setOnAction((event) -> {
			try {
				buttonPressed();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@FXML
	protected void buttonPressed() throws IOException, ParseException {

		// Speichern des Suchtextes in Mainvariable
		Main.setText(suchtextfeld.getText());
		suchtextfeld.clear();

		// Abfrage der ChoiceBoxen
		if (cbfourplayers.isSelected() == false) {
			Main.setCbfourplayers(false);
		}
		if (cbchip.selectedProperty().getValue() == false) {
			Main.setCbchip(false);
		}
		if (cbgamepro.selectedProperty().getValue() == false) {
			Main.setCbgamepro(false);
		}
		if (cbgamestar.selectedProperty().getValue() == false) {
			Main.setCbgamestar(false);
		}
		if (cbgiga.selectedProperty().getValue() == false) {
			Main.setCbgiga(false);
		}
		if (cbgolem.selectedProperty().getValue() == false) {
			Main.setCbgolem(false);
		}
		if (cbign.selectedProperty().getValue() == false) {
			Main.setCbign(false);
		}
		
		// Abfrage des Suchzeitraums
		datefrom.getValue();
		dateto.getValue();

		// Wechsel zum Resultlayout
		Main.showResultLayout();
	}
}