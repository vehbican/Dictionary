package com.teamfour.dictionary;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class FileManager {

    public static MultiValuedMap<String,Word> Parse(DataManager dataManager,String path, Config.Languages sourceLanguage, Config.Languages targetLanguage) throws IOException {

        InputStream is = App.class.getResourceAsStream(path);

        assert is != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        MultiValuedMap<String,Word> words = new ArrayListValuedHashMap<>();

        String line;
        String word;

        while ((line = reader.readLine()) != null){

            ArrayList<Word> translations = new ArrayList<>();

            if (line.isBlank()){continue;}

            String[] lineArray = line.trim().split(":");

            if (lineArray.length < 2) continue;

            word = lineArray[0].trim().toLowerCase();

            String[] translationArray;

            translationArray = lineArray[1].trim().split(";");


            for (String t:translationArray){

                Word trans = new Word(targetLanguage,t.trim().toLowerCase());
                translations.add(trans);

            }

            if (!word.isBlank()){

                Word w = new Word(sourceLanguage,word);
                w.setTranslations(translations);
                w.setTargetLanguage(targetLanguage);
                words.put(word,w);
                dataManager.getWordsDatabase().put(word,w);

            }

        }

        is.close();
        reader.close();


        return words;

    }

}
