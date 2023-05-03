package com.teamfour.dictionary;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;


public class AddController implements Initializable {
    @FXML
    protected MFXComboBox<Word> sourceLangComboBox;
    TEISAXParser teisaxParser = new TEISAXParser();

    DataManager dataManager = new DataManager();

    /*


    public class TEIFileModifier {

      public void addWordAndDefinitions() {

    }

    }
     */
    AddController addController = new AddController();

    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      /*  Word tr = new Word(Config.Languages.TURKISH, "Turkish");
        Word fra = new Word(Config.Languages.FRENCH, "French");
        Word eng = new Word(Config.Languages.ENGLISH, "English");
        Word swe = new Word(Config.Languages.SWEDISH, "Swedish");
        Word gre = new Word(Config.Languages.GREEK, "Greek");
        Word ita = new Word(Config.Languages.ITALIAN, "Italian");
        Word deu = new Word(Config.Languages.GERMAN, "German");*/


    }

    private void addEng(String word, String definitions, String path) throws ParserConfigurationException, IOException, SAXException, TransformerException {


        Word sourceLang = sourceLangComboBox.getValue();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(path);
        Element text = doc.getDocumentElement();

        //  ArrayList<> a = dataManager.getENGXDictionaries();

        for (int i = 0; i < dataManager.getENGXDictionaries().size(); i++) {
            switch (sourceLang.getLanguage()) {
                case TURKISH:
                    Element entry = doc.createElement("entry");


                    Element form = doc.createElement("form");


                    Element orth = doc.createElement("orth");
                    orth.appendChild(doc.createTextNode(word));
                    form.appendChild(orth);


                    entry.appendChild(form);


                    String[] definitionsArray = definitions.split(";"); // split the definitions string into an array
                    for (int j = 0; j < definitionsArray.length; j++) {
                        String definition = definitionsArray[j].trim(); // trim any leading/trailing whitespace from the definition

                        // Create a new "sense" element with the n attribute set to the current index + 1
                        Element sense = doc.createElement("sense");
                        sense.setAttribute("n", Integer.toString(j + 1));


                        Element cit = doc.createElement("cit");
                        cit.setAttribute("type", "trans");


                        Element quote = doc.createElement("quote");
                        quote.appendChild(doc.createTextNode(definition));


                        cit.appendChild(quote);


                        sense.appendChild(cit);


                        entry.appendChild(sense);

                        Element body = (Element) text.getElementsByTagName("body").item(0);
                        body.appendChild(entry);

                        // Write the modified document back to file
                        TransformerFactory tf = TransformerFactory.newInstance();
                        Transformer transformer = tf.newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(new File(path));
                        transformer.transform(source, result);
                    }


            }


        }


    }

    public void addDeu() {


    }

    public void addGreek() {
    }

    public void addFra() {
    }

    public void addIta() {
    }

    public void addSwe() {
    }

    public void addTur() {
    }

    public void addVocab(String string) {
    }
}

