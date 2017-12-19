package application;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TrefferAusgabe extends AnchorPane {
	Text textTitel;
	Text textDatum;
	Hyperlink textHyperlink;
	Button lokalerButton;
	TextField textFeld;
	
	public TrefferAusgabe(String title, String date, String link){
	super();
	textTitel = new Text();
		textTitel.setText(title);
	textDatum = new Text();
		textDatum.setText(date);
	textHyperlink = new Hyperlink();
		textHyperlink.setText(link);	
		
	lokalerButton = new Button();
		lokalerButton.setText("Test mal ganz viel");
	textFeld = new TextField();
		textFeld.setText("Test");
	
	HBox oben = new HBox();
	oben.getChildren().addAll(textTitel, textDatum);
	HBox unten = new HBox();
	unten.getChildren().addAll(textHyperlink, lokalerButton);
	this.getChildren().addAll(oben,unten,textFeld);
	
	}
	
	
	
}
