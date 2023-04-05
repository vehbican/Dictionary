package com.teamfour.dictionary;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class DataManager {


    private HashMap<Integer, Word> ENG_TUR_DICT;
    private HashMap<Integer, Word> ENG_FRA_DICT;
    private HashMap<Integer, Word> ENG_GER_DICT;
    private HashMap<Integer, Word> ENG_ITA_DICT;
    private HashMap<Integer, Word> ENG_GRE_DICT;
    private HashMap<Integer, Word> ENG_SWE_DICT;

    private ArrayList<HashMap<Integer, Word>> ENGXDictionaries;

    public HashMap<Integer,Word> WordsDatabase;

    public DataManager() {

        //TeiParser teiParser = new TeiParser();
        TEISAXParser teiParser = new TEISAXParser();
        WordsDatabase = new HashMap<>();
        ENGXDictionaries = new ArrayList<>();

        //From English to All
        //ENG_TUR_DICT = teiParser.ParseTEIToHashMap(this,Config.eng_tur_tei, Config.Languages.ENGLISH, Config.Languages.TURKISH);
        //ENG_FRA_DICT = teiParser.ParseTEIToHashMap(this,Config.eng_fra_tei, Config.Languages.ENGLISH, Config.Languages.FRENCH);
        //ENG_ITA_DICT = teiParser.ParseTEIToHashMap(this,Config.eng_ita_tei, Config.Languages.ENGLISH, Config.Languages.ITALIAN);
        //ENG_GRE_DICT = teiParser.ParseTEIToHashMap(this,Config.eng_ell_tei, Config.Languages.ENGLISH, Config.Languages.GREEK);
        //ENG_SWE_DICT = teiParser.ParseTEIToHashMap(this,Config.eng_swe_tei, Config.Languages.ENGLISH, Config.Languages.SWEDISH);
        //ENG_GER_DICT = teiParser.ParseTEIToHashMap(this,Config.eng_deu_tei, Config.Languages.ENGLISH, Config.Languages.GERMAN);

        //From English to All
        ENG_TUR_DICT = teiParser.ParseIntoHashMap(this,Config.eng_tur_tei, Config.Languages.ENGLISH, Config.Languages.TURKISH);
        ENG_FRA_DICT = teiParser.ParseIntoHashMap(this,Config.eng_fra_tei, Config.Languages.ENGLISH, Config.Languages.FRENCH);
        ENG_ITA_DICT = teiParser.ParseIntoHashMap(this,Config.eng_ita_tei, Config.Languages.ENGLISH, Config.Languages.ITALIAN);
        ENG_GRE_DICT = teiParser.ParseIntoHashMap(this,Config.eng_ell_tei, Config.Languages.ENGLISH, Config.Languages.GREEK);
        ENG_SWE_DICT = teiParser.ParseIntoHashMap(this,Config.eng_swe_tei, Config.Languages.ENGLISH, Config.Languages.SWEDISH);
        ENG_GER_DICT = teiParser.ParseIntoHashMap(this,Config.eng_deu_tei, Config.Languages.ENGLISH, Config.Languages.GERMAN);

        ENGXDictionaries.add(Config.eng_tur_index,ENG_TUR_DICT);
        ENGXDictionaries.add(Config.eng_fra_index,ENG_FRA_DICT);
        ENGXDictionaries.add(Config.eng_ita_index,ENG_ITA_DICT);
        ENGXDictionaries.add(Config.eng_gre_index,ENG_GRE_DICT);
        ENGXDictionaries.add(Config.eng_swe_index,ENG_SWE_DICT);
        ENGXDictionaries.add(Config.eng_ger_index,ENG_GER_DICT);

        //Write into JSON
        /*try {
            WriteLargeJSON(JSONSerializer.toJSON(ENG_GER_DICT),Config.eng_deu_json);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Read From JSON
        /*try {
            String d = Files.readString(Path.of(Config.eng_deu_json),Charset.forName("ISO-8859-1"));
            ENG_GER_DICT = (HashMap<Integer, Word>) JSONSerializer.getFromJSON(d,HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    public String[] getDictionaries() {
        String eng_tur = Config.eng_tur_tei;
        String eng_fra = Config.eng_fra_tei;
        String eng_ita = Config.eng_ita_tei;
        String eng_ell = Config.eng_ell_tei;
        String eng_swe = Config.eng_swe_tei;
        String eng_deu = Config.eng_deu_tei;

        return new String[]{eng_tur, eng_fra,  eng_ita, eng_ell, eng_swe,eng_deu};

    }

    public String[] getFlags() {

        String englandFlagImg = Config.englandFlagImg;
        String franceFlagImg = Config.franceFlagImg;
        String germanyFlagImg = Config.germanyFlagImg;
        String greeceFlagImg = Config.greeceFlagImg;
        String italyFlagImg = Config.italyFlagImg;
        String swedenFlagImg = Config.swedenFlagImg;
        String turkeyFlagImg = Config.turkeyFlagImg;



        return new String[]{turkeyFlagImg, franceFlagImg ,italyFlagImg, greeceFlagImg, swedenFlagImg,germanyFlagImg,englandFlagImg};
    }

    public void WriteLargeJSON(String json,String outputPath) throws IOException {

        FileChannel rwChannel = new RandomAccessFile(outputPath, "rw").getChannel();
        ByteBuffer wrBuf = rwChannel.map(FileChannel.MapMode.READ_WRITE, 0, json.length());

        wrBuf.put(json.getBytes());

        rwChannel.close();

    }

    /*
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
    */

    public HashMap<Integer, Word> getENG_TUR_DICT() {
        return ENG_TUR_DICT;
    }

    public HashMap<Integer, Word> getENG_FRA_DICT() {
        return ENG_FRA_DICT;
    }

    public HashMap<Integer, Word> getENG_GER_DICT() {
        return ENG_GER_DICT;
    }

    public HashMap<Integer, Word> getENG_ITA_DICT() {
        return ENG_ITA_DICT;
    }

    public HashMap<Integer, Word> getENG_GRE_DICT() {
        return ENG_GRE_DICT;
    }

    public HashMap<Integer, Word> getENG_SWE_DICT() {
        return ENG_SWE_DICT;
    }

    public ArrayList<HashMap<Integer, Word>> getENGXDictionaries() {
        return ENGXDictionaries;
    }

    public HashMap<Integer, Word> getWordsDatabase() {
        return WordsDatabase;
    }



}
