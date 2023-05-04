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



    }

    public void addEng(String word, String definitions) throws ParserConfigurationException, IOException, SAXException, TransformerException {


        if (sourceLangComboBox.getValue() == null) {
            sourceLangComboBox.selectIndex(0);
        }
        Word sourceLang = sourceLangComboBox.getValue();


        for (int i = 0; i < dataManager.getENGXDictionaries().size(); i++) {
            switch (sourceLang.getLanguage()) {
                case TURKISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.eng_tur_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.eng_tur_tei));
                        transformer.transform(source, result);

                    }
                }

                break;
                case FRENCH: {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.eng_fra_tei);
                    Element text = doc.getDocumentElement();
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
                        StreamResult result = new StreamResult(new File(Config.eng_fra_tei));
                        transformer.transform(source, result);
                        break;

                    }

                }
                case ITALIAN: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.eng_ita_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.eng_ita_tei));
                        transformer.transform(source, result);

                    }
                }
                case GREEK: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.eng_ell_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.eng_ell_tei));
                        transformer.transform(source, result);

                    }
                }
                case SWEDISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.eng_swe_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.eng_swe_tei));
                        transformer.transform(source, result);

                    }
                }
                case GERMAN: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.eng_deu_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.eng_deu_tei));
                        transformer.transform(source, result);

                    }
                }


            }


        }


    }

    public void addIta(String word, String definitions) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        if (sourceLangComboBox.getValue() == null) {
            sourceLangComboBox.selectIndex(0);
        }
        Word sourceLang = sourceLangComboBox.getValue();


        for (int i = 0; i < dataManager.getITAXDictionaries().size(); i++) {
            switch (sourceLang.getLanguage()) {
                case TURKISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.ita_tur_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.ita_tur_tei));
                        transformer.transform(source, result);

                    }
                }

                break;
                case FRENCH: {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.ita_fra_tei);
                    Element text = doc.getDocumentElement();
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
                        StreamResult result = new StreamResult(new File(Config.ita_fra_tei));
                        transformer.transform(source, result);
                        break;

                    }

                }
                case ENGLISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.ita_eng_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.ita_eng_tei));
                        transformer.transform(source, result);

                    }
                }
                case GREEK: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.ita_ell_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.ita_ell_tei));
                        transformer.transform(source, result);

                    }
                }
                case SWEDISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.ita_swe_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.ita_swe_tei));
                        transformer.transform(source, result);

                    }
                }
                case GERMAN: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.ita_deu_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.ita_deu_tei));
                        transformer.transform(source, result);

                    }
                }


            }


        }


    }

    public void addGer(String word, String definitions) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        if (sourceLangComboBox.getValue() == null) {
            sourceLangComboBox.selectIndex(0);
        }
        Word sourceLang = sourceLangComboBox.getValue();


        for (int i = 0; i < dataManager.getDEUXDictionaries().size(); i++) {
            switch (sourceLang.getLanguage()) {
                case TURKISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.deu_tur_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.deu_tur_tei));
                        transformer.transform(source, result);

                    }
                }

                break;
                case FRENCH: {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.deu_fra_tei);
                    Element text = doc.getDocumentElement();
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
                        StreamResult result = new StreamResult(new File(Config.deu_fra_tei));
                        transformer.transform(source, result);
                        break;

                    }

                }
                case ENGLISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.deu_eng_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.deu_eng_tei));
                        transformer.transform(source, result);

                    }
                }
                case GREEK: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.deu_ell_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.deu_ell_tei));
                        transformer.transform(source, result);

                    }
                }
                case SWEDISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.deu_swe_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.deu_swe_tei));
                        transformer.transform(source, result);

                    }
                }
                case ITALIAN: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.deu_ita_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.deu_ita_tei));
                        transformer.transform(source, result);

                    }
                }


            }


        }


    }

    public void addFra(String word, String definitions) throws ParserConfigurationException, IOException, SAXException, TransformerException {


        if (sourceLangComboBox.getValue() == null) {
            sourceLangComboBox.selectIndex(0);
        }
        Word sourceLang = sourceLangComboBox.getValue();


        for (int i = 0; i < dataManager.getFRAXDictionaries().size(); i++) {
            switch (sourceLang.getLanguage()) {
                case TURKISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.fra_tur_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.fra_tur_tei));
                        transformer.transform(source, result);

                    }
                }

                break;
                case ENGLISH: {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.fra_eng_tei);
                    Element text = doc.getDocumentElement();
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
                        StreamResult result = new StreamResult(new File(Config.fra_eng_tei));
                        transformer.transform(source, result);

                    }
                    break;

                }
                case ITALIAN: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.fra_ita_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.fra_ita_tei));
                        transformer.transform(source, result);

                    }
                }
                case GREEK: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.fra_ell_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.fra_ell_tei));
                        transformer.transform(source, result);

                    }
                }
                case SWEDISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.fra_swe_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.fra_swe_tei));
                        transformer.transform(source, result);

                    }
                }
                case GERMAN: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.fra_deu_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.fra_deu_tei));
                        transformer.transform(source, result);

                    }
                }


            }


        }


    }

    public void addSwe(String word, String definitions) throws ParserConfigurationException, IOException, SAXException, TransformerException {


        if (sourceLangComboBox.getValue() == null) {
            sourceLangComboBox.selectIndex(0);
        }
        Word sourceLang = sourceLangComboBox.getValue();


        for (int i = 0; i < dataManager.getSWEXDictionaries().size(); i++) {
            switch (sourceLang.getLanguage()) {
                case TURKISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.swe_tur_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.swe_tur_tei));
                        transformer.transform(source, result);

                    }
                }

                break;
                case FRENCH: {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.swe_fra_tei);
                    Element text = doc.getDocumentElement();
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
                        StreamResult result = new StreamResult(new File(Config.swe_fra_tei));
                        transformer.transform(source, result);
                        break;

                    }

                }
                case ITALIAN: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.swe_ita_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.swe_ita_tei));
                        transformer.transform(source, result);

                    }
                }
                case GREEK: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.swe_ell_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.swe_ell_tei));
                        transformer.transform(source, result);

                    }
                }
                case ENGLISH: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.swe_eng_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.swe_eng_tei));
                        transformer.transform(source, result);

                    }
                }
                case GERMAN: {

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(Config.swe_deu_tei);
                    Element text = doc.getDocumentElement();

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
                        StreamResult result = new StreamResult(new File(Config.swe_deu_tei));
                        transformer.transform(source, result);

                    }
                }


            }


        }


    }
}

