package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
//import levine.five.view.CalculatorController;
import application.Main;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Financial Aid Calculator"); //set the title of the Window
			this.primaryStage.getIcons().add(new Image("file:resources/images/AppIcon.png")); //set the application icon
			
			showCalculator();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void showCalculator() {
		try {
			FXMLLoader loader;
			loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("/levine/five/view/FCalculator.FXML"));

			rootLayout = (BorderPane) loader.load();

			Scene scene;
			scene = new Scene(rootLayout);

			primaryStage.setScene(scene);

			primaryStage.show();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
