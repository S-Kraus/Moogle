package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import crawler.tests.ReadTestNewNew;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

	private static final Logger logger = Logger.getLogger(Main.class.getName());
	final ReadTestNewNew rss = new ReadTestNewNew();

	private static final String RESULT_LAYOUT_VIEW = "../view/ResultLayout.fxml";
	private static final String SEARCH_LAYOUT_VIEW = "../view/SearchLayout.fxml";

	private static Main instance;
	private static Stage primaryStage;
	private static int cFlag = 0;
	private static SearchController controller1 = null;
	private static ResultController controller2 = null;
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

	public static int getcFlag() {
		return cFlag;
	}

	public static void setcFlag(int cFlag) {
		Main.cFlag = cFlag;
	}

	public static SearchController getController1() {
		return controller1;
	}

	public static void setController1(SearchController controller1) {
		Main.controller1 = controller1;
	}

	public static ResultController getController2() {
		return controller2;
	}

	public static void setController2(ResultController controller2) {
		Main.controller2 = controller2;
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

	public void initServices() {
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws Exception {
				rss.start();
				while (true) {
					if (rss.getState().toString() == "RUNNABLE") {
						Platform.runLater(() -> {
							if (getcFlag() == 1) {
								getController1().threadStatus.setText("RSS Crawler is running ... ");
								getController1().threadStatusCircle.setFill(Color.GREEN);
							} else {
								getController2().threadStatus.setText("RSS Crawler is running ... ");
								getController2().threadStatusCircle.setFill(Color.GREEN);
							}
						});
						Thread.sleep(3000);
					} else {
						Platform.runLater(() -> {
							if (getcFlag() == 1) {
								getController1().threadStatus.setText("RSS Crawler is not running ");
								getController1().threadStatusCircle.setFill(Color.RED);
							} else {
								getController2().threadStatus.setText("RSS Crawler is not running ");
								getController2().threadStatusCircle.setFill(Color.RED);
							}
						});
						Thread.sleep(3000);
					}
				}
			}
		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();

		primaryStage.setOnCloseRequest(event -> {
			logger.info("Stopping Services ...");
			rss.interrupt();
			th.interrupt();
		});
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;
			Main.primaryStage.setTitle("Moogle - Das LeckSieCon");

			showSearchLayout();
			primaryStage.show();
			initServices();

		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	public static Parent replaceSceneContent(String fxml) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource(fxml));
		Pane p = (Pane) fxmlLoader.load();
		if (SEARCH_LAYOUT_VIEW.equals(fxml)) {
			setController1(fxmlLoader.getController());
			setcFlag(1);
		} else {
			setController2(fxmlLoader.getController());
			setcFlag(2);
			getController2().suchtextfeld.setText(text);
			getController2().choiceBox.setValue(choiceBox);
			if (getCbfourplayers() == true) {
				getController2().cbfourplayers.setSelected(true);
			}
			if (getCbchip() == true) {
				getController2().cbchip.setSelected(true);
			}
			if (getCbgamepro() == true) {
				getController2().cbgamepro.setSelected(true);
			}
			if (getCbgamestar() == true) {
				getController2().cbgamestar.setSelected(true);
			}
			if (getCbgiga() == true) {
				getController2().cbgiga.setSelected(true);
			}
			if (getCbgolem() == true) {
				getController2().cbgolem.setSelected(true);
			}
			if (getCbign() == true) {
				getController2().cbign.setSelected(true);
			}
			if (datefrom != null) {
				getController2().datefrom.setValue(datefrom);
			}
			if (dateto != null) {
				getController2().dateto.setValue(dateto);
			}
			getController2().buttonPressed();
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
			// fxml = "SEARCH_LAYOUT_VIEW";
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		// return fxml;
	}

	static void showResultLayout() {
		try {
			replaceSceneContent(RESULT_LAYOUT_VIEW);
			// fxml = "RESULT_LAYOUT_VIEW";
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		// return fxml;
	}

	public void showHyperlink(Hyperlink url) {
		getHostServices().showDocument(url.getText());
	}

	public void showLocalFile(String path) {
		try {
			File file = new File(path);
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		launch(args);
	}
}