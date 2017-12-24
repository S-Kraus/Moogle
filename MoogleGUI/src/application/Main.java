package application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static final String RESULT_LAYOUT_VIEW = "../view/ResultLayout.fxml";
	private static final String SEARCH_LAYOUT_VIEW = "../view/SearchLayout.fxml";

	private static Stage primaryStage;
	private static Main instance;
	private static String text;

	public Main() {
		instance = this;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		primaryStage = Main.primaryStage;
	}

	public static Main getInstance() {
		return instance;
	}

	public static String getText() {
		return text;
	}

	public void setText(String text) {
		Main.text = text;
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

	public Parent replaceSceneContent(String fxml) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource(fxml));
		Pane p = (Pane) fxmlLoader.load();

		if (RESULT_LAYOUT_VIEW.equals(fxml)) {
			ResultController controller = fxmlLoader.getController();
			controller.setText(Main.getText());
			controller.buttonPressed();
		}

		Scene scene = primaryStage.getScene();
		if (scene == null) {
			scene = new Scene(p, (Screen.getPrimary().getVisualBounds().getWidth() - 0.0), (Screen.getPrimary().getVisualBounds().getHeight() - 25.0));
			primaryStage.setScene(scene);
		} else {
			primaryStage.getScene().setRoot(p);
		}
		primaryStage.sizeToScene();
		System.out.print(p);
		return p;
	}

	void showSearchLayout() {
		try {
			replaceSceneContent(SEARCH_LAYOUT_VIEW);
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	void showResultLayout() {
		try {
			replaceSceneContent(RESULT_LAYOUT_VIEW);
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}