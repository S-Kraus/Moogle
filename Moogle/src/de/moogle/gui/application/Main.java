package de.moogle.gui.application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.moogle.crawler.CrawlerService;
import de.moogle.gui.controller.ResultController;
import de.moogle.gui.controller.SearchController;
import de.moogle.lucene.tools.Site;
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

	private static final String RESULT_LAYOUT_VIEW = "../view/ResultLayout.fxml";
	private static final String SEARCH_LAYOUT_VIEW = "../view/SearchLayout.fxml";
	
	private CrawlerService rss;
	private static Main instance;

	private Stage primaryStage;
	private int cFlag;
	private SearchController searchController;
	private ResultController resultController;

	Thread rssThread;

	public Main() {
	}

	public static Main getInstance() {
		if (instance == null) {
			instance = new Main();
		}
		return instance;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		getInstance().primaryStage = primaryStage;
	}

	public int getcFlag() {
		return cFlag;
	}

	public void setcFlag(int cFlag) {
		getInstance().cFlag = cFlag;
	}

	public SearchController getSearchController() {
		return searchController;
	}

	public void setSearchController(SearchController searchController) {
		getInstance().searchController = searchController;
	}

	public ResultController getResultController() {
		return resultController;
	}

	public void setResultController(ResultController resultController) {
		getInstance().resultController = resultController;
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
		rss = new CrawlerService();
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws Exception {
				getRss().start();
				while (true) {
					if (getRss().getState().toString() == "RUNNABLE") {
						Platform.runLater(() -> {
							if (getcFlag() == 1) {
								getSearchController().getThreadStatus().setText("RSS Crawler is running ... ");
								getSearchController().getThreadStatusCircle().setFill(Color.GREEN);
							} else {
								getResultController().getThreadStatus().setText("RSS Crawler is running ... ");
								getResultController().getThreadStatusCircle().setFill(Color.GREEN);
							}
						});
						Thread.sleep(3000);
					} else {
						Platform.runLater(() -> {
							if (getcFlag() == 1) {
								getSearchController().getThreadStatus().setText("RSS Crawler is not running ");
								getSearchController().getThreadStatusCircle().setFill(Color.RED);
							} else {
								getResultController().getThreadStatus().setText("RSS Crawler is not running ");
								getResultController().getThreadStatusCircle().setFill(Color.RED);
							}
						});
						Thread.sleep(3000);
					}
				}
			}
		};
		rssThread = new Thread(task);
		rssThread.setDaemon(true);
		rssThread.start();
	}

	@Override
	public void stop() throws Exception {
		getLogger().info("Stopping Services ...");
		getRss().interrupt();
		if (rssThread != null) {
			rssThread.interrupt();
		}
		super.stop();
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

	private Parent replaceSceneContent(String fxml) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource(fxml));
		Pane p = (Pane) fxmlLoader.load();
		if (SEARCH_LAYOUT_VIEW.equals(fxml)) {
			setSearchController(fxmlLoader.getController());
			setcFlag(1);
		} else {
			setResultController(fxmlLoader.getController());
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
			getResultController().buttonPressed();
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

	public void showSearchLayout() {
		try {
			replaceSceneContent(SEARCH_LAYOUT_VIEW);
			// fxml = "SEARCH_LAYOUT_VIEW";
		} catch (Exception ex) {
			getLogger().log(Level.SEVERE, null, ex);
		}
		// return fxml;
	}

	public void showResultLayout(String text, String choice, List<Site> sites, LocalDate[] dates) {
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