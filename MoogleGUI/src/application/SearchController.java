package application;

import java.io.IOException;
import org.apache.lucene.queryparser.classic.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class SearchController {

	// Befüllung der Auswahlliste für die Suchart
	ObservableList<String> choiceboxList = FXCollections.observableArrayList("Volltextsuche", "Personensuche",
			"Organisationssuche", "Personen- und Organisationssuche");

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
	private void handleNew() {
		Main.showSearchLayout();
	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}

	@FXML
	private void handleClear() {
		suchtextfeld.clear();
		datefrom.setValue(null);
		dateto.setValue(null);
		choiceBox.getSelectionModel().select(0);
		cbfourplayers.setSelected(true);
		cbchip.setSelected(true);
		cbgamepro.setSelected(true);
		cbgamestar.setSelected(true);
		cbgiga.setSelected(true);
		cbgolem.setSelected(true);
		cbign.setSelected(true);
	}

	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Moogle - Das LeckSieCon");
		alert.setHeaderText("About");
		alert.setContentText("Authoren: René und seine 3 kleinen Schweinchen");

		alert.showAndWait();
	}

	@FXML
	private void handleHelp() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Moogle - Das LeckSieCon");
		alert.setHeaderText("Help");
		alert.setContentText("www.giyf.de");

		alert.showAndWait();
	}

	@FXML
	protected void buttonPressed() throws IOException, ParseException {

		// Speichern des Suchtextes in Mainvariable
		Main.setText(suchtextfeld.getText());

		// Abfrage der Suchart
		Main.setChoiceBox(choiceBox.getValue());

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
		if (datefrom.getValue() != null) {
			Main.setDatefrom(datefrom.getValue());
		}
		if (dateto.getValue() != null) {
			Main.setDateto(dateto.getValue());
		}

		// Clearing der Suchfelder
		handleClear();

		// Wechsel zum Resultlayout
		Main.showResultLayout();
	}
}