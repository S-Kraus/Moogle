package application;

import java.beans.EventHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sun.javafx.scene.EventHandlerProperties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
 
public class SearchController {
	
	ObservableList<String> choiceboxList = FXCollections.observableArrayList("Volltextsuche", "Personensuche", "Organisationssuche");
	
	@FXML
	ChoiceBox<String> choiceBox;
	
	@FXML
	TextField suchtextfeld;
//	
//	@FXML
//	DatePicker zeitraumvon;
//	
//	@FXML
//	DatePicker zeitraumbis;
	
	@FXML 
	public void initialize(){
		choiceBox.getItems().remove(choiceBox.getItems());
		choiceBox.getItems().addAll(choiceboxList);
		choiceBox.getSelectionModel().select(0);
	}
		
	@FXML
	Button suchanfrage;
	
	@FXML
	protected void buttonPressed(){
		String text = suchtextfeld.getText();
		suchtextfeld.clear();
		//suchtextfeld.setText("Selber " + text);
		
		//Der Beleidiger
		HashMap map = new HashMap();
			
		map.put(new String("arsch"), new String("Selber Arsch. Du riesen Schildkröte!"));
		map.put(new String("penner"), new String("Dei Muadda, Buarsche!!!"));
		map.put(new String("moogle"), new String("Ja. Und jetzt?"));
		//suchtextfeld.setText((String)map.get(text));
		
		 Iterator it = map.entrySet().iterator();
	        while (it.hasNext()) {
	          Map.Entry entry = (Map.Entry)it.next();
	          if(entry.getKey().equals(text.toLowerCase())){
	              suchtextfeld.setText((String)entry.getValue());
	          }

	}
}
}
