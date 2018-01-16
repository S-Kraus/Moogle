package de.moogle.gui.application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public int getcFlag() {
		return cFlag;
	}

	public void setcFlag(int cFlag) {
		this.cFlag = cFlag;
	}

	public SearchController getSearchController() {
		return searchController;
	}

	public void setSearchController(SearchController searchController) {
		this.searchController = searchController;
	}

	public ResultController getResultController() {
		return resultController;
	}

	public void setResultController(ResultController resultController) {
		this.resultController = resultController;
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
		getRss().terminate();
		if (rssThread != null) {
			rssThread.interrupt();
		}
		System.exit(0);
		super.stop();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			primaryStage.setTitle("Moogle - Das LeckSieCon");

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
			getSearchController().setMain(this);
			setcFlag(1);
		} else {
			setResultController(fxmlLoader.getController());
			getResultController().setMain(this);
			setcFlag(2);
			getResultController().initValues();
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

	public void showResultLayout() {
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