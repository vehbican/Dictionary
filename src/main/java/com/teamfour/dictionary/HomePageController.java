package com.teamfour.dictionary;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.*;

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


    public void HandleSearchButtonAction(DataManager dataManager) {


        cardListView.getItems().clear();

        if(sourceLangComboBox.getValue() == null){
            sourceLangComboBox.selectIndex(0);
        }

        Word sourceLang = sourceLangComboBox.getValue();

        ObservableList<Word> temp = FXCollections.observableArrayList();
        temp.addAll(languageCards);

        temp.remove(sourceLang);

        //Word searchTarget = new Word(sourceLang.getLanguage(),searchInput.getText().trim().toLowerCase());
        Word searchTarget = dataManager.getWordsDatabase().get(searchInput.getText().trim().toLowerCase().hashCode());

        /*if(searchTarget.getLanguage() != sourceLang.getLanguage()){

            System.out.println("Not Found");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Not Found");
            alert.setContentText("This word is not found for this source.");
            alert.show();
            return;
        }*/

        System.out.println(searchTarget.getLanguage().toString() + " - " + sourceLang.getLanguage().toString());

        switch (sourceLang.getLanguage()){

            case ENGLISH -> SearchInEnglish(dataManager,searchTarget);
            case TURKISH -> SearchInTurkish(dataManager,searchTarget);
            case FRENCH -> SearchInFrench(dataManager,searchTarget);
            case ITALIAN -> SearchInItalian(dataManager,searchTarget);
            case SWEDISH -> SearchInSwedish(dataManager,searchTarget);
            case GREEK -> SearchInGreek(dataManager,searchTarget);
            case GERMAN -> SearchInGerman(dataManager,searchTarget);

        }

        sourceLangComboBox.clearSelection();
        sourceLangComboBox.selectItem(sourceLang);

    }

    private void SearchInEnglish(DataManager dataManager,Word searchTarget){

        for (int i=0;i<dataManager.getDictionaries().length;i++){

            WordCard card = new WordCard(cardListView);

            Word w = dataManager.getENGXDictionaries().get(i).get(searchTarget.getHashCode());


            if(w != null && w.getLanguage() == Config.Languages.ENGLISH){

                card.getDefinitionsListView().getItems().addAll(w.getTranslations());
                card.getFlagImage().setImage(new Image(w.getTranslations().get(0).getFlagImgPath()));

            }else{

                Word t = new Word(Config.Languages.ENGLISH,"There is no translation for \""+searchTarget+"\" in this language.");
                card.getDefinitionsListView().getItems().add(t);
                card.getFlagImage().setImage(new Image(dataManager.getFlags()[i]));

            }

            cardListView.getItems().add(card);

            if(i<5){

                AnchorPane separator = new AnchorPane();
                separator.setPrefSize(Region.USE_COMPUTED_SIZE,20);
                separator.setStyle("-fx-background-color:  #ef233c");
                cardListView.getItems().add(separator);
            }

        }



    }
    private void SearchInTurkish(DataManager dataManager,Word searchTarget){

        List<Word> engList=new ArrayList<>();
        List<Word> fraList=new ArrayList<>();
        List<Word> itaList=new ArrayList<>();
        List<Word> greList=new ArrayList<>();
        List<Word> sweList=new ArrayList<>();
        List<Word> gerList=new ArrayList<>();

        List<List<Word>> all = new ArrayList<>();

        all.add(Config.eng_tur_index,engList);
        all.add(Config.eng_fra_index,fraList);
        all.add(Config.eng_ita_index,itaList);
        all.add(Config.eng_gre_index,greList);
        all.add(Config.eng_swe_index,sweList);
        all.add(Config.eng_ger_index,gerList);

        for (Word temp : dataManager.getENG_TUR_DICT().values()) {


            for (Word t :temp.getTranslations()){

                if(t.getWord().equals(searchTarget.getWord())){

                    engList.add(temp);

                }

            }

        }
        for (Word temp : dataManager.getENG_FRA_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    fraList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_ITA_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    itaList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_GRE_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    greList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_SWE_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    sweList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_GER_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    gerList.addAll(temp.getTranslations());

                }

            }

        }

        //Update UI
        for (int i=0;i<all.size();i++){

            WordCard card = new WordCard(cardListView);

            List<Word> translations = all.get(i);


            if(all.get(i).size()>0){

                Image image = new Image(translations.get(0).getFlagImgPath());
                card.getDefinitionsListView().getItems().addAll(translations);
                card.getFlagImage().setImage(image);

            }else{

                Word t = new Word(Config.Languages.ENGLISH,"There is no translation for \""+searchTarget+"\" in this language.");
                card.getDefinitionsListView().getItems().add(t);

                Image image;

                if (translations.equals(engList)) {
                    image = new Image(Config.englandFlagImg);
                }else if(translations.equals(fraList)){
                    image = new Image(Config.franceFlagImg);
                }else if(translations.equals(itaList)){
                    image = new Image(Config.italyFlagImg);
                }else if(translations.equals(greList)){
                    image = new Image(Config.greeceFlagImg);
                }else if(translations.equals(sweList)){
                    image = new Image(Config.swedenFlagImg);
                }else{
                    image = new Image(Config.germanyFlagImg);
                }

                card.getFlagImage().setImage(image);

            }

            cardListView.getItems().add(card);

            if(i<5){

                AnchorPane separator = new AnchorPane();
                separator.setPrefSize(Region.USE_COMPUTED_SIZE,20);
                separator.setStyle("-fx-background-color:  #ef233c");
                cardListView.getItems().add(separator);
            }

        }

    }
    private void SearchInFrench(DataManager dataManager,Word searchTarget){

        List<Word> engList=new ArrayList<>();
        List<Word> turList=new ArrayList<>();
        List<Word> itaList=new ArrayList<>();
        List<Word> greList=new ArrayList<>();
        List<Word> sweList=new ArrayList<>();
        List<Word> gerList=new ArrayList<>();

        List<List<Word>> all = new ArrayList<>();

        all.add(Config.eng_tur_index,turList);
        all.add(Config.eng_fra_index,engList);
        all.add(Config.eng_ita_index,itaList);
        all.add(Config.eng_gre_index,greList);
        all.add(Config.eng_swe_index,sweList);
        all.add(Config.eng_ger_index,gerList);

        for (Word temp : dataManager.getENG_FRA_DICT().values()) {


            for (Word t :temp.getTranslations()){

                if(t.getWord().equals(searchTarget.getWord())){

                    engList.add(temp);

                }

            }

        }
        for (Word temp : dataManager.getENG_TUR_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    turList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_ITA_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    itaList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_GRE_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    greList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_SWE_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    sweList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_GER_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    gerList.addAll(temp.getTranslations());

                }

            }

        }


        //Update UI
        for (int i=0;i<all.size();i++){

            WordCard card = new WordCard(cardListView);

            List<Word> translations = all.get(i);


            if(all.get(i).size()>0){

                Image image = new Image(translations.get(0).getFlagImgPath());
                card.getDefinitionsListView().getItems().addAll(translations);
                card.getFlagImage().setImage(image);

            }else{

                Word t = new Word(Config.Languages.ENGLISH,"There is no translation for \""+searchTarget+"\" in this language.");
                card.getDefinitionsListView().getItems().add(t);

                Image image;

                if (translations.equals(engList)) {
                    image = new Image(Config.englandFlagImg);
                }else if(translations.equals(turList)){
                    image = new Image(Config.turkeyFlagImg);
                }else if(translations.equals(itaList)){
                    image = new Image(Config.italyFlagImg);
                }else if(translations.equals(greList)){
                    image = new Image(Config.greeceFlagImg);
                }else if(translations.equals(sweList)){
                    image = new Image(Config.swedenFlagImg);
                }else{
                    image = new Image(Config.germanyFlagImg);
                }

                card.getFlagImage().setImage(image);

            }

            cardListView.getItems().add(card);

            if(i<5){

                AnchorPane separator = new AnchorPane();
                separator.setPrefSize(Region.USE_COMPUTED_SIZE,20);
                separator.setStyle("-fx-background-color:  #ef233c");
                cardListView.getItems().add(separator);
            }

        }


    }
    private void SearchInItalian(DataManager dataManager,Word searchTarget){

        List<Word> engList=new ArrayList<>();
        List<Word> fraList=new ArrayList<>();
        List<Word> turList=new ArrayList<>();
        List<Word> greList=new ArrayList<>();
        List<Word> sweList=new ArrayList<>();
        List<Word> gerList=new ArrayList<>();

        List<List<Word>> all = new ArrayList<>();

        all.add(Config.eng_tur_index,turList);
        all.add(Config.eng_fra_index,fraList);
        all.add(Config.eng_ita_index,engList);
        all.add(Config.eng_gre_index,greList);
        all.add(Config.eng_swe_index,sweList);
        all.add(Config.eng_ger_index,gerList);

        for (Word temp : dataManager.getENG_ITA_DICT().values()) {


            for (Word t :temp.getTranslations()){

                if(t.getWord().equals(searchTarget.getWord())){

                    engList.add(temp);

                }

            }

        }
        for (Word temp : dataManager.getENG_FRA_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    fraList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_TUR_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    turList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_GRE_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    greList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_SWE_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    sweList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_GER_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    gerList.addAll(temp.getTranslations());

                }

            }

        }

        //Update UI
        for (int i=0;i<all.size();i++){

            WordCard card = new WordCard(cardListView);

            List<Word> translations = all.get(i);


            if(all.get(i).size()>0){

                Image image = new Image(translations.get(0).getFlagImgPath());
                card.getDefinitionsListView().getItems().addAll(translations);
                card.getFlagImage().setImage(image);

            }else{

                Word t = new Word(Config.Languages.ENGLISH,"There is no translation for \""+searchTarget+"\" in this language.");
                card.getDefinitionsListView().getItems().add(t);

                Image image;

                if (translations.equals(engList)) {
                    image = new Image(Config.englandFlagImg);
                }else if(translations.equals(fraList)){
                    image = new Image(Config.franceFlagImg);
                }else if(translations.equals(turList)){
                    image = new Image(Config.turkeyFlagImg);
                }else if(translations.equals(greList)){
                    image = new Image(Config.greeceFlagImg);
                }else if(translations.equals(sweList)){
                    image = new Image(Config.swedenFlagImg);
                }else{
                    image = new Image(Config.germanyFlagImg);
                }

                card.getFlagImage().setImage(image);

            }

            cardListView.getItems().add(card);

            if(i<5){

                AnchorPane separator = new AnchorPane();
                separator.setPrefSize(Region.USE_COMPUTED_SIZE,20);
                separator.setStyle("-fx-background-color:  #ef233c");
                cardListView.getItems().add(separator);
            }

        }

    }
    private void SearchInSwedish(DataManager dataManager,Word searchTarget){

        List<Word> engList=new ArrayList<>();
        List<Word> fraList=new ArrayList<>();
        List<Word> itaList=new ArrayList<>();
        List<Word> greList=new ArrayList<>();
        List<Word> turList=new ArrayList<>();
        List<Word> gerList=new ArrayList<>();

        List<List<Word>> all = new ArrayList<>();

        all.add(Config.eng_tur_index,turList);
        all.add(Config.eng_fra_index,fraList);
        all.add(Config.eng_ita_index,itaList);
        all.add(Config.eng_gre_index,greList);
        all.add(Config.eng_swe_index,engList);
        all.add(Config.eng_ger_index,gerList);


        for (Word temp : dataManager.getENG_SWE_DICT().values()) {


            for (Word t :temp.getTranslations()){

                if(t.getWord().equals(searchTarget.getWord())){

                    engList.add(temp);

                }

            }

        }
        for (Word temp : dataManager.getENG_FRA_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    fraList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_ITA_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    itaList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_GRE_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    greList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_TUR_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    turList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_GER_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    gerList.addAll(temp.getTranslations());

                }

            }

        }

        //Update UI
        for (int i=0;i<all.size();i++){

            WordCard card = new WordCard(cardListView);

            List<Word> translations = all.get(i);


            if(all.get(i).size()>0){

                Image image = new Image(translations.get(0).getFlagImgPath());
                card.getDefinitionsListView().getItems().addAll(translations);
                card.getFlagImage().setImage(image);

            }else{

                Word t = new Word(Config.Languages.ENGLISH,"There is no translation for \""+searchTarget+"\" in this language.");
                card.getDefinitionsListView().getItems().add(t);

                Image image;

                if (translations.equals(engList)) {
                    image = new Image(Config.englandFlagImg);
                }else if(translations.equals(fraList)){
                    image = new Image(Config.franceFlagImg);
                }else if(translations.equals(itaList)){
                    image = new Image(Config.italyFlagImg);
                }else if(translations.equals(greList)){
                    image = new Image(Config.greeceFlagImg);
                }else if(translations.equals(turList)){
                    image = new Image(Config.turkeyFlagImg);
                }else{
                    image = new Image(Config.germanyFlagImg);
                }

                card.getFlagImage().setImage(image);

            }

            cardListView.getItems().add(card);

            if(i<5){

                AnchorPane separator = new AnchorPane();
                separator.setPrefSize(Region.USE_COMPUTED_SIZE,20);
                separator.setStyle("-fx-background-color:  #ef233c");
                cardListView.getItems().add(separator);
            }

        }


    }
    private void SearchInGreek(DataManager dataManager,Word searchTarget){

        List<Word> engList=new ArrayList<>();
        List<Word> fraList=new ArrayList<>();
        List<Word> itaList=new ArrayList<>();
        List<Word> turList=new ArrayList<>();
        List<Word> sweList=new ArrayList<>();
        List<Word> gerList=new ArrayList<>();

        List<List<Word>> all = new ArrayList<>();

        all.add(Config.eng_tur_index,turList);
        all.add(Config.eng_fra_index,fraList);
        all.add(Config.eng_ita_index,itaList);
        all.add(Config.eng_gre_index,engList);
        all.add(Config.eng_swe_index,sweList);
        all.add(Config.eng_swe_index,gerList);


        for (Word temp : dataManager.getENG_GRE_DICT().values()) {


            for (Word t :temp.getTranslations()){

                if(t.getWord().equals(searchTarget.getWord())){

                    engList.add(temp);

                }

            }

        }
        for (Word temp : dataManager.getENG_FRA_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    fraList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_ITA_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    itaList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_TUR_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    turList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_SWE_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    sweList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_GER_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    gerList.addAll(temp.getTranslations());

                }

            }

        }

        //Update UI
        for (int i=0;i<all.size();i++){

            WordCard card = new WordCard(cardListView);

            List<Word> translations = all.get(i);


            if(all.get(i).size()>0){

                Image image = new Image(translations.get(0).getFlagImgPath());
                card.getDefinitionsListView().getItems().addAll(translations);
                card.getFlagImage().setImage(image);

            }else{

                Word t = new Word(Config.Languages.ENGLISH,"There is no translation for \""+searchTarget+"\" in this language.");
                card.getDefinitionsListView().getItems().add(t);

                Image image;

                if (translations.equals(engList)) {
                    image = new Image(Config.englandFlagImg);
                }else if(translations.equals(fraList)){
                    image = new Image(Config.franceFlagImg);
                }else if(translations.equals(itaList)){
                    image = new Image(Config.italyFlagImg);
                }else if(translations.equals(turList)){
                    image = new Image(Config.turkeyFlagImg);
                }else if(translations.equals(sweList)){
                    image = new Image(Config.swedenFlagImg);
                }else{
                    image = new Image(Config.germanyFlagImg);
                }

                card.getFlagImage().setImage(image);

            }

            cardListView.getItems().add(card);

            if(i<5){

                AnchorPane separator = new AnchorPane();
                separator.setPrefSize(Region.USE_COMPUTED_SIZE,20);
                separator.setStyle("-fx-background-color:  #ef233c");
                cardListView.getItems().add(separator);
            }

        }

    }
    private void SearchInGerman(DataManager dataManager,Word searchTarget){
        List<Word> engList=new ArrayList<>();
        List<Word> fraList=new ArrayList<>();
        List<Word> itaList=new ArrayList<>();
        List<Word> greList=new ArrayList<>();
        List<Word> sweList=new ArrayList<>();
        List<Word> turList=new ArrayList<>();

        List<List<Word>> all = new ArrayList<>();

        all.add(Config.eng_ger_index,engList);
        all.add(Config.eng_fra_index,fraList);
        all.add(Config.eng_ita_index,itaList);
        all.add(Config.eng_gre_index,greList);
        all.add(Config.eng_swe_index,sweList);
        all.add(Config.eng_tur_index,turList);


        for (Word temp : dataManager.getENG_GER_DICT().values()) {


            for (Word t :temp.getTranslations()){

                if(t.getWord().equals(searchTarget.getWord())){

                    engList.add(temp);

                }

            }

        }
        for (Word temp : dataManager.getENG_FRA_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    fraList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_ITA_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    itaList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_GRE_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    greList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_SWE_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    sweList.addAll(temp.getTranslations());

                }

            }

        }
        for (Word temp : dataManager.getENG_TUR_DICT().values()) {


            for (Word t : engList){

                if(t.getWord().equals(temp.getWord())){

                    turList.addAll(temp.getTranslations());

                }

            }

        }

        //Update UI
        for (int i=0;i<all.size();i++){

            WordCard card = new WordCard(cardListView);

            List<Word> translations = all.get(i);


            if(all.get(i).size()>0){

                Image image = new Image(translations.get(0).getFlagImgPath());
                card.getDefinitionsListView().getItems().addAll(translations);
                card.getFlagImage().setImage(image);

            }else{

                Word t = new Word(Config.Languages.ENGLISH,"There is no translation for \""+searchTarget+"\" in this language.");
                card.getDefinitionsListView().getItems().add(t);

                Image image;

                if (translations.equals(engList)) {
                    image = new Image(Config.englandFlagImg);
                }else if(translations.equals(fraList)){
                    image = new Image(Config.franceFlagImg);
                }else if(translations.equals(itaList)){
                    image = new Image(Config.italyFlagImg);
                }else if(translations.equals(greList)){
                    image = new Image(Config.greeceFlagImg);
                }else if(translations.equals(sweList)){
                    image = new Image(Config.swedenFlagImg);
                }else{
                    image = new Image(Config.germanyFlagImg);
                }

                card.getFlagImage().setImage(image);

            }

            cardListView.getItems().add(card);

            if(i<5){

                AnchorPane separator = new AnchorPane();
                separator.setPrefSize(Region.USE_COMPUTED_SIZE,20);
                separator.setStyle("-fx-background-color:  #ef233c");
                cardListView.getItems().add(separator);
            }

        }
    }



}