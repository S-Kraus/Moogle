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

	public void setText(String text) {
		this.suchtextfeld.setText(text);
	}

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
		
		String text = suchtextfeld.getText();
		//suchTextfeld.clear();
		
		LuceneSearcher searcher = LuceneSearcher.getInstance();
		Sites[] sites = {Sites.GAMEPRO, Sites.GAMESTAR};
		String[] dates = {new Date().toString(), new Date().toString()};
		List<LuceneDocument> antwortListe = searcher.getFullSearchResults(text, sites, dates);
		// //suchtextfeld.setText(antwortListe.toString());
		
		for (int i = 0; i<antwortListe.size(); i++){
			
			System.out.println(antwortListe.get(i).toString());
			String titel = antwortListe.get(i).getTitle();
			String date2 = antwortListe.get(i).getDate();
			String link = antwortListe.get(i).getLink();
			
			TrefferAusgabe neuerEintrag = new TrefferAusgabe(i+1, titel, date2, link);
			resultvbox.getChildren().add(neuerEintrag);
		};
		
		
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
