package com.teamfour.dictionary;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class App extends Application {

    HomePageController controller;
    DataManager dataManager;

    @Override
    public void start(Stage stage) throws IOException {

        stage.onCloseRequestProperty().set(e->OnClose());

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
        MultiValuedMap<String,Word> m =  FileManager.Parse(dataManager,Config.testOutputPath, Config.Languages.ENGLISH, Config.Languages.TURKISH);

        String s = Files.readString(Path.of(Config.testOutputPath), StandardCharsets.UTF_8);
        System.out.println(s);
        System.out.println(m);
    }

    public static void main(String[] args) {
        launch();
    }

    public void OnClose(){

        FileManager.FromHashMapToFile(dataManager.getENG_TUR_DICT(),Config.eng_tur_tei);
        FileManager.FromHashMapToFile(dataManager.getENG_FRA_DICT(),Config.eng_fra_tei);
        FileManager.FromHashMapToFile(dataManager.getENG_GER_DICT(),Config.eng_deu_tei);
        FileManager.FromHashMapToFile(dataManager.getENG_ITA_DICT(),Config.eng_ita_tei);
        FileManager.FromHashMapToFile(dataManager.getENG_GRE_DICT(),Config.eng_ell_tei);
        FileManager.FromHashMapToFile(dataManager.getENG_SWE_DICT(),Config.eng_swe_tei);

        FileManager.FromHashMapToFile(dataManager.getDEU_ENG_DICT(),Config.deu_eng_tei);
        FileManager.FromHashMapToFile(dataManager.getDEU_FRA_DICT(),Config.deu_fra_tei);
        FileManager.FromHashMapToFile(dataManager.getDEU_TUR_DICT(),Config.deu_tur_tei);
        FileManager.FromHashMapToFile(dataManager.getDEU_ITA_DICT(),Config.deu_ita_tei);
        FileManager.FromHashMapToFile(dataManager.getDEU_ELL_DICT(),Config.deu_ell_tei);
        FileManager.FromHashMapToFile(dataManager.getDEU_SWE_DICT(),Config.deu_swe_tei);

        FileManager.FromHashMapToFile(dataManager.getFRA_ENG_DICT(),Config.fra_eng_tei);
        FileManager.FromHashMapToFile(dataManager.getFRA_DEU_DICT(),Config.fra_deu_tei);
        FileManager.FromHashMapToFile(dataManager.getFRA_TUR_DICT(),Config.fra_tur_tei);
        FileManager.FromHashMapToFile(dataManager.getFRA_ELL_DICT(),Config.fra_ell_tei);
        FileManager.FromHashMapToFile(dataManager.getFRA_ITA_DICT(),Config.fra_ita_tei);
        FileManager.FromHashMapToFile(dataManager.getFRA_SWE_DICT(),Config.fra_swe_tei);

        FileManager.FromHashMapToFile(dataManager.getITA_ENG_DICT(),Config.ita_eng_tei);
        FileManager.FromHashMapToFile(dataManager.getITA_TUR_DICT(),Config.ita_tur_tei);
        FileManager.FromHashMapToFile(dataManager.getITA_DEU_DICT(),Config.ita_deu_tei);
        FileManager.FromHashMapToFile(dataManager.getITA_FRA_DICT(),Config.ita_fra_tei);
        FileManager.FromHashMapToFile(dataManager.getITA_ELL_DICT(),Config.ita_ell_tei);
        FileManager.FromHashMapToFile(dataManager.getITA_SWE_DICT(),Config.ita_swe_tei);

        FileManager.FromHashMapToFile(dataManager.getSWE_ENG_DICT(),Config.swe_eng_tei);
        FileManager.FromHashMapToFile(dataManager.getSWE_TUR_DICT(),Config.swe_tur_tei);
        FileManager.FromHashMapToFile(dataManager.getSWE_FRA_DICT(),Config.swe_fra_tei);
        FileManager.FromHashMapToFile(dataManager.getSWE_ITA_DICT(),Config.swe_ita_tei);
        FileManager.FromHashMapToFile(dataManager.getSWE_DEU_DICT(),Config.swe_deu_tei);
        FileManager.FromHashMapToFile(dataManager.getSWE_ELL_DICT(),Config.swe_ell_tei);

        FileManager.FromHashMapToFile(dataManager.getTUR_ENG_DICT(),Config.tur_eng_tei);
        FileManager.FromHashMapToFile(dataManager.getTUR_DEU_DICT(),Config.tur_deu_tei);
        FileManager.FromHashMapToFile(dataManager.getTUR_FRA_DICT(),Config.tur_fra_tei);
        FileManager.FromHashMapToFile(dataManager.getTUR_ITA_DICT(),Config.tur_ita_tei);
        FileManager.FromHashMapToFile(dataManager.getTUR_ELL_DICT(),Config.tur_ell_tei);
        FileManager.FromHashMapToFile(dataManager.getTUR_SWE_DICT(),Config.tur_swe_tei);

        FileManager.FromHashMapToFile(dataManager.getELL_ENG_DICT(),Config.ell_eng_tei);
        FileManager.FromHashMapToFile(dataManager.getELL_TUR_DICT(),Config.ell_tur_tei);
        FileManager.FromHashMapToFile(dataManager.getELL_FRA_DICT(),Config.ell_fra_tei);
        FileManager.FromHashMapToFile(dataManager.getELL_ITA_DICT(),Config.ell_ita_tei);
        FileManager.FromHashMapToFile(dataManager.getELL_DEU_DICT(),Config.ell_deu_tei);
        FileManager.FromHashMapToFile(dataManager.getELL_SWE_DICT(),Config.ell_swe_tei);

    }

}