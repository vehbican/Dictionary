package com.teamfour.dictionary;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;

public class App extends Application {

    HomePageController controller;
    DataManager dataManager;

    @Override
    public void start(Stage stage) throws IOException {

        AddController acc = new AddController();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("DICT4TEAM Multi-Dictionary");
        stage.setScene(scene);
        stage.show();

        /* addEng denedim

        String[] myArray = new String[2];
        myArray[0] = "LEC";
        myArray[1] = "LCK";
        acc.addEng("asd",myArray,Config.eng_tur_tei);
        */
        long start = System.currentTimeMillis();
        dataManager = new DataManager();
        long end = System.currentTimeMillis() - start;

        System.out.println("Program Başlama Süresi : " + end);

        controller = fxmlLoader.getController();

        controller.searchButton.setOnAction(event -> controller.HandleSearchButtonAction(dataManager));



    }

    public static void main(String[] args) {
        launch();
    }
}