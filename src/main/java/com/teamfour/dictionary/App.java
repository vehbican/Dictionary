package com.teamfour.dictionary;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        //dataManager = new DataManager();
        long end = System.currentTimeMillis() - start;

        System.out.println("Program Başlama Süresi : " + end);

        controller = fxmlLoader.getController();

        controller.stage = stage;
        controller.dataManager = dataManager;
        controller.searchButton.setOnAction(event -> controller.HandleSearchButtonAction(dataManager));

        //Output Test
        MultiValuedMap<String,Word> map = new ArrayListValuedHashMap<>();

        Word w1 = new Word(Config.Languages.ENGLISH,"life");
        Word t1 = new Word(Config.Languages.TURKISH,"hayat");
        Word t2 = new Word(Config.Languages.TURKISH,"yaşam");
        Word t3 = new Word(Config.Languages.TURKISH,"ömür");

        w1.setTranslations(List.of(t1,t2,t3));

        Word w2 = new Word(Config.Languages.ENGLISH,"life");
        Word t4 = new Word(Config.Languages.TURKISH,"hayat");
        Word t5 = new Word(Config.Languages.TURKISH,"yaşam");
        Word t6 = new Word(Config.Languages.TURKISH,"ömür");

        w2.setTranslations(List.of(t4,t5,t6));

        Word w3 = new Word(Config.Languages.ENGLISH,"life");
        Word t7 = new Word(Config.Languages.TURKISH,"hayat");
        Word t8 = new Word(Config.Languages.TURKISH,"yaşam");
        Word t9 = new Word(Config.Languages.TURKISH,"ömür");

        w3.setTranslations(List.of(t7,t8,t9));

        Word w4 = new Word(Config.Languages.ENGLISH,"life");
        Word t10 = new Word(Config.Languages.TURKISH,"hayat");
        Word t11 = new Word(Config.Languages.TURKISH,"yaşam");
        Word t12 = new Word(Config.Languages.TURKISH,"ömür");

        w4.setTranslations(List.of(t10,t11,t12));

        map.put(w1.getWord(),w1);
        map.put(w2.getWord(),w2);
        map.put(w3.getWord(),w3);
        map.put(w4.getWord(),w4);

        FileManager.FromHashMapToFile(map, Config.testOutputPath);


    }

    public static void main(String[] args) {
        launch();
    }
}