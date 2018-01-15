package de.moogle.gui.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import de.moogle.gui.application.Main;
import de.moogle.lucene.tools.Site;
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


	@FXML
	private MenuItem mbnew;

	@FXML
	private MenuItem mbexit;

	@FXML
	private MenuItem mbclear;

	@FXML
	private MenuItem mbhelp;

	@FXML
	private MenuItem mbabout;

	@FXML
	private TextField suchtextfeld;

	@FXML
	private Button searchbutton;

	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private DatePicker datefrom;

	@FXML
	private DatePicker dateto;

	@FXML
	private ImageView image;

	@FXML
	private CheckBox cbfourplayers;

	@FXML
	private CheckBox cbgamestar;

	@FXML
	private CheckBox cbchip;

	@FXML
	private CheckBox cbgamepro;

	@FXML
	private CheckBox cbgiga;

	@FXML
	private CheckBox cbgolem;

	@FXML
	private CheckBox cbign;

	@FXML
	private Text threadStatus;

	@FXML
	private Circle threadStatusCircle;

	// JavaFX Elemente initialisieren
	@FXML
	public void initialize() {
		// choiceBox.getItems().remove(choiceBox.getItems());
		choiceBox.getItems().clear();
		choiceBox.getItems().addAll(SearchTypes.getAllTypeNames());
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
		Main instance = Main.getInstance();
		instance.showSearchLayout();
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
		
		// Main Instanz
		Main mainInstance = Main.getInstance();

		// Speichern des Suchtextes in Mainvariable
		String text = suchtextfeld.getText();

		// Abfrage der Suchart
		String choice = choiceBox.getValue();

		// Abfrage der ChoiceBoxen
		List<Site> siteList = new ArrayList<>();
		if (cbfourplayers.isSelected()) {
			siteList.add(Site.FOURPLAYERS);
		}
		if (cbchip.isSelected()) {
			siteList.add(Site.CHIP);
		}
		if (cbgamepro.isSelected()) {
			siteList.add(Site.GAMEPRO);
		}
		if (cbgamestar.isSelected()) {
			siteList.add(Site.GAMESTAR);
		}
		if (cbgiga.isSelected()) {
			siteList.add(Site.GIGA);
		}
		if (cbgolem.isSelected()) {
			siteList.add(Site.GOLEM);
		}
		if (cbign.isSelected()) {
			siteList.add(Site.IGN);
		}

		// Abfrage des Suchzeitraums
		LocalDate[] dates = {datefrom.getValue(), dateto.getValue()};

		// Clearing der Suchfelder
		handleClear();

		// Wechsel zum Resultlayout

		mainInstance.showResultLayout(text, choice, siteList, dates);
	}

	public MenuItem getMbnew() {
		return mbnew;
	}

	public MenuItem getMbexit() {
		return mbexit;
	}

	public MenuItem getMbclear() {
		return mbclear;
	}

	public MenuItem getMbhelp() {
		return mbhelp;
	}

	public MenuItem getMbabout() {
		return mbabout;
	}

	public TextField getSuchtextfeld() {
		return suchtextfeld;
	}

	public Button getSearchbutton() {
		return searchbutton;
	}

	public ChoiceBox<String> getChoiceBox() {
		return choiceBox;
	}

	public DatePicker getDatefrom() {
		return datefrom;
	}

	public DatePicker getDateto() {
		return dateto;
	}

	public ImageView getImage() {
		return image;
	}

	public CheckBox getCbfourplayers() {
		return cbfourplayers;
	}

	public CheckBox getCbgamestar() {
		return cbgamestar;
	}

	public CheckBox getCbchip() {
		return cbchip;
	}

	public CheckBox getCbgamepro() {
		return cbgamepro;
	}

	public CheckBox getCbgiga() {
		return cbgiga;
	}

	public CheckBox getCbgolem() {
		return cbgolem;
	}

	public CheckBox getCbign() {
		return cbign;
	}

	public Text getThreadStatus() {
		return threadStatus;
	}

	public Circle getThreadStatusCircle() {
		return threadStatusCircle;
	}
}