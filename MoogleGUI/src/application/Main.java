package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

	private static final String RESULT_LAYOUT_VIEW = "../view/ResultLayout.fxml";
	private static final String SEARCH_LAYOUT_VIEW = "../view/SearchLayout.fxml";

	private static Stage primaryStage;
	private static Main instance;
	private static String text = "null";
	private static Boolean cbfourplayers = true;
	private static Boolean cbchip = true;
	private static Boolean cbgamepro = true;
	private static Boolean cbgamestar = true;
	private static Boolean cbgiga = true;
	private static Boolean cbgolem = true;
	private static Boolean cbign = true;
	private static LocalDate datefrom = null;
	private static LocalDate dateto = null;
	private static String choiceBox = null;

	public Main() {
		instance = this;
	}

	public static Main getInstance() {
		return instance;
	}

	public static void setText(String text) {
		Main.text = text;
	}

	public static String getText() {
		return text;
	}

	public static void setCbfourplayers(Boolean cbfourplayers) {
		Main.cbfourplayers = cbfourplayers;
	}

	public static Boolean getCbfourplayers() {
		return cbfourplayers;
	}

	public static void setCbchip(Boolean cbchip) {
		Main.cbchip = cbchip;
	}

	public static Boolean getCbchip() {
		return cbchip;
	}

	public static void setCbgamepro(Boolean cbgamepro) {
		Main.cbgamepro = cbgamepro;
	}

	public static Boolean getCbgamepro() {
		return cbgamepro;
	}

	public static void setCbgamestar(Boolean cbgamestar) {
		Main.cbgamestar = cbgamestar;
	}

	public static Boolean getCbgamestar() {
		return cbgamestar;
	}

	public static void setCbgiga(Boolean cbgiga) {
		Main.cbgiga = cbgiga;
	}

	public static Boolean getCbgiga() {
		return cbgiga;
	}

	public static void setCbgolem(Boolean cbgolem) {
		Main.cbgolem = cbgolem;
	}

	public static Boolean getCbgolem() {
		return cbgolem;
	}

	public static void setCbign(Boolean cbign) {
		Main.cbign = cbign;
	}

	public static Boolean getCbign() {
		return cbign;
	}

	public static void setDatefrom(LocalDate datefrom) {
		Main.datefrom = datefrom;
	}

	public static LocalDate getDatefrom() {
		return datefrom;
	}

	public static void setDateto(LocalDate dateto) {
		Main.dateto = dateto;
	}

	public static LocalDate getDateto() {
		return dateto;
	}
	
	public static void setChoiceBox(String choiceBox) {
		Main.choiceBox = choiceBox;
	}
	
	public static String getChoiceBox() {
		return choiceBox;
	}

	public void setPrimaryStage(Stage primaryStage) {
		primaryStage = Main.primaryStage;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;
			Main.primaryStage.setTitle("Moogle - Das LeckSieCon");

			showSearchLayout();
			primaryStage.show();

		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static Parent replaceSceneContent(String fxml) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource(fxml));
		Pane p = (Pane) fxmlLoader.load();

		if (RESULT_LAYOUT_VIEW.equals(fxml)) {
			ResultController controller = fxmlLoader.getController();
			controller.suchtextfeld.setText(text);
			controller.choiceBox.setValue(choiceBox);
			if (getCbfourplayers() == true) {
				controller.cbfourplayers.setSelected(true);
			}
			if (getCbchip() == true) {
				controller.cbchip.setSelected(true);
			}
			if (getCbgamepro() == true) {
				controller.cbgamepro.setSelected(true);
			}
			if (getCbgamestar() == true) {
				controller.cbgamestar.setSelected(true);
			}
			if (getCbgiga() == true) {
				controller.cbgiga.setSelected(true);
			}
			if (getCbgolem() == true) {
				controller.cbgolem.setSelected(true);
			}
			if (getCbign() == true) {
				controller.cbign.setSelected(true);
			}
			if (datefrom != null) {
				controller.datefrom.setValue(datefrom);
			}
			if (dateto != null) {
				controller.dateto.setValue(dateto);
			}
			controller.buttonPressed();
		}

		Scene scene = primaryStage.getScene();
		if (scene == null) {
			scene = new Scene(p, (Screen.getPrimary().getVisualBounds().getWidth() - 0.0),
					(Screen.getPrimary().getVisualBounds().getHeight() - 25.0));
			primaryStage.setScene(scene);
		} else {
			primaryStage.getScene().setRoot(p);
		}
		primaryStage.sizeToScene();
		return p;
	}

	static void showSearchLayout() {
		try {
			replaceSceneContent(SEARCH_LAYOUT_VIEW);
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	static void showResultLayout() {
		try {
			replaceSceneContent(RESULT_LAYOUT_VIEW);
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void showHyperlink(Hyperlink url) {
		getHostServices().showDocument(url.getText());
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showLocalFile(String path) {
		try {
			File file = new File(path);
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}