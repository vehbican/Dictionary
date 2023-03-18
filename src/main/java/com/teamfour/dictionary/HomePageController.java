package com.teamfour.dictionary;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    protected MFXComboBox<Word> sourceLangComboBox;
    @FXML
    protected MFXButton searchButton;
    @FXML
    protected MFXTextField searchInput;

    @FXML
    private ObservableList<Word> languageCards;

    @FXML
    protected ListView<AnchorPane> cardListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



        Word tr = new Word(Config.Languages.TURKISH,"Turkish");
        Word fra = new Word(Config.Languages.FRENCH,"French");
        Word eng = new Word(Config.Languages.ENGLISH,"English");
        Word swe = new Word(Config.Languages.SWEDISH,"Swedish");
        Word gre = new Word(Config.Languages.GREEK,"Greek");
        Word ita = new Word(Config.Languages.ITALIAN,"Italian");
        Word deu = new Word(Config.Languages.GERMAN,"German");

        languageCards = FXCollections.observableArrayList(tr,fra,eng,swe,gre,ita,deu);
        sourceLangComboBox.setItems(languageCards);

        cardListView.setStyle("-fx-control-inner-background: #2b2d42; -fx-background-insets: 0;-fx-padding: 0; -fx-cell-size: 10");
        cardListView.setOrientation(Orientation.VERTICAL);


    }


    public void HandleSearchButtonAction(DataManager dataManager){


        cardListView.getItems().clear();

        if(sourceLangComboBox.getValue() == null){
            sourceLangComboBox.selectIndex(0);
        }

        Word sourceLang = sourceLangComboBox.getValue();

        ObservableList<Word> temp = FXCollections.observableArrayList();
        temp.addAll(languageCards);

        temp.remove(sourceLang);

        Word searchTarget = new Word(sourceLang.getLanguage(),searchInput.getText().trim().toLowerCase());

        for (int i=0;i<6;i++){

            WordCard card = new WordCard(cardListView);

            if(dataManager.getAllDictionaries().get(i).containsKey(searchTarget.getHashCode())){

                Word w = dataManager.getAllDictionaries().get(i).get(searchTarget.getHashCode());

                card.getDefinitionsListView().getItems().addAll(w.getTranslations());
                card.getFlagImage().setImage(new Image(w.getFlagImgPath()));

            }

            cardListView.getItems().add(card);
            if(i<5){

                AnchorPane separator = new AnchorPane();
                separator.setPrefSize(Region.USE_COMPUTED_SIZE,20);
                separator.setStyle("-fx-background-color:  #ef233c");
                cardListView.getItems().add(separator);
            }

        }

        sourceLangComboBox.clearSelection();
        sourceLangComboBox.selectItem(sourceLang);

    }

    /*private Word SearchInEnglish(){ }

    private Word SearchInTurkish(){ }

    private Word SearchInFrench(){ }

    private Word SearchInGerman(){ }

    private Word SearchInItalian(){ }

    private Word SearchInGreek(){ }

    private Word SearchInSwedish(){ }*/



}