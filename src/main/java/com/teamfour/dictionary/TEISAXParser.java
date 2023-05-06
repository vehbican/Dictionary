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

    public HashMap<String, Word> ParseIntoHashMap(DataManager dataManager, String path, Config.Languages sourceLang, Config.Languages targetLang) {

        HashMap<String, Word> dictionary = new HashMap<>();

        try {
            // Load the resource from the classpath
            InputStream inputStream = App.class.getResourceAsStream(path);

            if (inputStream != null) {
                // Create the SAXParser and MyHandler
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                MyHandler handler = new MyHandler(dictionary,sourceLang,targetLang,dataManager);

                // Parse the XML file
                saxParser.parse(inputStream, handler);

                // Return the dictionary
                return handler.dictionary;
            } else {
                // Resource not found, handle error appropriately
                throw new IllegalArgumentException("Resource not found: " + path);
            }
        } catch (Exception e) {
            // Handle any other exceptions that occur
            e.printStackTrace();
        }

        return dictionary;
    }


    private static class MyHandler extends DefaultHandler {
        private boolean inEntry = false;
        private boolean inForm = false;
        private boolean inOrth = false;
        private boolean inSense = false;
        private boolean inCit = false;
        private boolean inQuote = false;
        private boolean inDef = false; //for eng-greek dictionary
        private boolean inTrans = false;
        private boolean inExample = false;
        private String currentId;
        private String currentOrth;
        private String currentLang;
        private String currentType;
        private StringBuilder currentQuote;

        private HashMap<String,Word> dictionary;
        private Config.Languages sourceLang;
        private Config.Languages targetLang;
        private DataManager dataManager;

        private List<Word> quotes;

        public MyHandler(HashMap<String, Word> dictionary, Config.Languages sourceLang, Config.Languages targetLang, DataManager dataManager) {
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
            }
            else if (qName.equalsIgnoreCase("form") && inEntry){
                inForm = true;
            }
            else if (qName.equalsIgnoreCase("orth") && inForm) {
                inOrth = true;
            }
            else if (qName.equalsIgnoreCase("sense") && inEntry) {
                inSense = true;
            }
            else if (qName.equalsIgnoreCase("cit") && attributes.getValue("type").equals("trans") && inSense){
                inCit = true;
                inTrans = true;
                inExample = false;
                currentType = "trans";
            }
            else if (qName.equalsIgnoreCase("cit") && attributes.getValue("type").equals("example") && inSense){
                inCit = true;
                inTrans = false;
                inExample = true;
                currentType = "example";
            }
            else if (qName.equalsIgnoreCase("quote") && inCit && inTrans) {
                inQuote = true;
                currentLang = attributes.getValue("xml:lang");
                currentQuote = new StringBuilder();
            }
            else if (qName.equalsIgnoreCase("def") && inCit && inTrans){
                inDef = true;
                currentLang = attributes.getValue("xml:lang");
                currentQuote = new StringBuilder();
            }
            /*System.out.println("----------------------------------------");
            System.out.println("In Entry: "+ inEntry);
            System.out.println("In Form:" +inForm);
            System.out.println("In Orth:" + inOrth);
            System.out.println("In Sense:" + inSense);
            System.out.println("In Cit:" + inCit);
            System.out.println("In Trans:" + inTrans);
            System.out.println("In Example"+ inExample);
            System.out.println("In Quote:"+inQuote);
            System.out.println("In Def:" + inDef);
            System.out.println("Current Type:"+currentType);
            System.out.println("----------------------------------------");*/


        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (inOrth) {
                currentOrth = new String(ch, start, length);
                inOrth = false;
            } else if (inQuote) {
                currentQuote.append(new String(ch, start, length));
            }else if (inDef){
                currentQuote.append(new String(ch, start, length));
            }

            /*System.out.println("----------------------------------------");
            System.out.println("In Entry: "+ inEntry);
            System.out.println("In Form:" +inForm);
            System.out.println("In Orth:" + inOrth);
            System.out.println("In Sense:" + inSense);
            System.out.println("In Cit:" + inCit);
            System.out.println("In Trans:" + inTrans);
            System.out.println("In Example"+ inExample);
            System.out.println("In Quote:"+inQuote);
            System.out.println("In Def:" + inDef);
            System.out.println("Current Type:"+currentType);
            System.out.println("----------------------------------------");*/

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {


            if (qName.equalsIgnoreCase("entry")) {
                inEntry = false;
                /*System.out.println("ID: " + currentId);
                System.out.println("Orth: " + currentOrth + " - " + quotes);
                System.out.println("Type:" + currentType);*/

                if(dataManager.IsFirstCharUpper(currentOrth)){

                    currentOrth = currentOrth.trim().toLowerCase();

                }

                Word sourceWord = new Word(sourceLang,currentOrth);

                if(dictionary.containsKey(sourceWord.getWord())){

                    dictionary.get(sourceWord.getWord()).getTranslations().addAll(quotes);

                }else{

                    sourceWord.setTranslations(quotes);
                    dictionary.put(sourceWord.getWord(),sourceWord);

                }

                dataManager.getWordsDatabase().put(sourceWord.getWord(),sourceWord);


                /*System.out.println("ID: " + currentId);
                System.out.println("Orth: " + sourceWord + " - " + sourceWord.getTranslations());
                System.out.println();*/


            } else if (inSense) {
                inSense = false;
            } else if (qName.equalsIgnoreCase("quote") && inQuote) {
                inQuote = false;

                //System.out.println("Quote (" + currentLang + "): " + currentQuote);

                if(currentQuote != null){

                    String q = currentQuote.toString().trim();
                    if(dataManager.IsFirstCharUpper(q)){

                        q = q.trim().toLowerCase();

                    }

                    Word quote = new Word(targetLang, q);

                    quotes.add(quote);

                }

            }else if (qName.equalsIgnoreCase("def") && inDef){
                inDef = false;

                //System.out.println("Quote (" + currentLang + "): " + currentQuote);

                if(currentQuote != null){

                    String q = currentQuote.toString().trim();
                    if(dataManager.IsFirstCharUpper(q)){

                        q = q.trim().toLowerCase();

                    }

                    Word quote = new Word(targetLang, q);

                    quotes.add(quote);

                }


            }

            /*System.out.println("----------------------------------------");
            System.out.println("In Entry: "+ inEntry);
            System.out.println("In Form:" +inForm);
            System.out.println("In Orth:" + inOrth);
            System.out.println("In Sense:" + inSense);
            System.out.println("In Cit:" + inCit);
            System.out.println("In Trans:" + inTrans);
            System.out.println("In Example"+ inExample);
            System.out.println("In Quote:"+inQuote);
            System.out.println("In Def:" + inDef);
            System.out.println("Current Type:"+currentType);
            System.out.println("----------------------------------------");*/

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

