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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

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

	@FXML
	Text threadStatus;

	@FXML
	Circle threadStatusCircle;

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

		FlowPane fp = new FlowPane();
		Hyperlink link = new Hyperlink("www.gidf.de");
		fp.getChildren().addAll(link);
		alert.getDialogPane().contentProperty().set(fp);

		link.setOnAction((event) -> {
			alert.close();
			Main browser = new Main();
			browser.showHyperlink(link);
		});

		alert.showAndWait();
	}

	@FXML
	protected void buttonPressed() throws IOException, ParseException {

		// Speichern des Suchtextes in Mainvariable
		Main.setText(suchtextfeld.getText());

		// Abfrage der Suchart
		Main.setChoiceBox(choiceBox.getValue());

		// Abfrage der ChoiceBoxen
		Main.setCbfourplayers(cbfourplayers.isSelected());
		Main.setCbchip(cbchip.isSelected());
		Main.setCbgamepro(cbgamepro.isSelected());
		Main.setCbgamestar(cbgamestar.isSelected());
		Main.setCbgiga(cbgiga.isSelected());
		Main.setCbgolem(cbgolem.isSelected());
		Main.setCbign(cbign.isSelected());

		// Abfrage des Suchzeitraums
		Main.setDatefrom(datefrom.getValue());
		Main.setDateto(dateto.getValue());

		// Clearing der Suchfelder
		handleClear();

		// Wechsel zum Resultlayout

		Main.showResultLayout();
	}
}