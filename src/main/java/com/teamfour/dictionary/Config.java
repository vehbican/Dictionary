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

    public static final String ENG_TUR_ENG_TXT = "src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\ingtur-ing.txt";
    public static final String ENG_TUR_TUR_TXT = "src\\main\\resources\\com\\teamfour\\dictionary\\dicts\\ingtur-tur.txt";

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
