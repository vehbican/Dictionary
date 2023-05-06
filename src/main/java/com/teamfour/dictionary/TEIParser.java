package com.teamfour.dictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TEIParser {

    public static void main(String[] args) {

        //MultipleSense();
        //CitListTransType();
        //MultipleQuotes();
        MultipleSenseMultipleQuotes();

    }

    public static void MultipleSenseMultipleQuotes(){

        try {
            File outputFile = new File("tur-eng.txt");
            FileWriter fw = new FileWriter(outputFile, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(fw);

            File inputFile = new File("D:\\Dictionary\\src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\tur-eng.tei");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList entryList = doc.getElementsByTagName("entry");

            List<String> outputLines = new ArrayList<>();

            for (int i = 0; i < entryList.getLength(); i++) {
                Node entryNode = entryList.item(i);
                if (entryNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element entryElement = (Element) entryNode;

                    NodeList orthList = entryElement.getElementsByTagName("orth");
                    Element orthElement = (Element) orthList.item(0);
                    String orthText = orthElement.getTextContent();

                    StringBuilder output = new StringBuilder();
                    output.append(orthText);
                    output.append(":");

                    NodeList citList = entryElement.getElementsByTagName("cit");

                    for (int j = 0; j < citList.getLength(); j++) {
                        Node citNode = citList.item(j);
                        if (citNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element citElement = (Element) citNode;
                            String type = citElement.getAttribute("type");
                            if (type.equals("trans")) {
                                NodeList quoteList = citElement.getElementsByTagName("quote");

                                for (int k = 0; k < quoteList.getLength(); k++) {
                                    Node quoteNode = quoteList.item(k);
                                    if (quoteNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element quoteElement = (Element) quoteNode;
                                        String quoteText = quoteElement.getTextContent();
                                        output.append(quoteText);
                                        output.append(";");
                                    }
                                }

                            }
                        }
                    }
                    output.deleteCharAt(output.length() - 1);
                    bw.write(output.toString());
                    bw.newLine();
                }
            }

            bw.close();
            fw.close();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void MultipleQuotes(){
        try {

            File outputFile = new File("fra-ell.txt");
            FileWriter fw = new FileWriter(outputFile, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(fw);
            
            File inputFile = new File("D:\\Dictionary\\src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\fra-ell.tei");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList entryList = doc.getElementsByTagName("entry");

            for (int i = 0; i < entryList.getLength(); i++) {
                Node entryNode = entryList.item(i);
                if (entryNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element entryElement = (Element) entryNode;

                    // Extract orth tag
                    NodeList orthList = entryElement.getElementsByTagName("orth");
                    String orthText = orthList.item(0).getTextContent();

                    StringBuilder output = new StringBuilder();
                    output.append(orthText);
                    output.append(":");

                    // Extract all quote tags with type="trans"
                    NodeList citList = entryElement.getElementsByTagName("cit");
                    for (int j = 0; j < citList.getLength(); j++) {
                        Node citNode = citList.item(j);
                        if (citNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element citElement = (Element) citNode;
                            String typeAttr = citElement.getAttribute("type");
                            if (typeAttr.equals("trans")) {
                                NodeList quoteList = citElement.getElementsByTagName("quote");
                                for (int k = 0; k < quoteList.getLength(); k++) {
                                    Node quoteNode = quoteList.item(k);
                                    if (quoteNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element quoteElement = (Element) quoteNode;
                                        String quoteText = quoteElement.getTextContent();
                                        output.append(quoteText);
                                        output.append(";");
                                    }
                                }
                                output.deleteCharAt(output.length() - 1);
                                bw.write(output.toString());
                                bw.newLine();
                                output = new StringBuilder();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void CitListTransType(){

        try {
            File outputFile = new File("deu-fra.txt");
            FileWriter fw = new FileWriter(outputFile, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(fw);


            // Load TEI file
            File teiFile = new File("D:\\Dictionary\\src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\deu-fra.tei");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(teiFile);
            doc.getDocumentElement().normalize();

            // Get all entries
            NodeList entryList = doc.getElementsByTagName("entry");

            // Loop through entries and extract data
            for (int i = 0; i < entryList.getLength(); i++) {
                Element entry = (Element) entryList.item(i);
                String word = entry.getElementsByTagName("orth").item(0).getTextContent();
                List<String> translations = new ArrayList<>();
                NodeList senseList = entry.getElementsByTagName("sense");
                for (int j = 0; j < senseList.getLength(); j++) {
                    Element sense = (Element) senseList.item(j);
                    NodeList citList = sense.getElementsByTagName("cit");
                    for (int k = 0; k < citList.getLength(); k++) {
                        Element cit = (Element) citList.item(k);
                        if (cit.getAttribute("type").equals("example")) {
                            break;
                        }
                        Node node = cit.getElementsByTagName("quote").item(0);
                        if (node == null) continue;
                        String translation = node.getTextContent();
                        translations.add(translation);
                    }
                }


                // Print extracted data
                StringBuilder output = new StringBuilder();
                output.append(word);
                output.append(":");
                //System.out.println("Word: " + word);
                //System.out.println("Translations:");
                for (String translation : translations) {
                    //System.out.println(translation);
                    output.append(translation);
                    output.append(";");
                }
                output.deleteCharAt(output.length() - 1);
                bw.write(output.toString());
                bw.newLine();
            }

            bw.close();
            fw.close();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void UsingDef(){

        try {
            File outputFile = new File("eng-ell.txt");
            FileWriter fw = new FileWriter(outputFile, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(fw);


            // Load TEI file
            File teiFile = new File("D:\\Dictionary\\src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\eng-ell.tei");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(teiFile);
            doc.getDocumentElement().normalize();

            // Get all entries
            NodeList entryList = doc.getElementsByTagName("entry");

            // Loop through entries and extract data
            for (int i = 0; i < entryList.getLength(); i++) {
                Element entry = (Element) entryList.item(i);
                String word = entry.getElementsByTagName("orth").item(0).getTextContent();
                List<String> translations = new ArrayList<>();
                /*NodeList senseList = entry.getElementsByTagName("sense");
                for (int j = 0; j < senseList.getLength(); j++) {
                    Element sense = (Element) senseList.item(j);
                    NodeList citList = sense.getElementsByTagName("cit");
                    for (int k = 0; k < citList.getLength(); k++) {
                        Element cit = (Element) citList.item(k);
                        if (cit.getAttribute("type").equals("example")) {
                            break;
                        }
                        Node node = cit.getElementsByTagName("quote").item(0);
                        if (node == null) continue;
                        String translation = node.getTextContent();
                        translations.add(translation);
                    }
                }*/

                Element def = (Element) entry.getElementsByTagName("def").item(0);
                String translation = def.getTextContent();

                // Print extracted data
                StringBuilder output = new StringBuilder();
                output.append(word);
                output.append(":");
                output.append(translation);
                //System.out.println("Word: " + word);
                //System.out.println("Translations:");
                /*for (String translation : translations) {
                    //System.out.println(translation);
                    output.append(translation);
                    output.append(";");
                }*/
                //output.deleteCharAt(output.length() - 1);
                bw.write(output.toString());
                bw.newLine();
            }

            bw.close();
            fw.close();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void MultipleSense(){

        try {

            File outputFile = new File("deu-ita.txt");
            FileWriter fw = new FileWriter(outputFile, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(fw);

            File fXmlFile = new File("D:\\Dictionary\\src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\deu-ita.tei");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("entry");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String orth = eElement.getElementsByTagName("orth").item(0).getTextContent();
                    //String pron = eElement.getElementsByTagName("pron").item(0).getTextContent();

                    ArrayList<String> quotes = new ArrayList<String>();
                    NodeList citList = eElement.getElementsByTagName("cit");
                    for (int i = 0; i < citList.getLength(); i++) {
                        Node citNode = citList.item(i);
                        if (citNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element citElement = (Element) citNode;
                            if (citElement.getAttribute("type").equals("trans")) {
                                String quote = citElement.getElementsByTagName("quote").item(0).getTextContent();
                                quotes.add(quote);
                            }
                        }
                    }

                    // Print extracted data
                    StringBuilder output = new StringBuilder();
                    output.append(orth);
                    output.append(":");
                    //System.out.println("Word: " + word);
                    //System.out.println("Translations:");
                    for (String translation : quotes) {
                        //System.out.println(translation);
                        output.append(translation);
                        output.append(";");
                    }
                    output.deleteCharAt(output.length() - 1);
                    bw.write(output.toString());
                    bw.newLine();

                    /*System.out.println("Word: " + orth);
                    System.out.println("Pronunciation: " + pron);
                    System.out.println("Translations: " + quotes.toString());
                    System.out.println("--------------------");*/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

