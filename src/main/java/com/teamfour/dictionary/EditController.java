package com.teamfour.dictionary;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    }

    public void SetUpEditStage(){

        editInput.setText(editWord.getWord());

        ObservableList<Word> languageCards = FXCollections.observableArrayList();

        languageCards.addAll(editWord.getTranslations());

        editTargetTranslations.setItems(languageCards);


    }

    @FXML
    public void ApplyEditOperation(){

        editWord.setWord(editInput.getText().trim().toLowerCase());
        editWord.setTranslations(editTargetTranslations.getItems());

        Config.Languages source = editWord.getLanguage();
        Config.Languages target = editWord.getTargetLanguage();

        switch (source){

            case ENGLISH -> {

                switch (target){

                    case TURKISH -> dataManager.getENG_TUR_DICT().put(editWord.getWord(),editWord);
                    case FRENCH -> dataManager.getENG_FRA_DICT().put(editWord.getWord(),editWord);
                    case GERMAN -> dataManager.getENG_GER_DICT().put(editWord.getWord(),editWord);
                    case ITALIAN -> dataManager.getENG_ITA_DICT().put(editWord.getWord(),editWord);
                    case SWEDISH -> dataManager.getENG_SWE_DICT().put(editWord.getWord(),editWord);
                    case GREEK -> dataManager.getENG_GRE_DICT().put(editWord.getWord(), editWord);

                }

            }
            case TURKISH -> {

                switch (target){

                    case ENGLISH -> dataManager.getTUR_ENG_DICT().put(editWord.getWord(),editWord);
                    case FRENCH -> dataManager.getTUR_FRA_DICT().put(editWord.getWord(),editWord);
                    case GERMAN -> dataManager.getTUR_DEU_DICT().put(editWord.getWord(),editWord);
                    case ITALIAN -> dataManager.getTUR_ITA_DICT().put(editWord.getWord(),editWord);
                    case SWEDISH -> dataManager.getTUR_SWE_DICT().put(editWord.getWord(),editWord);
                    case GREEK -> dataManager.getTUR_ELL_DICT().put(editWord.getWord(), editWord);

                }

            }
            case FRENCH -> {

                switch (target){

                    case TURKISH -> dataManager.getFRA_TUR_DICT().put(editWord.getWord(),editWord);
                    case ENGLISH -> dataManager.getFRA_ENG_DICT().put(editWord.getWord(),editWord);
                    case GERMAN -> dataManager.getFRA_DEU_DICT().put(editWord.getWord(),editWord);
                    case ITALIAN -> dataManager.getFRA_ITA_DICT().put(editWord.getWord(),editWord);
                    case SWEDISH -> dataManager.getFRA_SWE_DICT().put(editWord.getWord(),editWord);
                    case GREEK -> dataManager.getFRA_ELL_DICT().put(editWord.getWord(), editWord);

                }

            }
            case GERMAN -> {

                switch (target){

                    case TURKISH -> dataManager.getDEU_TUR_DICT().put(editWord.getWord(),editWord);
                    case FRENCH -> dataManager.getDEU_FRA_DICT().put(editWord.getWord(),editWord);
                    case ENGLISH -> dataManager.getDEU_ENG_DICT().put(editWord.getWord(),editWord);
                    case ITALIAN -> dataManager.getDEU_ITA_DICT().put(editWord.getWord(),editWord);
                    case SWEDISH -> dataManager.getDEU_SWE_DICT().put(editWord.getWord(),editWord);
                    case GREEK -> dataManager.getDEU_ELL_DICT().put(editWord.getWord(), editWord);

                }

            }
            case ITALIAN -> {

                switch (target){

                    case TURKISH -> dataManager.getITA_TUR_DICT().put(editWord.getWord(),editWord);
                    case FRENCH -> dataManager.getITA_FRA_DICT().put(editWord.getWord(),editWord);
                    case GERMAN -> dataManager.getITA_DEU_DICT().put(editWord.getWord(),editWord);
                    case ENGLISH -> dataManager.getITA_ENG_DICT().put(editWord.getWord(),editWord);
                    case SWEDISH -> dataManager.getITA_SWE_DICT().put(editWord.getWord(),editWord);
                    case GREEK -> dataManager.getITA_ELL_DICT().put(editWord.getWord(), editWord);

                }

            }
            case SWEDISH -> {

                switch (target){

                    case TURKISH -> dataManager.getSWE_TUR_DICT().put(editWord.getWord(),editWord);
                    case FRENCH -> dataManager.getSWE_FRA_DICT().put(editWord.getWord(),editWord);
                    case GERMAN -> dataManager.getSWE_DEU_DICT().put(editWord.getWord(),editWord);
                    case ITALIAN -> dataManager.getSWE_ITA_DICT().put(editWord.getWord(),editWord);
                    case ENGLISH -> dataManager.getSWE_ENG_DICT().put(editWord.getWord(),editWord);
                    case GREEK -> dataManager.getSWE_ELL_DICT().put(editWord.getWord(), editWord);

                }

            }
            case GREEK -> {

                switch (target){

                    case TURKISH -> dataManager.getELL_TUR_DICT().put(editWord.getWord(),editWord);
                    case FRENCH -> dataManager.getELL_FRA_DICT().put(editWord.getWord(),editWord);
                    case GERMAN -> dataManager.getELL_DEU_DICT().put(editWord.getWord(),editWord);
                    case ITALIAN -> dataManager.getELL_ITA_DICT().put(editWord.getWord(),editWord);
                    case SWEDISH -> dataManager.getELL_SWE_DICT().put(editWord.getWord(),editWord);
                    case ENGLISH -> dataManager.getELL_ENG_DICT().put(editWord.getWord(), editWord);

                }

            }

        }

    }
}
