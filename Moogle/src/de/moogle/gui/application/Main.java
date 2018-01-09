package de.moogle.gui.application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.moogle.crawler.CrawlerService;
import de.moogle.gui.controller.ResultController;
import de.moogle.gui.controller.SearchController;
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

	private static Main instance;

	private static final Logger logger = Logger.getLogger(Main.class.getName());
	private static final CrawlerService rss = new CrawlerService();

	private static final String RESULT_LAYOUT_VIEW = "../view/ResultLayout.fxml";
	private static final String SEARCH_LAYOUT_VIEW = "../view/SearchLayout.fxml";

	private static Stage primaryStage;
	private static int cFlag;
	private static SearchController controller1;
	private static ResultController controller2;
	private static String text;
	private static Boolean cbfourplayers;
	private static Boolean cbchip;
	private static Boolean cbgamepro;
	private static Boolean cbgamestar;
	private static Boolean cbgiga;
	private static Boolean cbgolem;
	private static Boolean cbign;
	private static LocalDate datefrom;
	private static LocalDate dateto;
	private static String choiceBox;

	public Main() {
		instance = this;
	}

	public static Main getInstance() {
		return instance;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		Main.primaryStage = primaryStage;
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

	public static String getText() {
		return text;
	}

	public static void setText(String text) {
		Main.text = text;
	}

	public static Boolean getCbfourplayers() {
		return cbfourplayers;
	}

	public static void setCbfourplayers(Boolean cbfourplayers) {
		Main.cbfourplayers = cbfourplayers;
	}

	public static Boolean getCbchip() {
		return cbchip;
	}

	public static void setCbchip(Boolean cbchip) {
		Main.cbchip = cbchip;
	}

	public static Boolean getCbgamepro() {
		return cbgamepro;
	}

	public static void setCbgamepro(Boolean cbgamepro) {
		Main.cbgamepro = cbgamepro;
	}

	public static Boolean getCbgamestar() {
		return cbgamestar;
	}

	public static void setCbgamestar(Boolean cbgamestar) {
		Main.cbgamestar = cbgamestar;
	}

	public static Boolean getCbgiga() {
		return cbgiga;
	}

	public static void setCbgiga(Boolean cbgiga) {
		Main.cbgiga = cbgiga;
	}

	public static Boolean getCbgolem() {
		return cbgolem;
	}

	public static void setCbgolem(Boolean cbgolem) {
		Main.cbgolem = cbgolem;
	}

	public static Boolean getCbign() {
		return cbign;
	}

	public static void setCbign(Boolean cbign) {
		Main.cbign = cbign;
	}

	public static LocalDate getDatefrom() {
		return datefrom;
	}

	public static void setDatefrom(LocalDate datefrom) {
		Main.datefrom = datefrom;
	}

	public static LocalDate getDateto() {
		return dateto;
	}

	public static void setDateto(LocalDate dateto) {
		Main.dateto = dateto;
	}

	public static String getChoiceBox() {
		return choiceBox;
	}

	public static void setChoiceBox(String choiceBox) {
		Main.choiceBox = choiceBox;
	}

	public static Logger getLogger() {
		return logger;
	}

	public CrawlerService getRss() {
		return rss;
	}

	public static String getResultLayoutView() {
		return RESULT_LAYOUT_VIEW;
	}

	public static String getSearchLayoutView() {
		return SEARCH_LAYOUT_VIEW;
	}

	public void initServices() {
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws Exception {
				getRss().start();
				while (true) {
					if (getRss().getState().toString() == "RUNNABLE") {
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

		getPrimaryStage().setOnCloseRequest(event -> {
			getLogger().info("Stopping Services ...");
			getRss().interrupt();
			th.interrupt();
		});
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			setPrimaryStage(primaryStage);
			getPrimaryStage().setTitle("Moogle - Das LeckSieCon");

			showSearchLayout();
			getPrimaryStage().show();
			initServices();

		} catch (Exception ex) {
			getLogger().log(Level.SEVERE, null, ex);
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
			getController2().suchtextfeld.setText(getText());
			getController2().choiceBox.setValue(getChoiceBox());
			getController2().cbfourplayers.setSelected(getCbfourplayers());
			getController2().cbchip.setSelected(getCbchip());
			getController2().cbgamepro.setSelected(getCbgamepro());
			getController2().cbgamestar.setSelected(getCbgamestar());
			getController2().cbgiga.setSelected(getCbgiga());
			getController2().cbgolem.setSelected(getCbgolem());
			getController2().cbign.setSelected(getCbign());
			if (datefrom != null) {
				getController2().datefrom.setValue(getDatefrom());
			}
			if (dateto != null) {
				getController2().dateto.setValue(getDateto());
			}
			getController2().buttonPressed();
		}

		Scene scene = getPrimaryStage().getScene();
		if (scene == null) {
			scene = new Scene(p, (Screen.getPrimary().getVisualBounds().getWidth() - 0.0),
					(Screen.getPrimary().getVisualBounds().getHeight() - 25.0));
			getPrimaryStage().setScene(scene);
		} else {
			getPrimaryStage().getScene().setRoot(p);
		}
		getPrimaryStage().sizeToScene();
		return p;
	}

	public static void showSearchLayout() {
		try {
			replaceSceneContent(SEARCH_LAYOUT_VIEW);
			// fxml = "SEARCH_LAYOUT_VIEW";
		} catch (Exception ex) {
			getLogger().log(Level.SEVERE, null, ex);
		}
		// return fxml;
	}

	public static void showResultLayout() {
		try {
			replaceSceneContent(RESULT_LAYOUT_VIEW);
			// fxml = "RESULT_LAYOUT_VIEW";
		} catch (Exception ex) {
			getLogger().log(Level.SEVERE, null, ex);
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