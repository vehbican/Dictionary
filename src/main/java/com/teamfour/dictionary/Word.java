package com.teamfour.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Word {

    private Config.Languages language; // which contains this word
    private String word;
    private List<Word> translations; // to other languages
    private String type; // pronoun,noun,verb etc.
    private ArrayList<String> synonyms; // if exist
    private String flagImgPath;
    private int hashCode;

    public Word(Config.Languages language,String word) {

        this.language = language;
        this.word = word;
        this.translations = new ArrayList<>();

        if (this.word != null) {
            this.hashCode = this.word.hashCode();
        }

        switch (this.language){

            case ENGLISH -> this.flagImgPath = Config.englandFlagImg;
            case FRENCH -> this.flagImgPath = Config.franceFlagImg;
            case GERMAN -> this.flagImgPath = Config.germanyFlagImg;
            case GREEK -> this.flagImgPath = Config.greeceFlagImg;
            case SWEDISH -> this.flagImgPath = Config.swedenFlagImg;
            case ITALIAN -> this.flagImgPath = Config.italyFlagImg;
            case TURKISH -> this.flagImgPath = Config.turkeyFlagImg;

        }

    }

    public Config.Languages getLanguage() {
        return language;
    }

    public void setLanguage(Config.Languages languageName) {
        this.language = languageName;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Word> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Word> translations) {
        this.translations = translations;
    }

    public ArrayList<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(ArrayList<String> synonyms) {
        this.synonyms = synonyms;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlagImgPath() {
        return flagImgPath;
    }

    public void setFlagImgPath(String flagImgPath) {
        this.flagImgPath = flagImgPath;
    }

    @Override
    public String toString() {
        return word;
    }


    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }
}
