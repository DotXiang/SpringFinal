package com.ziarniak.project.app;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LibraryOfGameApp extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
	
		
		AnchorPane page = (AnchorPane) FXMLLoader.load(LibraryOfGameApp.class.getResource("../views/Test.fxml"));
		
		Scene scene = new Scene(page);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
	
}
