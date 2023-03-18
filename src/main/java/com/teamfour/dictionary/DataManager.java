package com.teamfour.dictionary;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DataManager {

    private HashMap<Integer, Word> ENG_TUR_DICT;
    private HashMap<Integer, Word> ENG_FRA_DICT;
    private HashMap<Integer, Word> ENG_GER_DICT;
    private HashMap<Integer, Word> ENG_ITA_DICT;
    private HashMap<Integer, Word> ENG_GRE_DICT;
    private HashMap<Integer, Word> ENG_SWE_DICT;

    private ArrayList<HashMap<Integer, Word>> AllDictionaries;

    public DataManager() {

        AllDictionaries = new ArrayList<>();

        //From English to All
        ENG_TUR_DICT = new HashMap<>();
        ENG_FRA_DICT = new HashMap<>();
        ENG_GER_DICT = new HashMap<>();
        ENG_ITA_DICT = new HashMap<>();
        ENG_GRE_DICT = new HashMap<>();
        ENG_SWE_DICT = new HashMap<>();

        LoadEnglishDictionaries();

        AllDictionaries.add(ENG_TUR_DICT);
        AllDictionaries.add(ENG_FRA_DICT);
        AllDictionaries.add(ENG_GER_DICT);
        AllDictionaries.add(ENG_ITA_DICT);
        AllDictionaries.add(ENG_GRE_DICT);
        AllDictionaries.add(ENG_SWE_DICT);

    }



    public void LoadEnglishDictionaries(){

        //parsing
        List<String> eng_tur_eng;
        List<String> eng_tur_tur;

        eng_tur_eng = BufferedReaderToList(Config.ENG_TUR_ENG_TXT);
        eng_tur_tur = BufferedReaderToList(Config.ENG_TUR_TUR_TXT);

        for (int i=0;i<eng_tur_eng.size();i++){

            Word eng = new Word(Config.Languages.ENGLISH,eng_tur_eng.get(i).trim().toLowerCase());
            Word tur = new Word(Config.Languages.TURKISH,eng_tur_tur.get(i).trim().toLowerCase());
            Word fra = new Word(Config.Languages.FRENCH,eng_tur_tur.get(i).trim().toLowerCase());
            Word ger = new Word(Config.Languages.GERMAN,eng_tur_tur.get(i).trim().toLowerCase());
            Word ita = new Word(Config.Languages.ITALIAN,eng_tur_tur.get(i).trim().toLowerCase());
            Word gre = new Word(Config.Languages.GREEK,eng_tur_tur.get(i).trim().toLowerCase());
            Word swe = new Word(Config.Languages.SWEDISH,eng_tur_tur.get(i).trim().toLowerCase());

            String[] a = tur.getWord().split(";");

            tur.setTranslations(a);
            fra.setTranslations(a);
            ger.setTranslations(a);
            ita.setTranslations(a);
            gre.setTranslations(a);
            swe.setTranslations(a);


            ENG_TUR_DICT.put(eng.getHashCode(), tur);
            ENG_FRA_DICT.put(eng.getHashCode(), fra);
            ENG_GER_DICT.put(eng.getHashCode(), ger);
            ENG_ITA_DICT.put(eng.getHashCode(), ita);
            ENG_GRE_DICT.put(eng.getHashCode(), gre);
            ENG_SWE_DICT.put(eng.getHashCode(), swe);
        }


    }

    private static List<String> BufferedReaderToList(String path) {

        List<String> list = new ArrayList<>();

        try {
            final BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                list.add(line);
            }
            in.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public HashMap<Integer, Word> getENG_TUR_DICT() {
        return ENG_TUR_DICT;
    }

    public void setENG_TUR_DICT(HashMap<Integer, Word> ENG_TUR_DICT) {
        this.ENG_TUR_DICT = ENG_TUR_DICT;
    }

    public HashMap<Integer, Word> getENG_FRA_DICT() {
        return ENG_FRA_DICT;
    }

    public void setENG_FRA_DICT(HashMap<Integer, Word> ENG_FRA_DICT) {
        this.ENG_FRA_DICT = ENG_FRA_DICT;
    }

    public HashMap<Integer, Word> getENG_GER_DICT() {
        return ENG_GER_DICT;
    }

    public void setENG_GER_DICT(HashMap<Integer, Word> ENG_GER_DICT) {
        this.ENG_GER_DICT = ENG_GER_DICT;
    }

    public HashMap<Integer, Word> getENG_ITA_DICT() {
        return ENG_ITA_DICT;
    }

    public void setENG_ITA_DICT(HashMap<Integer, Word> ENG_ITA_DICT) {
        this.ENG_ITA_DICT = ENG_ITA_DICT;
    }

    public HashMap<Integer, Word> getENG_GRE_DICT() {
        return ENG_GRE_DICT;
    }

    public void setENG_GRE_DICT(HashMap<Integer, Word> ENG_GRE_DICT) {
        this.ENG_GRE_DICT = ENG_GRE_DICT;
    }

    public HashMap<Integer, Word> getENG_SWE_DICT() {
        return ENG_SWE_DICT;
    }

    public void setENG_SWE_DICT(HashMap<Integer, Word> ENG_SWE_DICT) {
        this.ENG_SWE_DICT = ENG_SWE_DICT;
    }

    public ArrayList<HashMap<Integer, Word>> getAllDictionaries() {
        return AllDictionaries;
    }

    public void setAllDictionaries(ArrayList<HashMap<Integer, Word>> allDictionaries) {
        this.AllDictionaries = allDictionaries;
    }

}
