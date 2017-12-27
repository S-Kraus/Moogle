package application;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import io.LuceneDocument;
import io.LuceneSearcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tools.Site;

public class ResultController {

	// Befüllung der Auswahlliste für die Suchart
	ObservableList<String> choiceboxList = FXCollections.observableArrayList("Volltextsuche", "Personensuche",
			"Organisationssuche");

	// ArrayList for Hyperlinks
	List<Hyperlink> links = new ArrayList<>();

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
	VBox resultvbox;

	@FXML
	ScrollPane scrollpane;

	// JavaFX Elemente initialisieren
	@FXML
	public void initialize() {

		choiceBox.getItems().clear();
		choiceBox.getItems().addAll(choiceboxList);
		choiceBox.getSelectionModel().select(0);

		image.setOnMouseClicked((event) -> {
			try {
				MouseEvent();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

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
		resultvbox.getChildren().clear();
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

	// Link über Bild zurück zum SearchLayout
	@FXML
	private void MouseEvent() {
		Main.showSearchLayout();
	}

	@FXML
	protected void buttonPressed() throws IOException, ParseException {

		// vorherige Suchergebnisse löschen
		resultvbox.getChildren().clear();

		// Suchtext holen
		String suchtext = suchtextfeld.getText();

		// Motivator Strings
		HashMap<String, String> map = new HashMap<String, String>();

		map.put(new String("arsch"), new String("Selber. Du Riesen Schildkröte!"));
		map.put(new String("penner"), new String("Dei Muadda, Buarsche!!!"));
		map.put(new String("moogle"), new String("Ja. Und jetzt?"));
		map.put(new String("suche"), new String("Na klar. Such dich selber Junge!"));
		map.put(new String("rene"), new String("Wir lieben dich (deine drei kleinen Schweinchen!)"));
		map.put(new String("simon"), new String("Hallo Simon"));
		map.put(new String("andreas"), new String("Schön das du da bist und nicht hier :-)"));
		map.put(new String("stephan"), new String("Was soll mann dazu sagen? Du geiler Typ!"));

		// Motivator abfragen
		// Textübergabe an Main
		if (map.containsKey(suchtext)) {
			suchtextfeld.setText((String) map.get(suchtext));
		} else {
			// Suchabfrage für Lucene vorbereiten
			// Sucheinstellungen abfragen

			// Auswahl Suchart: Volltextsuche, Personensuche, Orgsuche abfragen
			String selectedSuchart = choiceBox.getSelectionModel().getSelectedItem();

			// Auswahl Checkboxes für Spieleseiten
			List<String> sitelist = new ArrayList<>();
			sitelist.add("FOURPLAYERS");
			sitelist.add("CHIP");
			sitelist.add("GAMEPRO");
			sitelist.add("GAMESTAR");
			sitelist.add("GIGA");
			sitelist.add("GOLEM");
			sitelist.add("IGN");

			if (cbfourplayers.selectedProperty().getValue() == false) {
				sitelist.remove("FOURPLAYERS");
			}
			if (cbchip.selectedProperty().getValue() == false) {
				sitelist.remove("CHIP");
			}
			if (cbgamepro.selectedProperty().getValue() == false) {
				sitelist.remove("GAMEPRO");
			}
			if (cbgamestar.selectedProperty().getValue() == false) {
				sitelist.remove("GAMESTAR");
			}
			if (cbgiga.selectedProperty().getValue() == false) {
				sitelist.remove("GIGA");
			}
			if (cbgolem.selectedProperty().getValue() == false) {
				sitelist.remove("GOLEM");
			}
			if (cbign.selectedProperty().getValue() == false) {
				sitelist.remove("IGN");
			}

			Site[] sites = new Site[sitelist.size()];
			for (int k = 0; k < sites.length; k++) {
				sites[k] = Site.valueOf(sitelist.get(k));
			}

			// Auswahl des Suchzeitraums
			Instant instantFrom = null;
			Instant instantTo = null;

			if (datefrom.getValue() != null) {
				LocalDate ldFrom = datefrom.getValue();
				instantFrom = Instant.from(ldFrom.atStartOfDay(ZoneId.systemDefault()));
			}
			if (dateto.getValue() != null) {
				LocalDate ldTo = dateto.getValue();
				instantTo = Instant.from(ldTo.atStartOfDay(ZoneId.systemDefault()));
			}

			// Lucene abfragen
			LuceneSearcher searcher = LuceneSearcher.getInstance();
			List<LuceneDocument> antwortListe = searcher.setSiteFilters(sites)
					.setFromDate(instantFrom != null ? Date.from(instantFrom) : null)
					.setToDate(instantTo != null ? Date.from(instantTo) : null)
					.getSearchResults(LuceneSearcher.TYPE_TEXT_SEARCH, suchtext);
			// System.out.println(antwortListe.toString());

			// Suchergebniscounter
			int j = 1;

			// Lucene Antworten Zeilenweise ausgeben
			for (int i = 0; i < antwortListe.size(); i++) {

				// System.out.println(antwortListe.get(i).toString());
				String titel = antwortListe.get(i).getTitle();
				String date2 = antwortListe.get(i).getDate();
				String link = antwortListe.get(i).getLink();

				// Trefferausgabe pro Treffer und Mehrfacheinträge filtern
				if (i == 0) {
					TrefferAusgabe neuerEintrag = new TrefferAusgabe(j, titel, date2, link);
					resultvbox.getChildren().add(neuerEintrag);
				} else if (!antwortListe.get(i).getLink().equals(antwortListe.get(i - 1).getLink())) {
					j++;
					TrefferAusgabe neuerEintrag = new TrefferAusgabe(j, titel, date2, link);
					resultvbox.getChildren().add(neuerEintrag);
				}
			}
		}
	}
}