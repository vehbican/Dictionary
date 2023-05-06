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
    MFXTextField wordInput;

    @FXML
    MFXTextField translationInput;

    DataManager dataManager;
    Stage stage;

    public void setData(Stage stage,DataManager dataManager) {
        this.stage = stage;
        this.dataManager = dataManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Word tr = new Word(Config.Languages.TURKISH,"Turkish");
        Word fra = new Word(Config.Languages.FRENCH,"French");
        Word eng = new Word(Config.Languages.ENGLISH,"English");
        Word swe = new Word(Config.Languages.SWEDISH,"Swedish");
        Word gre = new Word(Config.Languages.GREEK,"Greek");
        Word ita = new Word(Config.Languages.ITALIAN,"Italian");
        Word deu = new Word(Config.Languages.GERMAN,"German");

        ObservableList<Word> languageCards = FXCollections.observableArrayList(tr,fra,eng,swe,gre,ita,deu);
        sourceLanguages.setItems(languageCards);
        targetLanguages.setItems(languageCards);

    }

    @FXML
    public void ApplyAddOperation(){

        System.out.println(wordInput.getText() + " : " + translationInput.getText());
        System.out.println("Applied");

    }


}

