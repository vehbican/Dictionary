package com.teamfour.dictionary;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class HomePageController implements Initializable {

    @FXML
    protected MFXButton searchButton;
    @FXML
    protected MFXTextField searchInput;
    @FXML
    protected TabPane tabPane;
    @FXML
    protected MFXButton turkishButton;
    @FXML
    protected MFXButton englishButton;
    @FXML
    protected MFXButton italianButton;
    @FXML
    protected MFXButton frenchButton;
    @FXML
    protected MFXButton swedishButton;
    @FXML
    protected MFXButton germanButton;
    @FXML
    protected MFXButton greekButton;

    private List<Tab> tabs;
    private Tab turkishTab;
    private Tab englishTab;
    private Tab frenchTab;
    private Tab germanTab;
    private Tab italianTab;
    private Tab greekTab;
    private Tab swedishTab;
    private Tab synonymsTab;

    public DataManager dataManager;

    private static String input;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DisableLanguageButtons();

        int w = 50;

        tabPane.setTabMinWidth(100);
        tabPane.setTabMinHeight(30);
        tabPane.setTabMaxWidth(100);
        tabPane.setTabMaxHeight(30);

        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.REORDER);

        turkishTab = new Tab(Config.Languages.TURKISH.toString());
        turkishTab.setGraphic(buildTabIcon(Config.turkeyFlagImg,w));

        englishTab = new Tab(Config.Languages.ENGLISH.toString());
        englishTab.setGraphic(buildTabIcon(Config.englandFlagImg,w));

        frenchTab = new Tab(Config.Languages.FRENCH.toString());
        frenchTab.setGraphic(buildTabIcon(Config.franceFlagImg,w));

        germanTab = new Tab(Config.Languages.GERMAN.toString());
        germanTab.setGraphic(buildTabIcon(Config.germanyFlagImg,w));

        greekTab = new Tab(Config.Languages.GREEK.toString());
        greekTab.setGraphic(buildTabIcon(Config.greeceFlagImg,w));

        italianTab = new Tab(Config.Languages.ITALIAN.toString());
        italianTab.setGraphic(buildTabIcon(Config.italyFlagImg,w));

        swedishTab = new Tab(Config.Languages.SWEDISH.toString());
        swedishTab.setGraphic(buildTabIcon(Config.swedenFlagImg,w));

        synonymsTab = new Tab("SYNONYMS");
        //tab8.setGraphic(buildTabIcon(Config.turkeyFlagImg,w));


        tabs = List.of(turkishTab,englishTab,frenchTab,germanTab,greekTab,italianTab,swedishTab,synonymsTab);

        tabPane.getTabs().addAll(tabs);





    }


    public void HandleSearchButtonAction(DataManager dataManager) {

        ClearTabContents();
        DisableLanguageButtons();

        input = searchInput.getText().trim().toLowerCase();

        if (input.isBlank()) {
            CreateAlert("Empty Field","Filling the search box seems like a good idea! Let's try.");
            return;
        }

        //Word searchTarget = dataManager.getWordsDatabase().get(input);

        boolean isFound = dataManager.getWordsDatabase().containsKey(input);

        if (!isFound){
            CreateAlert("Not Found","\""+input+"\" is not found!");
            return;
        }

        for (Word w:dataManager.getWordsDatabase().get(input)) {

            switch (w.getLanguage()){

                case ENGLISH -> englishButton.disableProperty().set(false);
                case TURKISH -> turkishButton.disableProperty().set(false);
                case FRENCH  -> frenchButton.disableProperty().set(false);
                case GERMAN  -> germanButton.disableProperty().set(false);
                case GREEK   -> greekButton.disableProperty().set(false);
                case ITALIAN -> italianButton.disableProperty().set(false);
                case SWEDISH -> swedishButton.disableProperty().set(false);

            }

        }




    }

    @FXML
    public void SearchInTurkish(){

        ClearTabContents();

        List<Word> fraList=new ArrayList<>();
        List<Word> itaList=new ArrayList<>();
        List<Word> greList=new ArrayList<>();
        List<Word> sweList=new ArrayList<>();
        List<Word> gerList=new ArrayList<>();
        List<Word> engList = new ArrayList<>(dataManager.getTUR_ENG_DICT().get(input).getTranslations());

        for (Word w:engList){

            String i = w.getWord().trim().toLowerCase();
            fraList.addAll(dataManager.getENG_FRA_DICT().get(i).getTranslations());
            itaList.addAll(dataManager.getENG_ITA_DICT().get(i).getTranslations());
            greList.addAll(dataManager.getENG_GRE_DICT().get(i).getTranslations());
            sweList.addAll(dataManager.getENG_SWE_DICT().get(i).getTranslations());
            gerList.addAll(dataManager.getENG_GER_DICT().get(i).getTranslations());

        }

        WordCard w1 = new WordCard();
        WordCard w2 = new WordCard();
        WordCard w3 = new WordCard();
        WordCard w4 = new WordCard();
        WordCard w5 = new WordCard();
        WordCard w6 = new WordCard();

        w1.getDefinitionsListView().getItems().addAll(engList);
        englishTab.setContent(w1);

        w2.getDefinitionsListView().getItems().addAll(fraList);
        frenchTab.setContent(w2);

        w3.getDefinitionsListView().getItems().addAll(itaList);
        italianTab.setContent(w3);

        w4.getDefinitionsListView().getItems().addAll(greList);
        greekTab.setContent(w4);

        w5.getDefinitionsListView().getItems().addAll(sweList);
        swedishTab.setContent(w5);

        w6.getDefinitionsListView().getItems().addAll(gerList);
        germanTab.setContent(w6);

        //Synonyms?


    }
    @FXML
    public void SearchInEnglish(){

        ClearTabContents();

        ArrayList<Word> syns = new ArrayList<>();

        for (Tab t:tabPane.getTabs()){

            Word temp = null;
            WordCard w = new WordCard();

            switch (t.getText()){

                case "ENGLISH" -> {

                    continue;

                }
                case  "TURKISH" -> {

                    temp = dataManager.getENG_TUR_DICT().get(input);

                }
                case  "FRENCH" -> {

                    temp = dataManager.getENG_FRA_DICT().get(input);

                }
                case  "GERMAN" -> {

                    temp = dataManager.getENG_GER_DICT().get(input);

                }
                case  "GREEK" -> {

                    temp = dataManager.getENG_GRE_DICT().get(input);

                }
                case  "ITALIAN" -> {

                    temp = dataManager.getENG_ITA_DICT().get(input);

                }
                case  "SWEDISH" -> {

                    temp = dataManager.getENG_SWE_DICT().get(input);

                }

            }

            if (temp == null) {

                temp = new Word(Config.Languages.ENGLISH,"Message");
                temp.getTranslations().add(new Word(Config.Languages.ENGLISH,"\""+input+"\" is not found in this language."));
            }
            System.out.println("------------------------------------------");
            System.out.println(temp.getTranslations());
            System.out.println("------------------------------------------");
            w.getDefinitionsListView().getItems().addAll(temp.getTranslations());
            t.setContent(w);


        }

        /*WordCard synonymsCard = new WordCard();
        synonymsCard.getDefinitionsListView().getItems().addAll(syns);
        synonymsTab.setContent(synonymsCard);*/


    }
    @FXML
    public void SearchInFrench(){

        ClearTabContents();

        ArrayList<Word> syns = new ArrayList<>();

        for (Tab t:tabPane.getTabs()){

            Word temp = null;
            WordCard w = new WordCard();

            switch (t.getText()){

                case "ENGLISH" -> {

                    temp = dataManager.getFRA_ENG_DICT().get(input);

                }
                case  "TURKISH" -> {

                    temp = dataManager.getFRA_TUR_DICT().get(input);

                }
                case  "FRENCH" -> {

                    continue;

                }
                case  "GERMAN" -> {

                    temp = dataManager.getFRA_DEU_DICT().get(input);

                }
                case  "GREEK" -> {

                    temp = dataManager.getFRA_ELL_DICT().get(input);

                }
                case  "ITALIAN" -> {

                    temp = dataManager.getFRA_ITA_DICT().get(input);

                }
                case  "SWEDISH" -> {

                    temp = dataManager.getFRA_SWE_DICT().get(input);

                }

            }

            if (temp == null) {
                temp = new Word(Config.Languages.ENGLISH,"Message");
                temp.getTranslations().add(new Word(Config.Languages.ENGLISH,"\""+input+"\" is not found in this language."));
            }
            System.out.println("------------------------------------------");
            System.out.println(temp.getTranslations());
            System.out.println("------------------------------------------");
            w.getDefinitionsListView().getItems().addAll(temp.getTranslations());
            t.setContent(w);


        }

        /*WordCard synonymsCard = new WordCard();
        synonymsCard.getDefinitionsListView().getItems().addAll(syns);
        synonymsTab.setContent(synonymsCard);*/

    }
    @FXML
    public void SearchInGerman(){

        ClearTabContents();

        ArrayList<Word> syns = new ArrayList<>();

        for (Tab t:tabPane.getTabs()){

            Word temp = null;
            WordCard w = new WordCard();

            switch (t.getText()){

                case "ENGLISH" -> {

                    temp = dataManager.getDEU_ENG_DICT().get(input);

                }
                case  "TURKISH" -> {

                    temp = dataManager.getDEU_TUR_DICT().get(input);

                }
                case  "FRENCH" -> {

                    temp = dataManager.getDEU_FRA_DICT().get(input);

                }
                case  "GERMAN" -> {

                    continue;

                }
                case  "GREEK" -> {

                    temp = dataManager.getDEU_ELL_DICT().get(input);

                }
                case  "ITALIAN" -> {

                    temp = dataManager.getDEU_ITA_DICT().get(input);

                }
                case  "SWEDISH" -> {

                    temp = dataManager.getDEU_SWE_DICT().get(input);

                }

            }

            if (temp == null) {

                temp = new Word(Config.Languages.ENGLISH,"Message");
                temp.getTranslations().add(new Word(Config.Languages.ENGLISH,"\""+input+"\" is not found in this language."));
            }
            System.out.println("------------------------------------------");
            System.out.println(temp.getTranslations());
            System.out.println("------------------------------------------");
            w.getDefinitionsListView().getItems().addAll(temp.getTranslations());
            t.setContent(w);


        }

        /*WordCard synonymsCard = new WordCard();
        synonymsCard.getDefinitionsListView().getItems().addAll(syns);
        synonymsTab.setContent(synonymsCard);*/

    }
    @FXML
    public void SearchInGreek(){

        ClearTabContents();

        List<Word> fraList=new ArrayList<>();
        List<Word> itaList=new ArrayList<>();
        List<Word> turList =new ArrayList<>();
        List<Word> sweList=new ArrayList<>();
        List<Word> gerList=new ArrayList<>();
        List<Word> engList = new ArrayList<>(dataManager.getELL_ENG_DICT().get(input).getTranslations());

        for (Word w:engList){

            String i = w.getWord().trim().toLowerCase();
            fraList.addAll(dataManager.getENG_FRA_DICT().get(i).getTranslations());
            itaList.addAll(dataManager.getENG_ITA_DICT().get(i).getTranslations());
            turList .addAll(dataManager.getENG_TUR_DICT().get(i).getTranslations());
            sweList.addAll(dataManager.getENG_SWE_DICT().get(i).getTranslations());
            gerList.addAll(dataManager.getENG_GER_DICT().get(i).getTranslations());

        }

        WordCard w1 = new WordCard();
        WordCard w2 = new WordCard();
        WordCard w3 = new WordCard();
        WordCard w4 = new WordCard();
        WordCard w5 = new WordCard();
        WordCard w6 = new WordCard();

        w1.getDefinitionsListView().getItems().addAll(engList);
        englishTab.setContent(w1);

        w2.getDefinitionsListView().getItems().addAll(fraList);
        frenchTab.setContent(w2);

        w3.getDefinitionsListView().getItems().addAll(itaList);
        italianTab.setContent(w3);

        w4.getDefinitionsListView().getItems().addAll(turList );
        turkishTab.setContent(w4);

        w5.getDefinitionsListView().getItems().addAll(sweList);
        swedishTab.setContent(w5);

        w6.getDefinitionsListView().getItems().addAll(gerList);
        germanTab.setContent(w6);

        //Synonyms?

    }
    @FXML
    public void SearchInSwedish(){

        ClearTabContents();

        ArrayList<Word> syns = new ArrayList<>();

        for (Tab t:tabPane.getTabs()){

            Word temp = null;
            WordCard w = new WordCard();

            switch (t.getText()){

                case "ENGLISH" -> {

                    temp = dataManager.getSWE_ENG_DICT().get(input);

                }
                case  "TURKISH" -> {

                    temp = dataManager.getSWE_TUR_DICT().get(input);

                }
                case  "FRENCH" -> {

                    temp = dataManager.getSWE_FRA_DICT().get(input);

                }
                case  "GERMAN" -> {

                    temp = dataManager.getSWE_DEU_DICT().get(input);

                }
                case  "GREEK" -> {

                    temp = dataManager.getSWE_ELL_DICT().get(input);

                }
                case  "ITALIAN" -> {

                    temp = dataManager.getSWE_ITA_DICT().get(input);

                }
                case  "SWEDISH" -> {

                    continue;

                }

            }

            if (temp == null) {

                temp = new Word(Config.Languages.ENGLISH,"Message");
                temp.getTranslations().add(new Word(Config.Languages.ENGLISH,"\""+input+"\" is not found in this language."));
            }
            System.out.println("------------------------------------------");
            System.out.println(temp.getTranslations());
            System.out.println("------------------------------------------");
            w.getDefinitionsListView().getItems().addAll(temp.getTranslations());
            t.setContent(w);


        }

        /*WordCard synonymsCard = new WordCard();
        synonymsCard.getDefinitionsListView().getItems().addAll(syns);
        synonymsTab.setContent(synonymsCard);*/

    }
    @FXML
    public void SearchInItalian(){

        ClearTabContents();

        ArrayList<Word> syns = new ArrayList<>();

        for (Tab t:tabPane.getTabs()){

            Word temp = null;
            WordCard w = new WordCard();

            switch (t.getText()){

                case "ENGLISH" -> {

                    temp = dataManager.getITA_ENG_DICT().get(input);

                }
                case  "TURKISH" -> {

                    temp = dataManager.getITA_TUR_DICT().get(input);

                }
                case  "FRENCH" -> {

                    temp = dataManager.getITA_FRA_DICT().get(input);

                }
                case  "GERMAN" -> {

                    temp = dataManager.getITA_DEU_DICT().get(input);

                }
                case  "GREEK" -> {

                    temp = dataManager.getITA_ELL_DICT().get(input);

                }
                case  "ITALIAN" -> {

                    continue;

                }
                case  "SWEDISH" -> {

                    temp = dataManager.getITA_SWE_DICT().get(input);

                }

            }

            if (temp == null) {

                temp = new Word(Config.Languages.ENGLISH,"Message");
                temp.getTranslations().add(new Word(Config.Languages.ENGLISH,"\""+input+"\" is not found in this language."));
            }
            System.out.println("------------------------------------------");
            System.out.println(temp.getTranslations());
            System.out.println("------------------------------------------");
            w.getDefinitionsListView().getItems().addAll(temp.getTranslations());
            t.setContent(w);


        }

        /*WordCard synonymsCard = new WordCard();
        synonymsCard.getDefinitionsListView().getItems().addAll(syns);
        synonymsTab.setContent(synonymsCard);*/

    }


    private void CreateAlert(String title,String message){

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();

    }


    private void ClearTabContents(){

        for (Tab t:tabPane.getTabs()){

            t.setContent(null);
        }

    }

    private void DisableLanguageButtons(){

        turkishButton.disableProperty().set(true);
        englishButton.disableProperty().set(true);
        frenchButton.disableProperty().set(true);
        germanButton.disableProperty().set(true);
        greekButton.disableProperty().set(true);
        italianButton.disableProperty().set(true);
        swedishButton.disableProperty().set(true);

    }

    private Image createFlagImage(String path) {

        URL url = TEISAXParser.class.getResource(path);
        assert url != null;
        InputStream inputStream = TEISAXParser.class.getResourceAsStream(path);
        assert inputStream != null;
        return new Image(inputStream);

    }

    private ImageView buildTabIcon(String imgPath,int width) {
        Image i = createFlagImage(imgPath);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(width);
        imageView.setFitWidth(width);
        imageView.setImage(i);
        return imageView;
    }

}