package com.teamfour.dictionary;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    HomePageController controller;
    DataManager dataManager;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        dataManager = new DataManager();

        controller = fxmlLoader.getController();

        controller.searchButton.setOnAction(event -> controller.HandleSearchButtonAction(dataManager));




    }

    public static void main(String[] args) {
        launch();
    }
}