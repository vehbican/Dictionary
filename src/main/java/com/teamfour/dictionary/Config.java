package com.teamfour.dictionary;

import javafx.scene.paint.Color;

public class Config {

    public static final String englandFlagImg = "com/teamfour/dictionary/images/england.png";
    public static final String franceFlagImg = "com/teamfour/dictionary/images/france.png";
    public static final String germanyFlagImg = "com/teamfour/dictionary/images/germany.png";
    public static final String greeceFlagImg = "com/teamfour/dictionary/images/greece.png";
    public static final String italyFlagImg = "com/teamfour/dictionary/images/italy.png";
    public static final String swedenFlagImg = "com/teamfour/dictionary/images/sweden.png";
    public static final String turkeyFlagImg = "com/teamfour/dictionary/images/turkey.png";




    public static final String eng_ita_tei = "src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\eng-ita.tei";

    public static final String eng_tur_tei = "src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\eng-tur.tei";

    public static final String eng_deu_tei = "src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\eng-deu.tei";

    public static final String eng_ell_tei = "src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\eng-ell.tei";

    public static final String eng_swe_tei = "src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\eng-swe.tei";

    public static final String eng_fra_tei = "src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\eng-fra.tei";

    public static final String eng_deu_json = "src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\eng-deu.txt";

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
