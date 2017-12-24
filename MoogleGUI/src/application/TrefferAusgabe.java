package application;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TrefferAusgabe extends VBox {
	Text textTitel;
	Text textDatum;
	Text rangZiffer;
	Hyperlink textHyperlink;
	Button localButton;
	TextArea textArea;
	Text abstand;

	public TrefferAusgabe(int rang, String title, String date, String link) {
		super();
		AnchorPane ap = new AnchorPane();
		
		rangZiffer = new Text();
		rangZiffer.setText(Integer.toString(rang) + ". ");
		ap.setTopAnchor(rangZiffer, 10.0);
		ap.setLeftAnchor(rangZiffer, 10.0);		
		
		textTitel = new Text();
		textTitel.setText(title);
		textTitel.autosize();
		ap.setTopAnchor(textTitel, 10.0);
		ap.setLeftAnchor(textTitel, 25.0);
		ap.setRightAnchor(textTitel, 120.0);		
		
		textDatum = new Text();
		textDatum.setText(date);
		textDatum.autosize();
		ap.setTopAnchor(textDatum, 10.0);
		ap.setRightAnchor(textDatum, 10.0);
		
		textHyperlink = new Hyperlink();
		textHyperlink.setText(link);
		ap.setTopAnchor(textHyperlink, 25.0);
		ap.setLeftAnchor(textHyperlink, 10.0);
		ap.setRightAnchor(textHyperlink, 100.0);
		
		localButton = new Button();               
        localButton.setText("Lokal anzeigen");
        localButton.prefWidth(80.0);
        ap.setTopAnchor(localButton, 25.0);
        ap.setRightAnchor(localButton, 10.0);
        
		TextArea textArea = new TextArea();
		textArea.setText("Test");
		ap.setTopAnchor(textArea, 50.0);
		ap.setLeftAnchor(textArea, 10.0);
		ap.setRightAnchor(textArea, 10.0);
		ap.setBottomAnchor(textArea, 10.0);
		
//		HBox oben = new HBox();
//		oben.resize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
//		oben.getChildren().addAll(rangZiffer, textTitel, textDatum);
//
//		HBox unten = new HBox();
//		unten.resize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
//		unten.getChildren().addAll(textHyperlink, lokalerButton);
		
//		this.getChildren().addAll(oben, unten, textFeld, abstand);
		
		ap.getChildren().addAll(rangZiffer, textTitel, textDatum, textHyperlink, localButton, textArea);
		this.getChildren().add(ap);
	}
}
