package com.teamfour.dictionary;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddController implements Initializable {

    TEISAXParser teisaxParser = new TEISAXParser();
    TeiParser teiParser = new TeiParser();
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
        Word tr = new Word(Config.Languages.TURKISH, "Turkish");
        Word fra = new Word(Config.Languages.FRENCH, "French");
        Word eng = new Word(Config.Languages.ENGLISH, "English");
        Word swe = new Word(Config.Languages.SWEDISH, "Swedish");
        Word gre = new Word(Config.Languages.GREEK, "Greek");
        Word ita = new Word(Config.Languages.ITALIAN, "Italian");
        Word deu = new Word(Config.Languages.GERMAN, "German");
    }
    public String addEng(String word, String[] definitions, String teiFilePath){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            File teiFile = new File(teiFilePath);
            Document doc = docBuilder.parse(teiFile);
            Element rootElement = doc.getDocumentElement();

            Element entryElement = doc.createElement("entry");
            Element formElement = doc.createElement("form");
            Element orthElement = doc.createElement("orth");
            orthElement.setTextContent(word);
            formElement.appendChild(orthElement);
            entryElement.appendChild(formElement);

            for (int i = 0; i < definitions.length; i++) {
                Element senseElement = doc.createElement("sense");
                senseElement.setAttribute("n", Integer.toString(i+1));
                Element citElement = doc.createElement("cit");
                citElement.setAttribute("type", "trans");
                Element quoteElement = doc.createElement("quote");
                quoteElement.setTextContent(definitions[i]);
                citElement.appendChild(quoteElement);
                senseElement.appendChild(citElement);
                entryElement.appendChild(senseElement);
            }

            rootElement.appendChild(entryElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(teiFile));
            transformer.transform(source, result);

            System.out.println("Word and definitions added to the file.");
        } catch (ParserConfigurationException | IOException | TransformerException | org.xml.sax.SAXException e) {
            e.printStackTrace();

        }
        return word;

    }
    public void addDeu(){}
    public void addGreek(){}
    public void addFra(){}
    public void addIta(){}
    public void addSwe(){}
    public void addTur(){}
    public void addVocab(String string){}
}

