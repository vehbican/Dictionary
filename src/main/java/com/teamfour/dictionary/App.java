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
        stage.setTitle("DICT4TEAM Multi-Dictionary");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();


        long start = System.currentTimeMillis();
        dataManager = new DataManager();
        long end = System.currentTimeMillis() - start;

        System.out.println("Program Başlama Süresi : " + end);

        controller = fxmlLoader.getController();

        controller.dataManager = dataManager;
        controller.searchButton.setOnAction(event -> controller.HandleSearchButtonAction(dataManager));



    }

    public static void main(String[] args) {
        launch();
    }
}