package de.moogle.gui.controller;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.queryparser.classic.ParseException;

import de.moogle.gui.application.Main;
import de.moogle.gui.application.SearchResults;
import de.moogle.gui.application.SearchTypes;
import de.moogle.gui.application.TrefferAusgabe;
import de.moogle.lucene.io.LuceneDocument;
import de.moogle.lucene.io.LuceneSearcher;
import de.moogle.lucene.tools.Site;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class ResultController {

	private Main main;

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

	@FXML
	private VBox resultvbox;

	@FXML
	private ScrollPane scrollpane;

	// JavaFX Elemente initialisieren
	@FXML
	public void initialize() {

		choiceBox.getItems().clear();
		choiceBox.getItems().addAll(SearchTypes.getAllTypeNames());
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
		main.showSearchLayout();
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

	// Link über Bild zurück zum SearchLayout
	@FXML
	private void MouseEvent() {
		main.showSearchLayout();
	}

	@FXML
	public void buttonPressed() throws IOException, ParseException {

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
			String selectedSearch = choiceBox.getSelectionModel().getSelectedItem();

			// Auswahl Checkboxes für Spieleseiten
			List<Site> sitelist = new ArrayList<>();

			if (cbfourplayers.isSelected()) {
				sitelist.add(Site.FOURPLAYERS);
			}
			if (cbchip.isSelected()) {
				sitelist.add(Site.CHIP);
			}
			if (cbgamepro.isSelected()) {
				sitelist.add(Site.GAMEPRO);
			}
			if (cbgamestar.isSelected()) {
				sitelist.add(Site.GAMESTAR);
			}
			if (cbgiga.isSelected()) {
				sitelist.add(Site.GIGA);
			}
			if (cbgolem.isSelected()) {
				sitelist.add(Site.GOLEM);
			}
			if (cbign.isSelected()) {
				sitelist.add(Site.IGN);
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
			searcher = searcher.setSiteFilters(sitelist.toArray(new Site[sitelist.size()]))
					.setFromDate(instantFrom != null ? Date.from(instantFrom) : null)
					.setToDate(instantTo != null ? Date.from(instantTo) : null);
			List<LuceneDocument> documents;
			switch (selectedSearch) {
			case "Personen- und Organisationssuche":
				documents = searcher.getSearchResults(LuceneSearcher.TYPE_PERSON_ORG_SEARCH, suchtext);
				break;
			case "Personensuche":
				documents = searcher.getSearchResults(LuceneSearcher.TYPE_PERSON_SEARCH, suchtext);
				break;
			case "Organisationssuche":
				documents = searcher.getSearchResults(LuceneSearcher.TYPE_ORG_SEARCH, suchtext);
				break;
			default:
				documents = searcher.getSearchResults(LuceneSearcher.TYPE_TEXT_SEARCH, suchtext);
				break;
			}

			// Suchergebniscounter
			int j = 1;

			// Lucene Antworten Zeilenweise ausgeben
			for (int i = 0; i < documents.size(); i++) {

				// System.out.println(antwortListe.get(i).toString());
				String titel = documents.get(i).getTitle();
				String date2 = documents.get(i).getDate();
				String link = documents.get(i).getLink();
				String path = documents.get(i).getPath();

				// Trefferausgabe pro Treffer und Mehrfacheinträge filtern
				if (new File(documents.get(i).getPath()).exists()) {
					if (i == 0) {
						TrefferAusgabe neuerEintrag = new TrefferAusgabe(j, titel, date2, link, path);
						resultvbox.getChildren().add(neuerEintrag);
					} else if (!documents.get(i).getLink().equals(documents.get(i - 1).getLink())) {
						j++;
						TrefferAusgabe neuerEintrag = new TrefferAusgabe(j, titel, date2, link, path);
						resultvbox.getChildren().add(neuerEintrag);
					}
				}
			}
		}
	}
	
	public void initValues() {
		Map<String, Object> result = SearchResults.getLastResult();
		suchtextfeld.setText((String) result.get(SearchResults.TEXT));
		choiceBox.setValue((String) result.get(SearchResults.TYPE));
		List<Site> sites = (List<Site>) result.get(SearchResults.SITELIST);
		for (Site site : sites) {
			if (site.equals(Site.CHIP)) {
				cbchip.setSelected(true);
			}
			if (site.equals(Site.FOURPLAYERS)) {
				cbfourplayers.setSelected(true);
			}
			if (site.equals(Site.GAMEPRO)) {
				cbgamepro.setSelected(true);
			}
			if (site.equals(Site.GAMESTAR)) {
				cbgamestar.setSelected(true);
			}
			if (site.equals(Site.GIGA)) {
				cbgiga.setSelected(true);
			}
			if (site.equals(Site.GOLEM)) {
				cbgolem.setSelected(true);
			}
			if (site.equals(Site.IGN)) {
				cbign.setSelected(true);
			}
		}
		datefrom.setValue((LocalDate) result.get(SearchResults.DATE_FROM));
		dateto.setValue((LocalDate) result.get(SearchResults.DATE_TO));
	}
	
	public void setMain(Main main) {
		this.main = main;
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

	public VBox getResultvbox() {
		return resultvbox;
	}

	public ScrollPane getScrollpane() {
		return scrollpane;
	}
}