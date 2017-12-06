package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
    private BorderPane rootLayout;
    
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Moogle - Das LeckSieCon");

        initRootLayout();

        showSearchLayout();
//        showResultLayout();
	}
	
	public void startResult(Stage primaryStage, String text) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Moogle - Das LeckSieCon");

        initRootLayout();

        showResultLayout(text);
	}
	
	public void startSearch(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Moogle - Das LeckSieCon");

        initRootLayout();

        showSearchLayout();
        //showResultLayout();
	}
	
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showSearchLayout() {
        try {
        	
        	// Load Search Overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/SearchLayout.fxml"));
            AnchorPane searchLayout = (AnchorPane) loader.load();
            
            // Set SearchLayout into the center of RootLayout.
            rootLayout.setCenter(searchLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showResultLayout(String text) {
        try {
            // Load Result Overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/ResultLayout.fxml"));
            AnchorPane resultLayout = (AnchorPane) loader.load();
            ResultController controller = loader.getController();
            controller.setText(text);

            // Set ResultLayout into the center of RootLayout.
            rootLayout.setCenter(resultLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public Stage getPrimaryStage() {
        return primaryStage;
    }

	public static void main(String[] args) {
		launch(args);
	}
}