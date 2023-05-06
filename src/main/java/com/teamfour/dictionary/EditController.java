package com.teamfour.dictionary;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    MFXTextField editInput;

    @FXML
    MFXComboBox<Word> editTargetTranslations;

    public Word editWord;
    public Stage stage;
    public DataManager dataManager;

    public void setData(Word editTarget, DataManager dataManager, Stage stage){

        this.editWord = editTarget;
        this.stage = stage;
        this.dataManager = dataManager;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editTargetTranslations.setOnCommit(e-> OnCommit());

    }

    public void OnCommit(){

        System.out.println("commit");
        System.out.println(editTargetTranslations.getValue());
        System.out.println(editTargetTranslations.getText());

        editTargetTranslations.getSelectedItem().setWord(editTargetTranslations.getText());

        ArrayList<Word> l = new ArrayList<>(editTargetTranslations.getItems());

        editTargetTranslations.getItems().clear();

        editTargetTranslations.getItems().addAll(l);

        System.out.println(l);

    }

    public void SetUpEditStage(){

        editInput.setText(editWord.getWord());

        ObservableList<Word> languageCards = FXCollections.observableArrayList();

        languageCards.addAll(editWord.getTranslations());

        editTargetTranslations.setItems(languageCards);

    }

    @FXML
    public void ApplyEditOperation(){



    }
}
