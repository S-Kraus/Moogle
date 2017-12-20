package application;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import io.LuceneDocument;
import io.LuceneSearcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tools.Site;

public class ResultController {

	//Methode für Main (Zugriff auf Suchtext)
	public void setText(String suchtext) {
		this.suchtextfeld.setText(suchtext);
	}

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
	RadioButton rb4players;
	
	@FXML
	RadioButton rbgamestar;
	
	@FXML
	RadioButton rbchip;
	
	@FXML
	RadioButton rbgamepro;
	
	@FXML
	RadioButton rbgiga;
	
	@FXML
	RadioButton rbgolem;
	
	@FXML
	RadioButton rbign;
	
	@FXML
	VBox resultvbox;
	
	@FXML
	protected void buttonPressed() throws IOException, ParseException {
		
		//vorherige Suchergebnisse löschen
		resultvbox.getChildren().clear();

		//Sucheinstellungen abfragen 
		//Suchtext holen
		String suchtext = suchtextfeld.getText();
		//Auswahl Suchart: Volltextsuche, Personensuche, Orgsuche abfragen
		String selectetSuchart = choiceBox.getSelectionModel().getSelectedItem();
		//Auswahl Radiobutton für Spieleseiten
		
		//Auswahl des Suchzeitraums
		Date datefrom = new Date();
		Date dateto = new Date();
		datefrom = null;
		dateto = null;
		
		//
		Site[] sites = {Site.GAMESTAR, Site.GAMEPRO, Site.FOURPLAYERS};
		
		//Suchabfrage für Lucene vorbereiten
		
		//Lucene abfragen
		LuceneSearcher searcher = LuceneSearcher.getInstance();
		List<LuceneDocument> antwortListe = searcher.setSiteFilters(sites).setFromDate(datefrom).setToDate(dateto).getSearchResults(searcher.TYPE_TEXT_SEARCH, suchtext);
		System.out.println(antwortListe.toString());
		
		//Lucene Antworten Zeilenweise ausgeben
		for (int i = 0; i<antwortListe.size(); i++){
			
			System.out.println(antwortListe.get(i).toString());
			String titel = antwortListe.get(i).getTitle();
			String date2 = antwortListe.get(i).getDate();
			String link = antwortListe.get(i).getLink();
			
			//Trefferausgabe pro Treffer
			TrefferAusgabe neuerEintrag = new TrefferAusgabe(i+1, titel, date2, link);
			resultvbox.getChildren().add(neuerEintrag);
		};
		
		
	}

	// JavaFX Elemente initialisieren
	@FXML
	public void initialize() {
		
		choiceBox.getItems().remove(choiceBox.getItems());
		choiceBox.getItems().addAll(choiceboxList);
		choiceBox.getSelectionModel().select(0);

		image.setOnMouseClicked((event) -> {
			try {
				MouseEvent();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	//Link über Bild zurück zum SearchLayout
	@FXML
	private void MouseEvent() {
		Main search = new Main();
		search.showSearchLayout();

	}
}
