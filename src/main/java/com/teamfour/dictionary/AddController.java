package com.teamfour.dictionary;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class AddController implements Initializable {

    @FXML
    MFXComboBox<Word> sourceLanguages;

    @FXML
    MFXComboBox<Word> targetLanguages;

    @FXML
    MFXTextField word;

    @FXML
    MFXTextField definition;

    DataManager dataManager;
    Stage stage;

    public void setData(Stage stage, DataManager dataManager) {
        this.stage = stage;
        this.dataManager = dataManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Word tr = new Word(Config.Languages.TURKISH, "Turkish");
        Word fra = new Word(Config.Languages.FRENCH, "French");
        Word eng = new Word(Config.Languages.ENGLISH, "English");
        Word swe = new Word(Config.Languages.SWEDISH, "Swedish");
        Word gre = new Word(Config.Languages.GREEK, "Greek");
        Word ita = new Word(Config.Languages.ITALIAN, "Italian");
        Word deu = new Word(Config.Languages.GERMAN, "German");

        ObservableList<Word> languageCards = FXCollections.observableArrayList(tr, fra, eng, swe, gre, ita, deu);
        sourceLanguages.setItems(languageCards);
        targetLanguages.setItems(languageCards);

    }

    @FXML
    public void ApplyAddOperation() throws IOException {

        System.out.println(word.getText() + " : " + definition.getText());
        System.out.println("Applied");

        Word sourceLang = sourceLanguages.getValue();
        System.out.println(sourceLanguages.getValue());
        switch (sourceLang.getLanguage()) {
            case ENGLISH -> addEngWord();
            case GERMAN -> addDeuWord();
            case ITALIAN -> addItaWord();
            case FRENCH -> addFraWord();
            case SWEDISH -> addSweWord();
            case TURKISH -> addTurWord();
            case GREEK -> addGreWord();

        }


    }

    public void addEngWord() throws IOException {

        Word sourceLange = targetLanguages.getValue();
        System.out.println(sourceLange.getLanguage());

        switch (sourceLange.getLanguage()) {
            case TURKISH: {
                Word w = new Word(Config.Languages.ENGLISH, word.getText());
                Word definitions = new Word(Config.Languages.TURKISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getENG_TUR_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;


            }

            case SWEDISH: {
                Word w = new Word(Config.Languages.ENGLISH, word.getText());
                Word definitions = new Word(Config.Languages.SWEDISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getENG_SWE_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;

            }
            case FRENCH: {
                Word w = new Word(Config.Languages.ENGLISH, word.getText());
                Word definitions = new Word(Config.Languages.FRENCH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getENG_FRA_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case GREEK: {
                Word w = new Word(Config.Languages.ENGLISH, word.getText());
                Word definitions = new Word(Config.Languages.GREEK, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getENG_GRE_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case GERMAN: {
                Word w = new Word(Config.Languages.ENGLISH, word.getText());
                Word definitions = new Word(Config.Languages.GERMAN, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getENG_GER_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case ITALIAN: {
                Word w = new Word(Config.Languages.ENGLISH, word.getText());
                Word definitions = new Word(Config.Languages.ITALIAN, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getENG_ITA_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }

        }
    }

    public void addDeuWord() throws IOException {

        Word sourceLange = targetLanguages.getValue();
        System.out.println(sourceLange.getLanguage());

        switch (sourceLange.getLanguage()) {
            case TURKISH: {
                Word w = new Word(Config.Languages.GERMAN, word.getText());
                Word definitions = new Word(Config.Languages.TURKISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getDEU_TUR_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;


            }

            case SWEDISH: {
                Word w = new Word(Config.Languages.GERMAN, word.getText());
                Word definitions = new Word(Config.Languages.SWEDISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getDEU_SWE_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;

            }
            case FRENCH: {
                Word w = new Word(Config.Languages.GERMAN, word.getText());
                Word definitions = new Word(Config.Languages.FRENCH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getDEU_FRA_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case GREEK: {
                Word w = new Word(Config.Languages.GERMAN, word.getText());
                Word definitions = new Word(Config.Languages.GREEK, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getDEU_ELL_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case ENGLISH: {
                Word w = new Word(Config.Languages.GERMAN, word.getText());
                Word definitions = new Word(Config.Languages.ENGLISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getDEU_ENG_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case ITALIAN: {
                Word w = new Word(Config.Languages.GERMAN, word.getText());
                Word definitions = new Word(Config.Languages.ITALIAN, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getDEU_ITA_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }

        }
    }

    public void addItaWord() throws IOException {

        Word sourceLange = targetLanguages.getValue();
        System.out.println(sourceLange.getLanguage());

        switch (sourceLange.getLanguage()) {
            case TURKISH: {
                Word w = new Word(Config.Languages.ITALIAN, word.getText());
                Word definitions = new Word(Config.Languages.TURKISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getITA_TUR_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;


            }

            case SWEDISH: {
                Word w = new Word(Config.Languages.ITALIAN, word.getText());
                Word definitions = new Word(Config.Languages.SWEDISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getITA_SWE_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;

            }
            case FRENCH: {
                Word w = new Word(Config.Languages.ITALIAN, word.getText());
                Word definitions = new Word(Config.Languages.FRENCH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getITA_FRA_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case GREEK: {
                Word w = new Word(Config.Languages.ITALIAN, word.getText());
                Word definitions = new Word(Config.Languages.GREEK, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getITA_ELL_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case GERMAN: {
                Word w = new Word(Config.Languages.ITALIAN, word.getText());
                Word definitions = new Word(Config.Languages.GERMAN, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getITA_DEU_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case ENGLISH: {
                Word w = new Word(Config.Languages.ITALIAN, word.getText());
                Word definitions = new Word(Config.Languages.ENGLISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getITA_ENG_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }

        }
    }

    public void addFraWord() throws IOException {

        Word sourceLange = targetLanguages.getValue();
        // System.out.println(sourceLange.getLanguage());

        switch (sourceLange.getLanguage()) {
            case TURKISH: {
                Word w = new Word(Config.Languages.FRENCH, word.getText());
                Word definitions = new Word(Config.Languages.TURKISH, definition.getText());
                w.getTranslations().add(definitions);

                dataManager.getFRA_TUR_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;


            }

            case SWEDISH: {
                Word w = new Word(Config.Languages.FRENCH, word.getText());
                Word definitions = new Word(Config.Languages.SWEDISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getFRA_SWE_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;

            }
            case ENGLISH: {
                Word w = new Word(Config.Languages.FRENCH, word.getText());
                Word definitions = new Word(Config.Languages.ENGLISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getFRA_ENG_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case GREEK: {
                Word w = new Word(Config.Languages.FRENCH, word.getText());
                Word definitions = new Word(Config.Languages.GREEK, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getFRA_ELL_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case GERMAN: {
                Word w = new Word(Config.Languages.FRENCH, word.getText());
                Word definitions = new Word(Config.Languages.GERMAN, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getFRA_DEU_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case ITALIAN: {
                Word w = new Word(Config.Languages.FRENCH, word.getText());
                Word definitions = new Word(Config.Languages.ITALIAN, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getFRA_ITA_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }

        }
    }

    public void addSweWord() throws IOException {

        Word sourceLange = targetLanguages.getValue();
        System.out.println(sourceLange.getLanguage());

        switch (sourceLange.getLanguage()) {
            case TURKISH: {
                Word w = new Word(Config.Languages.SWEDISH, word.getText());
                Word definitions = new Word(Config.Languages.TURKISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getSWE_TUR_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;


            }

            case ENGLISH: {
                Word w = new Word(Config.Languages.SWEDISH, word.getText());
                Word definitions = new Word(Config.Languages.ENGLISH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getSWE_ENG_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;

            }
            case FRENCH: {
                Word w = new Word(Config.Languages.SWEDISH, word.getText());
                Word definitions = new Word(Config.Languages.FRENCH, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getSWE_FRA_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case GREEK: {
                Word w = new Word(Config.Languages.SWEDISH, word.getText());
                Word definitions = new Word(Config.Languages.GREEK, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getSWE_ELL_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case GERMAN: {
                Word w = new Word(Config.Languages.SWEDISH, word.getText());
                Word definitions = new Word(Config.Languages.GERMAN, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getSWE_DEU_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }
            case ITALIAN: {
                Word w = new Word(Config.Languages.SWEDISH, word.getText());
                Word definitions = new Word(Config.Languages.ITALIAN, definition.getText());
                w.getTranslations().add(definitions);


                dataManager.getSWE_ITA_DICT().put(word.getText(), w);
                dataManager.getWordsDatabase().put(word.getText(), w);
                break;
            }

        }
    }

    public void addTurWord() {

    }

    public void addGreWord() {

    }


}

