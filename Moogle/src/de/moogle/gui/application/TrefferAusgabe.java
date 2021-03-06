package de.moogle.gui.application;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.jdom2.JDOMException;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TrefferAusgabe extends VBox {
	private static final DateTimeFormatter GERMAN_DATE = DateTimeFormatter.ofPattern("EEE, dd. MMM yyyy HH:mm:ss 'Uhr'",
			Locale.GERMAN);
	private static final DateTimeFormatter RSS_DATE = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy",
			Locale.US);
	private Text textTitel;
	private Text textDatum;
	private Hyperlink textHyperlink;
	private Button localButton;
	private TextArea textArea;
	// private Text abstand;

	public TrefferAusgabe(int rang, String title, String date, String link, String path) {
		super();
		AnchorPane ap = new AnchorPane();

		textTitel = new Text();
		textTitel.setText(title);
		textTitel.setText(Integer.toString(rang) + ". " + title);
		AnchorPane.setTopAnchor(textTitel, 10.0);
		AnchorPane.setLeftAnchor(textTitel, 10.0);
		AnchorPane.setRightAnchor(textTitel, 120.0);

		textDatum = new Text();
		textDatum.setText(convertToGermanDate(date));
		AnchorPane.setTopAnchor(textDatum, 10.0);
		AnchorPane.setRightAnchor(textDatum, 10.0);

		textHyperlink = new Hyperlink(link);
		textHyperlink.getText();
		textHyperlink.setOnAction((event) -> {
			try {
				Main browser = new Main();
				browser.showHyperlink(textHyperlink);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		AnchorPane.setTopAnchor(textHyperlink, 25.0);
		AnchorPane.setLeftAnchor(textHyperlink, 10.0);
		AnchorPane.setRightAnchor(textHyperlink, 100.0);

		localButton = new Button();
		localButton.setText("Lokal anzeigen");
		localButton.prefWidth(80.0);
		localButton.setOnAction((event) -> {
			try {
				Main texteditor = new Main();
				texteditor.showLocalFile(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		AnchorPane.setTopAnchor(localButton, 25.0);
		AnchorPane.setRightAnchor(localButton, 10.0);

		textArea = new TextArea();
		try {
			textArea.setText(ReadXMLFile.getInstance().showXmlContent(path));
			textArea.setWrapText(true);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		if (path != null && new File(path).exists()) {
//			System.out.println(path);
//			try {
//				StringJoiner sj = new StringJoiner("\n");
//				Files.lines(Paths.get(path)).skip(2).limit(3).forEach(line -> sj.add(line));
//				textArea.setText(sj.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		AnchorPane.setTopAnchor(textArea, 50.0);
		AnchorPane.setLeftAnchor(textArea, 10.0);
		AnchorPane.setRightAnchor(textArea, 10.0);
		AnchorPane.setBottomAnchor(textArea, 10.0);

		ap.getChildren().addAll(textTitel, textDatum, textHyperlink, localButton, textArea);
		this.getChildren().add(ap);
	}

	private static String convertToGermanDate(String date) {
		if (date == null || "".equals(date.trim())) {
			return date;
		}

		try {
			LocalDateTime ldt = LocalDateTime.parse(date, RSS_DATE);
			String germanDate = ldt.format(GERMAN_DATE);
			return germanDate;
		} catch (DateTimeParseException e) {
			return date;
		}
	}

}
