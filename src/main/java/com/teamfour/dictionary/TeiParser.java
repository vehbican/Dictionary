package com.teamfour.dictionary;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TeiParser {

    /*
    public static List<String> getTranslations(String word, String filename) throws ParserConfigurationException, SAXException, IOException {

        List<String> translations = new ArrayList<>();
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
    */


    public HashMap<Integer, Word> ParseTEIToHashMap(DataManager dataManager, String path, Config.Languages sourceLang, Config.Languages targetLang){

        HashMap<Integer, Word> dictionary = new HashMap<>();
        List<Word> translations;
        File file = new File(path);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc;

        try {

            db = dbf.newDocumentBuilder();
            doc = db.parse(file);
            //doc.getDocumentElement().normalize();

            NodeList entryList;
            if(targetLang == Config.Languages.GERMAN){

                entryList = doc.getElementsByTagName("entry");

            }else{

                entryList = doc.getElementsByTagName("entry");

            }


            for (int i = 0; i < entryList.getLength(); i++) {

                Element entryElement = (Element) entryList.item(i);
                NodeList orthList = entryElement.getElementsByTagName("orth");
                Element orthElement = (Element) orthList.item(0);
                String sourceWord = orthElement.getTextContent();

                NodeList citList;
                if(targetLang == Config.Languages.GREEK){

                    citList = entryElement.getElementsByTagName("def");

                }else if(targetLang == Config.Languages.GERMAN){

                    citList = entryElement.getElementsByTagName("quote");

                } else{

                    citList = entryElement.getElementsByTagName("cit");

                }

                Word sourceWordTemp = new Word(sourceLang,sourceWord.trim().toLowerCase());
                translations = new ArrayList<>();
                Charset c;
                for (int j = 0; j < citList.getLength(); j++) {

                    Element citElement = (Element) citList.item(j);
                    String t = citElement.getTextContent().toLowerCase().trim();

                    if(targetLang == Config.Languages.GREEK){

                        c = Charset.forName("ISO-8859-7");

                    }else{

                        c = StandardCharsets.UTF_8;

                    }

                    Word translation = new Word(targetLang,decodeText(t, c).trim().toLowerCase());
                    translation.getTranslations().add(sourceWordTemp);
                    translations.add(translation);

                    //dictionary.put(translation.getHashCode(),sourceWordTemp);
                    dataManager.getWordsDatabase().put(translation.getHashCode(),translation);
                }

                if(dictionary.containsKey(sourceWordTemp.getHashCode())){

                    dictionary.get(sourceWordTemp.getHashCode()).getTranslations().addAll(translations);

                }else{

                    sourceWordTemp.setTranslations(translations);
                    dictionary.put(sourceWordTemp.getHashCode(),sourceWordTemp);

                }

                dataManager.getWordsDatabase().put(sourceWordTemp.getHashCode(),sourceWordTemp);


            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return dictionary;
    }

    String decodeText(String input, Charset charset) throws IOException {
        CharsetDecoder charsetDecoder = charset.newDecoder();
        charsetDecoder.onMalformedInput(CodingErrorAction.REPLACE);
        return new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(input.getBytes(charset)), charsetDecoder)).readLine();
    }

}
