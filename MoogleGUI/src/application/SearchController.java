package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

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
	
	// JavaFX Elemente initialisieren
	@FXML
	public void initialize() {
		choiceBox.getItems().remove(choiceBox.getItems());
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
		
		//Abfrage des Suchtextes
		String text = suchtextfeld.getText();
		suchtextfeld.clear();

		// Der Beleidiger
		HashMap<String, String> map = new HashMap<String, String>();

		map.put(new String("arsch"), new String("Selber. Du riesen Schildkröte!"));
		map.put(new String("penner"), new String("Dei Muadda, Buarsche!!!"));
		map.put(new String("moogle"), new String("Ja. Und jetzt?"));
		map.put(new String("suche"), new String("Na klar. Such dich selber Junge!"));
		map.put(new String("rene"), new String("Wir lieben dich (deine drei kleinen Schweinchen!)"));
		map.put(new String("simon"), new String("Hallo Simon"));
		map.put(new String("andreas"), new String("Schön das du da bist und nicht hier :-)"));
		map.put(new String("stephan"), new String("Was soll mann dazu sagen? Du geiler Typ!"));

		//Initialisierung der Main
		Main result = new Main();

		// Textübergabe an Main
		if (map.containsKey(text)) {
			result.setText((String) map.get(text));
		} else {
			result.setText(text);
		}

		//Wechsel zum Resultlayout
		result.showResultLayout();

	}
}
