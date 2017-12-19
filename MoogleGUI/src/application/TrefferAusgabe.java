package application;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TrefferAusgabe extends VBox {
	Text textTitel;
	Text textDatum;
	Text rangZiffer;
	Hyperlink textHyperlink;
	Button lokalerButton;
	TextField textFeld;
	Text abstand;
	
	public TrefferAusgabe(int rang, String title, String date, String link){
	super();
	textTitel = new Text();
		textTitel.setText(title);
	textDatum = new Text();
		textDatum.setText(date);
	textHyperlink = new Hyperlink();
		textHyperlink.setText(link);	
	rangZiffer = new Text();
		rangZiffer.setText(Integer.toString(rang) + ". ");
		
	abstand = new Text();
		abstand.setText("");
	lokalerButton = new Button();
		lokalerButton.setText("Test mal ganz viel");
	textFeld = new TextField();
		textFeld.setText("Test");
	
	HBox oben = new HBox();
	oben.getChildren().addAll(rangZiffer, textTitel, textDatum);
	HBox unten = new HBox();
	unten.getChildren().addAll(textHyperlink, lokalerButton);
	this.getChildren().addAll(oben,unten,textFeld,abstand);
//	this.setTopAnchor(oben, 0.0);
//	this.setLeftAnchor(unten, 0.0);
//	this.setBottomAnchor(textFeld, 0.0);

	
	
	}
	
	
	
}
