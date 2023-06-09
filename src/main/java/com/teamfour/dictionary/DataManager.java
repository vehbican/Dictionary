package com.teamfour.dictionary;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataManager {

    //ENG-X
    private MultiValuedMap<String, Word> ENG_TUR_DICT;
    private MultiValuedMap<String, Word> ENG_FRA_DICT;
    private MultiValuedMap<String, Word> ENG_GER_DICT;
    private MultiValuedMap<String, Word> ENG_ITA_DICT;
    private MultiValuedMap<String, Word> ENG_GRE_DICT;
    private MultiValuedMap<String, Word> ENG_SWE_DICT;

    //FRA-X
    private MultiValuedMap<String, Word> FRA_DEU_DICT;
    private MultiValuedMap<String, Word> FRA_ELL_DICT;
    private MultiValuedMap<String, Word> FRA_ENG_DICT;
    private MultiValuedMap<String, Word> FRA_ITA_DICT;
    private MultiValuedMap<String, Word> FRA_SWE_DICT;
    private MultiValuedMap<String, Word> FRA_TUR_DICT;

    //DEU-X
    private MultiValuedMap<String, Word> DEU_ELL_DICT;
    private MultiValuedMap<String, Word> DEU_ENG_DICT;
    private MultiValuedMap<String, Word> DEU_FRA_DICT;
    private MultiValuedMap<String, Word> DEU_ITA_DICT;
    private MultiValuedMap<String, Word> DEU_SWE_DICT;
    private MultiValuedMap<String, Word> DEU_TUR_DICT;

    //ITA-X
    private MultiValuedMap<String, Word> ITA_ELL_DICT;
    private MultiValuedMap<String, Word> ITA_ENG_DICT;
    private MultiValuedMap<String, Word> ITA_FRA_DICT;
    private MultiValuedMap<String, Word> ITA_DEU_DICT;
    private MultiValuedMap<String, Word> ITA_SWE_DICT;
    private MultiValuedMap<String, Word> ITA_TUR_DICT;

    //SWE-X
    private MultiValuedMap<String, Word> SWE_DEU_DICT;
    private MultiValuedMap<String, Word> SWE_ELL_DICT;
    private MultiValuedMap<String, Word> SWE_ENG_DICT;
    private MultiValuedMap<String, Word> SWE_FRA_DICT;
    private MultiValuedMap<String, Word> SWE_ITA_DICT;
    private MultiValuedMap<String, Word> SWE_TUR_DICT;

    //ELL-ENG
    private MultiValuedMap<String, Word> ELL_ENG_DICT;
    private MultiValuedMap<String, Word> ELL_TUR_DICT;
    private MultiValuedMap<String, Word> ELL_FRA_DICT;
    private MultiValuedMap<String, Word> ELL_DEU_DICT;
    private MultiValuedMap<String, Word> ELL_ITA_DICT;
    private MultiValuedMap<String, Word> ELL_SWE_DICT;

    //TUR-ENG
    private MultiValuedMap<String, Word> TUR_ENG_DICT;
    private MultiValuedMap<String, Word> TUR_FRA_DICT;
    private MultiValuedMap<String, Word> TUR_DEU_DICT;
    private MultiValuedMap<String, Word> TUR_ELL_DICT;
    private MultiValuedMap<String, Word> TUR_ITA_DICT;
    private MultiValuedMap<String, Word> TUR_SWE_DICT;


    private ArrayList<MultiValuedMap<String,Word>> FRAXDictionaries;
    private ArrayList<MultiValuedMap<String, Word>> ENGXDictionaries;
    private ArrayList<MultiValuedMap<String, Word>> DEUXDictionaries;
    private ArrayList<MultiValuedMap<String,Word>> SWEXDictionaries;
    private ArrayList<MultiValuedMap<String, Word>> ITAXDictionaries;
    private ArrayList<MultiValuedMap<String, Word>> TURXDictionaries;
    private ArrayList<MultiValuedMap<String, Word>> ELLXDictionaries;

    //X-ENG For Synonym Functionality




    public MultiValuedMap<String,Word> WordsDatabase;

    public DataManager() throws IOException {

        WordsDatabase = new ArrayListValuedHashMap<>();
        /*ENGXDictionaries = new ArrayList<>();
        FRAXDictionaries = new ArrayList<>();
        DEUXDictionaries = new ArrayList<>();
        ITAXDictionaries = new ArrayList<>();
        SWEXDictionaries = new ArrayList<>();
        TURXDictionaries = new ArrayList<>();
        ELLXDictionaries = new ArrayList<>();

        //From English to All
        ENG_TUR_DICT = FileManager.Parse(this,Config.eng_tur_tei, Config.Languages.ENGLISH, Config.Languages.TURKISH);
        ENG_FRA_DICT = FileManager.Parse(this,Config.eng_fra_tei, Config.Languages.ENGLISH, Config.Languages.FRENCH);
        ENG_ITA_DICT = FileManager.Parse(this,Config.eng_ita_tei, Config.Languages.ENGLISH, Config.Languages.ITALIAN);
        ENG_GRE_DICT = FileManager.Parse(this,Config.eng_ell_tei, Config.Languages.ENGLISH, Config.Languages.GREEK);
        ENG_SWE_DICT = FileManager.Parse(this,Config.eng_swe_tei, Config.Languages.ENGLISH, Config.Languages.SWEDISH);
        ENG_GER_DICT = FileManager.Parse(this,Config.eng_deu_tei, Config.Languages.ENGLISH, Config.Languages.GERMAN);

        ENGXDictionaries.add(Config.eng_tur_index,ENG_TUR_DICT);
        ENGXDictionaries.add(Config.eng_fra_index,ENG_FRA_DICT);
        ENGXDictionaries.add(Config.eng_ita_index,ENG_ITA_DICT);
        ENGXDictionaries.add(Config.eng_gre_index,ENG_GRE_DICT);
        ENGXDictionaries.add(Config.eng_swe_index,ENG_SWE_DICT);
        ENGXDictionaries.add(Config.eng_ger_index,ENG_GER_DICT);

        //From German to All
        DEU_TUR_DICT = FileManager.Parse(this,Config.deu_tur_tei,Config.Languages.GERMAN,Config.Languages.TURKISH);
        DEU_ELL_DICT = FileManager.Parse(this,Config.deu_ell_tei, Config.Languages.GERMAN, Config.Languages.GREEK);
        DEU_ENG_DICT = FileManager.Parse(this,Config.deu_eng_tei, Config.Languages.GERMAN, Config.Languages.ENGLISH);
        DEU_FRA_DICT = FileManager.Parse(this,Config.deu_fra_tei, Config.Languages.GERMAN, Config.Languages.FRENCH);
        DEU_ITA_DICT = FileManager.Parse(this,Config.deu_ita_tei, Config.Languages.GERMAN, Config.Languages.ITALIAN);
        DEU_SWE_DICT = FileManager.Parse(this,Config.deu_swe_tei, Config.Languages.GERMAN, Config.Languages.SWEDISH);

        DEUXDictionaries.add(Config.deu_tur_index,DEU_TUR_DICT);
        DEUXDictionaries.add(Config.deu_ell_index,DEU_ELL_DICT);
        DEUXDictionaries.add(Config.deu_eng_index,DEU_ENG_DICT);
        DEUXDictionaries.add(Config.deu_fra_index,DEU_FRA_DICT);
        DEUXDictionaries.add(Config.deu_ita_index,DEU_ITA_DICT);
        DEUXDictionaries.add(Config.deu_swe_index,DEU_SWE_DICT);

        //From French to all
        FRA_ENG_DICT = FileManager.Parse(this,Config.fra_eng_tei,Config.Languages.FRENCH,Config.Languages.ENGLISH);
        FRA_ITA_DICT = FileManager.Parse(this,Config.fra_ita_tei, Config.Languages.FRENCH, Config.Languages.ITALIAN);
        FRA_DEU_DICT = FileManager.Parse(this,Config.fra_deu_tei, Config.Languages.FRENCH, Config.Languages.GERMAN);
        FRA_TUR_DICT = FileManager.Parse(this,Config.fra_tur_tei, Config.Languages.FRENCH, Config.Languages.TURKISH);
        FRA_ELL_DICT = FileManager.Parse(this,Config.fra_ell_tei, Config.Languages.FRENCH, Config.Languages.GREEK);
        FRA_SWE_DICT = FileManager.Parse(this,Config.fra_swe_tei, Config.Languages.FRENCH, Config.Languages.SWEDISH);

        FRAXDictionaries.add(Config.fra_eng_index,FRA_ENG_DICT);
        FRAXDictionaries.add(Config.fra_ita_index,FRA_ITA_DICT);
        FRAXDictionaries.add(Config.fra_deu_index,FRA_DEU_DICT);
        FRAXDictionaries.add(Config.fra_tur_index,FRA_TUR_DICT);
        FRAXDictionaries.add(Config.fra_ell_index,FRA_ELL_DICT);
        FRAXDictionaries.add(Config.fra_swe_index,FRA_SWE_DICT);

        //From Italian to all
        ITA_ENG_DICT = FileManager.Parse(this,Config.ita_eng_tei,Config.Languages.ITALIAN,Config.Languages.ENGLISH);
        ITA_FRA_DICT = FileManager.Parse(this,Config.ita_fra_tei, Config.Languages.ITALIAN, Config.Languages.FRENCH);
        ITA_DEU_DICT = FileManager.Parse(this,Config.ita_deu_tei, Config.Languages.ITALIAN, Config.Languages.GERMAN);
        ITA_TUR_DICT = FileManager.Parse(this,Config.ita_tur_tei, Config.Languages.ITALIAN, Config.Languages.TURKISH);
        ITA_ELL_DICT = FileManager.Parse(this,Config.ita_ell_tei, Config.Languages.ITALIAN, Config.Languages.GREEK);
        ITA_SWE_DICT = FileManager.Parse(this,Config.ita_swe_tei, Config.Languages.ITALIAN, Config.Languages.SWEDISH);

        ITAXDictionaries.add(Config.ita_eng_index,ITA_ENG_DICT);
        ITAXDictionaries.add(Config.ita_fra_index,ITA_FRA_DICT);
        ITAXDictionaries.add(Config.ita_deu_index,ITA_DEU_DICT);
        ITAXDictionaries.add(Config.ita_tur_index,ITA_TUR_DICT);
        ITAXDictionaries.add(Config.ita_ell_index,ITA_ELL_DICT);
        ITAXDictionaries.add(Config.ita_swe_index,ITA_SWE_DICT);

        // From Swedish to All
        SWE_ENG_DICT = FileManager.Parse(this,Config.swe_eng_tei,Config.Languages.SWEDISH,Config.Languages.ENGLISH);
        SWE_FRA_DICT = FileManager.Parse(this,Config.swe_fra_tei, Config.Languages.SWEDISH, Config.Languages.FRENCH);
        SWE_DEU_DICT = FileManager.Parse(this,Config.swe_deu_tei, Config.Languages.SWEDISH, Config.Languages.GERMAN);
        SWE_TUR_DICT = FileManager.Parse(this,Config.swe_tur_tei, Config.Languages.SWEDISH, Config.Languages.TURKISH);
        SWE_ELL_DICT = FileManager.Parse(this,Config.swe_ell_tei, Config.Languages.SWEDISH, Config.Languages.GREEK);
        SWE_ITA_DICT = FileManager.Parse(this,Config.swe_ita_tei, Config.Languages.SWEDISH, Config.Languages.ITALIAN);

        SWEXDictionaries.add(Config.swe_eng_index,SWE_ENG_DICT);
        SWEXDictionaries.add(Config.swe_fra_index,SWE_FRA_DICT);
        SWEXDictionaries.add(Config.swe_deu_index,SWE_DEU_DICT);
        SWEXDictionaries.add(Config.swe_tur_index,SWE_TUR_DICT);
        SWEXDictionaries.add(Config.swe_ell_index,SWE_ELL_DICT);
        SWEXDictionaries.add(Config.swe_ita_index,SWE_ITA_DICT);

        // From Turkish to English
        TUR_ENG_DICT = FileManager.Parse(this,Config.tur_eng_tei, Config.Languages.TURKISH, Config.Languages.ENGLISH);
        TUR_DEU_DICT = FileManager.Parse(this,Config.tur_deu_tei, Config.Languages.TURKISH, Config.Languages.GERMAN);
        TUR_SWE_DICT = FileManager.Parse(this,Config.tur_swe_tei, Config.Languages.TURKISH, Config.Languages.SWEDISH);
        TUR_ELL_DICT = FileManager.Parse(this,Config.tur_ell_tei, Config.Languages.TURKISH, Config.Languages.GREEK);
        TUR_FRA_DICT = FileManager.Parse(this,Config.tur_fra_tei, Config.Languages.TURKISH, Config.Languages.FRENCH);
        TUR_ITA_DICT = FileManager.Parse(this,Config.tur_ita_tei, Config.Languages.TURKISH, Config.Languages.ITALIAN);

        TURXDictionaries.add(TUR_ENG_DICT);
        TURXDictionaries.add(TUR_DEU_DICT);
        TURXDictionaries.add(TUR_SWE_DICT);
        TURXDictionaries.add(TUR_ELL_DICT);
        TURXDictionaries.add(TUR_FRA_DICT);
        TURXDictionaries.add(TUR_ITA_DICT);


        // From Greek to English
        ELL_ENG_DICT = FileManager.Parse(this,Config.ell_eng_tei, Config.Languages.GREEK, Config.Languages.ENGLISH);
        ELL_DEU_DICT = FileManager.Parse(this,Config.ell_deu_tei, Config.Languages.GREEK, Config.Languages.GERMAN);
        ELL_SWE_DICT = FileManager.Parse(this,Config.ell_swe_tei, Config.Languages.GREEK, Config.Languages.SWEDISH);
        ELL_TUR_DICT = FileManager.Parse(this,Config.ell_tur_tei, Config.Languages.GREEK, Config.Languages.TURKISH);
        ELL_FRA_DICT = FileManager.Parse(this,Config.ell_fra_tei, Config.Languages.GREEK, Config.Languages.FRENCH);
        ELL_ITA_DICT = FileManager.Parse(this,Config.ell_ita_tei, Config.Languages.GREEK, Config.Languages.ITALIAN);

        ELLXDictionaries.add(ELL_ENG_DICT);
        ELLXDictionaries.add(ELL_DEU_DICT);
        ELLXDictionaries.add(ELL_SWE_DICT);
        ELLXDictionaries.add(ELL_TUR_DICT);
        ELLXDictionaries.add(ELL_FRA_DICT);
        ELLXDictionaries.add(ELL_ITA_DICT);*/


        File f = new File(Config.projectCreatedText);
        if (!f.isFile()){

            f.createNewFile();

        }



    }

    public boolean IsFirstCharUpper(String input){

        input = input.trim();
        Pattern p = Pattern.compile("\\b([A-Z][a-z]*)\\b");
        Matcher m = p.matcher(input);

        return m.find();

    }

   /* public String[] getSWEDictionaries(){
        String swe_eng = Config.swe_eng_tei;
        String swe_fra = Config.swe_fra_tei;
        String swe_deu = Config.swe_deu_tei;
        String swe_tur = Config.swe_tur_tei;
        String swe_ell = Config.swe_ell_tei;
        String swe_ita = Config.swe_ita_tei;

        return new String[]{swe_eng,swe_fra,swe_deu,swe_tur,swe_ell,swe_ita};

    }*/

    /*
    public String[] getITADictionaries(){
        String ita_eng = Config.ita_eng_tei;
        String ita_fra = Config.ita_fra_tei;
        String ita_deu = Config.ita_deu_tei;
        String ita_tur = Config.ita_tur_tei;
        String ita_ell = Config.ita_ell_tei;
        String ita_swe = Config.ita_swe_tei;

        return new String[]{ita_eng,ita_fra,ita_deu,ita_tur,ita_ell,ita_swe};

    }*/
  /*  public String[] getFRADictionaries(){
        String fra_eng = Config.fra_eng_tei;
        String fra_ita = Config.fra_ita_tei;
        String fra_deu = Config.fra_deu_tei;
        String fra_tur = Config.fra_tur_tei;
        String fra_ell = Config.fra_ell_tei;
        String fra_swe = Config.fra_swe_tei;

        return new String[]{fra_eng,fra_ita,fra_deu,fra_tur,fra_ell,fra_swe};

    }*/
   /* public String[] getGERDictionaries(){
        String deu_tur = Config.deu_tur_tei;
        String deu_ell = Config.deu_ell_tei;
        String deu_eng = Config.deu_eng_tei;
        String deu_fra = Config.deu_fra_tei;
        String deu_ita = Config.deu_ita_tei;
        String deu_swe = Config.deu_swe_tei;

        return new String[]{deu_tur, deu_ell,  deu_eng, deu_fra, deu_ita,deu_swe};

    }*/

   /* public String[] getENGDictionaries() {
        String eng_tur = Config.eng_tur_tei;
        String eng_fra = Config.eng_fra_tei;
        String eng_ita = Config.eng_ita_tei;
        String eng_ell = Config.eng_ell_tei;
        String eng_swe = Config.eng_swe_tei;
        String eng_deu = Config.eng_deu_tei;


        return new String[]{eng_tur, eng_fra,  eng_ita, eng_ell, eng_swe,eng_deu};


    }*/

    public String[] getFlags() {

        String englandFlagImg = Config.englandFlagImg;
        String franceFlagImg = Config.franceFlagImg;
        String germanyFlagImg = Config.germanyFlagImg;
        String greeceFlagImg = Config.greeceFlagImg;
        String italyFlagImg = Config.italyFlagImg;
        String swedenFlagImg = Config.swedenFlagImg;
        String turkeyFlagImg = Config.turkeyFlagImg;
        String helpButtonImg = Config.helpButtonImg;



        return new String[]{turkeyFlagImg, franceFlagImg ,italyFlagImg, greeceFlagImg, swedenFlagImg,germanyFlagImg,englandFlagImg,helpButtonImg};
    }

    public MultiValuedMap<String, Word> getENG_TUR_DICT() {
        return ENG_TUR_DICT;
    }

    public MultiValuedMap<String, Word> getENG_FRA_DICT() {
        return ENG_FRA_DICT;
    }

    public MultiValuedMap<String, Word> getENG_GER_DICT() {
        return ENG_GER_DICT;
    }

    public MultiValuedMap<String, Word> getENG_ITA_DICT() {
        return ENG_ITA_DICT;
    }

    public MultiValuedMap<String, Word> getENG_GRE_DICT() {
        return ENG_GRE_DICT;
    }

    public MultiValuedMap<String, Word> getENG_SWE_DICT() {
        return ENG_SWE_DICT;
    }

    public MultiValuedMap<String, Word> getELL_ENG_DICT() {
        return ELL_ENG_DICT;
    }

    public MultiValuedMap<String, Word> getTUR_ENG_DICT() {
        return TUR_ENG_DICT;
    }

    public MultiValuedMap<String, Word> getFRA_DEU_DICT() {
        return FRA_DEU_DICT;
    }

    public MultiValuedMap<String, Word> getFRA_ELL_DICT() {
        return FRA_ELL_DICT;
    }

    public MultiValuedMap<String, Word> getFRA_ENG_DICT() {
        return FRA_ENG_DICT;
    }

    public MultiValuedMap<String, Word> getFRA_ITA_DICT() {
        return FRA_ITA_DICT;
    }

    public MultiValuedMap<String, Word> getFRA_SWE_DICT() {
        return FRA_SWE_DICT;
    }

    public MultiValuedMap<String, Word> getFRA_TUR_DICT() {
        return FRA_TUR_DICT;
    }

    public MultiValuedMap<String, Word> getDEU_ELL_DICT() {
        return DEU_ELL_DICT;
    }

    public MultiValuedMap<String, Word> getDEU_ENG_DICT() {
        return DEU_ENG_DICT;
    }

    public MultiValuedMap<String, Word> getDEU_FRA_DICT() {
        return DEU_FRA_DICT;
    }

    public MultiValuedMap<String, Word> getDEU_ITA_DICT() {
        return DEU_ITA_DICT;
    }

    public MultiValuedMap<String, Word> getDEU_SWE_DICT() {
        return DEU_SWE_DICT;
    }

    public MultiValuedMap<String, Word> getDEU_TUR_DICT() {
        return DEU_TUR_DICT;
    }

    public MultiValuedMap<String, Word> getITA_ELL_DICT() {
        return ITA_ELL_DICT;
    }

    public MultiValuedMap<String, Word> getITA_ENG_DICT() {
        return ITA_ENG_DICT;
    }

    public MultiValuedMap<String, Word> getITA_FRA_DICT() {
        return ITA_FRA_DICT;
    }

    public MultiValuedMap<String, Word> getITA_DEU_DICT() {
        return ITA_DEU_DICT;
    }

    public MultiValuedMap<String, Word> getITA_SWE_DICT() {
        return ITA_SWE_DICT;
    }

    public MultiValuedMap<String, Word> getITA_TUR_DICT() {
        return ITA_TUR_DICT;
    }

    public MultiValuedMap<String, Word> getSWE_DEU_DICT() {
        return SWE_DEU_DICT;
    }

    public MultiValuedMap<String, Word> getSWE_ELL_DICT() {
        return SWE_ELL_DICT;
    }

    public MultiValuedMap<String, Word> getSWE_ENG_DICT() {
        return SWE_ENG_DICT;
    }

    public MultiValuedMap<String, Word> getSWE_FRA_DICT() {
        return SWE_FRA_DICT;
    }

    public MultiValuedMap<String, Word> getSWE_ITA_DICT() {
        return SWE_ITA_DICT;
    }

    public MultiValuedMap<String, Word> getSWE_TUR_DICT() {
        return SWE_TUR_DICT;
    }

    public MultiValuedMap<String, Word> getELL_TUR_DICT() {
        return ELL_TUR_DICT;
    }

    public MultiValuedMap<String, Word> getELL_FRA_DICT() {
        return ELL_FRA_DICT;
    }

    public MultiValuedMap<String, Word> getELL_DEU_DICT() {
        return ELL_DEU_DICT;
    }

    public MultiValuedMap<String, Word> getELL_ITA_DICT() {
        return ELL_ITA_DICT;
    }

    public MultiValuedMap<String, Word> getELL_SWE_DICT() {
        return ELL_SWE_DICT;
    }

    public MultiValuedMap<String, Word> getTUR_FRA_DICT() {
        return TUR_FRA_DICT;
    }

    public MultiValuedMap<String, Word> getTUR_DEU_DICT() {
        return TUR_DEU_DICT;
    }

    public MultiValuedMap<String, Word> getTUR_ELL_DICT() {
        return TUR_ELL_DICT;
    }

    public MultiValuedMap<String, Word> getTUR_ITA_DICT() {
        return TUR_ITA_DICT;
    }

    public MultiValuedMap<String, Word> getTUR_SWE_DICT() {
        return TUR_SWE_DICT;
    }

    public ArrayList<MultiValuedMap<String, Word>> getTURXDictionaries() {
        return TURXDictionaries;
    }

    public ArrayList<MultiValuedMap<String, Word>> getELLXDictionaries() {
        return ELLXDictionaries;
    }

    public ArrayList<MultiValuedMap<String, Word>> getENGXDictionaries() {
        return ENGXDictionaries;
    }
    public ArrayList<MultiValuedMap<String,Word>> getDEUXDictionaries(){
        return DEUXDictionaries;
    }
    public ArrayList<MultiValuedMap<String,Word>> getFRAXDictionaries(){
        return FRAXDictionaries;
    }
    public ArrayList<MultiValuedMap<String,Word>> getITAXDictionaries(){
        return ITAXDictionaries;
    }
    public ArrayList<MultiValuedMap<String,Word>> getSWEXDictionaries(){
        return SWEXDictionaries;
    }

    public MultiValuedMap<String, Word> getWordsDatabase() {
        return WordsDatabase;
    }



}
