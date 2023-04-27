package com.teamfour.dictionary;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TEISAXParser {

    public HashMap<Integer,Word> ParseIntoHashMap(DataManager dataManager, String path, Config.Languages sourceLang, Config.Languages targetLang){

        HashMap<Integer, Word> dictionary = new HashMap<>();

        try {

            URL url = TEISAXParser.class.getResource(path);
            assert url != null;
            InputStream inputStream = App.class.getResourceAsStream(path);
            assert inputStream != null;

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MyHandler handler = new MyHandler(dictionary,sourceLang,targetLang,dataManager);
            saxParser.parse(inputStream, handler);
            return handler.dictionary;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dictionary;
    }



    private static class MyHandler extends DefaultHandler {
        private boolean inEntry = false;
        private boolean inOrth = false;
        private boolean inDef = false; //for eng-greek dictionary
        private boolean inSense = false;
        private boolean inQuote = false;
        private String currentId;
        private String currentOrth;
        private String currentLang;
        private StringBuilder currentQuote;

        private HashMap<Integer,Word> dictionary;
        private Config.Languages sourceLang;
        private Config.Languages targetLang;
        private DataManager dataManager;

        private List<Word> quotes;

        public MyHandler(HashMap<Integer, Word> dictionary, Config.Languages sourceLang, Config.Languages targetLang, DataManager dataManager) {
            this.dictionary = dictionary;
            this.sourceLang = sourceLang;
            this.targetLang = targetLang;
            this.dataManager = dataManager;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("entry")) {
                inEntry = true;
                currentId = attributes.getValue("xml:id");
                quotes = new ArrayList<>();
            } else if (qName.equalsIgnoreCase("orth") && inEntry) {
                inOrth = true;
            }else if (qName.equalsIgnoreCase("sense") && inEntry) {
                inSense = true;
            } else if ((qName.equalsIgnoreCase("quote") || qName.equalsIgnoreCase("def")) && inSense) {
                inQuote = true;
                inDef = true;
                currentLang = attributes.getValue("xml:lang");
                currentQuote = new StringBuilder();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (inOrth) {
                currentOrth = new String(ch, start, length);
                inOrth = false;
            } else if (inQuote || inDef) {
                currentQuote.append(new String(ch, start, length));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {


            if (qName.equalsIgnoreCase("entry")) {
                inEntry = false;
                /*System.out.println("ID: " + currentId);
                System.out.println("Orth: " + currentOrth + " - " + quotes);
                System.out.println();*/

                if(dataManager.IsFirstCharUpper(currentOrth)){

                    currentOrth = currentOrth.trim().toLowerCase();

                }
                Word sourceWord = new Word(sourceLang,currentOrth);

                if(dictionary.containsKey(sourceWord.getHashCode())){

                    dictionary.get(sourceWord.getHashCode()).getTranslations().addAll(quotes);

                }else{

                    sourceWord.setTranslations(quotes);
                    dictionary.put(sourceWord.getHashCode(),sourceWord);

                }

                dataManager.getWordsDatabase().put(sourceWord.getHashCode(),sourceWord);


                /*System.out.println("ID: " + currentId);
                System.out.println("Orth: " + sourceWord + " - " + sourceWord.getTranslations());
                System.out.println();*/


            } else if (qName.equalsIgnoreCase("sense")) {
                inSense = false;

            } else if (qName.equalsIgnoreCase("quote") || qName.equalsIgnoreCase("def")) {
                inQuote = false;
                inDef = false;

                //System.out.println("Quote (" + currentLang + "): " + currentQuote);

                if(currentQuote != null){

                    String q = currentQuote.toString().trim();
                    if(dataManager.IsFirstCharUpper(q)){

                        q = q.trim().toLowerCase();

                    }

                    Word quote = new Word(targetLang, q);

                    quotes.add(quote);

                    dataManager.getWordsDatabase().put(quote.getHashCode(),quote);

                }



            }

        }
        String decodeText(String input, Charset charset){
            CharsetDecoder charsetDecoder = charset.newDecoder();
            charsetDecoder.onMalformedInput(CodingErrorAction.REPLACE);
            try {
                return new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream(input.getBytes(charset)), charsetDecoder)).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }



    }
}

