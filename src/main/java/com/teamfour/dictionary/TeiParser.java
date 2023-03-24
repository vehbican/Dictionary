package com.teamfour.dictionary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TeiParser {

    public static List<String> getTranslations(String word, String filename) throws ParserConfigurationException, SAXException, IOException {

        List<String> translations = new ArrayList<String>();
        File file = new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        NodeList entryList = doc.getElementsByTagName("entry");

        for (int i = 0; i < entryList.getLength(); i++) {

            Element entryElement = (Element) entryList.item(i);
            NodeList orthList = entryElement.getElementsByTagName("orth");
            Element orthElement = (Element) orthList.item(0);
            String entryWord = orthElement.getTextContent();

            if (entryWord.equals(word)) {
                NodeList citList = entryElement.getElementsByTagName("cit");
                for (int j = 0; j < citList.getLength(); j++) {
                    Element citElement = (Element) citList.item(j);
                    String translation = citElement.getTextContent();
                    if (translation.equals(word)) {
                        translations.add(translation.trim());
                    } else {
                        String[] words = translation.split("\\s+");
                        if (Arrays.asList(words).contains(word)) {
                            // Word is part of a longer word, don't add to translations
                        } else {
                            translations.add(translation.trim());
                        }
                    }
                }
            }
        }
        if (translations.isEmpty()) {
            translations.add("Could not find word: " + word);
        }
        return translations;
    }

}
