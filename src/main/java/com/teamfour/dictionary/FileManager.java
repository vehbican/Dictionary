package com.teamfour.dictionary;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

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

    public static void FromHashMapToFile(MultiValuedMap<String,Word> map,String outputPath){

        try {

            File file = new File(outputPath);
            String absolutePath = file.getCanonicalPath();


            BufferedWriter writer = new BufferedWriter(new FileWriter(absolutePath,StandardCharsets.UTF_8));

            StringBuilder line;

            for (Map.Entry<String,Word> entry:map.entries()){

                line = new StringBuilder();
                line.append(entry.getKey()).append(":");

                for (Word t:entry.getValue().getTranslations()){

                    line.append(t.getWord()).append(";");

                }

                line.deleteCharAt(line.length()-1);

                writer.write(line.toString());
                writer.newLine();

            }

            writer.close();



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
