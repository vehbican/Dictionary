package com.teamfour.dictionary;
import javafx.scene.paint.Color;


public class Config {

    public static final String englandFlagImg =  "images/england.png";
    public static final String franceFlagImg = "images/france.png";
    public static final String germanyFlagImg = "images/germany.png";
    public static final String greeceFlagImg = "images/greece.png";
    public static final String italyFlagImg = "images/italy.png";
    public static final String swedenFlagImg = "images/sweden.png";
    public static final String turkeyFlagImg = "images/turkey.png";


    public static final String eng_ita_tei = "dicts/eng-ita.tei";
    public static final String eng_tur_tei = "dicts/eng-tur.tei";
    public static final String eng_deu_tei = "dicts/eng-deu.tei";
    public static final String eng_ell_tei = "dicts/eng-ell.tei";
    public static final String eng_swe_tei = "dicts/eng-swe.tei";
    public static final String eng_fra_tei = "dicts/eng-fra.tei";

    public static int eng_tur_index = 0;
    public static int eng_fra_index = 1;
    public static int eng_ita_index = 2;
    public static int eng_gre_index = 3;
    public static int eng_swe_index = 4;
    public static int eng_ger_index = 5;


    public enum Languages{

        ENGLISH,
        FRENCH,
        GERMAN,
        GREEK,
        ITALIAN,
        SWEDISH,
        TURKISH

    }

    public static final Color background = Color.web( "#2b2d42");

}
