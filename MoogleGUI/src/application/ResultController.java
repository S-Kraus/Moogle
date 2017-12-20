package application;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import io.LuceneDocument;
import io.LuceneSearcher;
import io.LuceneSearcher.Sites;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ResultController {

	//Methode f�r Main (Zugriff auf Suchtext)
	public void setText(String suchtext) {
		this.suchtextfeld.setText(suchtext);
	}

	// Bef�llung der Auswahlliste f�r die Suchart
	ObservableList<String> choiceboxList = FXCollections.observableArrayList("Volltextsuche", "Personensuche",
			"Organisationssuche");

	@FXML
	VBox resultvbox;
	
	@FXML
	TextField suchtextfeld;

	@FXML
	ImageView picture;

	@FXML
	ChoiceBox<String> choiceBoxResult;
	
	@FXML
	Button suchanfrage;
	
	@FXML
	protected void buttonPressed() throws IOException, ParseException {
		
		//vorherige Suchergebnisse l�schen
		resultvbox.getChildren().clear();

		//Sucheinstellungen abfragen 
		//Suchtext holen
		String suchtext = suchtextfeld.getText();
		//Auswahl Suchart: Volltextsuche, Personensuche, Orgsuche abfragen
		String selectetSuchart = choiceBoxResult.getSelectionModel().getSelectedItem();
		//Auswahl Radiobutton f�r Spieleseiten
		
		//Auswahl des Suchzeitraums
		
		
		//Suchabfrage f�r Lucene vorbereiten
		Sites[] sites = {Sites.GAMEPRO, Sites.GAMESTAR};
		Date datefrom = new Date();
		Date dateto = new Date();
		String[] dates = {datefrom.toString(), dateto.toString()};
		
		//Lucene abfragen
		LuceneSearcher searcher = LuceneSearcher.getInstance();
		//List<LuceneDocument> antwortListe = searcher.getFullSearchResults(suchtext, sites, dates);
		
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
		
		choiceBoxResult.getItems().remove(choiceBoxResult.getItems());
		choiceBoxResult.getItems().addAll(choiceboxList);
		choiceBoxResult.getSelectionModel().select(0);

		picture.setOnMouseClicked((event) -> {
			try {
				MouseEvent();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	//Link �ber Bild zur�ck zum SearchLayout
	@FXML
	private void MouseEvent() {
		Main search = new Main();
		search.showSearchLayout();

	}
}
