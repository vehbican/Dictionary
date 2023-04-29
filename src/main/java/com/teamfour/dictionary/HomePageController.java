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

import java.io.InputStream;
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

        String s = searchInput.getText().trim();

        if(dataManager.IsFirstCharUpper(s)){

            s = s.toLowerCase();

        }

        Word searchTarget = dataManager.getWordsDatabase().get(s.hashCode());
        translate(dataManager,searchTarget);

        if(searchTarget == null ||(searchTarget.getLanguage() != sourceLang.getLanguage())){

            System.out.println("Not Found");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Not Found");
            alert.setContentText("This word is not found for this source.");
            alert.show();
            return;
        }






        sourceLangComboBox.clearSelection();
        sourceLangComboBox.selectItem(sourceLang);

    }

    private void translate(DataManager dataManager,Word searchTarget){


        Word sourceLang = sourceLangComboBox.getValue();




        switch (sourceLang.getLanguage()){
            case GERMAN:
                for(int i = 0;i<dataManager.getDEUXDictionaries().size();i++){
                    WordCard card = new WordCard(cardListView);
                    Word w = dataManager.getDEUXDictionaries().get(i).get(searchTarget.getHashCode());
                    if (w != null ) {

                        System.out.println(w.getTranslations());

                        card.getDefinitionsListView().getItems().addAll(w.getTranslations());

                        card.getFlagImage().setImage(createFlagImage(w.getTranslations().get(0).getFlagImgPath()));


                    } else  {

                        Word t = new Word(Config.Languages.FRENCH, "There is no translation for \"" + searchTarget + "\" in this language.");
                        card.getDefinitionsListView().getItems().add(t);
                        card.getFlagImage().setImage(createFlagImage(dataManager.getFlags()[i]));

                    }


                }

                // GUI

         /*       for (int i=0;i<dataManager.getDEUXDictionaries().size();i++){

                    WordCard card = new WordCard(cardListView);
                    String s = searchInput.getText().trim();

                    if(dataManager.IsFirstCharUpper(s)){

                        s = s.toLowerCase();

                    }
                    Word w = dataManager.getWordsDatabase().get(s.hashCode());
                    HashMap<Integer, Word> translations = dataManager.getDEUXDictionaries().get(i);


                    if(dataManager.getDEUXDictionaries().get(i).size()>0){

                        Image image = createFlagImage(translations.get(0).getFlagImgPath());
                        card.getDefinitionsListView().getItems().addAll((Collection<? extends Word>) translations);
                        card.getFlagImage().setImage(image);

                    }else{

                        Word t = new Word(Config.Languages.ENGLISH,"There is no translation for \""+searchTarget+"\" in this language.");
                        card.getDefinitionsListView().getItems().add(t);

                        Image image;

                        if (w.getLanguage().equals(Config.Languages.ENGLISH)) {
                            image = createFlagImage(Config.englandFlagImg);
                        }else if(w.getLanguage().equals(Config.Languages.FRENCH)){
                            image = createFlagImage(Config.franceFlagImg);
                        }else if(w.getLanguage().equals(Config.Languages.ITALIAN)){
                            image = createFlagImage(Config.italyFlagImg);
                        }else if(w.getLanguage().equals(Config.Languages.GREEK)){
                            image = createFlagImage(Config.greeceFlagImg);
                        }else if(w.getLanguage().equals(Config.Languages.SWEDISH)){
                            image = createFlagImage(Config.swedenFlagImg);
                        }else{
                            image = createFlagImage(Config.turkeyFlagImg);
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

                }*/


                break;

            case ENGLISH:
              for(int i = 0;i<dataManager.getENGXDictionaries().size();i++){
                  WordCard card = new WordCard(cardListView);
                  Word w = dataManager.getENGXDictionaries().get(i).get(searchTarget.getHashCode());
                  if (w != null) {
                        System.out.println(w.getTranslations());

                      card.getDefinitionsListView().getItems().addAll(w.getTranslations());

                      card.getFlagImage().setImage(createFlagImage(w.getTranslations().get(0).getFlagImgPath()));


                  } else {

                      Word t = new Word(Config.Languages.ENGLISH, "There is no translation for \"" + searchTarget + "\" in this language.");
                      card.getDefinitionsListView().getItems().add(t);
                      card.getFlagImage().setImage(createFlagImage(dataManager.getFlags()[i]));

                  }

              }
              break;
            case FRENCH:
                for(int i = 0;i<dataManager.getFRAXDictionaries().size();i++){
                    WordCard card = new WordCard(cardListView);
                    Word w = dataManager.getFRAXDictionaries().get(i).get(searchTarget.getHashCode());
                    if (w != null ) {
                        System.out.println(w.getTranslations());

                        card.getDefinitionsListView().getItems().addAll(w.getTranslations());

                        card.getFlagImage().setImage(createFlagImage(w.getTranslations().get(0).getFlagImgPath()));


                    } else {

                        Word t = new Word(Config.Languages.FRENCH, "There is no translation for \"" + searchTarget + "\" in this language.");
                        card.getDefinitionsListView().getItems().add(t);
                        card.getFlagImage().setImage(createFlagImage(dataManager.getFlags()[i]));

                    }

                }
                break;
            case ITALIAN:
                for(int i = 0;i<dataManager.getITAXDictionaries().size();i++){
                    WordCard card = new WordCard(cardListView);
                    Word w = dataManager.getITAXDictionaries().get(i).get(searchTarget.getHashCode());
                    if (w != null) {
                        System.out.println(w.getTranslations());

                        card.getDefinitionsListView().getItems().addAll(w.getTranslations());

                        card.getFlagImage().setImage(createFlagImage(w.getTranslations().get(0).getFlagImgPath()));


                    } else {

                        Word t = new Word(Config.Languages.ITALIAN, "There is no translation for \"" + searchTarget + "\" in this language.");
                        card.getDefinitionsListView().getItems().add(t);
                        card.getFlagImage().setImage(createFlagImage(dataManager.getFlags()[i]));

                    }

                }
                break;


            case SWEDISH:
                for(int i = 0;i<dataManager.getSWEXDictionaries().size();i++){
                    WordCard card = new WordCard(cardListView);
                    Word w = dataManager.getSWEXDictionaries().get(i).get(searchTarget.getHashCode());
                    if (w != null) {
                        System.out.println(w.getTranslations());

                        card.getDefinitionsListView().getItems().addAll(w.getTranslations());

                        card.getFlagImage().setImage(createFlagImage(w.getTranslations().get(0).getFlagImgPath()));


                    } else {

                        Word t = new Word(Config.Languages.SWEDISH, "There is no translation for \"" + searchTarget + "\" in this language.");
                        card.getDefinitionsListView().getItems().add(t);
                        card.getFlagImage().setImage(createFlagImage(dataManager.getFlags()[i]));

                    }

                }


                break;

            case TURKISH:
                List<Word> engList=new ArrayList<>();
                List<Word> fraList=new ArrayList<>();
                List<Word> itaList=new ArrayList<>();
                List<Word> greList=new ArrayList<>();
                List<Word> sweList=new ArrayList<>();
                List<Word> gerList=new ArrayList<>();

                List<List<Word>> all = new ArrayList<>(6);

                all.add(Config.eng_tur_index,engList);
                all.add(Config.eng_fra_index,fraList);
                all.add(Config.eng_ita_index,itaList);
                all.add(Config.eng_gre_index,greList);
                all.add(Config.eng_swe_index,sweList);
                all.add(Config.eng_ger_index,gerList);

                for (Word temp : dataManager.getENG_TUR_DICT().values()) {


                    for (Word t :temp.getTranslations()){

                        if(t.getWord().equals(searchTarget.getWord())){
                            System.out.println(temp);

                            engList.add(temp);

                        }

                    }

                }
                for (Word temp : dataManager.getENG_FRA_DICT().values()) {


                    for (Word t : engList){

                        if(t.getWord().equals(temp.getWord())){
                            System.out.println(temp);
                            fraList.addAll(temp.getTranslations());

                        }

                    }

                }
                for (Word temp : dataManager.getENG_ITA_DICT().values()) {


                    for (Word t : engList){

                        if(t.getWord().equals(temp.getWord())){
                            System.out.println(temp);
                            itaList.addAll(temp.getTranslations());

                        }

                    }

                }
                for (Word temp : dataManager.getENG_GRE_DICT().values()) {


                    for (Word t : engList){

                        if(t.getWord().equals(temp.getWord())){
                            System.out.println(temp);
                            greList.addAll(temp.getTranslations());

                        }

                    }

                }
                for (Word temp : dataManager.getENG_SWE_DICT().values()) {


                    for (Word t : engList){

                        if(t.getWord().equals(temp.getWord())){
                            System.out.println(temp);
                            sweList.addAll(temp.getTranslations());

                        }

                    }

                }
                for (Word temp : dataManager.getENG_GER_DICT().values()) {


                    for (Word t : engList){

                        if(t.getWord().equals(temp.getWord())){
                            System.out.println(temp);
                            gerList.addAll(temp.getTranslations());

                        }

                    }

                }
                break;

            case GREEK:
                List<Word> engList1=new ArrayList<>();
                List<Word> fraList1=new ArrayList<>();
                List<Word> itaList1=new ArrayList<>();
                List<Word> turList=new ArrayList<>();
                List<Word> sweList1=new ArrayList<>();
                List<Word> gerList1=new ArrayList<>();

                all = new ArrayList<>(6);

                all.add(Config.eng_tur_index,turList);
                all.add(Config.eng_fra_index,fraList1);
                all.add(Config.eng_ita_index,itaList1);
                all.add(Config.eng_gre_index,engList1);
                all.add(Config.eng_swe_index,sweList1);
                all.add(Config.eng_ger_index,gerList1);

                for (Word temp : dataManager.getENG_GRE_DICT().values()) {


                    for (Word t :temp.getTranslations()){

                        if(t.getWord().equals(searchTarget.getWord())){
                            System.out.println(temp);
                            engList1.add(temp);

                        }

                    }

                }
                for (Word temp : dataManager.getENG_FRA_DICT().values()) {


                    for (Word t : engList1){

                        if(t.getWord().equals(temp.getWord())){
                            System.out.println(temp);
                            fraList1.addAll(temp.getTranslations());

                        }

                    }

                }
                for (Word temp : dataManager.getENG_ITA_DICT().values()) {


                    for (Word t : engList1){

                        if(t.getWord().equals(temp.getWord())){
                            System.out.println(temp);
                            itaList1.addAll(temp.getTranslations());

                        }

                    }

                }
                for (Word temp : dataManager.getENG_TUR_DICT().values()) {


                    for (Word t : engList1){

                        if(t.getWord().equals(temp.getWord())){
                            System.out.println(temp);
                            turList.addAll(temp.getTranslations());

                        }

                    }

                }
                for (Word temp : dataManager.getENG_SWE_DICT().values()) {


                    for (Word t : engList1){

                        if(t.getWord().equals(temp.getWord())){
                            System.out.println(temp);
                            sweList1.addAll(temp.getTranslations());

                        }

                    }

                }
                for (Word temp : dataManager.getENG_GER_DICT().values()) {


                    for (Word t : engList1){

                        if(t.getWord().equals(temp.getWord())){
                            System.out.println(temp);
                            gerList1.addAll(temp.getTranslations());

                        }

                    }

                }



                break;




        }

        /*  for (int i=0;i<dataManager.getDictionaries().length;i++) {



          //  WordCard card = new WordCard(cardListView);
           // Word w,w1,w2,w3,w4,w5;

          //   w = dataManager.getENGXDictionaries().get(i).get(searchTarget.getHashCode());
           //  w1 = dataManager.getDEUXDictionaries().get(i).get(searchTarget.getHashCode());




          if (w != null && w.getLanguage() == Config.Languages.ENGLISH) {
              //  System.out.println(w.getTranslations());

                card.getDefinitionsListView().getItems().addAll(w.getTranslations());

                card.getFlagImage().setImage(createFlagImage(w.getTranslations().get(0).getFlagImgPath()));


            } else {

                Word t = new Word(Config.Languages.ENGLISH, "There is no translation for \"" + searchTarget + "\" in this language.");
                card.getDefinitionsListView().getItems().add(t);
                card.getFlagImage().setImage(createFlagImage(dataManager.getFlags()[i]));

            }

            if (w1 != null && w.getLanguage() == Config.Languages.GERMAN) {
                System.out.println(w1.getTranslations());

                card.getDefinitionsListView().getItems().addAll(w.getTranslations());

                card.getFlagImage().setImage(createFlagImage(w.getTranslations().get(0).getFlagImgPath()));


            } else {

                Word t = new Word(Config.Languages.ENGLISH, "There is no translation for \"" + searchTarget + "\" in this language.");
                card.getDefinitionsListView().getItems().add(t);
                card.getFlagImage().setImage(createFlagImage(dataManager.getFlags()[i]));

            }





        }*/

    }

    private Image createFlagImage(String path){

        URL url = TEISAXParser.class.getResource(path);
        assert url != null;
        InputStream inputStream = TEISAXParser.class.getResourceAsStream(path);
        assert inputStream != null;
        return new Image(inputStream);

    }













}