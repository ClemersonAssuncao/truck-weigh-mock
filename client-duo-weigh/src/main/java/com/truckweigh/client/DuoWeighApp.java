package com.truckweigh.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DuoWeighApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
            DuoWeighApp.class.getResource("main-view.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        
        stage.setTitle("DUO WEIGH - Simulador de Balança");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
